package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.NoteDocument
import com.example.ui.theme.*

@Composable
fun NotesConverterScreen(
    notes: List<NoteDocument>,
    isConverting: Boolean,
    onConvert: (String, String) -> Unit,
    onSelectNote: (String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var rawText by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Notes to AI Study Tools",
                    fontFamily = OutfitFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = TextPrimary
                )
                Text(
                    text = "Paste handwritten notes, PDF text, or worksheets to auto-generate formulas, mind maps, flashcards & quizzes.",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            }
        }

        // Input card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(18.dp))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Note Title (e.g. Class 10 Light Refraction)") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElectricCyan,
                            unfocusedBorderColor = DeepNavyCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedLabelColor = ElectricCyan,
                            unfocusedLabelColor = TextSecondary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("notes_title_input")
                    )

                    OutlinedTextField(
                        value = rawText,
                        onValueChange = { rawText = it },
                        label = { Text("Paste your notes or worksheet content...") },
                        minLines = 4,
                        maxLines = 8,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElectricCyan,
                            unfocusedBorderColor = DeepNavyCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedLabelColor = ElectricCyan,
                            unfocusedLabelColor = TextSecondary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("notes_content_input")
                    )

                    // Quick Sample Pills
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sample:",
                            fontFamily = PlusJakartaSans,
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                        SuggestionChip(
                            onClick = {
                                title = "Class 10 Light & Refraction"
                                rawText = "Refraction is the bending of light as it passes obliquely from one transparent medium to another. Snell's law states that sin i / sin r is constant (refractive index n21). In a glass slab, emergent ray is parallel to incident ray but laterally displaced. Concave mirror: converging, focal length negative. Convex mirror: diverging, focal length positive. Mirror formula: 1/f = 1/v + 1/u. Magnification: m = -v/u = h'/h."
                            },
                            label = { Text("Light & Mirrors", fontSize = 11.sp) },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = DeepNavySurface,
                                labelColor = ElectricCyan
                            )
                        )
                        SuggestionChip(
                            onClick = {
                                title = "Class 12 Electrostatics & Gauss Law"
                                rawText = "Coulomb's Law states electrostatic force between two point charges is directly proportional to product of charges and inversely proportional to square of distance: F = k q1 q2 / r^2. Electric field E = F/q0. Gauss's Law: Total electric flux through a closed surface is 1/ε0 times total charge enclosed: Φ = ∮ E·dA = q_enclosed / ε0. Field due to infinite wire: E = λ / (2πε0r). Electric potential V = k q / r."
                            },
                            label = { Text("Gauss's Law", fontSize = 11.sp) },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = DeepNavySurface,
                                labelColor = NeonIndigo
                            )
                        )
                    }

                    Button(
                        onClick = {
                            if (rawText.isNotBlank()) {
                                onConvert(title.ifBlank { "Study Notes" }, rawText)
                            }
                        },
                        enabled = !isConverting && rawText.isNotBlank(),
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("convert_notes_button")
                    ) {
                        if (isConverting) {
                            CircularProgressIndicator(
                                color = Color.Black,
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Extracting Formulas & Flashcards...",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Transform Into AI Study Pack",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }

        // Converted Study Packs
        item {
            Text(
                text = "Your Converted Study Packs (${notes.size})",
                fontFamily = OutfitFont,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = TextPrimary
            )
        }

        items(notes) { doc ->
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(16.dp))
                    .clickable { onSelectNote(doc.id) }
                    .testTag("note_doc_${doc.id}")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = doc.title,
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextPrimary
                        )
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "View",
                            tint = ElectricCyan
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = doc.originalText.take(120) + "...",
                        fontFamily = PlusJakartaSans,
                        fontSize = 12.sp,
                        color = TextSecondary,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            color = ElectricCyan.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "${doc.formulas.size} Formulas",
                                fontFamily = PlusJakartaSans,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = ElectricCyan,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Surface(
                            color = NeonIndigo.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "${doc.flashcards.size} Flashcards",
                                fontFamily = PlusJakartaSans,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = NeonIndigo,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Surface(
                            color = VividViolet.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "${doc.quizQuestions.size} Questions",
                                fontFamily = PlusJakartaSans,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = VividViolet,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
