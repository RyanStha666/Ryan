// StudyPal Web Server - Universal Cross-Browser Host with Real-Time Leaderboard Stream
const http = require('http');
const fs = require('fs');
const path = require('path');
const url = require('url');

const PORT = process.env.DEFAULT_APP_PORT || 3000;
const PUBLIC_DIR = path.join(__dirname, 'public');

const MIME_TYPES = {
  '.html': 'text/html; charset=UTF-8',
  '.css': 'text/css; charset=UTF-8',
  '.js': 'application/javascript; charset=UTF-8',
  '.json': 'application/json; charset=UTF-8',
  '.png': 'image/png',
  '.jpg': 'image/jpeg',
  '.jpeg': 'image/jpeg',
  '.svg': 'image/svg+xml',
  '.ico': 'image/x-icon',
  '.woff2': 'font/woff2',
  '.mp4': 'video/mp4',
  '.webm': 'video/webm',
  '.pdf': 'application/pdf'
};

// Global In-Memory Persistent Leaderboard
let GLOBAL_LEADERBOARD = [
  {
    id: 'usr_849',
    alias: 'Cosmic Scholar #849',
    avatar: '🦉',
    tier: 'Diamond',
    category: 'secondary',
    level: 'Class 10 Secondary',
    streak: 48,
    minutes: 2140,
    badge: 'Grandmaster 🏆',
    cheers: 142
  },
  {
    id: 'usr_210',
    alias: 'Nova Stargazer #210',
    avatar: '🚀',
    tier: 'Diamond',
    category: 'uni',
    level: "Bachelor's CSIT",
    streak: 43,
    minutes: 1920,
    badge: 'Code Sage ⚡',
    cheers: 118
  },
  {
    id: 'usr_392',
    alias: 'Alpine Lynx #392',
    avatar: '🏔️',
    tier: 'Diamond',
    category: 'higher',
    level: 'Class 12 Science (PCM)',
    streak: 39,
    minutes: 1750,
    badge: 'Physics Ace ⚛️',
    cheers: 95
  },
  {
    id: 'usr_105',
    alias: 'Solar Flare #105',
    avatar: '☀️',
    tier: 'Platinum',
    category: 'secondary',
    level: 'Class 10 Secondary',
    streak: 34,
    minutes: 1480,
    badge: 'Math Wizard 📐',
    cheers: 84
  },
  {
    id: 'usr_674',
    alias: 'Zenith Explorer #674',
    avatar: '🧭',
    tier: 'Platinum',
    category: 'secondary',
    level: 'Class 9 Foundation',
    streak: 29,
    minutes: 1310,
    badge: 'Bio Champ 🧬',
    cheers: 72
  },
  {
    id: 'usr_881',
    alias: 'Quantum Falcon #881',
    avatar: '🦅',
    tier: 'Gold',
    category: 'higher',
    level: 'Class 11 Management',
    streak: 24,
    minutes: 1120,
    badge: 'Focus Monk 🧘',
    cheers: 63
  },
  {
    id: 'usr_433',
    alias: 'Aurora Owl #433',
    avatar: '✨',
    tier: 'Gold',
    category: 'uni',
    level: "Master's MSc CS",
    streak: 19,
    minutes: 940,
    badge: 'Research Fellow 📜',
    cheers: 58
  },
  {
    id: 'usr_512',
    alias: 'Apex Prowler #512',
    avatar: '🐆',
    tier: 'Silver',
    category: 'higher',
    level: 'Class 12 Humanities',
    streak: 14,
    minutes: 680,
    badge: 'Essay Crafter ✍️',
    cheers: 45
  },
  {
    id: 'usr_729',
    alias: 'Nebula Voyager #729',
    avatar: '🪐',
    tier: 'Silver',
    category: 'secondary',
    level: 'Class 10 Secondary',
    streak: 9,
    minutes: 420,
    badge: 'Nepali Scholar 🇳🇵',
    cheers: 39
  },
  {
    id: 'usr_314',
    alias: 'Shadow Weaver #314',
    avatar: '🦊',
    tier: 'Silver',
    category: 'uni',
    level: "Bachelor's BCA",
    streak: 5,
    minutes: 270,
    badge: 'Algo Solver 💻',
    cheers: 27
  }
];

// Active Server-Sent Event (SSE) clients pool
const sseClients = new Set();

function broadcastLeaderboardUpdate(sourceAction = 'sync') {
  const payload = JSON.stringify({
    type: 'leaderboard_snapshot',
    action: sourceAction,
    timestamp: Date.now(),
    data: GLOBAL_LEADERBOARD
  });

  for (const client of sseClients) {
    try {
      client.write(`data: ${payload}\n\n`);
    } catch (e) {
      sseClients.delete(client);
    }
  }
}

