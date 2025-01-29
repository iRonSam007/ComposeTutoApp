package com.example.composetutoapp

import android.Manifest.permission.CAMERA
import android.content.Context
import android.graphics.Paint.Align
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
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
            //ListExample_2()
            //ConstraintLayoutCompose_1()
            //ConstraintLayoutCompose_2()
            //EffectHandlers()
            //Animated_CircularProgressBar()
            //Animated_CircularProgressBarCanvas()
            //Animated_ExpandedCard()
            //LazyGridExample()
            //MultiSelectLazyColumn()
            //NavigationManager()
            //ApplicationNavGraph()
            //BottomNavigationBarWithBadges()
            //DrawerNavigation()
            BottomSheetExample()
        }
    }
}

//12.5 - Bottom sheet: button show works:
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetExample() {
    // Bottom sheet state
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true // Skips partially expanded state
    )
    val scope = rememberCoroutineScope()

    // Scaffold with main content
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bottom Sheet Example") }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { scope.launch { sheetState.show() } }) {
                    Text("Show Bottom Sheet")
                }
            }
        }
    )

    // Modal Bottom Sheet
    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = { scope.launch { sheetState.hide() } },
            sheetState = sheetState
        ) {
            BottomSheetContent(
                onItemSelected = { selectedItem ->
                    scope.launch { sheetState.hide() }
                    // Handle selected item
                }
            )
        }
    }
}

@Composable
fun BottomSheetContent(onItemSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Choose an option", style = MaterialTheme.typography.headlineSmall)
        Divider()
        listOf("Option 1", "Option 2", "Option 3").forEach { option ->
            TextButton(onClick = { onItemSelected(option) }) {
                Text(option)
            }
        }
    }
}




//12.4 - Drawer Navigation
@Composable
fun DrawerNavigation(){
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onDestinationClicked = { route ->
                    scope.launch { drawerState.close() } //Close Drawer on Selection
                    navController.navigate(route){
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }

            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ){
            composable("home") { HomeScreenDN(scope, drawerState)  }
            composable("settings"){ SettingsScreenDN(scope, drawerState) }
            composable("profile"){ ProfileScreenDN(scope, drawerState) }
        }
    }
}

@Composable
fun DrawerContent(onDestinationClicked: (String) -> Unit){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ){
        Text(
            text = "Navigation Drawer",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        DrawerItem(title = "Home", route = "home", onClick = onDestinationClicked)
        DrawerItem(title = "Profile", route = "profile", onClick = onDestinationClicked)
        DrawerItem(title = "Settings", route = "settings", onClick = onDestinationClicked)
    }
}

@Composable
fun DrawerItem(title: String, route: String, onClick: (String) -> Unit){
    ClickableText(
        text = AnnotatedString(title),
        onClick = { onClick(route) },
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun HomeScreenDN(scope: CoroutineScope, drawerState: DrawerState){
    ContentScreenDN(
        scope = scope,
        drawerState = drawerState,
        title = "Home Screen"
    )
}

@Composable
fun ProfileScreenDN(scope: CoroutineScope, drawerState: DrawerState){
    ContentScreenDN(
        scope = scope,
        drawerState = drawerState,
        title = "Profile Screen"
    )
}

@Composable
fun SettingsScreenDN(scope: CoroutineScope, drawerState: DrawerState){
    ContentScreenDN(
        scope = scope,
        drawerState = drawerState,
        title = "Settings Screen"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreenDN(scope: CoroutineScope, drawerState: DrawerState, title: String){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(title)},
                navigationIcon = {
                    IconButton(onClick = {scope.launch { drawerState.open() }}) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        }
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ){
            Text(text = title, style = MaterialTheme.typography.headlineLarge)
        }
    }
}



//12.3 - Bottom navigation with badges
@Composable
fun BottomNavigationBarWithBadges(){
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Notifications,
        BottomNavItem.Profile,
        )

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, items = items) }
    ) { xPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(xPadding)
        ){
            composable(BottomNavItem.Home.route) { HomeScreenBN() }
            composable(BottomNavItem.Notifications.route) { NotificationsScreen()  }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
        }
    }
}
@Composable
fun BottomNavigationBar(navController: NavController, items: List<BottomNavItem>){
    NavigationBar {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        items.forEach{ item ->
            val isSelected = currentRoute ==item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(navController.graph.startDestinationId){saveState = true}
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    if (item.badgeCount > 0 ){
                        BadgedBox(badge = {Badge { Text("${item.badgeCount}") }}) {
                            Icon(imageVector = item.icon, contentDescription = item.title)
                        }
                    } else {
                        Icon(imageVector = item.icon, contentDescription = item.title)
                    }
                },
                label = {Text(item.title)}
            )
        }
    }
}

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String, val badgeCount: Int = 0){
    object Home: BottomNavItem("Home", Icons.Default.Home, "home" )
    object Notifications : BottomNavItem("Notifications", Icons.Default.Notifications, "notifications", badgeCount = 5)
    object Profile : BottomNavItem("Profile", Icons.Default.Person, "profile")
}

