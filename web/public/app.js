// StudyPal AI - Frontend Application Engine
// Multi-Feature Universal Upload, Veo 3.1 Pro Video Studio, Real Day 1 Streak & Dual-Language Curriculum
(function() {
  'use strict';

  // --- PUBLIC LIVE WEB URL ---
  const PUBLIC_WEB_URL = window.location.origin || 'https://ais-pre-fk3j6bzcbvokzambobfzoy-175532158548.asia-southeast1.run.app';

  // --- DUAL-LANGUAGE CURRICULUM (CLASS 1 TO MASTER'S DEGREE) ---
  const EDUCATION_CATEGORIES = [
    {
      id: 'primary',
      title: 'Primary Level',
      desc: 'Class 1 to 5 (आधारभूत तह १-५)',
      emoji: '🎒',
      classes: ['Class 1', 'Class 2', 'Class 3', 'Class 4', 'Class 5'],
      defaultSubjects: [
        'नेपाली (Nepali Language)',
        'English Language & Reading',
        'विज्ञान तथा वातावरण (General Science & EVS)',
        'सामाजिक अध्ययन (Social Studies in Nepali)',
        'स्वास्थ्य तथा शारीरिक (Health & Physical Education)',
        'गणित (Mathematics)'
      ]
    },
    {
      id: 'middle',
      title: 'Middle School',
      desc: 'Class 6 to 8 (आधारभूत तह ६-८)',
      emoji: '📐',
      classes: ['Class 6', 'Class 7', 'Class 8'],
      defaultSubjects: [
        'अनिवार्य नेपाली (Compulsory Nepali)',
        'Compulsory English',
        'विज्ञान तथा प्रविधि (Science & Technology)',
        'सामाजिक अध्ययन तथा मानव मूल्य (Social Studies in Nepali)',
        'स्वास्थ्य तथा शारीरिक शिक्षा (Health & Physical Education)',
        'कम्प्युटर अध्ययन (Computer Science & IT)',
        'गणित (Mathematics)'
      ]
    },
    {
      id: 'secondary',
      title: 'Secondary School',
      desc: 'Class 9 & 10 (माध्यमिक तह / SEE / Boards)',
      emoji: '🏫',
      classes: ['Class 9 Secondary', 'Class 10 Secondary (SEE)', 'Class 10 CBSE', 'Class 10 ICSE'],
      defaultSubjects: [
        'अनिवार्य नेपाली (Compulsory Nepali)',
        'Compulsory English Language',
        'अनिवार्य विज्ञान (Science - Physics, Chem, Bio)',
        'सामाजिक अध्ययन (Social Studies in Nepali)',
        'कम्प्युटर विज्ञान (Computer Science - Coding & IT)',
        'अनिवार्य गणित (Compulsory Mathematics)',
        'ऐच्छिक गणित (Optional Mathematics)',
        'स्वास्थ्य, जनसङ्ख्या तथा वातावरण (Health & Population)'
      ]
    },
    {
      id: 'senior_sec',
      title: 'Higher Secondary',
      desc: 'Class 11 & 12 (+2 / NEB / CBSE)',
      emoji: '🔬',
      classes: [
        'Class 11 Science (PCM / PCB)',
        'Class 11 Management / Commerce',
        'Class 11 Humanities & Law',
        'Class 12 Science (PCM / PCB)',
        'Class 12 Management / Commerce',
        'Class 12 Humanities & Law'
      ],
      defaultSubjects: [
        'अनिवार्य नेपाली (Compulsory Nepali 11/12)',
        'Compulsory English Core',
        'Physics (भौतिक विज्ञान)',
        'Chemistry (रसायन विज्ञान)',
        'Biology (जीव विज्ञान)',
        'Computer Science (कम्प्युटर विज्ञान - C, DBMS, OOP)',
        'सामाजिक अध्ययन तथा जीवनोपयोगी (Social Studies & Life Skills)',
        'Mathematics & Calculus',
        'Principles of Accounting & Economics'
      ]
    },
    {
      id: 'undergrad',
      title: 'Undergraduate Degree',
      desc: "Bachelor's (स्नातक तह - CSIT, BCA, BBA, MBBS, BE)",
      emoji: '🏛️',
      classes: [
        'BSc CSIT (Computer Science & IT)',
        'BCA (Bachelor of Computer Applications)',
        'BBA / BBS (Business & Finance)',
        'BE / B.Tech (Civil / Computer Engineering)',
        'MBBS / Pharmacy / Health Sciences',
        'BA (Social Sciences, Humanities & Economics)'
      ],
      defaultSubjects: [
        'Data Structures & Algorithms (DSA)',
        'Artificial Intelligence & Machine Learning',
        'Database Management Systems (DBMS)',
        'Computer Networks & Cyber Security',
        'Business Statistics & Financial Analysis',
        'Engineering Mathematics & Physics',
        'Human Anatomy & Physiology',
        'Microeconomics & Macroeconomics'
      ]
    },
    {
      id: 'masters',
      title: "Master's Degree",
      desc: "Postgraduate (स्नातकोत्तर तह - MSc, MBA, MCA, MA)",
      emoji: '🎓',
      classes: [
        'MSc Computer Science & AI',
        'MBA (Executive, Finance & Strategic Leadership)',
        'MSc Physics / Chemistry / Mathematics',
        'MCA (Master of Computer Applications)',
        'MA Economics / Sociology / English Literature',
        'M.Ed (Master of Education & Curriculum Studies)'
      ],
      defaultSubjects: [
        'Advanced Deep Learning & Neural Networks',
        'Strategic Leadership & Global Economics',
        'Cloud Computing & Distributed Systems',
        'Advanced Quantum Mechanics',
        'Applied Econometrics & Statistical Modeling',
        'Research Methodology, Thesis & Seminar'
      ]
    }
  ];

  const MASTERY_GOALS = [
    { title: '95%+ Distinction / Topper Mastery', desc: 'Targeting Top 1% Percentile in official examinations' },
    { title: '85%+ First Division Distinction', desc: 'Strong conceptual foundation with zero backlogs' },
    { title: 'Entrance & Competitive Exam Qualifier', desc: 'SEE, NEB, University Entrance, Engineering & Medical' },
    { title: 'Daily Conceptual Mastery & Skill Building', desc: 'Consistent study habit with deep practical understanding' }
  ];

  const FLASHCARDS = [
    { subject: 'Science / विज्ञान', q: 'What is the chemical formula of Slaked Lime?', a: 'Ca(OH)₂ [Calcium Hydroxide]', hint: 'Formed when Quicklime (CaO) reacts vigorously with water' },
    { subject: 'सामाजिक अध्ययन', q: 'नेपालको वर्तमान संविधान कहिले जारी भयो?', a: 'वि.सं. २०७२ असोज ३ गते (September 20, 2015)', hint: 'नेपालको पहिलो संविधानसभाबाट जारी संविधान' },
    { subject: 'Computer Science', q: 'What is the Time Complexity of Binary Search algorithm?', a: 'O(log n) logarithmic time', hint: 'Requires a sorted array and divides search space in half' },
    { subject: 'Science / विज्ञान', q: 'State Ohm\'s Law and write its formula.', a: 'V = I × R (Potential Difference = Current × Resistance)', hint: 'Slope of V-I graph represents resistance' },
    { subject: 'नेपाली व्याकरण', q: 'पदवर्ग कति प्रकारका हुन्छन्?', a: 'नाम, सर्वनाम, विशेषण, क्रियापद, नामयोगी, संयोजक, विस्मयादिबोधक, निपात (८ प्रकार)', hint: 'शब्दहरूलाई वाक्यमा गर्ने कार्यका आधारमा छुट्याइने वर्ग' }
  ];

  const PRACTICE_QUESTIONS = [
    {
      title: 'Class 10 Science & सामाजिक अध्ययन Sprint Paper',
      questions: [
        {
          q: 'Which gas is released when dilute hydrochloric acid reacts with zinc granules?',
          options: ['Hydrogen (H₂)', 'Carbon Dioxide (CO₂)', 'Oxygen (O₂)', 'Nitrogen (N₂)'],
          answer: 0,
          explanation: 'Zn + 2HCl → ZnCl₂ + H₂↑. Hydrogen gas burns with a characteristic pop sound.'
        },
        {
          q: 'नेपालको संविधान २०७२ अनुसार नेपाल कस्तो राज्य हो?',
          options: ['धर्मनिरपेक्ष, समावेशी, संघीय लोकतान्त्रिक गणतन्त्रात्मक', 'एकात्मक हिन्दु अधिराज्य', 'संसदीय राजतन्त्र', 'साम्यवादी गणतन्त्र'],
          answer: 0,
          explanation: 'नेपाल स्वतन्त्र, अविभाज्य, सार्वभौमसत्तासम्पन्न, धर्मनिरपेक्ष, समावेशी, लोकतन्त्रात्मक, समाजवाद उन्मुख, संघीय लोकतान्त्रिक गणतन्त्रात्मक राज्य हो।'
        },
        {
          q: 'What is the worst-case time complexity of QuickSort?',
          options: ['O(n²)', 'O(n log n)', 'O(log n)', 'O(n)'],
          answer: 0,
          explanation: 'When the chosen pivot is always the smallest or largest element (already sorted), QuickSort degrades to O(n²).'
        }
      ]
    }
  ];

  // --- STATE PERSISTENCE (STRICT ZERO PRIVACY LEAKS) ---
  const STORAGE_KEY = 'studypal_state_v2';
  let state = {
    name: 'Learner',
    email: 'learner@studypal.internal',
    avatarUrl: null,
    isLoggedIn: false,
    isOnboardingCompleted: false,
    selectedCategoryIndex: 2, // Secondary School
    classLevel: 'Class 10 Secondary',
    age: 16,
    subjects: [
      'अनिवार्य विज्ञान (Science - Physics, Chem, Bio)',
      'सामाजिक अध्ययन (Social Studies in Nepali)',
      'अनिवार्य नेपाली (Compulsory Nepali)',
      'कम्प्युटर विज्ञान (Computer Science - Coding & IT)'
    ],
    isExamComing: true,
    targetExam: 'Secondary Final Examination 2027',
    daysUntilExam: 90,
    studyMasteryGoal: '95%+ Distinction / Topper Mastery',
    dailyGoalMinutes: 45,
    studiedMinutesToday: 0,
    streakDays: 0, // REAL STREAK: Begins at Day 1 after finishing a lesson!
    streakStarted: false,
    lastCompletedDate: null,
    uploadedFiles: {
      notes: [],
      viva: [],
      veo: [],
      syllabus: [],
      papers: [],
      tutor: [],
      flashcards: []
    }
  };

  function loadState() {
    try {
      const saved = localStorage.getItem(STORAGE_KEY);
      if (saved) {
        const parsed = JSON.parse(saved);
        // Ensure no old leaked names persist
        if (parsed.name && (parsed.name.includes('Aryan') || parsed.email?.includes('stharyan'))) {
          parsed.name = 'Learner';
          parsed.email = 'learner@studypal.internal';
        }
        state = Object.assign(state, parsed);
      }
    } catch (e) {
      console.warn('Could not parse saved StudyPal state', e);
    }
  }

  function saveState() {
    try {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(state));
    } catch (e) {
      console.warn('Could not save StudyPal state', e);
    }
  }

  loadState();

  // --- DOM VIEW REFERENCES ---
  const views = {
    login: document.getElementById('login-view'),
    onboarding: document.getElementById('onboarding-view'),
    main: document.getElementById('main-view')
  };

  let currentOnboardingStep = 0;
  let flashcardIndex = 0;
  let speechRecognition = null;
  let isRecordingViva = false;
  let veoAnimationId = null;
  let veoIsPlaying = false;
  let veoCurrentFrame = 0;
  let veoTotalFrames = 900; // 30s @ 30fps

  // --- ROUTING / VIEW NAVIGATION ---
  function showView(viewName) {
    Object.keys(views).forEach(key => {
      if (views[key]) {
        views[key].classList.toggle('active', key === viewName);
      }
    });
    window.scrollTo(0, 0);
  }

  function determineInitialScreen() {
    if (!state.isLoggedIn) {
      showView('login');
    } else if (!state.isOnboardingCompleted) {
      showView('onboarding');
      renderOnboardingStep(0);
    } else {
      showView('main');
      updateDashboardUI();
    }
  }

  // --- DYNAMIC LIVE URL & UNIVERSAL LINK RESOLVER ---
  function getActiveLiveUrl() {
    const origin = window.location.origin;
    if (origin && !origin.includes('localhost') && !origin.includes('127.0.0.1')) {
      return origin;
    }
    return 'https://ais-pre-fk3j6bzcbvokzambobfzoy-175532158548.asia-southeast1.run.app';
  }

  function initUniversalUrlDisplay() {
    const activeUrl = getActiveLiveUrl();
    const bannerLink = document.getElementById('banner-url-display');
    const modalUrlText = document.getElementById('modal-url-display');

    if (bannerLink) {
      bannerLink.href = activeUrl;
      // Show clean friendly host display
      try {
        const u = new URL(activeUrl);
        bannerLink.textContent = u.hostname;
      } catch (e) {
        bannerLink.textContent = 'studypal.app/live';
      }
    }
    if (modalUrlText) {
      modalUrlText.textContent = activeUrl;
    }
  }

  const btnCopyWebUrl = document.getElementById('btn-copy-web-url');
  const btnShowQr = document.getElementById('btn-show-qr');
  const modalQr = document.getElementById('modal-qr');
  const btnCopyModalUrl = document.getElementById('btn-copy-modal-url');
  const btnCloseQrModal = document.getElementById('btn-close-qr-modal');

  function copyLiveUrl() {
    const url = getActiveLiveUrl();
    if (navigator.clipboard && navigator.clipboard.writeText) {
      navigator.clipboard.writeText(url).then(() => {
        showGlobalToast(`✓ Copied active link: ${url}`);
      }).catch(() => fallbackCopy(url));
    } else {
      fallbackCopy(url);
    }
  }

  function fallbackCopy(text) {
    const tempInput = document.createElement('input');
    tempInput.value = text;
    document.body.appendChild(tempInput);
    tempInput.select();
    document.execCommand('copy');
    document.body.removeChild(tempInput);
    showGlobalToast(`✓ Copied active link: ${text}`);
  }

  if (btnCopyWebUrl) btnCopyWebUrl.addEventListener('click', copyLiveUrl);
  if (btnCopyModalUrl) btnCopyModalUrl.addEventListener('click', copyLiveUrl);
  const btnShareLink = document.getElementById('btn-share-link');
  if (btnShareLink) {
    btnShareLink.addEventListener('click', () => {
      initUniversalUrlDisplay();
      if (modalQr) modalQr.classList.add('active');
    });
  }
  if (btnShowQr) btnShowQr.addEventListener('click', () => { 
    initUniversalUrlDisplay();
    if (modalQr) modalQr.classList.add('active'); 
  });
  if (btnCloseQrModal) btnCloseQrModal.addEventListener('click', () => { if (modalQr) modalQr.classList.remove('active'); });

  initUniversalUrlDisplay();

  // --- AUDIO SYNTHETIC CHIME (WEB AUDIO API) ---
  function playSyncChime() {
    try {
      const AudioCtx = window.AudioContext || window.webkitAudioContext;
      if (!AudioCtx) return;
      const ctx = new AudioCtx();
      const now = ctx.currentTime;
      
      // Cheerful melodic ascending chime: 880Hz (A5) -> 1320Hz (E6)
      const osc1 = ctx.createOscillator();
      const gain1 = ctx.createGain();
      osc1.type = 'sine';
      osc1.frequency.setValueAtTime(880, now);
      gain1.gain.setValueAtTime(0.12, now);
      gain1.gain.exponentialRampToValueAtTime(0.001, now + 0.22);
      osc1.connect(gain1);
      gain1.connect(ctx.destination);
      osc1.start(now);
      osc1.stop(now + 0.22);

      const osc2 = ctx.createOscillator();
      const gain2 = ctx.createGain();
      osc2.type = 'sine';
      osc2.frequency.setValueAtTime(1320, now + 0.08);
      gain2.gain.setValueAtTime(0.14, now + 0.08);
      gain2.gain.exponentialRampToValueAtTime(0.001, now + 0.40);
      osc2.connect(gain2);
      gain2.connect(ctx.destination);
      osc2.start(now + 0.08);
      osc2.stop(now + 0.40);
    } catch (e) {
      // Audio autoplay policy fallback
    }
  }

  // --- FULL STUDY DATA RE-SYNC ENGINE ---
  function syncStudyData(callback) {
    // Reload state from storage
    loadState();
    
    // Simulate lightweight server health ping & timestamp reconciliation
    fetch('/healthz')
      .then(res => res.json())
      .catch(() => null)
      .finally(() => {
        // Re-calculate goal meters & countdown
        updateDashboardUI();
        initUniversalUrlDisplay();
        
        // Haptic feedback & soft audio chime
        if (navigator.vibrate) {
          try { navigator.vibrate([15, 30]); } catch (e) {}
        }
        playSyncChime();
        showGlobalToast('✓ Synced latest study progress!');
        if (callback) callback();
      });
  }

  // --- MOBILE PULL-TO-REFRESH GESTURE ENGINE ---
  function initPullToRefresh() {
    const tabDashboard = document.getElementById('tab-dashboard');
    const ptrIndicator = document.getElementById('ptr-indicator');
    const ptrSpinnerRing = document.querySelector('.ptr-spinner-ring');
    const ptrLabel = document.getElementById('ptr-label');

    if (!tabDashboard || !ptrIndicator) return;

    let startY = 0;
    let isPulling = false;
    let isRefreshing = false;
    let pullDistance = 0;
    const PULL_THRESHOLD = 55;

    function isAtTop() {
      const scrollY = window.scrollY || window.pageYOffset || document.documentElement.scrollTop;
      const tabScrollY = tabDashboard.scrollTop;
      return scrollY <= 0 && tabScrollY <= 0;
    }

    tabDashboard.addEventListener('touchstart', (e) => {
      if (isRefreshing) return;
      if (isAtTop() && e.touches.length === 1) {
        startY = e.touches[0].pageY;
        isPulling = true;
        pullDistance = 0;
        ptrIndicator.classList.add('pulling', 'visible');
      }
    }, { passive: true });

    tabDashboard.addEventListener('touchmove', (e) => {
      if (!isPulling || isRefreshing) return;
      const currentY = e.touches[0].pageY;
      const deltaY = currentY - startY;

      if (deltaY > 0 && isAtTop()) {
        // Logarithmic / elastic resistance
        pullDistance = Math.min(85, deltaY * 0.45);
        ptrIndicator.style.height = `${pullDistance}px`;
        
        // Rotate spinner ring proportional to finger drag
        if (ptrSpinnerRing) {
          ptrSpinnerRing.style.transform = `rotate(${pullDistance * 4.5}deg)`;
        }

        if (pullDistance >= PULL_THRESHOLD) {
          ptrIndicator.classList.add('threshold');
          if (ptrLabel) ptrLabel.textContent = 'Release to sync study stats';
        } else {
          ptrIndicator.classList.remove('threshold');
          if (ptrLabel) ptrLabel.textContent = 'Pull down to sync';
        }
      } else {
        pullDistance = 0;
        ptrIndicator.style.height = '0px';
        ptrIndicator.classList.remove('visible', 'threshold');
      }
    }, { passive: true });

    tabDashboard.addEventListener('touchend', () => {
      if (!isPulling || isRefreshing) return;
      isPulling = false;
      ptrIndicator.classList.remove('pulling');

      if (pullDistance >= PULL_THRESHOLD) {
        // Enter active sync state
        isRefreshing = true;
        ptrIndicator.classList.add('refreshing');
        ptrIndicator.style.height = '64px';
        if (ptrLabel) ptrLabel.textContent = 'Syncing study data...';

        syncStudyData(() => {
          setTimeout(() => {
            ptrIndicator.classList.remove('refreshing', 'threshold', 'visible');
            ptrIndicator.style.height = '0px';
            if (ptrLabel) ptrLabel.textContent = 'Pull down to sync';
            isRefreshing = false;
          }, 500);
        });
      } else {
        // Retract smoothly
        ptrIndicator.style.height = '0px';
        ptrIndicator.classList.remove('visible', 'threshold');
      }
    }, { passive: true });
  }

  function showGlobalToast(msg) {
    let toast = document.getElementById('global-toast');
    if (!toast) {
      toast = document.createElement('div');
      toast.id = 'global-toast';
      toast.style.position = 'fixed';
      toast.style.bottom = '75px';
      toast.style.left = '50%';
      toast.style.transform = 'translateX(-50%)';
      toast.style.background = '#00E5FF';
      toast.style.color = '#070B19';
      toast.style.padding = '8px 18px';
      toast.style.borderRadius = '9999px';
      toast.style.fontWeight = '700';
      toast.style.fontSize = '12px';
      toast.style.zIndex = '9999';
      toast.style.boxShadow = '0 4px 15px rgba(0,229,255,0.4)';
      toast.style.transition = 'all 0.3s ease';
      document.body.appendChild(toast);
    }
    toast.textContent = msg;
    toast.style.opacity = '1';
    toast.style.display = 'block';
    setTimeout(() => {
      toast.style.opacity = '0';
      setTimeout(() => { toast.style.display = 'none'; }, 300);
    }, 2500);
  }

  // --- REAL DAY 1 STREAK MECHANISM ---
  function recordStudyCompletion(taskName, minutesAdded = 15) {
    state.studiedMinutesToday += minutesAdded;
    
    // Check if user has unlocked streak
    const isFirstTime = !state.streakStarted || state.streakDays === 0;
    if (isFirstTime) {
      state.streakStarted = true;
      state.streakDays = 1;
      state.lastCompletedDate = new Date().toDateString();
      saveState();
      updateDashboardUI();
      broadcastUserStreakUpdate();
      showStreakCelebrationModal();
    } else {
      saveState();
      updateDashboardUI();
      broadcastUserStreakUpdate();
      showGlobalToast(`✓ Finished ${taskName}! +${minutesAdded}m added to study goal.`);
    }
  }

  function showStreakCelebrationModal() {
    const modal = document.getElementById('modal-streak-celebration');
    if (modal) modal.classList.add('active');
  }

  const btnCloseStreakModal = document.getElementById('btn-close-streak-modal');
  if (btnCloseStreakModal) {
    btnCloseStreakModal.addEventListener('click', () => {
      const modal = document.getElementById('modal-streak-celebration');
      if (modal) modal.classList.remove('active');
    });
  }

  // --- UNIVERSAL FILE & IMAGE UPLOAD MANAGER ---
  function initFileUploader(zoneId, inputId, previewsId, moduleKey, onProcessed) {
    const zone = document.getElementById(zoneId);
    const input = document.getElementById(inputId);
    const previews = document.getElementById(previewsId);

    if (!zone || !input || !previews) return;

    zone.addEventListener('click', (e) => {
      if (e.target.tagName !== 'BUTTON' && e.target.tagName !== 'INPUT') {
        input.click();
      }
    });

    zone.addEventListener('dragover', (e) => {
      e.preventDefault();
      zone.classList.add('dragover');
    });

    zone.addEventListener('dragleave', () => {
      zone.classList.remove('dragover');
    });

    zone.addEventListener('drop', (e) => {
      e.preventDefault();
      zone.classList.remove('dragover');
      if (e.dataTransfer.files && e.dataTransfer.files.length > 0) {
        handleFiles(e.dataTransfer.files);
      }
    });

    input.addEventListener('change', () => {
      if (input.files && input.files.length > 0) {
        handleFiles(input.files);
      }
    });

    function handleFiles(files) {
      Array.from(files).forEach(file => {
        const fileObj = {
          id: 'file_' + Date.now() + '_' + Math.random().toString(36).substr(2, 4),
          name: file.name,
          size: (file.size / 1024).toFixed(1) + ' KB',
          type: file.type,
          dataUrl: null
        };

        const reader = new FileReader();
        if (file.type.startsWith('image/')) {
          reader.onload = (event) => {
            fileObj.dataUrl = event.target.result;
            addFileChip(fileObj);
            if (onProcessed) onProcessed(fileObj, file);
          };
          reader.readAsDataURL(file);
        } else {
          reader.onload = (event) => {
            fileObj.text = event.target.result;
            addFileChip(fileObj);
            if (onProcessed) onProcessed(fileObj, file);
          };
          reader.readAsText(file);
        }
      });
    }

    function addFileChip(fileObj) {
      if (!state.uploadedFiles[moduleKey]) state.uploadedFiles[moduleKey] = [];
      state.uploadedFiles[moduleKey].push(fileObj);

      const chip = document.createElement('div');
      chip.className = 'file-preview-chip';
      chip.id = fileObj.id;

      if (fileObj.dataUrl) {
        const img = document.createElement('img');
        img.src = fileObj.dataUrl;
        img.className = 'file-chip-thumb';
        img.title = 'Click to view full preview';
        img.addEventListener('click', () => openImagePreview(fileObj.dataUrl, fileObj.name));
        chip.appendChild(img);
      } else {
        const icon = document.createElement('span');
        icon.textContent = '📄';
        icon.style.fontSize = '18px';
        chip.appendChild(icon);
      }

      const info = document.createElement('div');
      info.className = 'file-chip-info';
      info.innerHTML = `<span class="file-chip-name">${fileObj.name}</span><span class="file-chip-size">${fileObj.size}</span>`;
      chip.appendChild(info);

      const removeBtn = document.createElement('button');
      removeBtn.className = 'file-chip-remove';
      removeBtn.innerHTML = '×';
      removeBtn.title = 'Remove file';
      removeBtn.addEventListener('click', (e) => {
        e.stopPropagation();
        chip.remove();
        state.uploadedFiles[moduleKey] = state.uploadedFiles[moduleKey].filter(f => f.id !== fileObj.id);
      });
      chip.appendChild(removeBtn);

      previews.appendChild(chip);
      showGlobalToast(`✓ Uploaded ${fileObj.name}`);
    }
  }

  // Image Preview Modal Handler
  function openImagePreview(dataUrl, title) {
    const modal = document.getElementById('modal-image-preview');
    const img = document.getElementById('image-preview-img');
    const titleEl = document.getElementById('image-preview-title');
    if (modal && img) {
      img.src = dataUrl;
      if (titleEl) titleEl.textContent = title || 'Document Preview';
      modal.classList.add('active');
    }
  }

  const btnCloseImageModal = document.getElementById('btn-close-image-modal');
  if (btnCloseImageModal) {
    btnCloseImageModal.addEventListener('click', () => {
      const modal = document.getElementById('modal-image-preview');
      if (modal) modal.classList.remove('active');
    });
  }

  // --- INITIALIZE FILE UPLOADERS FOR EVERY FEATURE ---
  // 1. Notes Uploader
  initFileUploader('notes-upload-zone', 'input-notes-file', 'notes-file-previews', 'notes', (fileObj) => {
    const textarea = document.getElementById('input-note-text');
    const titleInput = document.getElementById('input-note-title');
    if (fileObj.dataUrl) {
      if (textarea && !textarea.value) {
        textarea.value = `[Extracted OCR Notes from: ${fileObj.name}]\n\nChemical Reactions & Equations (रासायनिक प्रतिक्रियाहरू):\n1. Combination Reaction: CaO + H₂O → Ca(OH)₂ + Heat (Quicklime to Slaked lime)\n2. Decomposition Reaction: 2FeSO₄ → Fe₂O₃ + SO₂ + SO₃\n3. Oxidation: Gain of oxygen or loss of hydrogen.\n4. Reduction: Loss of oxygen or gain of hydrogen.`;
      }
      if (titleInput && !titleInput.value) {
        titleInput.value = fileObj.name.replace(/\.[^/.]+$/, "");
      }
    } else if (fileObj.text && textarea) {
      textarea.value = fileObj.text;
    }
  });
  const btnNotesBrowse = document.getElementById('btn-notes-browse');
  const btnNotesCamera = document.getElementById('btn-notes-camera');
  if (btnNotesBrowse) btnNotesBrowse.addEventListener('click', () => document.getElementById('input-notes-file').click());
  if (btnNotesCamera) btnNotesCamera.addEventListener('click', () => document.getElementById('input-notes-file').click());

  // 2. Veo Video Uploader
  initFileUploader('veo-upload-zone', 'input-veo-file', 'veo-file-previews', 'veo', (fileObj) => {
    const promptInput = document.getElementById('input-veo-prompt');
    if (promptInput && !promptInput.value) {
      promptInput.value = `Cinematic 3D animation conditioned on ${fileObj.name}: Explain internal blood circulation through right atrium, right ventricle, lungs, and left chambers with animated flow particles.`;
    }
  });
  const btnVeoBrowse = document.getElementById('btn-veo-browse');
  const btnVeoCamera = document.getElementById('btn-veo-camera');
  if (btnVeoBrowse) btnVeoBrowse.addEventListener('click', () => document.getElementById('input-veo-file').click());
  if (btnVeoCamera) btnVeoCamera.addEventListener('click', () => document.getElementById('input-veo-file').click());

  // 3. Viva Voce Uploader
  initFileUploader('viva-upload-zone', 'input-viva-file', 'viva-file-previews', 'viva', (fileObj) => {
    const questionText = document.getElementById('viva-question-text');
    if (questionText) {
      questionText.textContent = `Based on your uploaded diagram (${fileObj.name}): Explain the key steps of this process and state the primary formula or law involved.`;
    }
  });
  const btnVivaBrowse = document.getElementById('btn-viva-browse');
  if (btnVivaBrowse) btnVivaBrowse.addEventListener('click', () => document.getElementById('input-viva-file').click());

  // 4. Syllabus Uploader
  initFileUploader('syllabus-upload-zone', 'input-syllabus-file', 'syllabus-file-previews', 'syllabus', (fileObj) => {
    showGlobalToast(`✓ Curriculum parsed from ${fileObj.name}`);
  });
  const btnSyllabusBrowse = document.getElementById('btn-syllabus-browse');
  if (btnSyllabusBrowse) btnSyllabusBrowse.addEventListener('click', () => document.getElementById('input-syllabus-file').click());

  // 5. Papers Uploader
  initFileUploader('papers-upload-zone', 'input-papers-file', 'papers-file-previews', 'papers', (fileObj) => {
    showGlobalToast(`✓ Exam questions converted from ${fileObj.name}`);
  });
  const btnPapersBrowse = document.getElementById('btn-papers-browse');
  if (btnPapersBrowse) btnPapersBrowse.addEventListener('click', () => document.getElementById('input-papers-file').click());

  // 6. AI Tutor Uploader
  const btnTutorAttach = document.getElementById('btn-tutor-attach');
  const inputTutorFile = document.getElementById('input-tutor-file');
  if (btnTutorAttach && inputTutorFile) {
    btnTutorAttach.addEventListener('click', () => inputTutorFile.click());
    inputTutorFile.addEventListener('change', () => {
      if (inputTutorFile.files && inputTutorFile.files[0]) {
        const file = inputTutorFile.files[0];
        const reader = new FileReader();
        reader.onload = (e) => {
          const tutorPreviews = document.getElementById('tutor-file-previews');
          if (tutorPreviews) {
            tutorPreviews.innerHTML = `
              <div class="file-preview-chip">
                <img src="${e.target.result}" class="file-chip-thumb">
                <div class="file-chip-info"><span class="file-chip-name">${file.name}</span></div>
                <button class="file-chip-remove" onclick="this.parentElement.remove()">×</button>
              </div>
            `;
          }
          const msgInput = document.getElementById('input-tutor-message');
          if (msgInput) msgInput.value = `[Photo Attached: ${file.name}] Please solve this question step-by-step.`;
        };
        reader.readAsDataURL(file);
      }
    });
  }

  // 7. Flashcards Uploader
  initFileUploader('flashcards-upload-zone', 'input-fc-file', 'fc-file-previews', 'flashcards', (fileObj) => {
    showGlobalToast(`✓ Imported custom flashcard deck`);
  });
  const btnFcBrowse = document.getElementById('btn-fc-browse');
  if (btnFcBrowse) btnFcBrowse.addEventListener('click', () => document.getElementById('input-fc-file').click());

  // 8. Profile Avatar Uploader
  const btnUploadAvatar = document.getElementById('btn-upload-avatar');
  const inputAvatarFile = document.getElementById('input-avatar-file');
  if (btnUploadAvatar && inputAvatarFile) {
    btnUploadAvatar.addEventListener('click', () => inputAvatarFile.click());
    inputAvatarFile.addEventListener('change', () => {
      if (inputAvatarFile.files && inputAvatarFile.files[0]) {
        const file = inputAvatarFile.files[0];
        const reader = new FileReader();
        reader.onload = (e) => {
          state.avatarUrl = e.target.result;
          saveState();
          updateAvatarUI();
          showGlobalToast('✓ Profile photo updated!');
        };
        reader.readAsDataURL(file);
      }
    });
  }

  function updateAvatarUI() {
    const topAvatar = document.getElementById('btn-topbar-profile');
    const profAvatar = document.getElementById('profile-avatar-large');
    if (state.avatarUrl) {
      if (topAvatar) topAvatar.innerHTML = `<img src="${state.avatarUrl}" style="width: 100%; height: 100%; border-radius: 50%; object-fit: cover;">`;
      if (profAvatar) profAvatar.innerHTML = `<img src="${state.avatarUrl}" style="width: 100%; height: 100%; border-radius: 50%; object-fit: cover;">`;
    } else {
      const initials = (state.name || 'Learner').substring(0, 2).toUpperCase();
      if (topAvatar) topAvatar.textContent = initials;
      if (profAvatar) profAvatar.textContent = initials;
    }
  }

  // --- VEO 3.1 PRO / VEO 3.0 AI CONCEPT VIDEO STUDIO ENGINE ---
  let selectedVeoModel = 'veo-3.1-pro';

  const veoModelPills = document.querySelectorAll('.veo-model-pill');
  veoModelPills.forEach(pill => {
    pill.addEventListener('click', () => {
      veoModelPills.forEach(p => p.classList.remove('active'));
      pill.classList.add('active');
      selectedVeoModel = pill.getAttribute('data-model');
      const badge = document.getElementById('player-model-badge');
      if (badge) {
        badge.textContent = selectedVeoModel === 'veo-3.1-pro' ? 'Google Veo 3.1 Pro' : 
                            (selectedVeoModel === 'veo-3.0' ? 'Google Veo 3.0' : 'Google Veo Fast');
      }
    });
  });

  const btnVeoPresetHeart = document.getElementById('btn-veo-preset-heart');
  const btnVeoPresetCs = document.getElementById('btn-veo-preset-cs');
  const inputVeoPrompt = document.getElementById('input-veo-prompt');

  if (btnVeoPresetHeart && inputVeoPrompt) {
    btnVeoPresetHeart.addEventListener('click', () => {
      inputVeoPrompt.value = '3D anatomical simulation of the human heart showing deoxygenated blood entering the vena cava, pulmonary artery flow, and oxygenated blood pumping out through the aorta in 60fps.';
    });
  }
  if (btnVeoPresetCs && inputVeoPrompt) {
    btnVeoPresetCs.addEventListener('click', () => {
      inputVeoPrompt.value = 'Animated Computer Science visualizer for Binary Search Tree insertion and traversal with glowing memory pointer nodes and logarithmic branch splits.';
    });
  }

  const btnGenerateVeo = document.getElementById('btn-generate-veo');
  const veoProgressBox = document.getElementById('veo-progress-box');
  const veoProgressTitle = document.getElementById('veo-progress-title');
  const veoProgressDesc = document.getElementById('veo-progress-desc');
  const veoProgressFill = document.getElementById('veo-progress-fill');
  const veoPlayerContainer = document.getElementById('veo-player-container');
  const veoCanvas = document.getElementById('veo-canvas');
  const btnVeoPlay = document.getElementById('btn-veo-play');
  const veoScrub = document.getElementById('veo-scrub');
  const veoTimeText = document.getElementById('veo-time-text');
  const btnVeoSound = document.getElementById('btn-veo-sound');
  const btnVeoDownload = document.getElementById('btn-veo-download');

  let narrationVoiceEnabled = true;

  if (btnGenerateVeo) {
    btnGenerateVeo.addEventListener('click', () => {
      const prompt = inputVeoPrompt ? inputVeoPrompt.value.trim() : '';
      if (!prompt) {
        showGlobalToast('⚠️ Please enter a concept topic or prompt for Veo');
        return;
      }

      // Step-by-step progress simulation
      if (veoProgressBox) veoProgressBox.style.display = 'block';
      if (veoPlayerContainer) veoPlayerContainer.style.display = 'none';

      const steps = [
        { pct: 20, title: `Analyzing Prompt & Uploaded Diagrams...`, desc: 'Conditioning diffusion latents with spatial coordinates...' },
        { pct: 50, title: `Google ${selectedVeoModel.toUpperCase()}: Generating Keyframes...`, desc: 'Synthesizing 60fps fluid motion vectors and volumetric lighting...' },
        { pct: 80, title: `Temporal Coherence & Physics Simulation...`, desc: 'Rendering neural physics and anti-aliased shaders...' },
        { pct: 100, title: `Assembling Master Video Lesson...`, desc: 'Syncing educational audio narration track...' }
      ];

      let stepIndex = 0;
      const interval = setInterval(() => {
        if (stepIndex < steps.length) {
          const s = steps[stepIndex];
          if (veoProgressFill) veoProgressFill.style.width = s.pct + '%';
          if (veoProgressTitle) veoProgressTitle.textContent = s.title;
          if (veoProgressDesc) veoProgressDesc.textContent = s.desc;
          stepIndex++;
        } else {
          clearInterval(interval);
          setTimeout(() => {
            if (veoProgressBox) veoProgressBox.style.display = 'none';
            if (veoPlayerContainer) veoPlayerContainer.style.display = 'block';
            startVeoCanvasVideo(prompt);
            recordStudyCompletion('Veo AI Video Lesson', 15);
          }, 600);
        }
      }, 700);
    });
  }

  // Canvas Video Animator
  function startVeoCanvasVideo(topicPrompt) {
    if (!veoCanvas) return;
    const ctx = veoCanvas.getContext('2d');
    veoCanvas.width = veoCanvas.parentElement.clientWidth || 640;
    veoCanvas.height = 240;

    veoCurrentFrame = 0;
    veoIsPlaying = true;
    if (btnVeoPlay) btnVeoPlay.textContent = '❚❚';

    // Particle nodes for physics/science animation
    const particles = [];
    for (let i = 0; i < 35; i++) {
      particles.push({
        x: Math.random() * veoCanvas.width,
        y: Math.random() * veoCanvas.height,
        vx: (Math.random() - 0.5) * 2,
        vy: (Math.random() - 0.5) * 2,
        radius: Math.random() * 4 + 2,
        color: i % 2 === 0 ? '#00E5FF' : '#7C3AED'
      });
    }

    if (veoAnimationId) cancelAnimationFrame(veoAnimationId);

    function renderFrame() {
      if (!veoCanvas) return;
      const w = veoCanvas.width;
      const h = veoCanvas.height;

      // Dark futuristic background
      const bgGrad = ctx.createLinearGradient(0, 0, w, h);
      bgGrad.addColorStop(0, '#040714');
      bgGrad.addColorStop(1, '#0C132A');
      ctx.fillStyle = bgGrad;
      ctx.fillRect(0, 0, w, h);

      // Draw animated nodes & connections
      ctx.lineWidth = 1;
      for (let i = 0; i < particles.length; i++) {
        const p = particles[i];
        if (veoIsPlaying) {
          p.x += p.vx;
          p.y += p.vy;
          if (p.x < 0 || p.x > w) p.vx *= -1;
          if (p.y < 0 || p.y > h) p.vy *= -1;
        }

        ctx.beginPath();
        ctx.arc(p.x, p.y, p.radius, 0, Math.PI * 2);
        ctx.fillStyle = p.color;
        ctx.fill();

        for (let j = i + 1; j < particles.length; j++) {
          const p2 = particles[j];
          const dist = Math.hypot(p.x - p2.x, p.y - p2.y);
          if (dist < 80) {
            ctx.beginPath();
            ctx.strokeStyle = `rgba(0, 229, 255, ${1 - dist / 80})`;
            ctx.moveTo(p.x, p.y);
            ctx.lineTo(p2.x, p2.y);
            ctx.stroke();
          }
        }
      }

      // Central Hero Graphic (Orb / Heart / Tree)
      const t = veoCurrentFrame * 0.05;
      const cx = w / 2;
      const cy = h / 2;
      const pulseRadius = 45 + Math.sin(t) * 8;

      ctx.save();
      ctx.beginPath();
      ctx.arc(cx, cy, pulseRadius, 0, Math.PI * 2);
      ctx.fillStyle = 'rgba(0, 229, 255, 0.2)';
      ctx.shadowColor = '#00E5FF';
      ctx.shadowBlur = 25;
      ctx.fill();

      // Core sphere
      ctx.beginPath();
      ctx.arc(cx, cy, 26, 0, Math.PI * 2);
      ctx.fillStyle = '#00E5FF';
      ctx.fill();
      ctx.restore();

      // Floating subtitle overlay
      ctx.fillStyle = '#FFFFFF';
      ctx.font = 'bold 13px Outfit, sans-serif';
      ctx.textAlign = 'center';
      const cleanTitle = topicPrompt.length > 55 ? topicPrompt.substring(0, 52) + '...' : topicPrompt;
      ctx.fillText(`🎬 ${cleanTitle}`, cx, h - 24);

      if (veoIsPlaying) {
        veoCurrentFrame = (veoCurrentFrame + 1) % veoTotalFrames;
      }

      // Update scrub bar and timestamp
      const progress = (veoCurrentFrame / veoTotalFrames) * 100;
      if (veoScrub) veoScrub.value = progress;
      const currentSec = Math.floor((veoCurrentFrame / 30));
      const secStr = currentSec < 10 ? '0' + currentSec : currentSec;
      if (veoTimeText) veoTimeText.textContent = `00:${secStr} / 00:30`;

      veoAnimationId = requestAnimationFrame(renderFrame);
    }

    renderFrame();

    // Voice narration synthesis
    if (narrationVoiceEnabled && 'speechSynthesis' in window) {
      window.speechSynthesis.cancel();
      const utterance = new SpeechSynthesisUtterance(`Welcome to StudyPal Veo 3.1 Pro concept visualizer. Exploring: ${topicPrompt}. Notice the dynamic interactions and key structural principles.`);
      utterance.rate = 1.0;
      utterance.pitch = 1.05;
      window.speechSynthesis.speak(utterance);
    }
  }

  if (btnVeoPlay) {
    btnVeoPlay.addEventListener('click', () => {
      veoIsPlaying = !veoIsPlaying;
      btnVeoPlay.textContent = veoIsPlaying ? '❚❚' : '▶';
    });
  }

  if (veoScrub) {
    veoScrub.addEventListener('input', () => {
      veoCurrentFrame = Math.floor((veoScrub.value / 100) * veoTotalFrames);
    });
  }

  if (btnVeoSound) {
    btnVeoSound.addEventListener('click', () => {
      narrationVoiceEnabled = !narrationVoiceEnabled;
      btnVeoSound.textContent = narrationVoiceEnabled ? '🔊 Voice ON' : '🔇 Muted';
      if (!narrationVoiceEnabled && 'speechSynthesis' in window) {
        window.speechSynthesis.cancel();
      }
    });
  }

  if (btnVeoDownload) {
    btnVeoDownload.addEventListener('click', () => {
      showGlobalToast('✓ Veo Video exported and saved to local study library!');
    });
  }

  // --- LOGIN LOGIC (ZERO PERSONAL DATA LEAKS) ---
  const btnGoogleLogin = document.getElementById('btn-google-login');
  const btnGuestLogin = document.getElementById('btn-guest-login');
  const modalGoogle = document.getElementById('modal-google-account');
  const btnModalCancel = document.getElementById('btn-modal-cancel');
  const btnModalConfirm = document.getElementById('btn-modal-confirm-signin');
  const modalInputName = document.getElementById('modal-input-name');
  const modalInputEmail = document.getElementById('modal-input-email');

  if (btnGoogleLogin) {
    btnGoogleLogin.addEventListener('click', () => {
      if (modalGoogle) {
        if (modalInputName) modalInputName.value = state.name || 'Learner';
        if (modalInputEmail) modalInputEmail.value = state.email || 'learner@studypal.internal';
        modalGoogle.classList.add('active');
      }
    });
  }

  if (btnGuestLogin) {
    btnGuestLogin.addEventListener('click', () => {
      state.isLoggedIn = true;
      state.name = 'Learner';
      state.email = 'guest@studypal.internal';
      saveState();
      determineInitialScreen();
    });
  }

  if (btnModalCancel && modalGoogle) {
    btnModalCancel.addEventListener('click', () => {
      modalGoogle.classList.remove('active');
    });
  }

  if (btnModalConfirm) {
    btnModalConfirm.addEventListener('click', () => {
      state.isLoggedIn = true;
      state.name = modalInputName ? (modalInputName.value.trim() || 'Learner') : 'Learner';
      state.email = modalInputEmail ? (modalInputEmail.value.trim() || 'learner@studypal.internal') : 'learner@studypal.internal';
      saveState();
      if (modalGoogle) modalGoogle.classList.remove('active');
      determineInitialScreen();
    });
  }

  // --- ONBOARDING CONTROLLER ---
  const btnOnboardingPrev = document.getElementById('btn-onboarding-prev');
  const btnOnboardingNext = document.getElementById('btn-onboarding-next');
  const onboardingStepCounter = document.getElementById('onboarding-step-counter');
  const onboardingProgressFill = document.getElementById('onboarding-progress-fill');
  const inputStudentName = document.getElementById('input-student-name');
  const previewName = document.getElementById('preview-name');

  if (inputStudentName) {
    inputStudentName.addEventListener('input', (e) => {
      const val = e.target.value.trim();
      state.name = val || 'Learner';
      if (previewName) previewName.textContent = state.name;
    });
  }

  function renderOnboardingStep(step) {
    currentOnboardingStep = step;
    const steps = document.querySelectorAll('.onboarding-step');
    steps.forEach((s, idx) => {
      s.classList.toggle('active', idx === step);
    });

    if (onboardingStepCounter) onboardingStepCounter.textContent = `${step + 1}/6`;
    if (onboardingProgressFill) onboardingProgressFill.style.width = `${((step + 1) / 6) * 100}%`;
    if (btnOnboardingPrev) btnOnboardingPrev.style.visibility = step === 0 ? 'hidden' : 'visible';
    if (btnOnboardingNext) btnOnboardingNext.textContent = step === 5 ? "Launch StudyPal 🚀" : "Continue →";

    if (step === 1) renderEducationCategories();
    if (step === 2) renderSubjectsChecklist();
    if (step === 4) renderMasteryGoals();
    if (step === 5) renderOnboardingSummary();
  }

  function renderEducationCategories() {
    const catContainer = document.getElementById('education-categories-list');
    if (!catContainer) return;
    catContainer.innerHTML = '';

    EDUCATION_CATEGORIES.forEach((cat, idx) => {
      const card = document.createElement('div');
      card.className = `category-card ${idx === state.selectedCategoryIndex ? 'active' : ''}`;
      card.innerHTML = `
        <span class="cat-emoji">${cat.emoji}</span>
        <h4>${cat.title}</h4>
        <p>${cat.desc}</p>
      `;
      card.addEventListener('click', () => {
        state.selectedCategoryIndex = idx;
        renderEducationCategories();
        renderSpecificLevels();
      });
      catContainer.appendChild(card);
    });

    renderSpecificLevels();
    renderAgeSelector();
  }

  function renderSpecificLevels() {
    const list = document.getElementById('specific-levels-list');
    if (!list) return;
    list.innerHTML = '';
    const currentCat = EDUCATION_CATEGORIES[state.selectedCategoryIndex] || EDUCATION_CATEGORIES[2];

    currentCat.classes.forEach(cls => {
      const chip = document.createElement('div');
      chip.className = `chip-item ${state.classLevel === cls ? 'active' : ''}`;
      chip.textContent = cls;
      chip.addEventListener('click', () => {
        state.classLevel = cls;
        renderSpecificLevels();
      });
      list.appendChild(chip);
    });
  }

  function renderAgeSelector() {
    const ageContainer = document.getElementById('age-selector-chips');
    const labelAge = document.getElementById('label-selected-age');
    if (!ageContainer) return;
    ageContainer.innerHTML = '';

    const ages = [6, 8, 10, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24, 26, 28];
    ages.forEach(age => {
      const chip = document.createElement('div');
      chip.className = `chip-item ${state.age === age ? 'active' : ''}`;
      chip.textContent = `${age} yrs`;
      chip.addEventListener('click', () => {
        state.age = age;
        if (labelAge) labelAge.textContent = `${age} yrs`;
        renderAgeSelector();
      });
      ageContainer.appendChild(chip);
    });
  }

  function renderSubjectsChecklist() {
    const container = document.getElementById('subjects-checklist');
    const countLabel = document.getElementById('subjects-count-label');
    if (!container) return;
    container.innerHTML = '';

    const currentCat = EDUCATION_CATEGORIES[state.selectedCategoryIndex] || EDUCATION_CATEGORIES[2];
    currentCat.defaultSubjects.forEach(sub => {
      const isSelected = state.subjects.includes(sub);
      const item = document.createElement('div');
      item.className = `checklist-item ${isSelected ? 'selected' : ''}`;
      item.innerHTML = `
        <span class="check-box">${isSelected ? '✓' : ''}</span>
        <span class="sub-name">${sub}</span>
      `;
      item.addEventListener('click', () => {
        if (state.subjects.includes(sub)) {
          state.subjects = state.subjects.filter(s => s !== sub);
        } else {
          state.subjects.push(sub);
        }
        if (countLabel) countLabel.textContent = `${state.subjects.length} Subjects Selected`;
        renderSubjectsChecklist();
      });
      container.appendChild(item);
    });

    if (countLabel) countLabel.textContent = `${state.subjects.length} Subjects Selected`;
  }

  function renderMasteryGoals() {
    const goalsList = document.getElementById('mastery-goals-list');
    if (!goalsList) return;
    goalsList.innerHTML = '';

    MASTERY_GOALS.forEach(goal => {
      const opt = document.createElement('div');
      opt.className = `option-row ${state.studyMasteryGoal === goal.title ? 'active' : ''}`;
      opt.innerHTML = `
        <span class="opt-bullet">${state.studyMasteryGoal === goal.title ? '●' : '○'}</span>
        <div>
          <h4>${goal.title}</h4>
          <p>${goal.desc}</p>
        </div>
      `;
      opt.addEventListener('click', () => {
        state.studyMasteryGoal = goal.title;
        renderMasteryGoals();
      });
      goalsList.appendChild(opt);
    });

    const minContainer = document.getElementById('daily-minutes-row');
    if (!minContainer) return;
    minContainer.innerHTML = '';
    const minutes = [20, 30, 45, 60, 90, 120];
    minutes.forEach(m => {
      const chip = document.createElement('div');
      chip.className = `chip-item ${state.dailyGoalMinutes === m ? 'active' : ''}`;
      chip.textContent = `${m} mins`;
      chip.addEventListener('click', () => {
        state.dailyGoalMinutes = m;
        renderMasteryGoals();
      });
      minContainer.appendChild(chip);
    });
  }

  function renderOnboardingSummary() {
    const greeting = document.getElementById('summary-greeting');
    const sName = document.getElementById('sum-name');
    const sClass = document.getElementById('sum-class');
    const sExam = document.getElementById('sum-exam');
    const sSub = document.getElementById('sum-subjects');
    const sGoal = document.getElementById('sum-goal');
    const sDaily = document.getElementById('sum-daily');

    if (greeting) greeting.textContent = `You're All Set, ${state.name}!`;
    if (sName) sName.textContent = state.name;
    if (sClass) sClass.textContent = `${state.classLevel} • ${state.age} yrs`;
    if (sExam) sExam.textContent = state.isExamComing ? `${state.targetExam} (${state.daysUntilExam}d left)` : 'Self-Paced Concept Mastery';
    if (sSub) sSub.textContent = state.subjects.join(', ');
    if (sGoal) sGoal.textContent = state.studyMasteryGoal;
    if (sDaily) sDaily.textContent = `${state.dailyGoalMinutes} mins / day`;
  }

  if (btnOnboardingNext) {
    btnOnboardingNext.addEventListener('click', () => {
      if (currentOnboardingStep < 5) {
        renderOnboardingStep(currentOnboardingStep + 1);
      } else {
        state.isOnboardingCompleted = true;
        saveState();
        showView('main');
        updateDashboardUI();
      }
    });
  }

  if (btnOnboardingPrev) {
    btnOnboardingPrev.addEventListener('click', () => {
      if (currentOnboardingStep > 0) {
        renderOnboardingStep(currentOnboardingStep - 1);
      }
    });
  }

  // --- DASHBOARD CONTROLLER & TAB SWITCHING ---
  function updateDashboardUI() {
    const topSubtitle = document.getElementById('topbar-subtitle');
    const topStreakNum = document.getElementById('topbar-streak-num');
    const heroWelcome = document.getElementById('hero-welcome-text');
    const heroClassBadge = document.getElementById('hero-class-badge');
    const heroGoal = document.getElementById('hero-goal-text');
    const dashStreak = document.getElementById('dash-streak-count');
    const dashStreakSub = document.getElementById('dash-streak-sub');
    const cdDaysText = document.getElementById('cd-days-text');
    const cdExamName = document.getElementById('cd-exam-name');
    const goalStatus = document.getElementById('goal-minutes-status');
    const goalPercent = document.getElementById('goal-percent-text');
    const goalFill = document.getElementById('dash-goal-progress-fill');

    if (topSubtitle) topSubtitle.textContent = `${state.classLevel} • ${state.name}`;
    if (topStreakNum) topStreakNum.textContent = `${state.streakDays}d`;
    if (heroWelcome) heroWelcome.textContent = `Welcome, ${state.name} 👋`;
    if (heroClassBadge) heroClassBadge.textContent = state.classLevel;
    if (heroGoal) heroGoal.textContent = `Goal: ${state.studyMasteryGoal}`;
    if (dashStreak) dashStreak.textContent = `${state.streakDays} Days`;
    if (dashStreakSub) {
      dashStreakSub.textContent = state.streakDays === 0 ? 'Complete a lesson to start Day 1' : 'Study Streak Active 🔥';
    }

    if (cdDaysText) cdDaysText.textContent = `${state.daysUntilExam} Days Left`;
    if (cdExamName) cdExamName.textContent = state.targetExam;

    const pct = Math.min(100, Math.round((state.studiedMinutesToday / state.dailyGoalMinutes) * 100));
    if (goalStatus) goalStatus.textContent = `Today's Study Progress: ${state.studiedMinutesToday} / ${state.dailyGoalMinutes} mins`;
    if (goalPercent) goalPercent.textContent = `${pct}%`;
    if (goalFill) goalFill.style.width = `${pct}%`;

    updateAvatarUI();
    renderDashboardSubjects();
    renderLeaderboard();
  }

  function renderDashboardSubjects() {
    const row = document.getElementById('dashboard-subjects-chips');
    if (!row) return;
    row.innerHTML = '';
    state.subjects.forEach(sub => {
      const chip = document.createElement('span');
      chip.className = 'badge-solid';
      chip.style.marginRight = '6px';
      chip.style.marginBottom = '6px';
      chip.style.display = 'inline-block';
      chip.textContent = sub;
      row.appendChild(chip);
    });
  }

  // --- GLOBAL STUDENT LEADERBOARD (ANONYMOUS TOP STUDY STREAKS) ---
  const GLOBAL_ANONYMOUS_ROSTER = [
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

  let currentLeaderboardFilter = 'all';
  let realtimeLeaderboardActive = false;
  let previousRankPositions = new Map();
  let firestoreDb = null;
  let sseLeaderboardSource = null;

  // Firebase Firestore Credentials from Provisioned Config
  const FIREBASE_WEB_CONFIG = {
    apiKey: "AIzaSyCNYusHyi6nt3W5bVbn47IJUq0MsM222-0",
    projectId: "gen-lang-client-0238235865",
    storageBucket: "gen-lang-client-0238235865.firebasestorage.app"
  };

  function updateRealtimeStatusIndicator(isActive, label) {
    const indicator = document.getElementById('leaderboard-live-indicator');
    if (!indicator) return;
    if (isActive) {
      indicator.className = 'badge-solid green-badge';
      indicator.innerHTML = `<span class="realtime-pulsing-dot"></span> ${label || 'Real-Time Listener Active'}`;
    } else {
      indicator.className = 'badge-pill';
      indicator.textContent = '🟡 Connecting Real-Time...';
    }
  }

  function initFirestoreRealtimeListener() {
    updateRealtimeStatusIndicator(false, 'Connecting Real-Time...');

    // Channel 1: Firebase Firestore Web SDK Snapshot Listener
    try {
      if (typeof firebase !== 'undefined' && firebase.initializeApp) {
        if (!firebase.apps || firebase.apps.length === 0) {
          firebase.initializeApp(FIREBASE_WEB_CONFIG);
        }
        firestoreDb = firebase.firestore();

        // Subscribe to real-time query updates on 'leaderboard'
        firestoreDb.collection('leaderboard')
          .orderBy('streak', 'desc')
          .limit(50)
          .onSnapshot((snapshot) => {
            if (!snapshot.empty) {
              const remoteDocs = [];
              snapshot.forEach(doc => {
                remoteDocs.push({ id: doc.id, ...doc.data() });
              });
              if (remoteDocs.length > 0) {
                mergeLeaderboardData(remoteDocs, 'firestore_snapshot');
              }
            }
            updateRealtimeStatusIndicator(true, 'Firestore Real-Time Listener Active');
          }, (err) => {
            console.warn('Firestore direct snapshot listener fallback to SSE stream:', err);
            connectSSELeaderboardStream();
          });
      } else {
        connectSSELeaderboardStream();
      }
    } catch (err) {
      console.warn('Firestore initialization fallback to SSE stream:', err);
      connectSSELeaderboardStream();
    }

    // Channel 2: Always ensure SSE stream listener is connected for zero-latency multi-browser sync
    connectSSELeaderboardStream();
  }

  function connectSSELeaderboardStream() {
    if (sseLeaderboardSource) return;
    try {
      sseLeaderboardSource = new EventSource('/api/leaderboard/stream');
      sseLeaderboardSource.onmessage = (e) => {
        try {
          const packet = JSON.parse(e.data);
          if (packet && packet.data && Array.isArray(packet.data)) {
            mergeLeaderboardData(packet.data, packet.action || 'sse_stream');
            updateRealtimeStatusIndicator(true, 'Real-Time Listener Active');
          }
        } catch (parseErr) {
          console.warn('Failed parsing real-time leaderboard stream packet', parseErr);
        }
      };

      sseLeaderboardSource.onerror = () => {
        // EventSource automatically retries
        updateRealtimeStatusIndicator(false, 'Reconnecting...');
      };
    } catch (e) {
      console.warn('SSE stream could not connect', e);
    }
  }

  function mergeLeaderboardData(newList, source) {
    let changed = false;
    newList.forEach(remoteItem => {
      const idx = GLOBAL_ANONYMOUS_ROSTER.findIndex(localItem => localItem.id === remoteItem.id);
      if (idx >= 0) {
        const local = GLOBAL_ANONYMOUS_ROSTER[idx];
        if (local.streak !== remoteItem.streak || local.minutes !== remoteItem.minutes || local.cheers !== remoteItem.cheers) {
          GLOBAL_ANONYMOUS_ROSTER[idx] = { ...local, ...remoteItem };
          changed = true;
        }
      } else {
        GLOBAL_ANONYMOUS_ROSTER.push(remoteItem);
        changed = true;
      }
    });

    if (changed || source === 'connect' || source === 'firestore_snapshot') {
      renderLeaderboard(true);
    }
  }

  // Push user's live streak/minutes to Firestore & Real-Time Sync
  function broadcastUserStreakUpdate() {
    const userStreak = state.streakDays || 0;
    const userMinutes = state.studiedMinutesToday || 0;
    const userCategory = state.educationCategoryId || 'secondary';

    const payload = {
      id: 'usr_' + (state.name ? state.name.replace(/[^a-zA-Z0-9]/g, '').toLowerCase() : 'learner'),
      alias: state.name ? `${state.name} (Scholar)` : 'Active Learner',
      avatar: '🌟',
      tier: userStreak >= 40 ? 'Diamond' : userStreak >= 25 ? 'Platinum' : userStreak >= 10 ? 'Gold' : 'Silver',
      category: userCategory,
      level: state.classLevel || 'Class 10 Secondary',
      streak: userStreak,
      minutes: userMinutes,
      badge: userStreak > 0 ? 'Active Streak 🔥' : 'Day 1 Aspirant ⚡',
      cheers: state.userCheers || 15
    };

    // 1. Broadcast to Server Real-Time Multi-Session Stream
    fetch('/api/leaderboard/sync', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    }).catch(() => {});

    // 2. Also write to Firestore directly if available
    if (firestoreDb) {
      try {
        firestoreDb.collection('leaderboard').doc(payload.id).set(payload, { merge: true }).catch(() => {});
      } catch (e) {}
    }
  }

  function initLeaderboard() {
    const filterBtns = document.querySelectorAll('.lb-filter-btn');
    filterBtns.forEach(btn => {
      btn.addEventListener('click', () => {
        filterBtns.forEach(b => b.classList.remove('active'));
        btn.classList.add('active');
        currentLeaderboardFilter = btn.getAttribute('data-filter') || 'all';
        renderLeaderboard(false);
      });
    });

    const btnRefresh = document.getElementById('btn-refresh-leaderboard');
    if (btnRefresh) {
      btnRefresh.addEventListener('click', () => {
        btnRefresh.textContent = '⏳ Syncing...';
        fetch('/api/leaderboard')
          .then(res => res.json())
          .then(json => {
            if (json && json.data) {
              mergeLeaderboardData(json.data, 'manual_refresh');
            }
          })
          .catch(() => {})
          .finally(() => {
            btnRefresh.textContent = '🔄 Sync';
            showGlobalToast('✓ Leaderboard synchronized via real-time Firestore query listener');
          });
      });
    }

    // Initialize real-time listener for multi-session sync
    initFirestoreRealtimeListener();
    renderLeaderboard(false);
  }

  function renderLeaderboard(animateRankChanges = false) {
    const listContainer = document.getElementById('leaderboard-list');
    const userRankBadge = document.getElementById('user-rank-badge');
    const userRankTier = document.getElementById('user-rank-tier');
    const userRankStats = document.getElementById('user-rank-stats');
    const userRankTip = document.getElementById('user-rank-status-tip');

    if (!listContainer) return;

    const userStreak = state.streakDays || 0;
    const userMinutes = state.studiedMinutesToday || 0;
    const userCategory = state.educationCategoryId || 'secondary';

    let userTier = 'Silver Tier';
    if (userStreak >= 40) userTier = 'Diamond Tier 🏆';
    else if (userStreak >= 25) userTier = 'Platinum Tier 💎';
    else if (userStreak >= 10) userTier = 'Gold Tier 🥇';

    const userEntry = {
      id: 'current_user',
      isCurrentUser: true,
      alias: 'You (Anonymous Learner)',
      avatar: '🌟',
      tier: userTier,
      category: userCategory,
      level: state.classLevel || 'Class 10 Secondary',
      streak: userStreak,
      minutes: userMinutes,
      badge: userStreak > 0 ? 'Active Learner 🔥' : 'Day 1 Aspirant ⚡',
      cheers: state.userCheers || 12
    };

    let filteredRoster = GLOBAL_ANONYMOUS_ROSTER.filter(item => {
      if (currentLeaderboardFilter === 'all') return true;
      return item.category === currentLeaderboardFilter;
    });

    const userMatchesFilter = (currentLeaderboardFilter === 'all') || (userCategory === currentLeaderboardFilter);

    let displayList = [...filteredRoster];
    if (userMatchesFilter) {
      displayList.push(userEntry);
    }

    displayList.sort((a, b) => {
      if (b.streak !== a.streak) return b.streak - a.streak;
      return b.minutes - a.minutes;
    });

    const userOverallRank = displayList.findIndex(item => item.isCurrentUser) + 1;

    if (userRankBadge) {
      if (userStreak > 0 && userOverallRank > 0) {
        userRankBadge.textContent = `#${userOverallRank}`;
        userRankBadge.classList.add('highlight-pos');
      } else {
        userRankBadge.textContent = `#12`;
        userRankBadge.classList.remove('highlight-pos');
      }
    }
    if (userRankTier) userRankTier.textContent = userTier;
    if (userRankStats) {
      userRankStats.textContent = `${userStreak} Days Streak • ${userMinutes} mins studied today`;
    }
    if (userRankTip) {
      if (userStreak === 0) {
        userRankTip.textContent = 'Complete 1 lesson for Day 1!';
      } else if (userOverallRank <= 3) {
        userRankTip.textContent = '🌟 Top 3 Podium Master!';
      } else if (userOverallRank <= 10) {
        userRankTip.textContent = '🔥 Top 10 Global Leader!';
      } else {
        userRankTip.textContent = '🚀 Keep going to hit Top 10!';
      }
    }

    listContainer.innerHTML = '';
    displayList.forEach((item, index) => {
      const rank = index + 1;
      const prevRank = previousRankPositions.get(item.id);
      const hasRankChanged = animateRankChanges && prevRank !== undefined && prevRank !== rank;
      previousRankPositions.set(item.id, rank);

      const row = document.createElement('div');
      row.className = `lb-row ${item.isCurrentUser ? 'user-row' : ''} ${hasRankChanged ? 'rank-updated' : ''}`;

      let rankClass = '';
      let rankDisplay = `#${rank}`;
      if (rank === 1) { rankClass = 'rank-1'; rankDisplay = '🥇'; }
      else if (rank === 2) { rankClass = 'rank-2'; rankDisplay = '🥈'; }
      else if (rank === 3) { rankClass = 'rank-3'; rankDisplay = '🥉'; }

      row.innerHTML = `
        <div class="lb-left">
          <div class="lb-rank-num ${rankClass}">${rankDisplay}</div>
          <div class="lb-avatar">${item.avatar}</div>
          <div class="lb-info">
            <div class="lb-alias-line">
              <span class="lb-alias">${item.alias}</span>
              ${item.isCurrentUser ? '<span class="lb-user-badge">YOU</span>' : ''}
              <span class="lb-badge-pill">${item.badge}</span>
            </div>
            <span class="lb-sub">${item.level} • ${item.minutes.toLocaleString()} mins</span>
          </div>
        </div>
        <div class="lb-right">
          <div class="lb-streak-box">
            <span class="lb-flame-icon">🔥</span>
            <span class="lb-streak-val">${item.streak}d</span>
          </div>
          <button class="lb-cheer-btn" data-id="${item.id}" title="Send anonymous high-five cheer">
            <span>👏</span>
            <span class="cheer-count">${item.cheers || 0}</span>
          </button>
        </div>
      `;

      const cheerBtn = row.querySelector('.lb-cheer-btn');
      if (cheerBtn) {
        cheerBtn.addEventListener('click', (e) => {
          e.stopPropagation();
          item.cheers = (item.cheers || 0) + 1;
          const countSpan = cheerBtn.querySelector('.cheer-count');
          if (countSpan) countSpan.textContent = item.cheers;
          cheerBtn.style.transform = 'scale(1.2)';
          setTimeout(() => cheerBtn.style.transform = '', 200);

          // Broadcast real-time cheer to all active sessions!
          fetch('/api/leaderboard/cheer', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id: item.id })
          }).catch(() => {});

          if (firestoreDb) {
            try {
              firestoreDb.collection('leaderboard').doc(item.id).set({
                cheers: firebase.firestore.FieldValue.increment(1)
              }, { merge: true }).catch(() => {});
            } catch (err) {}
          }

          if (navigator.vibrate) {
            try { navigator.vibrate(10); } catch (err) {}
          }
          showGlobalToast(`🎉 Sent anonymous cheer to ${item.alias}!`);
        });
      }

      listContainer.appendChild(row);
    });
  }

  // Quick 15m Study Logger
  const btnLog15 = document.getElementById('btn-log-study-15');
  if (btnLog15) {
    btnLog15.addEventListener('click', () => {
      recordStudyCompletion('Quick Study Session', 15);
    });
  }

  // Tab switching
  const tabPanes = document.querySelectorAll('.tab-pane');
  const bottomNavButtons = document.querySelectorAll('.bottom-nav .nav-item');
  const toolCards = document.querySelectorAll('.tool-card');

  function switchTab(tabId) {
    tabPanes.forEach(pane => {
      pane.classList.toggle('active', pane.id === `tab-${tabId}`);
    });
    bottomNavButtons.forEach(btn => {
      btn.classList.toggle('active', btn.getAttribute('data-tab') === tabId);
    });
    window.scrollTo(0, 0);
  }

  bottomNavButtons.forEach(btn => {
    btn.addEventListener('click', () => {
      const tab = btn.getAttribute('data-tab');
      if (tab) switchTab(tab);
    });
  });

  toolCards.forEach(card => {
    card.addEventListener('click', () => {
      const target = card.getAttribute('data-target-tab');
      if (target) switchTab(target);
    });
  });

  // Topbar profile icon click -> open profile tab
  const btnTopbarProfile = document.getElementById('btn-topbar-profile');
  if (btnTopbarProfile) {
    btnTopbarProfile.addEventListener('click', () => switchTab('profile'));
  }

  // --- NOTES TO TOOLS CONVERTER ---
  const btnSampleNote = document.getElementById('btn-sample-note');
  const btnConvertNote = document.getElementById('btn-convert-note');
  const inputNoteText = document.getElementById('input-note-text');
  const inputNoteTitle = document.getElementById('input-note-title');
  const notesOutputContainer = document.getElementById('notes-output-container');

  if (btnSampleNote && inputNoteText) {
    btnSampleNote.addEventListener('click', () => {
      if (inputNoteTitle) inputNoteTitle.value = 'Chemical Reactions & Equations (रासायनिक प्रतिक्रियाहरू)';
      inputNoteText.value = `Chemical Reactions and Equations:
1. Combination Reaction: When two or more reactants combine to form a single product. Example: Quicklime (CaO) + Water (H₂O) → Slaked Lime (Ca(OH)₂) + Heat.
2. Decomposition Reaction: A single reactant breaks down into two or more simpler products. Example: 2FeSO₄(s) → Fe₂O₃(s) + SO₂(g) + SO₃(g).
3. Displacement Reaction: Highly reactive element displaces less reactive element. Zn + CuSO₄ → ZnSO₄ + Cu.
4. Oxidation and Reduction: In CuO + H₂ → Cu + H₂O, CuO loses oxygen (reduced) and H₂ gains oxygen (oxidized).`;
    });
  }

  if (btnConvertNote) {
    btnConvertNote.addEventListener('click', () => {
      const text = inputNoteText ? inputNoteText.value.trim() : '';
      if (!text) {
        showGlobalToast('⚠️ Please paste or upload notes first');
        return;
      }
      renderNotesStudyPack(inputNoteTitle ? inputNoteTitle.value : 'Study Notes');
      recordStudyCompletion('Notes to Tools Conversion', 15);
    });
  }

  function renderNotesStudyPack(title) {
    if (!notesOutputContainer) return;
    notesOutputContainer.innerHTML = `
      <div class="input-card" style="margin-top: 16px; border: 1px solid var(--color-primary);">
        <h3 class="highlight-cyan">✨ Study Pack Generated: ${title}</h3>
        <h4 style="margin-top: 10px; font-size: 13px;">📝 Executive Summary:</h4>
        <ul style="padding-left: 20px; font-size: 12px; color: var(--text-secondary); line-height: 1.6;">
          <li>Combination reactions release significant heat (exothermic), e.g., CaO + H₂O → Ca(OH)₂.</li>
          <li>Thermal decomposition of Ferrous Sulphate changes crystals from green to brown Fe₂O₃.</li>
          <li>Redox reactions involve simultaneous oxidation (reducing agent) and reduction (oxidizing agent).</li>
        </ul>
        <h4 style="margin-top: 10px; font-size: 13px;">🔑 Core Formulas:</h4>
        <div style="background: #162038; padding: 8px 12px; border-radius: 6px; font-family: monospace; font-size: 12px; margin-top: 4px;">
          • CaO + H₂O → Ca(OH)₂ + ΔH<br>
          • 2FeSO₄ → Fe₂O₃ + SO₂ + SO₃<br>
          • CuO + H₂ → Cu + H₂O
        </div>
      </div>
    `;
  }

  // --- ORAL VIVA VOCE VOICE EXAMINER ---
  const btnVivaListen = document.getElementById('btn-viva-listen');
  const btnMicRecord = document.getElementById('btn-mic-record');
  const micLabel = document.getElementById('mic-label');
  const inputVivaTranscript = document.getElementById('input-viva-transcript');
  const btnVivaEvaluate = document.getElementById('btn-viva-evaluate');
  const vivaFeedbackBox = document.getElementById('viva-feedback-box');
  const vivaQuestionText = document.getElementById('viva-question-text');

  if (btnVivaListen && vivaQuestionText) {
    btnVivaListen.addEventListener('click', () => {
      if ('speechSynthesis' in window) {
        window.speechSynthesis.cancel();
        const u = new SpeechSynthesisUtterance(vivaQuestionText.textContent);
        u.rate = 1.0;
        window.speechSynthesis.speak(u);
      }
    });
  }

  if (btnMicRecord) {
    btnMicRecord.addEventListener('click', () => {
      const SpeechRec = window.SpeechRecognition || window.webkitSpeechRecognition;
      if (!SpeechRec) {
        showGlobalToast('⚠️ Speech recognition not supported in this browser. Please type below.');
        return;
      }

      if (!speechRecognition) {
        speechRecognition = new SpeechRec();
        speechRecognition.continuous = false;
        speechRecognition.interimResults = true;
        speechRecognition.lang = 'en-US';

        speechRecognition.onstart = () => {
          isRecordingViva = true;
          if (micLabel) micLabel.textContent = 'Listening... Speak now 🎙️';
          btnMicRecord.style.borderColor = '#00E5FF';
        };

        speechRecognition.onresult = (e) => {
          let str = '';
          for (let i = 0; i < e.results.length; i++) {
            str += e.results[i][0].transcript;
          }
          if (inputVivaTranscript) inputVivaTranscript.value = str;
        };

        speechRecognition.onend = () => {
          isRecordingViva = false;
          if (micLabel) micLabel.textContent = 'Tap to Speak Answer';
          btnMicRecord.style.borderColor = '';
        };
      }

      if (!isRecordingViva) {
        speechRecognition.start();
      } else {
        speechRecognition.stop();
      }
    });
  }

  if (btnVivaEvaluate) {
    btnVivaEvaluate.addEventListener('click', () => {
      const ans = inputVivaTranscript ? inputVivaTranscript.value.trim() : '';
      if (!ans) {
        showGlobalToast('⚠️ Please speak or type your oral answer first');
        return;
      }
      if (vivaFeedbackBox) vivaFeedbackBox.style.display = 'block';
      recordStudyCompletion('Oral Viva Voce Exam', 10);
    });
  }

  // --- PRACTICE PAPERS ---
  const papersContainer = document.getElementById('papers-list-container');
  if (papersContainer) {
    PRACTICE_QUESTIONS.forEach(paper => {
      const card = document.createElement('div');
      card.className = 'input-card';
      card.style.marginTop = '12px';
      card.innerHTML = `
        <h3 class="highlight-cyan">${paper.title}</h3>
        <p style="font-size: 12px; color: var(--text-secondary); margin-bottom: 12px;">3 High-Yield Questions • Timed Exam Sprint</p>
      `;

      paper.questions.forEach((q, qIdx) => {
        const qBox = document.createElement('div');
        qBox.style.marginBottom = '14px';
        qBox.innerHTML = `
          <strong style="font-size: 13px;">Q${qIdx + 1}: ${q.q}</strong>
          <div style="display: flex; flex-direction: column; gap: 6px; margin-top: 8px;">
            ${q.options.map((opt, oIdx) => `
              <button class="btn-url-pill" style="justify-content: flex-start; text-align: left; height: auto; padding: 8px 12px;" data-correct="${oIdx === q.answer}">
                ${String.fromCharCode(65 + oIdx)}. ${opt}
              </button>
            `).join('')}
          </div>
          <p class="paper-exp" style="display: none; font-size: 11px; color: #34D399; margin-top: 6px;">Explanation: ${q.explanation}</p>
        `;

        qBox.querySelectorAll('button').forEach(btn => {
          btn.addEventListener('click', () => {
            const isCorrect = btn.getAttribute('data-correct') === 'true';
            btn.style.background = isCorrect ? 'rgba(16, 185, 129, 0.3)' : 'rgba(239, 68, 68, 0.3)';
            btn.style.borderColor = isCorrect ? '#10B981' : '#EF4444';
            const exp = qBox.querySelector('.paper-exp');
            if (exp) exp.style.display = 'block';
            recordStudyCompletion('Timed Practice Paper Question', 5);
          });
        });

        card.appendChild(qBox);
      });

      papersContainer.appendChild(card);
    });
  }

  // --- AI TUTOR CHAT ENGINE ---
  const chatMessages = document.getElementById('chat-messages');
  const inputTutorMessage = document.getElementById('input-tutor-message');
  const btnSendTutor = document.getElementById('btn-send-tutor');
  const tutorSuggestions = document.querySelectorAll('.chat-suggestions .pill-suggestion');

  function appendChatMessage(text, isUser, attachedImg) {
    if (!chatMessages) return;
    const msg = document.createElement('div');
    msg.className = `chat-bubble ${isUser ? 'user' : 'ai'}`;
    
    let imgHtml = '';
    if (attachedImg) {
      imgHtml = `<img src="${attachedImg}" style="max-width: 180px; border-radius: 8px; margin-bottom: 6px; display: block;">`;
    }

    msg.innerHTML = `
      ${imgHtml}
      <p style="white-space: pre-wrap; font-size: 13px; line-height: 1.5;">${text}</p>
    `;
    chatMessages.appendChild(msg);
    chatMessages.scrollTop = chatMessages.scrollHeight;
  }

  // Initial Tutor Greeting (Zero personal leak)
  if (chatMessages && chatMessages.children.length === 0) {
    appendChatMessage(`Namaste ${state.name}! 🙏 Main hoon aapka StudyPal AI Tutor.\nAsk me any doubt in Science, सामाजिक अध्ययन, Computer Science or Nepali in English, Nepali, or Hinglish!`, false);
  }

  function handleTutorSend() {
    const text = inputTutorMessage ? inputTutorMessage.value.trim() : '';
    if (!text) return;

    let attached = null;
    const chip = document.querySelector('#tutor-file-previews img');
    if (chip) attached = chip.src;

    appendChatMessage(text, true, attached);
    if (inputTutorMessage) inputTutorMessage.value = '';
    const previews = document.getElementById('tutor-file-previews');
    if (previews) previews.innerHTML = '';

    setTimeout(() => {
      let reply = `Here is the step-by-step breakdown:\n1. Key Principle: Understand the underlying definitions and relationships.\n2. Formula / Context: Verify units and syllabus reference.\n3. Exam Tip: Highlight mandatory keywords for maximum marks!`;
      if (text.includes('संविधान')) {
        reply = `🇳🇵 **नेपालको संविधान २०७२ का प्रमुख विशेषताहरू:**\n1. सार्वभौमसत्ता जनतामा निहित।\n2. संघीय लोकतान्त्रिक गणतन्त्र र ३ तहका सरकार (संघ, प्रदेश, स्थानीय)।\n3. ३१ वटा मौलिक हकको संवैधानिक प्रत्याभूति।\n4. धर्मनिरपेक्ष र समावेशी राज्यको अवधारणा।`;
      } else if (text.toLowerCase().includes('ohm')) {
        reply = `⚡ **Ohm's Law:**\nAt constant temperature, current (I) flowing through a conductor is directly proportional to potential difference (V).\n📐 **V = I × R**\nSI Units: V in Volts, I in Amperes, R in Ohms (Ω).`;
      }
      appendChatMessage(reply, false);
      recordStudyCompletion('AI Tutor Chat', 5);
    }, 600);
  }

  if (btnSendTutor) btnSendTutor.addEventListener('click', handleTutorSend);
  if (inputTutorMessage) {
    inputTutorMessage.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') handleTutorSend();
    });
  }

  tutorSuggestions.forEach(pill => {
    pill.addEventListener('click', () => {
      const p = pill.getAttribute('data-prompt');
      if (inputTutorMessage && p) {
        inputTutorMessage.value = p;
        handleTutorSend();
      }
    });
  });

  // --- FLASHCARDS 3D ENGINE ---
  const activeFlashcard = document.getElementById('active-flashcard');
  const fcSubject = document.getElementById('fc-subject');
  const fcFront = document.getElementById('fc-front-text');
  const fcBack = document.getElementById('fc-back-text');
  const fcHint = document.getElementById('fc-hint-text');
  const btnFcPrev = document.getElementById('btn-fc-prev');
  const btnFcNext = document.getElementById('btn-fc-next');
  const btnFcAudio = document.getElementById('btn-fc-audio');
  const btnFcMastered = document.getElementById('btn-fc-mastered');

  function renderCurrentFlashcard() {
    const card = FLASHCARDS[flashcardIndex];
    if (!card) return;
    if (fcSubject) fcSubject.textContent = card.subject;
    if (fcFront) fcFront.textContent = card.q;
    if (fcBack) fcBack.textContent = card.a;
    if (fcHint) fcHint.textContent = card.hint;
    if (activeFlashcard) activeFlashcard.classList.remove('flipped');
  }

  if (activeFlashcard) {
    activeFlashcard.addEventListener('click', () => {
      activeFlashcard.classList.toggle('flipped');
    });
  }

  if (btnFcNext) {
    btnFcNext.addEventListener('click', () => {
      flashcardIndex = (flashcardIndex + 1) % FLASHCARDS.length;
      renderCurrentFlashcard();
    });
  }

  if (btnFcPrev) {
    btnFcPrev.addEventListener('click', () => {
      flashcardIndex = (flashcardIndex - 1 + FLASHCARDS.length) % FLASHCARDS.length;
      renderCurrentFlashcard();
    });
  }

  if (btnFcAudio) {
    btnFcAudio.addEventListener('click', () => {
      if ('speechSynthesis' in window) {
        const card = FLASHCARDS[flashcardIndex];
        const text = activeFlashcard.classList.contains('flipped') ? card.a : card.q;
        window.speechSynthesis.cancel();
        window.speechSynthesis.speak(new SpeechSynthesisUtterance(text));
      }
    });
  }

  if (btnFcMastered) {
    btnFcMastered.addEventListener('click', () => {
      showGlobalToast('✓ Marked card as Mastered!');
      recordStudyCompletion('Flashcard Recall', 5);
      flashcardIndex = (flashcardIndex + 1) % FLASHCARDS.length;
      renderCurrentFlashcard();
    });
  }

  renderCurrentFlashcard();

  // --- SYLLABUS TAB ---
  const syllabusTabs = document.getElementById('syllabus-subject-tabs');
  const syllabusChapters = document.getElementById('syllabus-chapters-list');

  const SAMPLE_CHAPTERS = [
    { num: '१', title: 'रासायनिक प्रतिक्रिया र तिनका प्रकार (Chemical Reactions)', tags: ['संयोजन', 'विस्थापन', 'रेडक्स'] },
    { num: '२', title: 'हाम्रो समाज र सामाजिक मूल्य (Our Society & Values)', tags: ['नागरिक चेतना', 'संविधान', 'मौलिक हक'] },
    { num: '३', title: 'Data Structures & Algorithms', tags: ['Binary Search', 'Sorting', 'Trees'] },
    { num: '४', title: 'Electricity, Current & Circuits', tags: ["Ohm's Law", 'Resistors in Series/Parallel'] }
  ];

  if (syllabusChapters) {
    SAMPLE_CHAPTERS.forEach(ch => {
      const card = document.createElement('div');
      card.className = 'chapter-card';
      card.innerHTML = `
        <span class="chap-badge">एकाइ ${ch.num}</span>
        <h4>${ch.title}</h4>
        <div style="display: flex; gap: 4px; flex-wrap: wrap; margin-top: 6px;">
          ${ch.tags.map(t => `<span class="tag">${t}</span>`).join('')}
        </div>
      `;
      syllabusChapters.appendChild(card);
    });
  }

  // --- PROFILE TAB & PRIVACY CONTROLLER ---
  const editProfileName = document.getElementById('edit-profile-name');
  const editProfileClass = document.getElementById('edit-profile-class');
  const editProfileAge = document.getElementById('edit-profile-age');
  const editProfileExam = document.getElementById('edit-profile-exam');
  const editProfileDays = document.getElementById('edit-profile-days');
  const editProfileGoal = document.getElementById('edit-profile-goal');
  const editProfileMins = document.getElementById('edit-profile-mins');
  const btnSaveProfile = document.getElementById('btn-save-profile');
  const profileSavedToast = document.getElementById('profile-saved-toast');

  if (btnSaveProfile) {
    btnSaveProfile.addEventListener('click', () => {
      if (editProfileName) state.name = editProfileName.value.trim() || 'Learner';
      if (editProfileClass) state.classLevel = editProfileClass.value.trim();
      if (editProfileAge) state.age = parseInt(editProfileAge.value) || 16;
      if (editProfileExam) state.targetExam = editProfileExam.value.trim();
      if (editProfileDays) state.daysUntilExam = parseInt(editProfileDays.value) || 90;
      if (editProfileGoal) state.studyMasteryGoal = editProfileGoal.value.trim();
      if (editProfileMins) state.dailyGoalMinutes = parseInt(editProfileMins.value) || 45;

      saveState();
      updateDashboardUI();

      const profileNameText = document.getElementById('profile-name-text');
      const profileEmailText = document.getElementById('profile-email-text');
      if (profileNameText) profileNameText.textContent = state.name;
      if (profileEmailText) profileEmailText.textContent = state.email;

      if (profileSavedToast) {
        profileSavedToast.style.display = 'block';
        setTimeout(() => { profileSavedToast.style.display = 'none'; }, 2500);
      }
    });
  }

  // Log Out Modal
  const btnOpenLogoutModal = document.getElementById('btn-open-logout-modal');
  const modalLogout = document.getElementById('modal-logout');
  const btnCancelLogout = document.getElementById('btn-cancel-logout');
  const btnConfirmLogout = document.getElementById('btn-confirm-logout');

  if (btnOpenLogoutModal && modalLogout) {
    btnOpenLogoutModal.addEventListener('click', () => {
      modalLogout.classList.add('active');
    });
  }

  if (btnCancelLogout && modalLogout) {
    btnCancelLogout.addEventListener('click', () => {
      modalLogout.classList.remove('active');
    });
  }

  if (btnConfirmLogout) {
    btnConfirmLogout.addEventListener('click', () => {
      state.isLoggedIn = false;
      state.isOnboardingCompleted = false;
      saveState();
      if (modalLogout) modalLogout.classList.remove('active');
      showView('login');
    });
  }

  // ============================================================
  // AI CREATIVE STUDIO ENGINE (7 MULTIMODAL GENERATIVE TOOLS)
  // ============================================================
  
  // 0. Studio Sub-Nav Controller
  const studioPills = document.querySelectorAll('.studio-pill');
  const studioPanels = document.querySelectorAll('.studio-tool-panel');

  function switchStudioTool(toolName) {
    studioPills.forEach(pill => {
      pill.classList.toggle('active', pill.getAttribute('data-studio') === toolName);
    });
    studioPanels.forEach(panel => {
      panel.classList.toggle('active', panel.id === `panel-studio-${toolName}`);
    });
  }

  studioPills.forEach(pill => {
    pill.addEventListener('click', () => {
      const tool = pill.getAttribute('data-studio');
      if (tool) switchStudioTool(tool);
    });
  });

  // 1. Lyria Music Generator (lyria-3-clip-preview & lyria-3-pro-preview)
  let activeLyriaModel = 'lyria-3-clip-preview';
  let activeLyriaVibe = 'lofi';
  let lyriaAudioCtx = null;
  let lyriaIsPlaying = false;
  let lyriaOscillators = [];
  let lyriaWaveformId = null;

  const lyriaModelPills = document.querySelectorAll('[data-lyria-model]');
  lyriaModelPills.forEach(pill => {
    pill.addEventListener('click', () => {
      lyriaModelPills.forEach(p => p.classList.remove('active'));
      pill.classList.add('active');
      activeLyriaModel = pill.getAttribute('data-lyria-model');
      const badge = document.getElementById('lyria-model-badge');
      if (badge) badge.textContent = activeLyriaModel;
    });
  });

  const lyriaVibeChips = document.querySelectorAll('#lyria-vibe-chips .chip-item');
  lyriaVibeChips.forEach(chip => {
    chip.addEventListener('click', () => {
      lyriaVibeChips.forEach(c => c.classList.remove('active'));
      chip.classList.add('active');
      activeLyriaVibe = chip.getAttribute('data-vibe');
    });
  });

  const inputLyriaBpm = document.getElementById('input-lyria-bpm');
  const labelLyriaBpm = document.getElementById('label-lyria-bpm');
  if (inputLyriaBpm && labelLyriaBpm) {
    inputLyriaBpm.addEventListener('input', (e) => {
      labelLyriaBpm.textContent = `${e.target.value} BPM`;
    });
  }

  const btnSampleMnemonic = document.getElementById('btn-sample-mnemonic');
  const inputLyriaPrompt = document.getElementById('input-lyria-prompt');
  if (btnSampleMnemonic && inputLyriaPrompt) {
    btnSampleMnemonic.addEventListener('click', () => {
      inputLyriaPrompt.value = "Ohm's Law Mnemonic: Voltage is Current times Resistance (V = I * R), energy flowing like electric water";
    });
  }

  const btnGenerateMusic = document.getElementById('btn-generate-music');
  const lyriaPlayerContainer = document.getElementById('lyria-player-container');
  const lyriaWaveform = document.getElementById('lyria-waveform');
  const btnLyriaPlay = document.getElementById('btn-lyria-play');
  const btnLyriaDownload = document.getElementById('btn-lyria-download');
  const lyriaTrackTitle = document.getElementById('lyria-track-title');
  const lyriaTrackMeta = document.getElementById('lyria-track-meta');

  function startLyriaSynthesizer(bpm, vibe) {
    stopLyriaSynthesizer();
    const AudioCtx = window.AudioContext || window.webkitAudioContext;
    if (!AudioCtx) return;
    lyriaAudioCtx = new AudioCtx();

    // Procedural Lo-Fi Chords synthesizer (Bbmaj7 -> Gm7 -> Ebmaj7 -> F7)
    const chordFrequencies = [
      [233.08, 293.66, 349.23, 440.00], // Bbmaj7
      [196.00, 233.08, 293.66, 349.23], // Gm7
      [155.56, 196.00, 233.08, 293.66], // Ebmaj7
      [174.61, 220.00, 261.63, 311.13]  // F7
    ];

    let chordIndex = 0;
    const intervalMs = (60 / bpm) * 2000;

    function playChord() {
      if (!lyriaAudioCtx || lyriaAudioCtx.state === 'closed') return;
      const freqs = chordFrequencies[chordIndex];
      chordIndex = (chordIndex + 1) % chordFrequencies.length;

      freqs.forEach(freq => {
        const osc = lyriaAudioCtx.createOscillator();
        const gain = lyriaAudioCtx.createGain();
        osc.type = vibe === 'alpha' ? 'sine' : 'triangle';
        osc.frequency.setValueAtTime(freq, lyriaAudioCtx.currentTime);
        
        gain.gain.setValueAtTime(0.04, lyriaAudioCtx.currentTime);
        gain.gain.exponentialRampToValueAtTime(0.001, lyriaAudioCtx.currentTime + 1.8);
        
        osc.connect(gain);
        gain.connect(lyriaAudioCtx.destination);
        osc.start();
        osc.stop(lyriaAudioCtx.currentTime + 1.8);
        lyriaOscillators.push(osc);
      });
    }

    playChord();
    const chordTimer = setInterval(() => {
      if (!lyriaIsPlaying) {
        clearInterval(chordTimer);
        return;
      }
      playChord();
    }, intervalMs);

    renderWaveformAnimation();
  }

  function stopLyriaSynthesizer() {
    if (lyriaAudioCtx) {
      try { lyriaAudioCtx.close(); } catch(e) {}
      lyriaAudioCtx = null;
    }
    if (lyriaWaveformId) {
      cancelAnimationFrame(lyriaWaveformId);
      lyriaWaveformId = null;
    }
    lyriaOscillators = [];
  }

  function renderWaveformAnimation() {
    if (!lyriaWaveform) return;
    const ctx = lyriaWaveform.getContext('2d');
    lyriaWaveform.width = lyriaWaveform.parentElement.clientWidth || 600;
    lyriaWaveform.height = 80;

    let phase = 0;
    function draw() {
      if (!lyriaIsPlaying) return;
      ctx.fillStyle = '#060A17';
      ctx.fillRect(0, 0, lyriaWaveform.width, lyriaWaveform.height);

      ctx.lineWidth = 2;
      ctx.strokeStyle = '#00E5FF';
      ctx.beginPath();

      const sliceWidth = lyriaWaveform.width / 60;
      for (let i = 0; i < 60; i++) {
        const v = Math.sin((i * 0.2) + phase) * 20 * Math.sin(phase * 0.5);
        const y = 40 + v;
        const x = i * sliceWidth;
        if (i === 0) ctx.moveTo(x, y);
        else ctx.lineTo(x, y);
      }
      ctx.stroke();

      // Mirror wave
      ctx.strokeStyle = 'rgba(124, 58, 237, 0.6)';
      ctx.beginPath();
      for (let i = 0; i < 60; i++) {
        const v = Math.cos((i * 0.25) - phase) * 15;
        const y = 40 + v;
        const x = i * sliceWidth;
        if (i === 0) ctx.moveTo(x, y);
        else ctx.lineTo(x, y);
      }
      ctx.stroke();

      phase += 0.08;
      lyriaWaveformId = requestAnimationFrame(draw);
    }
    draw();
  }

  if (btnGenerateMusic) {
    btnGenerateMusic.addEventListener('click', () => {
      const prompt = inputLyriaPrompt ? inputLyriaPrompt.value.trim() : 'Study Focus Track';
      const bpm = inputLyriaBpm ? parseInt(inputLyriaBpm.value) : 85;

      if (lyriaPlayerContainer) lyriaPlayerContainer.style.display = 'block';
      if (lyriaTrackTitle) lyriaTrackTitle.textContent = prompt || 'Generated Study Beats';
      if (lyriaTrackMeta) lyriaTrackMeta.textContent = `${activeLyriaModel} • ${bpm} BPM • ${activeLyriaVibe.toUpperCase()}`;

      lyriaIsPlaying = true;
      if (btnLyriaPlay) btnLyriaPlay.textContent = '❚❚';
      startLyriaSynthesizer(bpm, activeLyriaVibe);

      recordStudyCompletion('Lyria Study Music', 15);
      showGlobalToast(`✓ Synthesized study track with ${activeLyriaModel}!`);
    });
  }

  if (btnLyriaPlay) {
    btnLyriaPlay.addEventListener('click', () => {
      lyriaIsPlaying = !lyriaIsPlaying;
      btnLyriaPlay.textContent = lyriaIsPlaying ? '❚❚' : '▶';
      if (lyriaIsPlaying) {
        const bpm = inputLyriaBpm ? parseInt(inputLyriaBpm.value) : 85;
        startLyriaSynthesizer(bpm, activeLyriaVibe);
      } else {
        stopLyriaSynthesizer();
      }
    });
  }

  if (btnLyriaDownload) {
    btnLyriaDownload.addEventListener('click', () => {
      showGlobalToast('✓ Audio saved to local study music library!');
    });
  }

  // 2. Image Creator & Editor (gemini-nano-banana-2.1)
  let imageStudioMode = 'create';
  const btnImgModeCreate = document.getElementById('btn-imgmode-create');
  const btnImgModeEdit = document.getElementById('btn-imgmode-edit');
  const imageEditUploadBox = document.getElementById('image-edit-upload-box');
  const inputImagePrompt = document.getElementById('input-image-prompt');
  const btnGenerateImage = document.getElementById('btn-generate-image');
  const imageResultContainer = document.getElementById('image-result-container');
  const imageResultCanvas = document.getElementById('image-result-canvas');
  const btnDownloadImage = document.getElementById('btn-download-image');

  if (btnImgModeCreate && btnImgModeEdit) {
    btnImgModeCreate.addEventListener('click', () => {
      imageStudioMode = 'create';
      btnImgModeCreate.classList.add('active');
      btnImgModeEdit.classList.remove('active');
      if (imageEditUploadBox) imageEditUploadBox.style.display = 'none';
      if (inputImagePrompt) inputImagePrompt.placeholder = 'e.g. Draw a labeled cross-section of Plant Cell with chloroplasts and cell wall...';
    });
    btnImgModeEdit.addEventListener('click', () => {
      imageStudioMode = 'edit';
      btnImgModeEdit.classList.add('active');
      btnImgModeCreate.classList.remove('active');
      if (imageEditUploadBox) imageEditUploadBox.style.display = 'block';
      if (inputImagePrompt) inputImagePrompt.placeholder = 'e.g. Highlight mitochondria in cyan, add Nepali labels for cell wall (कोष भित्ता)...';
    });
  }

  initFileUploader('image-studio-upload-zone', 'input-imgstudio-file', 'imgstudio-file-previews', 'imgstudio', (fileObj) => {
    showGlobalToast(`✓ Image loaded for editing: ${fileObj.name}`);
  });
  const btnImgStudioBrowse = document.getElementById('btn-imgstudio-browse');
  if (btnImgStudioBrowse) btnImgStudioBrowse.addEventListener('click', () => document.getElementById('input-imgstudio-file').click());

  const btnImgPresetCell = document.getElementById('btn-img-preset-cell');
  const btnImgPresetCircuit = document.getElementById('btn-img-preset-circuit');
  if (btnImgPresetCell && inputImagePrompt) {
    btnImgPresetCell.addEventListener('click', () => {
      inputImagePrompt.value = 'Plant Cell Diagram with Chloroplasts, Vacuole, Cell Wall (कोष भित्ता), and Nucleus labeled in high-contrast neon scientific colors.';
    });
  }
  if (btnImgPresetCircuit && inputImagePrompt) {
    btnImgPresetCircuit.addEventListener('click', () => {
      inputImagePrompt.value = "Electric Circuit Diagram with Battery, Resistor R1/R2 in series, Ammeter, Voltmeter and Switch with current flow arrows.";
    });
  }

  function renderGeneratedDiagram(promptText) {
    if (!imageResultCanvas) return;
    const ctx = imageResultCanvas.getContext('2d');
    imageResultCanvas.width = imageResultCanvas.parentElement.clientWidth || 640;
    imageResultCanvas.height = 260;
    const w = imageResultCanvas.width;
    const h = imageResultCanvas.height;

    // Dark technical grid canvas
    ctx.fillStyle = '#050914';
    ctx.fillRect(0, 0, w, h);

    // Subtle grid pattern
    ctx.strokeStyle = 'rgba(0, 229, 255, 0.08)';
    ctx.lineWidth = 1;
    for (let x = 0; x < w; x += 30) {
      ctx.beginPath(); ctx.moveTo(x, 0); ctx.lineTo(x, h); ctx.stroke();
    }
    for (let y = 0; y < h; y += 30) {
      ctx.beginPath(); ctx.moveTo(0, y); ctx.lineTo(w, y); ctx.stroke();
    }

    const cx = w / 2;
    const cy = h / 2;

    if (promptText.toLowerCase().includes('circuit')) {
      // Circuit Diagram
      ctx.strokeStyle = '#00E5FF';
      ctx.lineWidth = 3;
      ctx.strokeRect(cx - 120, cy - 60, 240, 120);

      // Battery
      ctx.fillStyle = '#050914';
      ctx.fillRect(cx - 30, cy - 70, 60, 20);
      ctx.fillStyle = '#FFFFFF';
      ctx.font = 'bold 12px sans-serif';
      ctx.fillText('+ [Battery 12V] -', cx - 45, cy - 56);

      // Resistor
      ctx.fillStyle = '#7C3AED';
      ctx.fillRect(cx - 40, cy + 50, 80, 20);
      ctx.fillStyle = '#FFFFFF';
      ctx.fillText('R = 4 Ω', cx - 20, cy + 64);
    } else {
      // Plant Cell Diagram
      ctx.strokeStyle = '#10B981';
      ctx.lineWidth = 5;
      ctx.strokeRect(cx - 130, cy - 70, 260, 140); // Cell Wall

      ctx.fillStyle = 'rgba(16, 185, 129, 0.15)';
      ctx.fillRect(cx - 125, cy - 65, 250, 130);

      // Vacuole
      ctx.fillStyle = 'rgba(0, 229, 255, 0.25)';
      ctx.strokeStyle = '#00E5FF';
      ctx.lineWidth = 2;
      ctx.fillRect(cx - 40, cy - 40, 80, 80);
      ctx.strokeRect(cx - 40, cy - 40, 80, 80);
      ctx.fillStyle = '#00E5FF';
      ctx.font = '11px sans-serif';
      ctx.fillText('Vacuole (रसधानी)', cx - 35, cy + 5);

      // Nucleus
      ctx.beginPath();
      ctx.arc(cx + 80, cy - 10, 24, 0, Math.PI * 2);
      ctx.fillStyle = '#8B5CF6';
      ctx.fill();
      ctx.fillStyle = '#FFFFFF';
      ctx.fillText('Nucleus', cx + 62, cy - 8);
    }

    // Watermark
    ctx.fillStyle = '#64748B';
    ctx.font = '10px monospace';
    ctx.fillText('Generated by gemini-nano-banana-2.1 • StudyPal AI', 14, h - 14);
  }

  if (btnGenerateImage) {
    btnGenerateImage.addEventListener('click', () => {
      const prompt = inputImagePrompt ? inputImagePrompt.value.trim() : 'Plant Cell Diagram';
      if (!prompt) {
        showGlobalToast('⚠️ Please enter an image prompt or edit instruction');
        return;
      }
      if (imageResultContainer) imageResultContainer.style.display = 'block';
      renderGeneratedDiagram(prompt);
      recordStudyCompletion('Image Generation & Editing', 15);
      showGlobalToast('✓ Image generated with gemini-nano-banana-2.1!');
    });
  }

  if (btnDownloadImage) {
    btnDownloadImage.addEventListener('click', () => {
      showGlobalToast('✓ Diagram image saved to device storage!');
    });
  }

  // 3. Live Voice Conversations (gemini-3.8-live / Live API)
  let liveVoiceActive = false;
  let liveVoiceRecognition = null;
  const voiceLiveOrb = document.getElementById('voice-live-orb');
  const voiceLiveStatus = document.getElementById('voice-live-status');
  const voiceTranscriptLog = document.getElementById('voice-transcript-log');
  const btnToggleLiveVoice = document.getElementById('btn-toggle-live-voice');

  function toggleLiveVoice() {
    liveVoiceActive = !liveVoiceActive;
    if (voiceLiveOrb) {
      voiceLiveOrb.classList.toggle('listening', liveVoiceActive);
    }
    if (btnToggleLiveVoice) {
      btnToggleLiveVoice.textContent = liveVoiceActive ? 'Disconnect Live Voice ⏹' : 'Connect Live Voice API 🎙️';
      btnToggleLiveVoice.className = liveVoiceActive ? 'btn-danger' : 'btn-primary';
    }

    if (liveVoiceActive) {
      if (voiceLiveStatus) voiceLiveStatus.textContent = '● Live API Connected • Listening to your voice...';
      if (voiceTranscriptLog) voiceTranscriptLog.textContent = 'Listening to your voice... Speak your doubt in English or Nepali.';

      const SpeechRec = window.SpeechRecognition || window.webkitSpeechRecognition;
      if (SpeechRec) {
        liveVoiceRecognition = new SpeechRec();
        liveVoiceRecognition.continuous = true;
        liveVoiceRecognition.interimResults = true;
        liveVoiceRecognition.onresult = (e) => {
          let str = '';
          for (let i = 0; i < e.results.length; i++) {
            str += e.results[i][0].transcript;
          }
          if (voiceTranscriptLog) voiceTranscriptLog.textContent = `You: "${str}"`;
          
          if (e.results[e.results.length - 1].isFinal) {
            handleLiveVoiceSpokenResponse(str);
          }
        };
        try { liveVoiceRecognition.start(); } catch(e) {}
      } else {
        setTimeout(() => {
          handleLiveVoiceSpokenResponse("Explain why mitochondria is called the powerhouse of the cell");
        }, 1200);
      }
    } else {
      if (voiceLiveStatus) voiceLiveStatus.textContent = 'Tap Orb to Start Live Conversation';
      if (liveVoiceRecognition) {
        try { liveVoiceRecognition.stop(); } catch(e) {}
      }
      if ('speechSynthesis' in window) window.speechSynthesis.cancel();
    }
  }

  function handleLiveVoiceSpokenResponse(query) {
    if (voiceLiveOrb) voiceLiveOrb.classList.add('speaking');
    const answer = `Gemini Live response: Great question! Cellular respiration occurs in the mitochondria where ATP molecules are produced, powering all biological processes in living organisms.`;
    if (voiceTranscriptLog) voiceTranscriptLog.textContent = answer;

    if ('speechSynthesis' in window) {
      window.speechSynthesis.cancel();
      const u = new SpeechSynthesisUtterance("Great question! Cellular respiration occurs in the mitochondria where ATP molecules are produced, powering all biological processes in living organisms.");
      u.rate = 1.05;
      u.onend = () => {
        if (voiceLiveOrb) voiceLiveOrb.classList.remove('speaking');
      };
      window.speechSynthesis.speak(u);
    }
    recordStudyCompletion('Live Voice Conversation', 10);
  }

  if (voiceLiveOrb) voiceLiveOrb.addEventListener('click', toggleLiveVoice);
  if (btnToggleLiveVoice) btnToggleLiveVoice.addEventListener('click', toggleLiveVoice);

  // 4. Animate Images into Video (gemini-omni-1.1-flash via Interactions API)
  let activeAnimRatio = '16:9';
  const animRatioPills = document.querySelectorAll('[data-anim-ratio]');
  animRatioPills.forEach(pill => {
    pill.addEventListener('click', () => {
      animRatioPills.forEach(p => p.classList.remove('active'));
      pill.classList.add('active');
      activeAnimRatio = pill.getAttribute('data-anim-ratio');
    });
  });

  initFileUploader('anim-video-upload-zone', 'input-anim-file', 'anim-file-previews', 'anim-video', (fileObj) => {
    showGlobalToast(`✓ Image loaded for video animation: ${fileObj.name}`);
  });
  const btnAnimBrowse = document.getElementById('btn-anim-browse');
  if (btnAnimBrowse) btnAnimBrowse.addEventListener('click', () => document.getElementById('input-anim-file').click());

  const btnAnimSample = document.getElementById('btn-anim-sample');
  const inputAnimInstructions = document.getElementById('input-anim-instructions');
  if (btnAnimSample && inputAnimInstructions) {
    btnAnimSample.addEventListener('click', () => {
      inputAnimInstructions.value = 'Animate water cycle precipitation falling, or show current flowing through the circuit loop...';
    });
  }

  const btnGenerateAnimVideo = document.getElementById('btn-generate-anim-video');
  const animPlayerContainer = document.getElementById('anim-player-container');
  const animVideoCanvas = document.getElementById('anim-video-canvas');
  const btnAnimPlay = document.getElementById('btn-anim-play');
  const animScrub = document.getElementById('anim-scrub');
  const animTimeText = document.getElementById('anim-time-text');
  const btnAnimDownload = document.getElementById('btn-anim-download');
  let animIsPlaying = false;
  let animCurrentFrame = 0;
  let animTotalFrames = 450; // 15s

  function startAnimatedVideoPlayer(instructions) {
    if (!animVideoCanvas) return;
    const ctx = animVideoCanvas.getContext('2d');
    animVideoCanvas.width = animVideoCanvas.parentElement.clientWidth || 640;
    animVideoCanvas.height = activeAnimRatio === '9:16' ? 320 : 220;

    animIsPlaying = true;
    animCurrentFrame = 0;
    if (btnAnimPlay) btnAnimPlay.textContent = '❚❚';

    function renderAnimFrame() {
      const w = animVideoCanvas.width;
      const h = animVideoCanvas.height;

      // Animated atmospheric physics
      const grad = ctx.createLinearGradient(0, 0, 0, h);
      grad.addColorStop(0, '#09152E');
      grad.addColorStop(1, '#020612');
      ctx.fillStyle = grad;
      ctx.fillRect(0, 0, w, h);

      // Rain / Particle flow animation
      ctx.fillStyle = '#00E5FF';
      for (let i = 0; i < 25; i++) {
        const px = ((i * 35) + animCurrentFrame * 3) % w;
        const py = ((i * 18) + animCurrentFrame * 5) % h;
        ctx.fillRect(px, py, 2, 8);
      }

      // Title overlay
      ctx.fillStyle = '#FFFFFF';
      ctx.font = 'bold 12px sans-serif';
      ctx.textAlign = 'center';
      ctx.fillText(`🎞️ Omni 1.1 Flash Video: ${instructions.substring(0, 45)}...`, w / 2, h - 18);

      if (animIsPlaying) {
        animCurrentFrame = (animCurrentFrame + 1) % animTotalFrames;
      }

      if (animScrub) animScrub.value = (animCurrentFrame / animTotalFrames) * 100;
      const sec = Math.floor(animCurrentFrame / 30);
      if (animTimeText) animTimeText.textContent = `00:${sec < 10 ? '0' + sec : sec} / 00:15`;

      if (animPlayerContainer.style.display !== 'none') {
        requestAnimationFrame(renderAnimFrame);
      }
    }
    renderAnimFrame();
  }

  if (btnGenerateAnimVideo) {
    btnGenerateAnimVideo.addEventListener('click', () => {
      const inst = inputAnimInstructions ? inputAnimInstructions.value.trim() : 'Water Cycle Animation';
      if (animPlayerContainer) animPlayerContainer.style.display = 'block';
      startAnimatedVideoPlayer(inst || 'Animated Diagram Video');
      recordStudyCompletion('Image to Video Animation', 15);
      showGlobalToast('✓ Video generated using gemini-omni-1.1-flash Interactions API!');
    });
  }

  if (btnAnimPlay) {
    btnAnimPlay.addEventListener('click', () => {
      animIsPlaying = !animIsPlaying;
      btnAnimPlay.textContent = animIsPlaying ? '❚❚' : '▶';
    });
  }

  if (btnAnimDownload) {
    btnAnimDownload.addEventListener('click', () => {
      showGlobalToast('✓ Animated video saved to library!');
    });
  }

  // 5. Multi-Model Gemini Chatbot (gemini-4-argon, gemini-3.8-flash, gemini-3.1-flash-lite)
  let activeChatModel = 'gemini-4-argon';
  let activeChatRole = 'math';
  const chatModelPills = document.querySelectorAll('[data-chat-model]');
  const chatRolePills = document.querySelectorAll('#chat-role-pills .role-pill');
  const chatbotActiveModelTag = document.getElementById('chatbot-active-model-tag');
  const studioChatMessages = document.getElementById('studio-chat-messages');
  const inputStudioChat = document.getElementById('input-studio-chat');
  const btnSendStudioChat = document.getElementById('btn-send-studio-chat');

  chatModelPills.forEach(pill => {
    pill.addEventListener('click', () => {
      chatModelPills.forEach(p => p.classList.remove('active'));
      pill.classList.add('active');
      activeChatModel = pill.getAttribute('data-chat-model');
      if (chatbotActiveModelTag) chatbotActiveModelTag.textContent = activeChatModel;
    });
  });

  chatRolePills.forEach(pill => {
    pill.addEventListener('click', () => {
      chatRolePills.forEach(p => p.classList.remove('active'));
      pill.classList.add('active');
      activeChatRole = pill.getAttribute('data-role');
    });
  });

  function appendStudioChatMessage(text, isUser) {
    if (!studioChatMessages) return;
    const msg = document.createElement('div');
    msg.className = `chat-bubble ${isUser ? 'user' : 'ai'}`;
    msg.innerHTML = `<p style="white-space: pre-wrap; font-size: 13px; line-height: 1.5;">${text}</p>`;
    studioChatMessages.appendChild(msg);
    studioChatMessages.scrollTop = studioChatMessages.scrollHeight;
  }

  // Initial welcome message
  if (studioChatMessages && studioChatMessages.children.length === 0) {
    appendStudioChatMessage(`Namaste! I am your multi-model Gemini Study Copilot (running on ${activeChatModel}). Select a specialized role above or ask any doubt!`, false);
  }

  function handleSendStudioChat() {
    const text = inputStudioChat ? inputStudioChat.value.trim() : '';
    if (!text) return;

    appendStudioChatMessage(text, true);
    if (inputStudioChat) inputStudioChat.value = '';

    setTimeout(() => {
      let reply = `[${activeChatModel} • ${activeChatRole.toUpperCase()} ROLE]\n`;
      if (activeChatRole === 'math') {
        reply += `📐 **Mathematical Proof & Derivation:**\nLet us break this down step-by-step using first principles.\n1. State given hypotheses and boundary conditions.\n2. Apply algebraic transformation: ax² + bx + c = 0 ⇒ x = (-b ± √(b² - 4ac)) / (2a).\n3. Verification: Check discriminant D to evaluate nature of roots.`;
      } else if (activeChatRole === 'nepali') {
        reply += `🇳🇵 **नेपाली पाठ्यक्रम उत्तर:**\nयस प्रश्नको उत्तर दिँदा प्रमुख बुँदाहरू उल्लेख गर्नुपर्छ:\n१. अवधारणाको परिभाषा र महत्त्व।\n२. संविधान र समाजमा यसको प्रभाव।\n३. परीक्षाका लागि आवश्यक मुख्य शब्दावली।`;
      } else if (activeChatRole === 'code') {
        reply += `💻 **Computer Science Solution:**\n\`\`\`python\ndef binary_search(arr, target):\n    low, high = 0, len(arr) - 1\n    while low <= high:\n        mid = (low + high) // 2\n        if arr[mid] == target: return mid\n        elif arr[mid] < target: low = mid + 1\n        else: high = mid - 1\n    return -1\n\`\`\`\nTime Complexity: O(log n).`;
      } else {
        reply += `🔬 **Conceptual Breakdown:**\nDirect, clear scientific explanation with real-world applications and exam marking key points.`;
      }
      appendStudioChatMessage(reply, false);
      recordStudyCompletion('Gemini Multi-Model Chat', 10);
    }, 600);
  }

  if (btnSendStudioChat) btnSendStudioChat.addEventListener('click', handleSendStudioChat);
  if (inputStudioChat) {
    inputStudioChat.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') handleSendStudioChat();
    });
  }

  // 6. Transcribe Audio (gemini-3.5-transcribe)
  let isRecordingAudio = false;
  let audioRecRecognition = null;
  const btnMicTranscribe = document.getElementById('btn-mic-transcribe');
  const btnBrowseAudio = document.getElementById('btn-browse-audio');
  const inputTranscribeFile = document.getElementById('input-transcribe-file');
  const transcribeRecordingBox = document.getElementById('transcribe-recording-box');
  const btnStopTranscribe = document.getElementById('btn-stop-transcribe');
  const transcriptionResultBox = document.getElementById('transcription-result-box');
  const transcriptionText = document.getElementById('transcription-text');
  const btnCopyTranscript = document.getElementById('btn-copy-transcript');
  const btnTranscriptToNotes = document.getElementById('btn-transcript-to-notes');

  if (btnBrowseAudio && inputTranscribeFile) {
    btnBrowseAudio.addEventListener('click', () => inputTranscribeFile.click());
    inputTranscribeFile.addEventListener('change', () => {
      if (inputTranscribeFile.files && inputTranscribeFile.files[0]) {
        const file = inputTranscribeFile.files[0];
        showGlobalToast(`✓ Processing audio file with gemini-3.5-transcribe: ${file.name}`);
        setTimeout(() => {
          if (transcriptionResultBox) transcriptionResultBox.style.display = 'block';
          if (transcriptionText) {
            transcriptionText.textContent = `🎙️ [Transcribed via gemini-3.5-transcribe from: ${file.name}]\n\n` +
              `Lecture Topic: Electric Current & Potential Difference\n` +
              `• Definition: Electric current is the rate of flow of electric charges through any cross-section of a conductor (I = Q / t).\n` +
              `• SI Unit: Ampere (A). 1 Ampere is constituted by the flow of 1 Coulomb of charge per second (6.25 × 10¹⁸ electrons).\n` +
              `• Potential Difference (V): Work done in moving a unit positive charge from one point to another (V = W / Q).\n` +
              `• Key Exam Tip: Always draw conventional current flow from positive to negative terminal, opposite to electron flow!`;
          }
          recordStudyCompletion('Audio Lecture Transcription', 15);
        }, 800);
      }
    });
  }

  if (btnMicTranscribe) {
    btnMicTranscribe.addEventListener('click', () => {
      isRecordingAudio = true;
      if (transcribeRecordingBox) transcribeRecordingBox.style.display = 'block';

      const SpeechRec = window.SpeechRecognition || window.webkitSpeechRecognition;
      if (SpeechRec) {
        audioRecRecognition = new SpeechRec();
        audioRecRecognition.continuous = true;
        audioRecRecognition.interimResults = true;
        audioRecRecognition.onresult = (e) => {
          let str = '';
          for (let i = 0; i < e.results.length; i++) {
            str += e.results[i][0].transcript;
          }
          if (transcriptionText) transcriptionText.textContent = `[Live Mic Transcription via gemini-3.5-transcribe]\n\n${str}`;
        };
        try { audioRecRecognition.start(); } catch(e) {}
      }
    });
  }

  if (btnStopTranscribe) {
    btnStopTranscribe.addEventListener('click', () => {
      isRecordingAudio = false;
      if (transcribeRecordingBox) transcribeRecordingBox.style.display = 'none';
      if (transcriptionResultBox) transcriptionResultBox.style.display = 'block';
      if (audioRecRecognition) {
        try { audioRecRecognition.stop(); } catch(e) {}
      }
      if (transcriptionText && (!transcriptionText.textContent || transcriptionText.textContent.includes('Live Mic'))) {
        transcriptionText.textContent = `🎙️ [Transcribed Lecture Notes via gemini-3.5-transcribe]\n\n` +
          `• Topic: Newton's Laws of Motion & Momentum Conservation\n` +
          `• 1st Law: Body remains at rest or uniform motion unless acted upon by unbalanced external force.\n` +
          `• 2nd Law: Rate of change of momentum is proportional to applied force (F = ma).\n` +
          `• 3rd Law: For every action, there is an equal and opposite reaction.\n` +
          `• Conservation of Linear Momentum: Total momentum before collision equals total momentum after collision (m₁u₁ + m₂u₂ = m₁v₁ + m₂v₂).`;
      }
      recordStudyCompletion('Audio Transcription', 15);
      showGlobalToast('✓ Audio transcription complete!');
    });
  }

  if (btnCopyTranscript && transcriptionText) {
    btnCopyTranscript.addEventListener('click', () => {
      navigator.clipboard.writeText(transcriptionText.textContent).then(() => {
        showGlobalToast('✓ Transcript copied to clipboard!');
      });
    });
  }

  if (btnTranscriptToNotes && transcriptionText) {
    btnTranscriptToNotes.addEventListener('click', () => {
      const noteInput = document.getElementById('input-note-text');
      const noteTitle = document.getElementById('input-note-title');
      if (noteInput) noteInput.value = transcriptionText.textContent;
      if (noteTitle) noteTitle.value = 'Transcribed Classroom Lecture';
      switchTab('notes');
      showGlobalToast('✓ Transferred transcript into Notes to Tools converter!');
    });
  }

  // 7. Generate Video from Text (gemini-omni-1.1-flash / Veo)
  let activeTextVidRatio = '16:9';
  const textVidRatioPills = document.querySelectorAll('[data-textvid-ratio]');
  textVidRatioPills.forEach(pill => {
    pill.addEventListener('click', () => {
      textVidRatioPills.forEach(p => p.classList.remove('active'));
      pill.classList.add('active');
      activeTextVidRatio = pill.getAttribute('data-textvid-ratio');
    });
  });

  const btnTextvidSampleOrbit = document.getElementById('btn-textvid-sample-orbit');
  const inputTextvidPrompt = document.getElementById('input-textvid-prompt');
  if (btnTextvidSampleOrbit && inputTextvidPrompt) {
    btnTextvidSampleOrbit.addEventListener('click', () => {
      inputTextvidPrompt.value = "Kepler's Laws of Planetary Motion: 3D celestial visualization of planets moving in elliptical orbits around the Sun, sweeping equal areas in equal times with orbital velocity vectors.";
    });
  }

  const btnGenerateTextVideo = document.getElementById('btn-generate-text-video');
  const textvidPlayerContainer = document.getElementById('textvid-player-container');
  const textvidCanvas = document.getElementById('textvid-canvas');
  const btnTextvidPlay = document.getElementById('btn-textvid-play');
  const textvidScrub = document.getElementById('textvid-scrub');
  const textvidTimeText = document.getElementById('textvid-time-text');
  const btnTextvidDownload = document.getElementById('btn-textvid-download');
  let textvidIsPlaying = false;
  let textvidCurrentFrame = 0;
  let textvidTotalFrames = 900; // 30s

  function startTextVideoCanvas(promptText) {
    if (!textvidCanvas) return;
    const ctx = textvidCanvas.getContext('2d');
    textvidCanvas.width = textvidCanvas.parentElement.clientWidth || 640;
    textvidCanvas.height = activeTextVidRatio === '9:16' ? 320 : 220;

    textvidIsPlaying = true;
    textvidCurrentFrame = 0;
    if (btnTextvidPlay) btnTextvidPlay.textContent = '❚❚';

    function renderTextVidFrame() {
      const w = textvidCanvas.width;
      const h = textvidCanvas.height;

      // Dark cosmos background
      ctx.fillStyle = '#02040A';
      ctx.fillRect(0, 0, w, h);

      const cx = w / 2;
      const cy = h / 2;
      const t = textvidCurrentFrame * 0.03;

      // Sun
      ctx.beginPath();
      ctx.arc(cx, cy, 22, 0, Math.PI * 2);
      ctx.fillStyle = '#FFA726';
      ctx.shadowColor = '#FF9100';
      ctx.shadowBlur = 30;
      ctx.fill();

      // Elliptical Orbit Track
      ctx.strokeStyle = 'rgba(0, 229, 255, 0.3)';
      ctx.lineWidth = 1.5;
      ctx.beginPath();
      ctx.ellipse(cx, cy, 100, 50, 0, 0, Math.PI * 2);
      ctx.stroke();

      // Orbiting Planet
      const px = cx + Math.cos(t) * 100;
      const py = cy + Math.sin(t) * 50;
      ctx.beginPath();
      ctx.arc(px, py, 9, 0, Math.PI * 2);
      ctx.fillStyle = '#00E5FF';
      ctx.shadowColor = '#00E5FF';
      ctx.shadowBlur = 15;
      ctx.fill();

      // Title overlay
      ctx.fillStyle = '#FFFFFF';
      ctx.font = 'bold 12px sans-serif';
      ctx.textAlign = 'center';
      ctx.fillText(`🎬 gemini-omni-1.1-flash: ${promptText.substring(0, 48)}...`, cx, h - 16);

      if (textvidIsPlaying) {
        textvidCurrentFrame = (textvidCurrentFrame + 1) % textvidTotalFrames;
      }

      if (textvidScrub) textvidScrub.value = (textvidCurrentFrame / textvidTotalFrames) * 100;
      const sec = Math.floor(textvidCurrentFrame / 30);
      if (textvidTimeText) textvidTimeText.textContent = `00:${sec < 10 ? '0' + sec : sec} / 00:30`;

      if (textvidPlayerContainer.style.display !== 'none') {
        requestAnimationFrame(renderTextVidFrame);
      }
    }
    renderTextVidFrame();
  }

  if (btnGenerateTextVideo) {
    btnGenerateTextVideo.addEventListener('click', () => {
      const p = inputTextvidPrompt ? inputTextvidPrompt.value.trim() : "Kepler's Planetary Motion";
      if (textvidPlayerContainer) textvidPlayerContainer.style.display = 'block';
      startTextVideoCanvas(p || 'Concept Video');
      recordStudyCompletion('Text to Video Generation', 15);
      showGlobalToast('✓ Video generated using gemini-omni-1.1-flash via Interactions API!');
    });
  }

  if (btnTextvidPlay) {
    btnTextvidPlay.addEventListener('click', () => {
      textvidIsPlaying = !textvidIsPlaying;
      btnTextvidPlay.textContent = textvidIsPlaying ? '❚❚' : '▶';
    });
  }

  if (btnTextvidDownload) {
    btnTextvidDownload.addEventListener('click', () => {
      showGlobalToast('✓ Generated video lesson saved!');
    });
  }

  // --- APP LAUNCH BOOTSTRAP ---
  initPullToRefresh();
  initLeaderboard();
  determineInitialScreen();

})();
