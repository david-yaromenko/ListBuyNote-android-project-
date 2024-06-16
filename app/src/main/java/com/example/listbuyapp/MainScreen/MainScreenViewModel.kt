package com.example.listbuyapp.MainScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listbuyapp.Data.Entity.ShoppingListItem
import com.example.listbuyapp.Data.Repository.ShoppingListRepository
import com.example.listbuyapp.Dialog.DialogController
import com.example.listbuyapp.Dialog.DialogEvent
import com.example.listbuyapp.Screens.ShoppingListScreen.ShoppingListEvent
import com.example.listbuyapp.Utils.Routes
import com.example.listbuyapp.Utils.UIEvent
import com.example.listbuyapp.Utils.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


//4.33.

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ShoppingListRepository
): ViewModel(), DialogController {


    private val _uiEvent = Channel<UIEvent>()


    val uiEvent = _uiEvent.receiveAsFlow()

    var showFloatingButton = mutableStateOf(true)
        private set


    override var dialogTitle = mutableStateOf("List name: ")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set

    override var showEditableText = mutableStateOf(true)
        private set


    fun onEvent(event: MainScreenEvent){

        when(event){

            is MainScreenEvent.OnItemSave -> {

                if(editableText.value.isEmpty()) return

                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            null,
                            editableText.value,
                            getCurrentTime(),
                            0,
                            0

                        )
                    )
                }
            }

            is MainScreenEvent.OnNewItemClick -> {
                if(event.route == Routes.SHOPPING_LIST){
                    openDialog.value = true
                }else{
                    sendUiEvent(UIEvent.NavigateMain(Routes.NOTE_ITEM + "/-1"))
                }

            }

            is MainScreenEvent.Navigate ->{
                sendUiEvent(UIEvent.Navigate(event.route))
               showFloatingButton.value =
                   if (event.route == Routes.ABOUT || event.route == Routes.SETTINGS){
                       false
                   }else{
                       true
                   }
            }

            is MainScreenEvent.NavigateMain ->{
                sendUiEvent(UIEvent.NavigateMain(event.route))
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {

        when(event){
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }

            is DialogEvent.OnConfirm -> {
                    onEvent(MainScreenEvent.OnItemSave)
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }

    }

    private fun sendUiEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}