@Composable
fun HomeScreenBN(){
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Home Screen", style = MaterialTheme.typography.headlineMedium)
    }
}
@Composable
fun NotificationsScreen(){
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Notifications Screen")
    }
}
@Composable
fun ProfileScreen(){
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        Text(text = "Profile screen")
    }

}





//12.2 - Navigation/ Splash Screen
@Composable
fun ApplicationNavGraph(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash"){
            SplashScreen(navController)
        }
        composable("home") {
            HomeScreenSplash()
        }
    }
}
@Composable
fun HomeScreenSplash(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Zelcome to Home Screen")

    }
}
@Composable
fun SplashScreen(navController: NavController){
    //Animating the logo scaling
    val scale = remember {Animatable(0f)}

    //Trigger the animation when the composable is displayed
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
        //delay to show the splash screen:
        delay(2000)
        navController.navigate("home"){
            //clear the splash screen from the back stack:
            popUpTo("splash"){inclusive = true}
        }
    }

    //UI of the splash screen:
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.iron_man),
                contentDescription = "App logo",
                modifier = Modifier
                    .size(150.dp)
                    .scale(scale.value)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "My beautiful app",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}

//12.1 - Navigation between two screens
@Composable
fun NavigationManager(){
    val navController = rememberNavController()

    Scaffold { xPadding ->
        NavHost(                            // 1 - Defines the navigation graph
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(xPadding)
        ) {// 2 - Host composables of each root
            // home route composable
            composable("home") {
                HomeScreen(onNavigationToDetails = { navController.navigate("details")})
            }
            // details route composable
            composable("details") {
                DetailsScreen(onNavigationToHomeScreen = { navController.navigate("home")})
            }
        }
    }
}

@Composable
fun HomeScreen(onNavigationToDetails: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Home Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigationToDetails ) {
            Text("Switch to DetailsScreen")
        }
    }
}

@Composable
fun DetailsScreen(onNavigationToHomeScreen: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Details Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigationToHomeScreen) {
            Text("Switch to HomeScreen")
        }
    }

}


//11.5 Multi select lazy column
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MultiSelectLazyColumn() {
    val items = List(100) { "Product $it" }
    val selectedItems = remember { mutableStateListOf<String>() }

    LazyColumn(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        stickyHeader {
            Text(
                text = "Selected Items: ${selectedItems.size}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(8.dp)
            )
        }

        items(items){item ->
            ColumnRowContent(
                item = item,
                isSelected = selectedItems.contains(item),
                onSelectChange = { isSelected ->
                    if (isSelected) selectedItems.add(item) else selectedItems.remove(item)
                }
            )
        }
    }
}

@Composable
fun ColumnRowContent(item: String, isSelected: Boolean, onSelectChange: (Boolean) -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                onSelectChange(!isSelected)
            }
    ) {
        val icon: ImageVector = if(isSelected) Icons.Default.Check else Icons.Default.Add
        Text(text = item, modifier = Modifier.weight(1f))
        Icon(
            imageVector = icon,
            contentDescription = if(isSelected) "Selected" else "Not selected",
            tint = if(isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
            modifier = Modifier
                .absoluteOffset()
                .size(24.dp)
        )
    }
}


//11.4 Lazy Grid
@Composable
fun LazyGridExample() {
    val items = List(100) { "Prod $it" }

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        state = rememberLazyGridState(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(items) { item ->
            GridItem(item)
        }
    }
}

@Composable
fun GridItem(text: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) //Square shape
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}


