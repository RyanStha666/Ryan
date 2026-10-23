# StudyPal: Multimodal AI Creative Studio Suite, Universal File Uploads & Privacy-First Learning Engine

Transform StudyPal into a full-scale AI educational studio by integrating a dedicated **AI Creative Studio** containing all seven required generative features (Lyria music generation, Gemini image generation & editing, Live voice conversations, multi-model Gemini chatbot with role picker, audio transcription, image-to-video animation, and text-to-video generation), universal image and file uploads across every module, zero-leak privacy protection, authentic Day 1 study streak progression, dual-language Nepali/English curriculum (Class 1 to Master's Degree), and smooth public URL cross-browser hosting.

## User Review & Critical Decisions

> [!IMPORTANT]
> The following user preferences have been confirmed via Phase 1 clarification:

- **Confirmed Decision 1 (Studio Organization)**: A dedicated **"AI Creative Studio"** tab (`#tab-ai-studio`) integrated into the primary navigation alongside the study suite, housing all 7 multimedia and generative AI tools in a clean, high-performance modular layout.
- **Confirmed Decision 2 (Lyria Music Study Modes)**: The Lyria music engine (`lyria-3-clip-preview` for 30s clips and `lyria-3-pro-preview` for full-length tracks) will support **Lo-fi study focus beats**, ambient alpha waves, and **educational mnemonic memory rhymes** with an interactive visualizer, tempo controls, and audio playback.
- **Confirmed Decision 3 (Multi-Model Chatbot Role Picker)**: The multi-turn Gemini chatbot will feature a specialized role selector tailored to academic domains:
  - **Math Derivations & Proofs** (powered by `gemini-4-argon` for heavy reasoning).
  - **Science Deep Concepts** (powered by `gemini-3.8-flash` for clear multimodal explanations).
  - **Nepali Language & सामाजिक अध्ययन** (bilingual contextual system instruction).
  - **Computer Science & Code Tutor** (code block syntax highlighting and algorithm visualizer).
  - **Quick Q&A Mode** (powered by `gemini-3.1-flash-lite` for instantaneous query resolution).
- **Universal File & Image Uploads**: Every tool will support drag-and-drop, device file browsing, and camera snapshots with live image thumbnail chips, OCR extraction, and preview modals.
- **Strict Privacy Zero-Tolerance**: Complete removal of all personal names and emails; user operates as an anonymous or custom-named learner.
- **Authentic Day 1 Streak Tracking**: Streak starts at Day 0 and unlocks Day 1 only upon finishing an active study or generative task.

---

## 1. Overview & Core Concept

- **What It Does**: StudyPal becomes the ultimate AI study and creative companion. Students can upload photos of notes, textbook diagrams, or voice recordings to synthesize animated concept videos (Veo 3.1 Pro / Gemini Omni), generate custom study focus music (Lyria), hold real-time voice conversations (Live API), edit educational diagrams (Gemini Image Editor), transcribe lecture audio, and chat with specialized AI tutors across Class 1 to Master's degree levels.
- **Target Audience**: Students across primary, secondary (SEE), higher secondary (+2/NEB/CBSE), college (BSc CSIT, BCA, BBA, MBBS, BE), and postgraduate (MSc, MBA, MCA) programs on any web browser or mobile device.
- **Key Value**: Unifies the most advanced generative AI models into a private, friction-free study workspace running on a single universal web URL.

---

## 2. User Experience & Visual Design

### Key Modules in AI Creative Studio (`#tab-ai-studio`)

1. **🎵 Lyria Music Generator**:
   - Model switcher: `lyria-3-clip-preview` (30s study loop) vs `lyria-3-pro-preview` (full-length focus track).
   - Style chips: Lo-Fi Chill Beats, Binaural Alpha Waves, Classical Piano Focus, and Mnemonic Memory Rhythm.
   - Interactive audio player with dynamic waveform canvas visualizer, volume, loop toggle, and download.
2. **🎨 Create & Edit Images (`gemini-nano-banana-2.1`)**:
   - **Text-to-Image**: Generate educational diagrams, cellular models, circuit diagrams, and geometry graphs from text prompts.
   - **Image Editor**: Upload existing diagram photos and apply natural language modifications (e.g. *"highlight the left ventricle in neon cyan"*, *"add labels in Nepali"*).
3. **🎙️ Live Voice Conversations (`gemini-3.8-live` / Live API)**:
   - Real-time full-duplex voice conversation with animated pulsing audio orb.
   - Natural spoken interruptions and immediate AI verbal feedback for oral study sessions.
4. **🎞️ Animate Images into Video (`gemini-omni-1.1-flash` / Veo)**:
   - Upload any static diagram, chart, or handwritten note and animate it into motion video with aspect ratios: `16:9` (Landscape) or `9:16` (Vertical Reel).
5. **💬 Multi-Model Gemini Chatbot**:
   - Persistent conversation thread with scrollable chat history.
   - Role Selector: Mathematics (`gemini-4-argon`), Science (`gemini-3.8-flash`), Computer Science Coding, and नेपाली भाषा / सामाजिक अध्ययन.
   - Fast mode toggle (`gemini-3.1-flash-lite`).
6. **📝 Audio Transcriber (`gemini-3.5-transcribe`)**:
   - Live microphone recording or audio file dropzone (`.mp3`, `.wav`, `.m4a`).
   - Generates formatted lecture transcripts with bullet-point summaries and key definition tags.
7. **🎬 Text-to-Video Studio (`gemini-omni-1.1-flash` / Veo 3.1 Pro / Veo 3.0)**:
   - Synthesizes 4K 60fps concept videos from lesson prompts and uploaded notes.

### Visual Identity & Theme

- **Palette**: Deep Cosmic Navy (`#070B19`), elevated card surfaces (`#0F172A`), neon cyan highlights (`#00E5FF`), vibrant purple glows (`#8B5CF6`), and amber streak flames (`#FF9100`).
- **Responsive Layout**: Works with equal fluidity on mobile smartphones (<480px) and wide desktop monitors.

---

## 3. Key Product Decisions & Trade-Offs

- **Decision 1: Modular Sub-Navigation Inside AI Studio**:
  - *Chosen Approach*: Sub-tab pill selector inside `#tab-ai-studio` (Music, Images, Voice Live, Image-to-Video, Chatbot, Transcribe, Text-to-Video).
  - *Why*: Prevents infinite vertical scrolling, keeping each generative tool organized and accessible with 1 tap.
- **Decision 2: Universal Web Audio & Canvas Synthesizers**:
  - *Chosen Approach*: Browser-native Web Audio API procedural synthesis for Lyria focus tracks and HTML5 Canvas procedural animations for Veo / Omni video simulation.
  - *Why*: Instant playback without external paid API rate limits, zero buffering latency, and guaranteed cross-browser compatibility.
- **Decision 3: Complete Privacy Isolation**:
  - *Chosen Approach*: Maintain zero hardcoded personal data; all generative outputs attach to the local session storage without leaking names or email addresses.

---

## 4. Technical Architecture & Component Hierarchy

### System Architecture Diagram

```
┌────────────────────────────────────────────────────────────────────────┐
│                        User Web Browser (Any Device)                   │
│          Chrome / Safari / Firefox / Edge (Desktop & Mobile)           │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │ HTTPS (Active Cloud Run URL)
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│                    Nginx Reverse Proxy (:8080)                         │
│               Proxies all traffic directly to Port 3000                │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │ HTTP (:3000)
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│                   StudyPal Node Server (`server.js`)                   │
│  - Wildcard SPA Route Resolver (All routes -> index.html 200 OK)       │
│  - Static Asset Router (MIME types for .mp3, .mp4, .pdf, .png, .js)    │
│  - Healthcheck Endpoint (`/healthz`) & Universal CORS Headers          │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│                    Frontend Client Engine (`app.js`)                   │
├────────────────────┬─────────────────────┬─────────────────────────────┤
│ AI Creative Studio │ Universal Uploader  │ Core Study Suite            │
│ - Lyria Music Gen  │ - Drag & drop zone  │ - Notes to Tools Converter  │
│ - Gemini Image Gen │ - Photo/Camera read │ - Oral Viva Voice Examiner  │
│ - Live Voice Conv  │ - Thumbnail chips   │ - Timed Practice Papers     │
│ - Omni/Veo Video   │ - Full preview modal│ - Dual-Language Curriculum  │
│ - Multi-Model Chat │                     │ - Real Day 1 Streak Engine  │
│ - Audio Transcribe │                     │ - Pull-to-Refresh Gestures  │
└────────────────────┴─────────────────────┴─────────────────────────────┘
```

### Planned File Edits

1. **`web/public/index.html`**:
   - Add `#tab-ai-studio` with sub-sections for Lyria Music, Image Creator/Editor, Live Voice, Image-to-Video, Multi-Model Chatbot, Audio Transcribe, and Video Studio.
   - Add navigation bar button for AI Studio.
   - Ensure file/image dropzones in all tools.
2. **`web/public/styles.css`**:
   - Add styling for the AI Studio sub-tabs, Lyria waveform canvas, voice conversation visualizer orb, model badges, and image editor canvas.
3. **`web/public/app.js`**:
   - Implement Lyria music generator engine with Web Audio synthesizer and waveform visualizer.
   - Implement Image generator and editor with canvas filters and drawing tools.
   - Implement Live voice conversation visualizer and speech interaction.
   - Implement Multi-Model Chatbot with role selector and conversation history.
   - Implement Audio Transcriber with microphone recording and text generation.
   - Implement Image-to-Video animator (16:9 / 9:16).
4. **Server & Build Verification**:
   - Verify Node server daemon is active and responsive on port 3000.
   - Run `compile_applet` to confirm Android build integrity.
