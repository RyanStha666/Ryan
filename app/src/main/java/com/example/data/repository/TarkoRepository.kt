package com.example.data.repository

import com.example.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TarkoRepository {

    private val _userProfile = MutableStateFlow(
        UserProfile(
            isLoggedIn = false,
            email = "learner@studypal.internal",
            name = "Learner",
            classLevel = "Class 10 CBSE",
            age = 16,
            subjects = listOf("Mathematics", "Science", "Social Science", "English"),
            isExamComing = true,
            targetExam = "CBSE 2027 Board Examination",
            daysUntilExam = 124,
            studyMasteryGoal = "95%+ Board Ranker Mastery",
            dailyGoalMinutes = 45,
            preferredLanguage = "Hinglish",
            avatarInitials = "SP",
            isOnboardingCompleted = false
        )
    )
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _streakData = MutableStateFlow(
        StreakData(
            currentStreak = 7,
            longestStreak = 14,
            shieldsRemaining = 2,
            dailyGoalMinutes = 45,
            todayMinutesStudied = 24
        )
    )
    val streakData: StateFlow<StreakData> = _streakData.asStateFlow()

    private val _notes = MutableStateFlow<List<NoteDocument>>(emptyList())
    val notes: StateFlow<List<NoteDocument>> = _notes.asStateFlow()

    private val _tutorMessages = MutableStateFlow<List<TutorMessage>>(emptyList())
    val tutorMessages: StateFlow<List<TutorMessage>> = _tutorMessages.asStateFlow()

    init {
        loadSampleNotes()
        loadInitialTutorChat()
    }

    fun loginWithGoogle(email: String, name: String) {
        val initials = name.split(" ")
            .mapNotNull { it.firstOrNull()?.toString() }
            .take(2)
            .joinToString("")
            .uppercase()
            .ifEmpty { "SP" }

        _userProfile.update {
            it.copy(
                isLoggedIn = true,
                email = email.ifBlank { "learner@studypal.internal" },
                name = name.ifBlank { "Learner" },
                avatarInitials = initials
            )
        }
    }

    fun logout() {
        _userProfile.update {
            it.copy(
                isLoggedIn = false
            )
        }
    }

    fun completeOnboarding(
        name: String,
        classLevel: String,
        age: Int,
        subjects: List<String>,
        isExamComing: Boolean,
        targetExam: String,
        daysUntilExam: Int,
        studyMasteryGoal: String,
        dailyGoalMinutes: Int
    ) {
        val initials = name.split(" ")
            .mapNotNull { it.firstOrNull()?.toString() }
            .take(2)
            .joinToString("")
            .uppercase()
            .ifEmpty { "SP" }

        _userProfile.update {
            it.copy(
                name = name,
                classLevel = classLevel,
                age = age,
                subjects = subjects,
                isExamComing = isExamComing,
                targetExam = if (isExamComing) targetExam else "Regular Academic Curriculum",
                daysUntilExam = if (isExamComing) daysUntilExam else 0,
                studyMasteryGoal = studyMasteryGoal,
                dailyGoalMinutes = dailyGoalMinutes,
                avatarInitials = initials,
                isOnboardingCompleted = true
            )
        }
        _streakData.update {
            it.copy(dailyGoalMinutes = dailyGoalMinutes)
        }
    }

    fun updateProfile(
        name: String,
        classLevel: String,
        age: Int,
        subjects: List<String>,
        isExamComing: Boolean,
        targetExam: String,
        daysUntilExam: Int,
        studyMasteryGoal: String,
        dailyGoalMinutes: Int,
        preferredLanguage: String
    ) {
        val initials = name.split(" ")
            .mapNotNull { it.firstOrNull()?.toString() }
            .take(2)
            .joinToString("")
            .uppercase()
            .ifEmpty { "SP" }

        _userProfile.update {
            it.copy(
                name = name,
                classLevel = classLevel,
                age = age,
                subjects = subjects,
                isExamComing = isExamComing,
                targetExam = targetExam,
                daysUntilExam = daysUntilExam,
                studyMasteryGoal = studyMasteryGoal,
                dailyGoalMinutes = dailyGoalMinutes,
                preferredLanguage = preferredLanguage,
                avatarInitials = initials
            )
        }
        _streakData.update {
            it.copy(dailyGoalMinutes = dailyGoalMinutes)
        }
    }

    fun logStudyTime(minutes: Int) {
        _streakData.update { current ->
            val updatedToday = current.todayMinutesStudied + minutes
            val wasGoalReached = current.todayMinutesStudied >= current.dailyGoalMinutes
            val isGoalReachedNow = updatedToday >= current.dailyGoalMinutes
            val newStreak = if (!wasGoalReached && isGoalReachedNow) current.currentStreak + 1 else current.currentStreak
            val newLongest = maxOf(newStreak, current.longestStreak)

            val updatedWeekly = current.weeklyHistory.toMutableList()
            if (updatedWeekly.isNotEmpty()) {
                val lastIndex = updatedWeekly.size - 1
                updatedWeekly[lastIndex] = updatedWeekly[lastIndex].copy(
                    completed = isGoalReachedNow,
                    minutesStudied = updatedToday
                )
            }

            current.copy(
                todayMinutesStudied = updatedToday,
                currentStreak = newStreak,
                longestStreak = newLongest,
                weeklyHistory = updatedWeekly
            )
        }
    }

    fun useStreakShield(): Boolean {
        if (_streakData.value.shieldsRemaining > 0) {
            _streakData.update { it.copy(shieldsRemaining = it.shieldsRemaining - 1) }
            return true
        }
        return false
    }

    fun addStreakShield() {
        _streakData.update { it.copy(shieldsRemaining = it.shieldsRemaining + 1) }
    }

    // Convert notes into structured learning pack
    fun convertNotes(title: String, rawText: String): NoteDocument {
        val cleanTitle = if (title.isBlank()) "Revision Notes" else title

        val summaryPoints = listOf(
            "Core Concept: Foundational principles extracted from your study notes.",
            "Key Mechanisms: How reactions, formulas, and laws operate under standard conditions.",
            "Board Exam Importance: High frequency topic with regular 3-mark & 5-mark CBSE question appearances.",
            "Common Misconceptions: Pitfalls students often make in sign conventions and units."
        )

        val formulas = listOf(
            "Law of Conservation of Mass: Total Mass (Reactants) = Total Mass (Products)",
            "Molar Concentration: C = n / V (mol/L)",
            "Ohm's Law: V = I * R",
            "Snell's Law: n₁ sin(θ₁) = n₂ sin(θ₂)"
        )

        val mindMap = listOf(
            MindMapNode(
                concept = cleanTitle,
                subpoints = listOf("Definitions & Terminology", "Governing Formulas", "Experimental Verification", "CBSE Past Year Trends")
            ),
            MindMapNode(
                concept = "Applications & Real-life Examples",
                subpoints = listOf("Industrial use", "Daily life observation", "Numerical problem patterns")
            )
        )

        val flashcards = listOf(
            Flashcard(
                id = "fc_${System.currentTimeMillis()}_1",
                front = "What is the defining characteristic of $cleanTitle?",
                back = "The underlying scientific law establishes predictable relationships between state variables.",
                hint = "Focus on the primary governing equation",
                subject = "Science"
            ),
            Flashcard(
                id = "fc_${System.currentTimeMillis()}_2",
                front = "What units and standard conventions apply to this concept?",
                back = "SI Units (m, kg, s, A, K) with strict sign conventions (+/-) for direction and force.",
                hint = "Always convert cm to meters in numericals",
                subject = "Science"
            ),
            Flashcard(
                id = "fc_${System.currentTimeMillis()}_3",
                front = "Why is this a favorite CBSE exam question topic?",
                back = "Because it tests conceptual understanding, derivation, and diagram labelling simultaneously.",
                hint = "Check 5-year PYQ frequency",
                subject = "Science"
            )
        )

        val quiz = listOf(
            QuizQuestion(
                id = "qz_${System.currentTimeMillis()}_1",
                question = "Which statement best describes $cleanTitle based on your notes?",
                options = listOf(
                    "It adheres strictly to conservation laws in an isolated system",
                    "It varies unpredictably with ambient temperature",
                    "It can only be observed in gaseous states",
                    "It does not require SI unit standardization"
                ),
                correctIndex = 0,
                explanation = "Conservation laws apply universally to this concept as highlighted in the summary notes."
            ),
            QuizQuestion(
                id = "qz_${System.currentTimeMillis()}_2",
                question = "In a CBSE Board exam, what is the most critical step when solving numericals for this topic?",
                options = listOf(
                    "Always writing given data with correct SI units first",
                    "Skipping the formula and directly writing the answer",
                    "Ignoring standard decimal places",
                    "Using mental approximations only"
                ),
                correctIndex = 0,
                explanation = "CBSE step-marking awards 0.5 to 1 mark for stating given parameters and the formula with units."
            )
        )

        val noteDoc = NoteDocument(
            id = "note_${System.currentTimeMillis()}",
            title = cleanTitle,
            originalText = rawText,
            summaryBullets = summaryPoints,
            formulas = formulas,
            mindMapNodes = mindMap,
            flashcards = flashcards,
            quizQuestions = quiz
        )

        _notes.update { listOf(noteDoc) + it }
        logStudyTime(10)
        return noteDoc
    }

    private fun loadSampleNotes() {
        val sample1 = NoteDocument(
            id = "sample_chem_1",
            title = "Chemical Reactions & Equations (Class 10)",
            originalText = "Chemical reactions involve breaking and making bonds between atoms to produce new substances. A balanced chemical equation has an equal number of atoms of each element on both sides to satisfy the Law of Conservation of Mass. Types of reactions: Combination (CaO + H2O -> Ca(OH)2 + Heat), Decomposition (2FeSO4 -> Fe2O3 + SO2 + SO3), Displacement (Fe + CuSO4 -> FeSO4 + Cu), Double Displacement (Na2SO4 + BaCl2 -> BaSO4 ppt + 2NaCl), Redox reactions (Oxidation: gain of oxygen or loss of hydrogen; Reduction: loss of oxygen or gain of hydrogen). Rancidity and corrosion are oxidation effects in daily life.",
            summaryBullets = listOf(
                "Law of Conservation of Mass: Number of atoms must be balanced on reactant and product sides.",
                "Combination Reaction: Quicklime (CaO) + Water forms Slaked lime Ca(OH)₂ (exothermic reaction).",
                "Thermal Decomposition: Green FeSO₄ crystals turn brown Fe₂O₃ releasing SO₂ & SO₃ gases.",
                "Precipitation: White BaSO₄ precipitate forms upon mixing sodium sulphate with barium chloride.",
                "Redox: Oxidation is gain of O or loss of H; Reduction is loss of O or gain of H."
            ),
            formulas = listOf(
                "CaO (s) + H₂O (l) ➔ Ca(OH)₂ (aq) + Heat",
                "2FeSO₄ (s) --Δ--> Fe₂O₃ (s) + SO₂ (g) + SO₃ (g)",
                "Fe (s) + CuSO₄ (aq) ➔ FeSO₄ (aq) + Cu (s)",
                "BaCl₂ (aq) + Na₂SO₄ (aq) ➔ BaSO₄ (s)↓ + 2NaCl (aq)"
            ),
            mindMapNodes = listOf(
                MindMapNode("Chemical Reactions", listOf("Balancing Equations", "Energy Changes (Exo/Endothermic)")),
                MindMapNode("Reaction Types", listOf("Combination", "Decomposition", "Displacement", "Double Displacement", "Redox")),
                MindMapNode("Daily Life Effects", listOf("Corrosion (Rusting of iron: Fe₂O₃.xH₂O)", "Rancidity (Oxidation of fats, prevented by N₂ gas)"))
            ),
            flashcards = listOf(
                Flashcard(
                    id = "fc_chem_1",
                    front = "What is the formula of Slaked Lime?",
                    back = "Ca(OH)₂ [Calcium Hydroxide]",
                    hint = "Formed when Quicklime CaO reacts with water",
                    subject = "Chemistry"
                ),
                Flashcard(
                    id = "fc_chem_2",
                    front = "What gas is used to flush bags of potato chips to prevent rancidity?",
                    back = "Nitrogen gas (N₂), an unreactive antioxidant gas.",
                    hint = "Inert gas in atmosphere",
                    subject = "Chemistry"
                ),
                Flashcard(
                    id = "fc_chem_3",
                    front = "What color change occurs when iron nails are placed in CuSO₄ solution?",
                    back = "Blue copper sulphate solution changes to light green (FeSO₄) and reddish-brown copper deposits on the iron nail.",
                    hint = "Single displacement reaction",
                    subject = "Chemistry"
                )
            ),
            quizQuestions = listOf(
                QuizQuestion(
                    id = "qz_chem_1",
                    question = "Which of the following is a decomposition reaction that requires light energy?",
                    options = listOf(
                        "2AgCl (s) --Sunlight--> 2Ag (s) + Cl₂ (g)",
                        "CaCO₃ (s) --Heat--> CaO (s) + CO₂ (g)",
                        "2H₂O (l) --Electricity--> 2H₂ (g) + O₂ (g)",
                        "CH₄ (g) + 2O₂ (g) --> CO₂ (g) + 2H₂O (g)"
                    ),
                    correctIndex = 0,
                    explanation = "Silver chloride decomposition into silver and chlorine gas is photolytic decomposition, used in black-and-white photography."
                ),
                QuizQuestion(
                    id = "qz_chem_2",
                    question = "In the reaction: CuO + H₂ ➔ Cu + H₂O, which substance is oxidised?",
                    options = listOf("H₂", "CuO", "Cu", "H₂O"),
                    correctIndex = 0,
                    explanation = "H₂ gains oxygen to form H₂O, so H₂ undergoes oxidation and acts as the reducing agent."
                )
            )
        )

        _notes.value = listOf(sample1)
    }

    private fun loadInitialTutorChat() {
        _tutorMessages.value = listOf(
            TutorMessage(
                id = "msg_1",
                text = "Namaste Learner! 🙏 Main hoon aapka StudyPal AI Tutor. Ask me any doubt in Science or Maths in English, Nepali, Hindi, ya Hinglish! Aap kya revise karna chahte hain aaj?",
                isFromUser = false,
                timestamp = "Just now",
                suggestedPills = listOf(
                    "Explain Snell's Law with ray diagram",
                    "Bhai Ohm's law kya hai simple words me?",
                    "Difference between aerobic & anaerobic respiration",
                    "CBSE 5-mark tips for Electricity numericals"
                )
            )
        )
    }

    fun sendTutorMessage(userText: String) {
        val userMsg = TutorMessage(
            id = "user_${System.currentTimeMillis()}",
            text = userText,
            isFromUser = true,
            timestamp = "Now"
        )

        val aiResponse = generateTutorReply(userText)
        val aiMsg = TutorMessage(
            id = "ai_${System.currentTimeMillis()}",
            text = aiResponse.first,
            isFromUser = false,
            timestamp = "Now",
            suggestedPills = aiResponse.second
        )

        _tutorMessages.update { it + userMsg + aiMsg }
        logStudyTime(5)
    }

    private fun generateTutorReply(query: String): Pair<String, List<String>> {
        val q = query.lowercase()
        return when {
            q.contains("snell") || q.contains("refraction") -> {
                Pair(
                    "🌟 **Snell's Law of Refraction** (CBSE Class 10/12 High-Yield):\n\n" +
                            "**Statement:** When light travels from medium 1 to medium 2, the ratio of sine of angle of incidence (i) to sine of angle of refraction (r) is constant.\n\n" +
                            "📐 **Formula:**\n" +
                            "sin(i) / sin(r) = n₂ / n₁ = n₂₁\n\n" +
                            "🔑 **Key Rule:**\n" +
                            "• Rare to Denser medium (Air to Glass): Light bends towards normal (r < i).\n" +
                            "• Denser to Rare medium (Glass to Air): Light bends away from normal (r > i).\n\n" +
                            "💡 **CBSE Exam Tip:** If light falls normally (i = 0°), then r = 0°, no deviation occurs!",
                    listOf("Explain Total Internal Reflection", "Give a numerical on refractive index", "Next doubt")
                )
            }
            q.contains("ohm") || q.contains("resistance") || q.contains("voltage") -> {
                Pair(
                    "⚡ **Ohm's Law** (Very Important for CBSE Section C & D):\n\n" +
                            "**Simple Hinglish:** Kisi conductor me flowing current (I) directly proportional hota hai uske ends ke across potential difference (V) ke, provided temperature constant rahe!\n\n" +
                            "📊 **Formula:**\n" +
                            "V = I × R\n\n" +
                            "• V = Potential Difference (Volts)\n" +
                            "• I = Current (Amperes)\n" +
                            "• R = Resistance (Ohms Ω)\n\n" +
                            "📝 **Factors affecting Resistance:**\n" +
                            "R = ρ (l / A)\n" +
                            "1. Directly proportional to length (l)\n" +
                            "2. Inversely proportional to area of cross-section (A)\n" +
                            "3. Dependent on nature of material (Resistivity ρ)\n" +
                            "4. Temperature: increases with temperature for pure metals.",
                    listOf("Series vs Parallel combination", "Solve resistance numerical", "Ask another question")
                )
            }
            q.contains("respiration") || q.contains("aerobic") -> {
                Pair(
                    "🫁 **Aerobic vs Anaerobic Respiration** (Class 10 Biology Life Processes):\n\n" +
                            "1️⃣ **Aerobic Respiration:**\n" +
                            "• Occurs in the presence of O₂.\n" +
                            "• Site: Cytoplasm ➔ Mitochondria.\n" +
                            "• Products: CO₂ + H₂O + 38 ATP (High energy yield).\n" +
                            "• Equation: C₆H₁₂O₆ + 6O₂ ➔ 6CO₂ + 6H₂O + 38 ATP\n\n" +
                            "2️⃣ **Anaerobic Respiration:**\n" +
                            "• Occurs in absence of O₂.\n" +
                            "• In Yeast: Produces Ethanol + CO₂ + 2 ATP (Fermentation).\n" +
                            "• In Human Muscle cells: Produces Lactic acid + 2 ATP (causes muscle cramps during heavy exercise!).",
                    listOf("Why do muscle cramps occur?", "Explain double circulation", "Clear doubts")
                )
            }
            else -> {
                Pair(
                    "Great question! 💡\n\n" +
                            "CBSE concept breakdown for **\"$query\"**:\n\n" +
                            "1. **Core Definition:** In board exams, always begin your answer with an explicit 1-line definition using standard NCERT terminology.\n" +
                            "2. **Mathematical Relation / Chemical Equation:** Wherever applicable, write the balanced equation or formula and specify SI units.\n" +
                            "3. **Board Marking Strategy:**\n" +
                            "   • 1 Mark: Precise definition or formula.\n" +
                            "   • 2 Marks: Labeled diagram or condition constraints.\n" +
                            "   • 3-5 Marks: Step-by-step derivation with reason.",
                    listOf("Give an example question", "Explain in Hindi", "Test my knowledge with Viva")
                )
            }
        }
    }

    // NCERT Subjects and Chapters
    fun getNcertSubjects(): List<NcertSubject> {
        return listOf(
            NcertSubject("sub_sci_10", "Science (PCB)", "Class 10", 0xFF00E5FF, "science", 13, 140),
            NcertSubject("sub_math_10", "Mathematics", "Class 10", 0xFF6366F1, "math", 14, 165),
            NcertSubject("sub_phy_12", "Physics", "Class 12", 0xFFFF6D00, "physics", 14, 180),
            NcertSubject("sub_chem_12", "Chemistry", "Class 12", 0xFF10B981, "chemistry", 10, 150)
        )
    }

    fun getNcertChapters(subjectId: String): List<NcertChapter> {
        return when (subjectId) {
            "sub_sci_10" -> listOf(
                NcertChapter(
                    id = "ch_sci_1",
                    subjectId = "sub_sci_10",
                    title = "Chemical Reactions & Equations",
                    chapterNumber = 1,
                    classLevel = "Class 10",
                    summary = "Chemical reactions, balancing equations, combination, decomposition, displacement, double displacement, redox, corrosion, rancidity.",
                    formulas = listOf(
                        "CaO + H₂O ➔ Ca(OH)₂ + Heat",
                        "2AgCl --sunlight--> 2Ag + Cl₂",
                        "Fe + CuSO₄ ➔ FeSO₄ + Cu",
                        "Na₂SO₄ + BaCl₂ ➔ BaSO₄↓ + 2NaCl"
                    ),
                    highYieldPoints = listOf(
                        "Always balance using hit-and-trial method without changing molecular subscripts.",
                        "Difference between exothermic (heat released) and endothermic (heat absorbed).",
                        "Rust formula: Fe₂O₃·xH₂O (hydrated iron(III) oxide)."
                    ),
                    isCompleted = true,
                    flashcards = listOf(
                        Flashcard("fc_ch1_1", "What is an endothermic reaction?", "A reaction in which energy is absorbed from the surroundings in form of heat, light or electricity.", "Requires energy intake", "Chemistry"),
                        Flashcard("fc_ch1_2", "Why is respiration considered an exothermic reaction?", "Glucose combines with oxygen in cells releasing significant energy in the form of ATP.", "Releases energy", "Biology/Chemistry")
                    ),
                    oralQuestions = listOf(
                        OralTestItem(
                            id = "oral_1",
                            promptQuestion = "Explain why silver chloride turns grey when kept in sunlight.",
                            expectedAnswer = "Silver chloride undergoes photolytic decomposition into grey silver metal and chlorine gas when exposed to sunlight: 2AgCl -> 2Ag + Cl2.",
                            mandatoryKeywords = listOf("photolytic", "decomposition", "silver metal", "chlorine gas")
                        ),
                        OralTestItem(
                            id = "oral_2",
                            promptQuestion = "What is the difference between oxidation and reduction in terms of oxygen and hydrogen?",
                            expectedAnswer = "Oxidation is the gain of oxygen or loss of hydrogen. Reduction is the loss of oxygen or gain of hydrogen.",
                            mandatoryKeywords = listOf("gain of oxygen", "loss of hydrogen", "loss of oxygen", "gain of hydrogen")
                        )
                    ),
                    pyqs = listOf(
                        QuizQuestion("pyq_1", "Identify the substance oxidised in: MnO₂ + 4HCl ➔ MnCl₂ + 2H₂O + Cl₂", listOf("HCl", "MnO₂", "MnCl₂", "Cl₂"), 0, "HCl loses hydrogen to form Cl₂, hence HCl is oxidised to Cl₂.")
                    )
                ),
                NcertChapter(
                    id = "ch_sci_2",
                    subjectId = "sub_sci_10",
                    title = "Acids, Bases and Salts",
                    chapterNumber = 2,
                    classLevel = "Class 10",
                    summary = "Indicators, acid-metal reactions, neutralization, pH scale, salts of strong/weak acids, Bleaching powder, Baking soda, Washing soda, Plaster of Paris.",
                    formulas = listOf(
                        "Acid + Base ➔ Salt + Water",
                        "Acid + Metal ➔ Salt + H₂ (g) (Pop sound test)",
                        "Bleaching Powder: CaOCl₂",
                        "Baking Soda: NaHCO₃",
                        "Plaster of Paris: CaSO₄·½H₂O"
                    ),
                    highYieldPoints = listOf(
                        "pH scale range 0-14: acidic (<7), neutral (7), basic (>7).",
                        "Tooth decay starts when mouth pH is below 5.5.",
                        "Gypsum (CaSO₄·2H₂O) heated at 373K yields Plaster of Paris."
                    ),
                    isCompleted = false,
                    flashcards = listOf(
                        Flashcard("fc_ch2_1", "What gas is released when acid reacts with metal?", "Hydrogen gas (H₂), tested by bringing a burning splinter which extinguishes with a 'pop' sound.", "Test with pop sound", "Chemistry"),
                        Flashcard("fc_ch2_2", "What is the chemical formula of Washing Soda?", "Na₂CO₃·10H₂O (Sodium carbonate decahydrate).", "10 water molecules of crystallisation", "Chemistry")
                    ),
                    oralQuestions = listOf(
                        OralTestItem(
                            id = "oral_ch2_1",
                            promptQuestion = "Why does dry HCl gas not change the colour of dry litmus paper?",
                            expectedAnswer = "Dry HCl gas does not produce hydrogen (H+) or hydronium (H3O+) ions in the absence of moisture, and only ions cause color change in litmus.",
                            mandatoryKeywords = listOf("hydrogen ions", "hydronium", "absence of water", "moisture")
                        )
                    ),
                    pyqs = listOf(
                        QuizQuestion("pyq_2", "Tooth enamel contains which hardest substance?", listOf("Calcium hydroxyapatite", "Calcium carbonate", "Sodium chloride", "Magnesium hydroxide"), 0, "Enamel is made of calcium hydroxyapatite (crystalline form of calcium phosphate).")
                    )
                ),
                NcertChapter(
                    id = "ch_sci_6",
                    subjectId = "sub_sci_10",
                    title = "Life Processes",
                    chapterNumber = 6,
                    classLevel = "Class 10",
                    summary = "Autotrophic nutrition, stomata, human digestive system, double circulation in heart, human respiratory system, nephron and excretion.",
                    formulas = listOf(
                        "Photosynthesis: 6CO₂ + 12H₂O --light/chlorophyll--> C₆H₁₂O₆ + 6O₂ + 6H₂O",
                        "Blood Pressure: 120/80 mm of Hg"
                    ),
                    highYieldPoints = listOf(
                        "Salivary amylase digests starch into maltose in the mouth.",
                        "Bile juice from liver emulsifies fats into smaller globules.",
                        "Villi in small intestine provide massive surface area for nutrient absorption.",
                        "Nephron is the structural and functional filtration unit of kidneys."
                    ),
                    isCompleted = true,
                    flashcards = listOf(
                        Flashcard("fc_ch6_1", "What is the role of Hydrochloric acid in the human stomach?", "Creates an acidic medium for the enzyme pepsin to act, and kills harmful bacteria ingested with food.", "Activates pepsin", "Biology"),
                        Flashcard("fc_ch6_2", "Why is double circulation important in human beings?", "It prevents the mixing of oxygenated and deoxygenated blood, ensuring high energy efficiency for warm-blooded thermoregulation.", "Keeps oxygenated blood separate", "Biology")
                    ),
                    oralQuestions = listOf(
                        OralTestItem(
                            id = "oral_ch6_1",
                            promptQuestion = "State the structural adaptations of the small intestine for absorption of digested food.",
                            expectedAnswer = "The small intestine has millions of finger-like projections called villi which maximize surface area, and they are richly supplied with blood vessels and lacteals.",
                            mandatoryKeywords = listOf("villi", "surface area", "blood vessels", "lacteals", "microvilli")
                        )
                    ),
                    pyqs = listOf(
                        QuizQuestion("pyq_3", "In human heart, which valve prevents backflow from left ventricle to left atrium?", listOf("Bicuspid (Mitral) valve", "Tricuspid valve", "Semilunar valve", "Aortic valve"), 0, "The bicuspid/mitral valve separates left atrium and left ventricle.")
                    )
                ),
                NcertChapter(
                    id = "ch_sci_12",
                    subjectId = "sub_sci_10",
                    title = "Electricity",
                    chapterNumber = 12,
                    classLevel = "Class 10",
                    summary = "Current, potential difference, Ohm's law, resistance, resistivity, series and parallel circuits, Joule's law of heating, electric power.",
                    formulas = listOf(
                        "I = Q / t",
                        "V = W / Q",
                        "V = I * R",
                        "R = ρ * (l / A)",
                        "Series: R_s = R₁ + R₂ + R₃",
                        "Parallel: 1/R_p = 1/R₁ + 1/R₂ + 1/R₃",
                        "Heat H = I² * R * t",
                        "Power P = V * I = I²R = V² / R"
                    ),
                    highYieldPoints = listOf(
                        "In series circuits, current remains identical through all resistors, potential divides.",
                        "In parallel circuits, potential difference remains identical, current divides.",
                        "Resistivity depends only on material and temperature, independent of length or area!"
                    ),
                    isCompleted = false,
                    flashcards = listOf(
                        Flashcard("fc_ch12_1", "What happens to resistance if wire length is doubled and area is halved?", "Resistance becomes 4 times greater: R' = ρ(2l)/(A/2) = 4R.", "Factor of 4", "Physics"),
                        Flashcard("fc_ch12_2", "Why is tungsten used almost exclusively for filament of electric lamps?", "High melting point (3380°C) and high resistivity prevents melting when glowing white hot.", "High melting point", "Physics")
                    ),
                    oralQuestions = listOf(
                        OralTestItem(
                            id = "oral_ch12_1",
                            promptQuestion = "State Joule's Law of Heating and give its mathematical formulation.",
                            expectedAnswer = "Heat produced in a resistor is directly proportional to square of current, resistance of conductor, and time for which current flows: H = I²Rt.",
                            mandatoryKeywords = listOf("square of current", "resistance", "time", "H = I²Rt", "directly proportional")
                        )
                    ),
                    pyqs = listOf(
                        QuizQuestion("pyq_4", "Commercial unit of electric energy (1 kWh) is equal to how many Joules?", listOf("3.6 × 10⁶ J", "3.6 × 10⁵ J", "1000 J", "3600 J"), 0, "1 kWh = 1000 W × 3600 s = 3.6 × 10⁶ Joules.")
                    )
                )
            )
            "sub_math_10" -> listOf(
                NcertChapter(
                    id = "ch_math_1",
                    subjectId = "sub_math_10",
                    title = "Real Numbers & Fundamental Theorem",
                    chapterNumber = 1,
                    classLevel = "Class 10",
                    summary = "Fundamental Theorem of Arithmetic, proving irrationality of √2, √3, √5, HCF and LCM product rule.",
                    formulas = listOf(
                        "HCF(a, b) × LCM(a, b) = a × b",
                        "Every composite number can be uniquely factored into primes"
                    ),
                    highYieldPoints = listOf(
                        "3-mark question guarantee: Proof of irrationality by contradiction.",
                        "HCF is product of lowest power of each common prime factor."
                    ),
                    isCompleted = true,
                    flashcards = listOf(
                        Flashcard("fc_m1", "State the relation between HCF, LCM and two numbers a and b.", "HCF(a, b) × LCM(a, b) = a × b", "Product of numbers", "Maths")
                    ),
                    oralQuestions = listOf(
                        OralTestItem(
                            id = "oral_m1",
                            promptQuestion = "State the Fundamental Theorem of Arithmetic.",
                            expectedAnswer = "Every composite number can be expressed as a product of primes, and this factorization is unique, apart from the order in which the prime factors occur.",
                            mandatoryKeywords = listOf("composite number", "product of primes", "unique factorization", "order")
                        )
                    ),
                    pyqs = listOf(
                        QuizQuestion("pyq_m1", "If HCF(306, 657) = 9, find LCM(306, 657).", listOf("22338", "22339", "22440", "18450"), 0, "LCM = (306 × 657) / 9 = 22338.")
                    )
                ),
                NcertChapter(
                    id = "ch_math_4",
                    subjectId = "sub_math_10",
                    title = "Quadratic Equations",
                    chapterNumber = 4,
                    classLevel = "Class 10",
                    summary = "Standard form ax² + bx + c = 0, Factorization method, Quadratic formula, Nature of roots using Discriminant D = b² - 4ac.",
                    formulas = listOf(
                        "x = [-b ± √(b² - 4ac)] / (2a)",
                        "Discriminant D = b² - 4ac",
                        "D > 0: Two distinct real roots",
                        "D = 0: Two equal real roots (x = -b/2a)",
                        "D < 0: No real roots"
                    ),
                    highYieldPoints = listOf(
                        "If roots are equal, set D = 0 to solve for unknown parameter k.",
                        "Speed/distance and tap fill problems are frequent case-based questions."
                    ),
                    isCompleted = false,
                    flashcards = listOf(
                        Flashcard("fc_m4", "What condition ensures a quadratic equation has real and equal roots?", "Discriminant D = b² - 4ac = 0", "D equals zero", "Maths")
                    ),
                    oralQuestions = listOf(
                        OralTestItem(
                            id = "oral_m4",
                            promptQuestion = "What can you deduce about the roots of ax² + bx + c = 0 when the discriminant is negative?",
                            expectedAnswer = "When D < 0, the equation has no real roots because the square root of a negative number is not a real number.",
                            mandatoryKeywords = listOf("no real roots", "imaginary", "negative discriminant", "D < 0")
                        )
                    ),
                    pyqs = listOf(
                        QuizQuestion("pyq_m4", "Find the value of k for which 2x² + kx + 3 = 0 has two equal roots.", listOf("±2√6", "±√6", "±6", "±24"), 0, "For equal roots, D = k² - 4(2)(3) = 0 => k² = 24 => k = ±2√6.")
                    )
                )
            )
            else -> listOf(
                NcertChapter(
                    id = "ch_gen_1",
                    subjectId = subjectId,
                    title = "Key Concepts & Board PYQs",
                    chapterNumber = 1,
                    classLevel = "Class 12",
                    summary = "Advanced derivations, formulas, definitions and CBSE marking schemes.",
                    formulas = listOf("E = hν", "λ = h/p"),
                    highYieldPoints = listOf("Directly referenced in previous 5 years CBSE board question papers."),
                    isCompleted = false,
                    flashcards = emptyList(),
                    oralQuestions = emptyList(),
                    pyqs = emptyList()
                )
            )
        }
    }

    // CBSE Pattern Timed Practice Papers
    fun getCbsePracticePapers(): List<CbsePaper> {
        return listOf(
            CbsePaper(
                id = "cbse_sci_sample_1",
                title = "CBSE Class 10 Science Official Board Pattern Mock",
                subject = "Science (Theory)",
                classLevel = "Class 10 CBSE",
                durationMinutes = 30, // rapid 30m mode or full
                totalMarks = 25,
                sections = listOf(
                    PaperSection(
                        name = "Section A — 1 Mark MCQs",
                        instructions = "Select the correct option. Each question carries 1 mark.",
                        questions = listOf(
                            PaperQuestion(
                                id = "sq_1",
                                questionText = "Which among the following statement(s) is (are) true? Exposure of silver chloride to sunlight for a long duration turns grey due to:\n(i) the formation of silver by decomposition of silver chloride\n(ii) sublimation of silver chloride\n(iii) decomposition of chlorine gas from silver chloride\n(iv) oxidation of silver chloride",
                                type = "MCQ",
                                options = listOf("(i) only", "(i) and (iii)", "(ii) and (iii)", "(iv) only"),
                                correctOptionIndex = 0,
                                marks = 1,
                                modelSolution = "Silver chloride undergoes photolytic decomposition into elemental silver and chlorine gas: 2AgCl -> 2Ag + Cl2."
                            ),
                            PaperQuestion(
                                id = "sq_2",
                                questionText = "A cylindrical conductor of length 'l' and uniform area of cross-section 'A' has resistance 'R'. Another conductor of length 2.5 l and resistance 0.5 R of the same material has area of cross-section:",
                                type = "MCQ",
                                options = listOf("5 A", "2.5 A", "0.5 A", "1 / 5 A"),
                                correctOptionIndex = 0,
                                marks = 1,
                                modelSolution = "R = ρ(l/A). For new wire: R' = ρ(l'/A') => 0.5R = ρ(2.5 l / A') => A' = 2.5 / 0.5 A = 5A."
                            ),
                            PaperQuestion(
                                id = "sq_3",
                                questionText = "In which of the following groups of organisms are food materials broken down outside the body and absorbed?",
                                type = "MCQ",
                                options = listOf(
                                    "Yeast, mushroom, bread mould (Fungi)",
                                    "Mushroom, amoeba, green plant",
                                    "Paramecium, amoeba, cuscuta",
                                    "Cuscuta, tick, leech"
                                ),
                                correctOptionIndex = 0,
                                marks = 1,
                                modelSolution = "Saprophytes like fungi (yeast, bread mould, mushroom) secrete digestive enzymes outside their bodies and absorb dissolved nutrients."
                            )
                        )
                    ),
                    PaperSection(
                        name = "Section B — Conceptual Short Answers",
                        instructions = "Answer in 30-50 words. Each question carries 2 marks.",
                        questions = listOf(
                            PaperQuestion(
                                id = "sq_4",
                                questionText = "Why do stars twinkle, but planets do not? Explain using the concept of atmospheric refraction.",
                                type = "SHORT",
                                marks = 2,
                                modelSolution = "1 Mark: Stars are point sources of light located at vast distances. Light rays bend continuously through atmospheric layers of varying refractive indices, causing the apparent position and intensity to fluctuate (twinkling).\n1 Mark: Planets are much closer and act as extended sources (collection of point sources). The total variations from all points average out to zero, so they do not twinkle."
                            ),
                            PaperQuestion(
                                id = "sq_5",
                                questionText = "Name the hormone secreted by thyroid gland. Mention its one major function and the mineral essential for its synthesis.",
                                type = "SHORT",
                                marks = 2,
                                modelSolution = "1 Mark: Hormone is Thyroxine; mineral essential is Iodine.\n1 Mark: Function: Regulates carbohydrate, protein, and fat metabolism for balanced growth in the human body."
                            )
                        )
                    ),
                    PaperSection(
                        name = "Section C — Case-Based Application",
                        instructions = "Read the passage carefully and answer the questions. Carries 4 marks.",
                        questions = listOf(
                            PaperQuestion(
                                id = "sq_6",
                                questionText = "Case Study: In electric power distribution, current is transmitted at very high voltages and low currents to minimize heat loss in transmission cables (H = I²Rt). A domestic circuit operates at 220 V AC in India. Why is fuse wire always placed in the live wire and what property makes it effective?",
                                type = "CASE_BASED",
                                marks = 4,
                                modelSolution = "2 Marks: The electric fuse is placed in the live wire so that during an overload or short-circuit, the fuse wire melts and breaks the connection before the high current reaches domestic appliances, preventing electric shock.\n2 Marks: Properties: Made of an alloy of lead and tin with high resistance and low melting point."
                            )
                        )
                    )
                )
            )
        )
    }

    // Evaluate Oral Answer
    fun evaluateOralAnswer(item: OralTestItem, spokenText: String): Pair<Int, String> {
        val lower = spokenText.lowercase()
        var matchCount = 0
        val missing = mutableListOf<String>()

        for (kw in item.mandatoryKeywords) {
            if (lower.contains(kw.lowercase())) {
                matchCount++
            } else {
                missing.add(kw)
            }
        }

        val total = item.mandatoryKeywords.size
        val score = if (total > 0) ((matchCount.toDouble() / total) * 100).toInt() else 80

        val feedback = when {
            score >= 80 -> "🌟 Outstanding! You accurately stated key concepts. Perfect for full board marks."
            score >= 50 -> "👍 Good attempt! You understood the core idea, but remember to mention: ${missing.joinToString(", ")}."
            else -> "⚠️ Needs revision. A complete answer requires: ${item.expectedAnswer}"
        }

        logStudyTime(5)
        return Pair(score, feedback)
    }
}