//11.3 - Animation: State-driven, visibility, transition, infinite animation, Physics-based(Spring bounce)
@Composable
fun Animated_ExpandedCard(){
    // State for Card expansion
    var isCardExpanded by remember {mutableStateOf(false)}

    // State for progress
    var progress by remember { mutableStateOf(0.0f) }

    // Infinite pulse animation
    val infiniteTransition = rememberInfiniteTransition()
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Animating size for Card expansion
    val cardHeight by animateDpAsState(
        targetValue = if (isCardExpanded) 300.dp else 100.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy) // Try with medium
    )

    // Progress update animation's scope
    val coroutineScope = rememberCoroutineScope()

    //UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(width = 300.dp, height = cardHeight)
                .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(17.dp))
                .padding(16.dp)
                .clickable {
                    isCardExpanded = !isCardExpanded
                    if (isCardExpanded) {
                        coroutineScope.launch {
                            //When is expanded, it will display progress
                            for (i in 1..100) {
                                delay(50)
                                progress = i / 100f
                            }
                        }
                    }
                }
        ){
            // Visibility when card is collapsed
            AnimatedVisibility(visible = !isCardExpanded){
                Text(
                    text = "Expand by tapping",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            // Visibility when card is expanded
            AnimatedVisibility(visible = isCardExpanded, enter = fadeIn(), exit = fadeOut()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    CircularProgressIndicator(
                        progress = progress,
                        modifier = Modifier.size(100.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        strokeWidth = 8.dp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .size(50.dp * pulseScale)
                            .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(50))
                            .padding(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Progress is ${(progress * 100).toInt()}%",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

            }
        }

    }
}

//11.2 - Animation CPB Using Canvas
@Composable
fun Animated_CircularProgressBarCanvas(){
    // progress 0.0f to 1.0f
    var progress by remember { mutableStateOf(0.0f) }

    // Animate progress for smooth update
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(modifier = Modifier.size(200.dp)){
            Canvas(modifier = Modifier.fillMaxSize()) {
                //Canvas dimensions
                val canvaSize = size.minDimension
                val strokeWith = 12.dp.toPx()

                //draw background circle
                drawCircle(
                    color = Color.LightGray,
                    radius = canvaSize/2 - strokeWith/2,
                    center = center,
                    style = Stroke(width = strokeWith)
                )

                //draw progress arc
                drawArc(
                    color = Color(0xFF6200EE),
                    startAngle = 90f,                      // Start at the top
                    sweepAngle = animatedProgress * 360f,   // progress angle
                    useCenter = false,
                    style = Stroke(
                        width = strokeWith,
                        cap = StrokeCap.Round               // Rounded end
                    ),
                    topLeft = Offset(
                        x = strokeWith / 2,
                        y = strokeWith / 2
                    ),
                    size = Size(
                        width = canvaSize - strokeWith,
                        height = canvaSize - strokeWith
                    )
                )
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick  = { if(progress > 0) progress -=0.1f}, enabled = progress >0)
            {
                Text("Decrease")
            }
            Button(onClick = { if(progress < 1.0f ) progress +=0.1f }, enabled = progress < 1) {
                Text("Increase")
            }
        }

    }
}

//11.1 - Animation: ProgressBar
@Composable
fun Animated_CircularProgressBar(){
    // Progress state (0.0f to 1.0f)
    var progress by remember {mutableStateOf(0.0f)}

    // Animate progress changes
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec  = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    //UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CircularProgressIndicator(
            progress =  animatedProgress,
            modifier = Modifier.size(150.dp),
            strokeWidth = 12.dp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Display progress value
        Text(
            text = "Progress is ${(progress*100).toInt()}%",
            //fontSize = 24.dp,
            //fontWeight = FontWeight.Bold,
            //color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick  ={ if(progress > 0) progress -=0.1f}) {
                Text("Decrease")
            }
            Button(onClick = { if(progress < 1.0f ) progress +=0.1f }) {
                Text("Increase")
            }
        }
    }
}


//10.2 - Effect Handlers: Sophisticated Example{ SideEffect, LaunchedEffect, DisposableEffect}
@Composable
fun EffectHandlers(){
    val context = LocalContext.current
    LoginPage { outputMessage -> Toast.makeText(context, outputMessage, Toast.LENGTH_SHORT).show() }
}

