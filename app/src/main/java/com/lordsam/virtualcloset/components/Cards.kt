package com.lordsam.virtualcloset.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.lordsam.virtualcloset.R


@Composable
fun CategoryCard(
    imageID: Int,
    text: String
){

        Card (
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Surface(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .size(80.dp)
                ) {

                    Image(
                        painter = painterResource(id = imageID),
                        contentDescription = stringResource(R.string.category_icon),
                        modifier = Modifier
                    )
                }

                Text(
                    text = text,
                    style = MaterialTheme.typography.h6,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
    }
}