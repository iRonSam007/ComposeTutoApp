package com.example.composetutoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetutoapp.ui.theme.ComposeTutoAppTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //DemoColRow()
            //DemoModifiers()
            //imgCardExample()
            //StylingTextExample()
            StateExample()
        }
    }
}

//5 - Example: Using State, Dynamic content (UI state)
@Composable
fun StateExample(){
    Column(Modifier.fillMaxSize()){
        val color = remember { mutableStateOf(Color.Yellow) }
        ColorBox(
            Modifier
                .weight(1f)
                .fillMaxSize()){ //Call of lampbda function
            color.value = it
        }
        Box(modifier = Modifier
            .background(color.value)
            .weight(1f)
            .fillMaxSize())
    }

}
@Composable
fun ColorBox(modifier: Modifier = Modifier,
             updateColor: (Color)-> Unit //Unit is void in Java
){
    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat()
                )
            )
        })
}



//4 - Example: Styling text: using downloaded fonts and Annotated strings(point fort de JetPack against Xml)
@Composable
fun StylingTextExample(){
    //val fontFamily = FontFamily(
    //Font(R.font.inriasans_bold, FontWeight.Bold),
    //Font(R.font.inriasans_light, FontWeight.Light),
    //Font(R.font.inriasans_regular, FontWeight.Normal))
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF101010))){
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp
                    )
                ){
                    append("W") //Part of string u want withStyle to applied on
                }
                append("e are the chosen ")

                withStyle(
                    style = SpanStyle(
                        color = Color.Red,
                        fontSize = 50.sp
                    )
                ){
                    append("P") //Part of string u want withStyle to applied on
                }
                append("eaple")


            },
            color = Color.White,
            fontSize = 30.sp,
            //fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline               //Passed annotated string
        )
    }
}


//3 - Example: Using Image Card
@Composable
fun imgCardExample(){
    val painter = painterResource(id = R.drawable.iron_man)
    val description = "description ... Kermo in snow"
    val title = "title bla bla"
    Box(modifier = Modifier
        .fillMaxSize(0.5f)
        .padding(10.dp)){
        ImageCard(painter = painter, contentDescription = description, title = title)
    }
}
@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        //elevation = 5.dp
    )
    {
        Box(modifier = Modifier.height(200.dp)) {
            Image( //Image first layer of Card
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box( //Gradient layer applied on the Image
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f //Could be calculated dynamically
                        )
                    )
            )
            Box( // Top layer of Card containt a text
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Column() {
                    Text(
                        title,
                        style = TextStyle(color = Color.White, fontSize = 20.sp)
                    )
                    Text(
                        contentDescription,
                        style = TextStyle(color = Color.White, fontSize = 12.sp)
                    )
                }
            }
        }
    }
}



//2 - Example: Using modifiers
@Composable
fun DemoModifiers(){
    Column(
        modifier = Modifier //Functions of modifier applied sequentielly => Cool stuff with borader and padding: Hell in XML
            .background(Color.Red)
            .fillMaxHeight(0.7f)
            .fillMaxWidth() //.requiredWidth(600.dp)// force a hardcoded width
            .padding(top = 15.dp)
            .border(5.dp, Color.Black)
            .padding(5.dp)
            .border(5.dp, Color.Blue)
            .padding(5.dp)
            .border(5.dp, Color.Yellow)

        //,
        //horizontalAlignment = Alignment.Start,
        //verticalArrangement = Arrangement.SpaceAround
    ){
        Text("Issam", modifier = Modifier
            .offset(20.dp, 20.dp))
        Spacer(modifier = Modifier.height(50.dp)) //Push anything below with a 50dp => Give space to above composable

        Text(" Great", modifier = Modifier
            .border(5.dp, Color.Black)
            .padding(5.dp)
            .border(5.dp, Color.Blue)
            .padding(5.dp)
            .border(5.dp, Color.Yellow))

        Text("Why", modifier = Modifier.clickable { //Interactible

        })
    }
}


//1 - Example: Using columns and rows
@Composable
fun DemoColRow(){    //NB: Composable function always starts with a capital letter
    Row(
        modifier = Modifier
            .fillMaxSize(0.7f)
            .background(Color.Green),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        Text("Hello")
        Text("Issam")
        Text(" Again")
    }
}