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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //DemoColRow()
            //DemoModifiers()
            //imgCardExample()
            //StylingTextExample()
            //StateExample()
            //ScaffoldExample()
            //ScaffoldSnackbarExample_2()
            //ListExample_1()
            ListExample_2()

        }
    }
}


//8.2 - Example: Sophisticated example of List usage
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListExample_2() {
    val items = remember {mutableStateListOf("Banana", "Apple", "Dates")}
    var selectedItem by remember { mutableStateOf<String?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(title = {Text("Shopping List Top Bar")})
        },
        content = {xPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(xPadding)
            ){
                LazyColumn(
                    modifier = Modifier.weight(1f) //Text in below at the bottom
                ) {
                    itemsIndexed(items){ index, item -> XListOfItems(
                        text = item,
                        index = index,
                        onItemClick = { selectedItem = it },
                        onDeleteClick = { items.removeAt(index)}
                    )
                    }
                }
                if (selectedItem != null){
                    Text(
                        text = "Slected item is $selectedItem",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton( onClick = {
                items.add("New Item ${items.size+1}")  //To dod: Add item name getter
            }) {
                    Icon(Icons.Default.Add, contentDescription = "Add item")
            }
        }
    )

}

@Composable
fun XListOfItems(
    text: String,
    index: Int,
    onItemClick: (String)->Unit,
    onDeleteClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onItemClick(text) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Item Icon",
            modifier = Modifier
                .size(24.dp)
                .padding(end = 16.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onDeleteClick) {
            Icon(Icons.Default.Delete, contentDescription = "delete item")
        }
    }
}




//8.1 - Example: Lists // RecyclerView using LazyColumn.
@Composable
fun ListExample_1(){
    LazyColumn {
        itemsIndexed( listOf("My", "List", "kinda", "replace", "Recycler", "View")) //Can accept any type of list, better than RV.
        { index, string -> Text(
            text = string,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp))
        }
    }
}


//7.2 - Example: Scaffold with a snackbar / coroutine
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldSnackbarExample_2(){
    val snackBarHostState = remember {SnackbarHostState()}
    val coroutineScop = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = {Text("Scaffold TopAppBar with Snackbar")})
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScop.launch {
                    snackBarHostState.showSnackbar(
                        message = "This the snackbar we waiting",
                        actionLabel = "Dismiss",
                        duration = SnackbarDuration.Short
                    )
                }

            }) {
                Icon(Icons.Default.Done, contentDescription = "Show snackBar")
            }
        },
        content = {
                mPadding -> Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(mPadding),
            contentAlignment = Alignment.Center
        ) {
            Button( onClick = {
                coroutineScop.launch {
                    snackBarHostState.showSnackbar(
                        message = "This is the snackbar we waiting/Coroutine",
                        actionLabel = "Dismiss",
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Text("Showing Snackbar")
            }
        }
        }
    )
}


//7.1 - Example: Scaffold with a snackbar / LauncherEffect
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldSnackbarExample_1(){
    val snackBarHostState = remember {SnackbarHostState()}
    var showSnackBar by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = {Text("Scaffold TopAppBar with Snackbar")})
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {showSnackBar = true}) {
                Icon(Icons.Default.Done, contentDescription = "Show snackBar")
            }
        },
        content = {
            mPadding -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(mPadding),
                contentAlignment = Alignment.Center
            ) {
               Button( onClick = { showSnackBar = true }) {
                   Text("Showing snackbar")
               }
            }
        }
    )

    if (showSnackBar){
        LaunchedEffect(snackBarHostState) {
            snackBarHostState.showSnackbar(
                message = "This the snackbar we waiting",
                actionLabel = "Dismiss",
                duration = SnackbarDuration.Short
            )
            showSnackBar = false
        }
    }
}


//6 - Example: Scaffold :
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(){
    //Hold the background color of TopAppBar in a MutableState
    var appBarColor by remember {mutableStateOf(Color.Blue)}

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Top App Bar by Scaffold")},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = appBarColor
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton( onClick = {
                //Toggle color on click
                appBarColor = if (appBarColor == Color.Blue) Color.Green else Color.Red
            }) {
                Icon(Icons.Default.Star, contentDescription = "Changing color of TopBar")
            }

        },
        content = { xPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(xPadding),
                contentAlignment = Alignment.Center
            ){
                Text("xContent: This scaffold can change the color of App Top Bar By clicking the Floating Action Button ")
            }

        }


    )

}


//5 - Example: Using State, Dynamic content (UI state)
@Composable
fun StateExample(){
    Column(Modifier.fillMaxSize()){
        val color = remember { mutableStateOf(Color.Yellow) }
        ColorBox(
            Modifier
                .weight(1f)
                .fillMaxSize()){
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