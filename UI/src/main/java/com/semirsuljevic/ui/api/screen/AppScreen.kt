package com.semirsuljevic.ui.api.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.semirsuljevic.ui.R
import com.semirsuljevic.ui.api.common.UiText
import com.semirsuljevic.ui.api.navigation.NavigationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    bottomBar: (@Composable () -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    navigationViewModel: NavigationViewModel = hiltViewModel(),
    canNavigateBack: Boolean = true,
    content: @Composable (PaddingValues) -> Unit,
    
    //this view model serves to implement back pop for each screen
    //it has AppNavigator injected inside it
    //navigationViewModel: NavigationViewModel = hiltViewModel(),
    
) {
    Scaffold (
        bottomBar = {
            if(bottomBar != null) {
                bottomBar()
            }
        },
        topBar = {
            if(!canNavigateBack) {
                return@Scaffold
            }
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = navigationViewModel::pop
                    ) { Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = UiText.StringResource(R.string.go_back).asString()
                    )}
                }
                
            )
            
        },
        containerColor = MaterialTheme.colorScheme.background
    ){ values: PaddingValues -> 
        content(values)
        //simple way I handle loading, passing resource class that manifests state
//        AnimatedVisibility(visible = resource is AppResource.Loading) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(
//                        HechimTheme.colors.backgroundDefault
//                    ),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                CircularProgressIndicator(
//                    color = HechimTheme.colors.primary
//
//                )
//            }
//        }
        //similar way error is handled - project specific
//        AnimatedVisibility(visible = resource is HechimResource.Error) {
//            Column(
//                modifier = Modifier
//                    .padding(it)
//                    .padding(horizontal = HechimTheme.sizes.scaffoldHorizontal)
//                    .fillMaxSize()
//                    .background(
//                        HechimTheme.colors.backgroundDefault
//                    ),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Spacer(modifier = Modifier.weight(0.1f))
//                Image(painter = painterResource(id = R.drawable.img_nothing_found), contentDescription = "Error")
//                Spacer(modifier = Modifier.height(HechimTheme.sizes.xxLarge))
//                if(resource is HechimResource.Error) {
//                    Text(
//                        resource.error.message,
//                        style = HechimTheme.fonts.bodyLarge,
//                        color = HechimTheme.colors.textDefault,
//                        textAlign = TextAlign.Center
//                    )
//                }
//                Spacer(modifier = Modifier.weight(1f))
//                HechimButton(
//                    onClick = {
//                        config.errorReset?.invoke()
//                    },
//                    text = "Retry strategy"
//                )
//                Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
//            }
//        }
    }
}
