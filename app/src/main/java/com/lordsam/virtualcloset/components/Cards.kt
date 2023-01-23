package com.lordsam.virtualcloset.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lordsam.virtualcloset.R
import com.lordsam.virtualcloset.data.ClosetData
import com.lordsam.virtualcloset.utils.DateConverter


@Composable
fun CategoryCard(
    imageID: Int,
    text: String,
    onClick : () -> Unit
){

        Card (
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clickable {
                    onClick()
                }
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ClosetCard(closet: ClosetData){

    var expanded by remember {
        mutableStateOf(false)
    }
    var modifier by remember {
        mutableStateOf(
            Modifier
            .height(128.dp)
            .width(100.dp)
            .padding(4.dp)
        )
    }

    val tempDate = closet.dateTime.toString().split(" ")
    val date = tempDate[1] + " " + tempDate[2] + " " + tempDate[3]

    Card(
        shape = RoundedCornerShape(CornerSize(8.dp)),
        elevation = 8.dp,
        modifier = modifier
            .clickable {
                expanded = !expanded
                modifier = if (expanded){
                    Modifier
                        .height(256.dp)
                        .fillMaxWidth()
                        .padding(4.dp)
                } else{
                    Modifier
                        .height(128.dp)
                        .width(100.dp)
                        .padding(4.dp)
                }
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GlideImage(
                model = closet.uri.toUri(),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
            Text(
                text = closet.name,
                modifier = Modifier
                    .padding(4.dp)
            )
            if (expanded){
                Text(
                    text = "Description: " + closet.description,
                    modifier = Modifier
                        .padding(4.dp)
                )
                Text(
                    text = "Kept At: " + closet.location,
                    modifier = Modifier
                        .padding(4.dp)
                )
                Text(
                    text = date,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}