package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.Screen

data class NavItem(
    val screen: Screen,
    val title: String,
    val icon: ImageVector,
    val tag: String
)

@Composable
fun TarkoBottomNav(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    val items = listOf(
        NavItem(Screen.Dashboard, "Home", Icons.Default.Home, "nav_home"),
        NavItem(Screen.NotesConverter, "Notes AI", Icons.Default.Description, "nav_notes"),
        NavItem(Screen.NcertDirectory, "NCERT", Icons.Default.MenuBook, "nav_ncert"),
        NavItem(Screen.OralViva(), "Viva Voce", Icons.Default.RecordVoiceOver, "nav_viva"),
        NavItem(Screen.CbsePapers, "Papers", Icons.Default.Quiz, "nav_papers"),
        NavItem(Screen.AiTutor, "AI Tutor", Icons.Default.Forum, "nav_tutor")
    )

    NavigationBar(
        containerColor = DeepNavySurface,
        contentColor = TextPrimary,
        tonalElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEach { item ->
            val isSelected = when (item.screen) {
                is Screen.Dashboard -> currentScreen is Screen.Dashboard
                is Screen.NotesConverter -> currentScreen is Screen.NotesConverter || currentScreen is Screen.NoteDetail
                is Screen.NcertDirectory -> currentScreen is Screen.NcertDirectory || currentScreen is Screen.NcertChapterDetail
                is Screen.OralViva -> currentScreen is Screen.OralViva
                is Screen.CbsePapers -> currentScreen is Screen.CbsePapers || currentScreen is Screen.TakePaper
                is Screen.AiTutor -> currentScreen is Screen.AiTutor
                else -> false
            }

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(item.screen) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (isSelected) ElectricCyan else TextMuted
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontFamily = PlusJakartaSans,
                        fontSize = 10.sp,
                        color = if (isSelected) ElectricCyan else TextMuted
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = DeepNavyCard,
                    selectedIconColor = ElectricCyan,
                    selectedTextColor = ElectricCyan,
                    unselectedIconColor = TextMuted,
                    unselectedTextColor = TextMuted
                ),
                modifier = Modifier.testTag(item.tag)
            )
        }
    }
}