@Composable
fun LoginPage(toaster: (String) -> Unit ){
    val username = remember { mutableStateOf("") }
    val loginStatus = remember { mutableStateOf("Waiting for input") }
    val timerPointer = remember {mutableStateOf(0)}
    val countRecompo = remember { mutableStateOf(0) }

    //count how many successful recomposition were done

    SideEffect {
        countRecompo.value +=1
        //toaster("Login screen recomposed: ${countRecompo.value}")
    }

    LaunchedEffect(Unit){
        delay(2000) // Simulate saved credential loading
        loginStatus.value = "First recomposition started, Getting ready for login"
    }

    //Calculate the time Login screen stayed
    DisposableEffect(Unit) {
        val timerJob = TimerUpdate(timerPointer)
        onDispose {
            timerJob.cancel()
            Log.d("DisposableEffectLog", "Login screen exited, timer stopped, timer was pointing to: ${timerPointer.value}")
            Log.d("DisposableEffectLog", "Recomposed ${countRecompo.value}")

        }
    }


    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Login Status: ${loginStatus.value}")
        Text("Time on screen: ${timerPointer.value} seconds")

        BasicTextField(
            value = username.value,
            onValueChange = { username.value = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(onClick = {
            if (username.value.isNotEmpty()) {
                loginStatus.value = "Logged in as ${username.value}"
            } else {
                loginStatus.value = "Please enter a username"
            }
        }) {
            Text("Click Login")
        }
    }
}
fun TimerUpdate(tPointer: MutableState<Int>): Job{
    val job =  Job() // for explicit control over coroutine lifecycle
    CoroutineScope(job).launch {
        while(isActive){
            delay(1000)
            tPointer.value += 1
        }
    }
    return job
}

//10.1.3 - Effect Handlers: LaunchedEffect
@Composable
fun LaunchedEffectUpdate(es: MutableState<String>, key: Int){ //needless param imho
    //When key change or Composable enters composition,
    //Execute suspend tasks(or coroutines but tied to the composition lifecycle)
    LaunchedEffect(key1 = key) {
        delay(1000)
        es.value = "Update with key: $key"
    }
    Text(text = "LauncherEffect example: ${es.value}")
}
//10.1.2 - Effect Handlers: SideEffect
@Composable
fun SideEffectUpdate(externalState: MutableState<String>){
    val recompositionCount = remember {mutableStateOf(0)}
    recompositionCount.value++

    //Execute non-suspending (tasks)side effect after a successful recomposition (of the composable called within)
    SideEffect {
        externalState.value = "Recomposed ${recompositionCount.value} times"
    }
    Text(text = "SideEffect example: ${recompositionCount.value}")

}
//10.1.1 - Effect Handlers: DisposableEffect
@Composable
fun DisposableEffectUpdate(es: MutableState<String>, dependency: Int){
    //Used for side effects that requires clean-up: starting/stopping observers, listeners..
    DisposableEffect(dependency) {
        es.value = "Effect started with dependency: $dependency"
        onDispose {
            es.value ="Effect clean up for $dependency"
        }
    }
    Text(text = "DisposableEffect example: ${es.value}")

}




//9.2 - Example: ConstraintLayout (Complex positioning)
@Composable
fun ConstraintLayoutCompose_2(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        val (title, subtitle, button) = createRefs()
        BasicText(
            text = "title",
            modifier = Modifier
                .constrainAs(title){
                    top.linkTo(parent.top, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )

        BasicText(
            text = "This is the subtitle",
            modifier = Modifier
                .constrainAs(subtitle){
                    top.linkTo(title.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Green,
                textAlign = TextAlign.Center
            )
        )

        Button(
            onClick = {},
            modifier = Modifier
                .constrainAs(button){
                    top.linkTo(subtitle.bottom, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text("Click Here Sam")
        }
    }
}

//9.1 - Example: ConstraintLayout
@Composable
fun ConstraintLayoutCompose_1(){
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (title, button) = createRefs() //Each composable under CL shall have a ref and constraints Modifier shall apply on him

        Text(
            text = "Hello, IronSam from constraint layout",
            modifier = Modifier.constrainAs(title){
                top.linkTo(parent.top, margin = 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button){
                bottom.linkTo(parent.bottom, margin = 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text("click Here")
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




//8.1 - Example: Lists // Eq to RecyclerView using LazyColumn.
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