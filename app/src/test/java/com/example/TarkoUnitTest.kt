package com.example

import com.example.data.model.OralTestItem
import com.example.data.repository.TarkoRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TarkoUnitTest {

    @Test
    fun testInitialStreakAndProfile() {
        val repo = TarkoRepository()
        val profile = repo.userProfile.value
        val streak = repo.streakData.value

        assertEquals("Learner", profile.name)
        assertEquals("Class 10 CBSE", profile.classLevel)
        assertEquals(7, streak.currentStreak)
        assertEquals(30, streak.dailyGoalMinutes)
    }

    @Test
    fun testStudyMinutesProgression() {
        val repo = TarkoRepository()
        val initialStreak = repo.streakData.value.currentStreak

        // Log 15 minutes
        repo.logStudyTime(15)
        assertEquals(24 + 15, repo.streakData.value.todayMinutesStudied)
        // Since goal was 30 and 39 >= 30, streak increments
        assertEquals(initialStreak + 1, repo.streakData.value.currentStreak)
    }

    @Test
    fun testNotesConversion() {
        val repo = TarkoRepository()
        val doc = repo.convertNotes(
            title = "Refraction of Light",
            rawText = "Snell's law states that ratio of sine of angle of incidence to refraction is constant."
        )

        assertEquals("Refraction of Light", doc.title)
        assertTrue(doc.summaryBullets.isNotEmpty())
        assertTrue(doc.formulas.isNotEmpty())
        assertTrue(doc.flashcards.isNotEmpty())
        assertTrue(doc.quizQuestions.isNotEmpty())
    }

    @Test
    fun testOralVivaEvaluation() {
        val repo = TarkoRepository()
        val testItem = OralTestItem(
            id = "test_1",
            promptQuestion = "What is Snell's Law?",
            expectedAnswer = "Ratio of sine of angle of incidence to angle of refraction is constant.",
            mandatoryKeywords = listOf("sine", "incidence", "refraction", "constant")
        )

        val perfectResult = repo.evaluateOralAnswer(
            testItem,
            "The ratio of sine of angle of incidence to angle of refraction is a constant."
        )
        assertEquals(100, perfectResult.first)
        assertTrue(perfectResult.second.contains("Outstanding"))

        val partialResult = repo.evaluateOralAnswer(
            testItem,
            "It deals with angle of incidence and refraction in a medium."
        )
        assertEquals(50, partialResult.first)
    }

    @Test
    fun testCbseMockPapersAvailability() {
        val repo = TarkoRepository()
        val papers = repo.getCbsePracticePapers()
        assertTrue(papers.isNotEmpty())
        val first = papers.first()
        assertEquals(25, first.totalMarks)
        assertTrue(first.sections.size >= 3)
    }
}
