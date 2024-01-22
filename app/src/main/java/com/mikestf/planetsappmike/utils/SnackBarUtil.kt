package com.mikestf.planetsappmike.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.mikestf.planetsappmike.ui.theme.Pink40
import kotlinx.coroutines.launch

class SnackBarUtil {
    companion object {
        private val snackBarMessage = mutableStateOf("")
        private var isSnackBarVisible = mutableStateOf(false)

        fun showSnackBar(message: String) {
            snackBarMessage.value = message
            isSnackBarVisible.value = true
        }

        fun getSnackBarMessage() = snackBarMessage

        fun hideSnackBar() {
            isSnackBarVisible.value = false
        }

        fun isSnackBarVisible() = isSnackBarVisible

        @Composable
        fun SnackBarWithoutScaffold(
            message: String,
            isVisible: Boolean,
            onVisibilityChange: (Boolean) -> Unit
        ) {
            val snackState = remember { SnackbarHostState() }
            val snackScope = rememberCoroutineScope()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(10f),
                contentAlignment = Alignment.BottomCenter
            ) {
                SnackbarHost(
                    modifier = Modifier,
                    hostState = snackState
                ) {
                    Snackbar(
                        snackbarData = it,
                        containerColor = Pink40,
                        contentColor = Color.White
                    )
                }
            }

            if (isVisible) {
                LaunchedEffect(Unit) {
                    snackScope.launch {
                        snackState.showSnackbar(message)
                        onVisibilityChange(false)
                    }
                }
            }
        }
    }
}