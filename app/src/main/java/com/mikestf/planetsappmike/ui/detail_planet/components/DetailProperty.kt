package com.mikestf.planetsappmike.ui.detail_planet.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetailProperty(
    modifier: Modifier = Modifier,
    label: String,
    value: String?,
    imageVector: ImageVector
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp, horizontal = 5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.align(Alignment.Top)
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(text = label, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(Modifier.height(5.dp))
                Text(text = value ?: "",  color = Color.Black, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}