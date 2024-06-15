package com.example.sachosaeng.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SplashViewModel : ViewModel(), ContainerHost<Boolean, Unit> {
    override val container: Container<Boolean, Unit> = container(true)

    init {
        showSplashWhileThreeSecond()
    }

    fun showSplashWhileThreeSecond() = intent {
        viewModelScope.launch {
            delay(1000)
            reduce { false }
        }
    }
}