const server = http.createServer((req, res) => {
  // Universal CORS & Security headers
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET, HEAD, OPTIONS, POST');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Authorization, X-Requested-With');

  if (req.method === 'OPTIONS') {
    res.writeHead(204);
    res.end();
    return;
  }

  const parsedUrl = url.parse(req.url, true);
  let pathname = parsedUrl.pathname || '/';

  // Standard healthcheck endpoints
  if (pathname === '/healthz' || pathname === '/_health') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({ 
      status: 'ok', 
      service: 'studypal', 
      uptime: process.uptime(),
      activeRealtimeSessions: sseClients.size 
    }));
    return;
  }

  // --- REAL-TIME LEADERBOARD API ENDPOINTS ---
  // 1. GET /api/leaderboard (REST snapshot)
  if (pathname === '/api/leaderboard' && req.method === 'GET') {
    res.writeHead(200, { 
      'Content-Type': 'application/json',
      'Cache-Control': 'no-cache, no-store, must-revalidate'
    });
    res.end(JSON.stringify({ status: 'ok', data: GLOBAL_LEADERBOARD, timestamp: Date.now() }));
    return;
  }

  // 2. GET /api/leaderboard/stream (Server-Sent Events Real-Time Query Listener)
  if (pathname === '/api/leaderboard/stream' && req.method === 'GET') {
    res.writeHead(200, {
      'Content-Type': 'text/event-stream',
      'Cache-Control': 'no-cache, no-transform',
      'Connection': 'keep-alive',
      'X-Accel-Buffering': 'no'
    });

    res.write(`retry: 3000\n\n`);
    // Send immediate initial snapshot
    const initialPayload = JSON.stringify({
      type: 'leaderboard_snapshot',
      action: 'connect',
      timestamp: Date.now(),
      data: GLOBAL_LEADERBOARD
    });
    res.write(`data: ${initialPayload}\n\n`);

    sseClients.add(res);

    req.on('close', () => {
      sseClients.delete(res);
    });
    return;
  }

  // 3. POST /api/leaderboard/sync (Update user streak / minutes and broadcast in real time)
  if (pathname === '/api/leaderboard/sync' && req.method === 'POST') {
    let body = '';
    req.on('data', chunk => { body += chunk; });
    req.on('end', () => {
      try {
        const item = JSON.parse(body || '{}');
        if (item && item.id) {
          const existingIdx = GLOBAL_LEADERBOARD.findIndex(e => e.id === item.id);
          if (existingIdx >= 0) {
            GLOBAL_LEADERBOARD[existingIdx] = { ...GLOBAL_LEADERBOARD[existingIdx], ...item };
          } else {
            GLOBAL_LEADERBOARD.push(item);
          }

          // Re-sort leaderboard descending by streak, then minutes
          GLOBAL_LEADERBOARD.sort((a, b) => {
            if (b.streak !== a.streak) return b.streak - a.streak;
            return b.minutes - a.minutes;
          });

          // Broadcast to all active sessions instantly
          broadcastLeaderboardUpdate('user_sync');
        }

        res.writeHead(200, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ status: 'ok', success: true }));
      } catch (err) {
        res.writeHead(400, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ status: 'error', error: err.message }));
      }
    });
    return;
  }

  // 4. POST /api/leaderboard/cheer (Cheer student and broadcast instantly)
  if (pathname === '/api/leaderboard/cheer' && req.method === 'POST') {
    let body = '';
    req.on('data', chunk => { body += chunk; });
    req.on('end', () => {
      try {
        const payload = JSON.parse(body || '{}');
        const entry = GLOBAL_LEADERBOARD.find(e => e.id === payload.id);
        if (entry) {
          entry.cheers = (entry.cheers || 0) + 1;
          broadcastLeaderboardUpdate('cheer');
        }
        res.writeHead(200, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ status: 'ok', cheers: entry ? entry.cheers : 0 }));
      } catch (err) {
        res.writeHead(400, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ status: 'error', error: err.message }));
      }
    });
    return;
  }

  // Static File Serving
  if (pathname === '/') {
    pathname = '/index.html';
  }

  const safePath = path.normalize(pathname).replace(/^(\.\.[\/\\])+/, '');
  let filePath = path.join(PUBLIC_DIR, safePath);

  fs.stat(filePath, (err, stats) => {
    if (err || !stats.isFile()) {
      filePath = path.join(PUBLIC_DIR, 'index.html');
    }

    const ext = path.extname(filePath).toLowerCase();
    const contentType = MIME_TYPES[ext] || 'application/octet-stream';

    fs.readFile(filePath, (readErr, content) => {
      if (readErr) {
        res.writeHead(500, { 'Content-Type': 'text/plain' });
        res.end('500 Internal Server Error');
        return;
      }

      res.writeHead(200, {
        'Content-Type': contentType,
        'Cache-Control': 'no-cache, no-store, must-revalidate'
      });
      res.end(content);
    });
  });
});

server.listen(PORT, '0.0.0.0', () => {
  console.log(`StudyPal Web Server listening on http://0.0.0.0:${PORT}`);
});
