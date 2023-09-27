package cn.xqher.smartcity.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel(){
    private val appBarText = mutableStateOf("智慧城市")
}