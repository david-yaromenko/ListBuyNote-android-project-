package com.example.listbuyapp.Screens.ShoppingListScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listbuyapp.Data.Entity.ShoppingListItem
import com.example.listbuyapp.Data.Repository.ShoppingListRepository
import com.example.listbuyapp.DataStoreManager.DataStore
import com.example.listbuyapp.Dialog.DialogController
import com.example.listbuyapp.Dialog.DialogEvent
import com.example.listbuyapp.Utils.UIEvent
import com.example.listbuyapp.Utils.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//4.28.

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository,
    dataStore: DataStore
    ):ViewModel(), DialogController {

    val list = repository.getAllItems()

    private val _uiEvent = Channel<UIEvent>()


    val uiEvent = _uiEvent.receiveAsFlow()


    private var listItem: ShoppingListItem? = null

    override var dialogTitle = mutableStateOf("")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set

    override var showEditableText = mutableStateOf(false)
        private set

    var titleColor = mutableStateOf("#000000")

    var bgColor = mutableStateOf("#FFE0E0E0")

    init {
        viewModelScope.launch {
            dataStore.getStringPreference(
                DataStore.TITLE_COLOR,
                "#000000"
            ).collect{color ->

                titleColor.value = color

            }
        }

        viewModelScope.launch {
            dataStore.getStringPreference(
                DataStore.THEME_COLOR,
                "#FFE0E0E0"
            ).collect{color ->

                bgColor.value = color

            }
        }
    }


    fun onEvent(event: ShoppingListEvent){

        when(event){

            is ShoppingListEvent.OnItemSave -> {

                if(editableText.value.isEmpty()) return

                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            listItem?.id,
                            editableText.value,
                            listItem?.time ?: getCurrentTime(),
                            listItem?.allItemsCount ?: 0,
                            listItem?.allSelectedItemsCount ?: 0

                        )
                    )
                }
            }

            is ShoppingListEvent.OnItemClick -> {
                sendUiEvent(UIEvent.Navigate(event.route))
            }

            is ShoppingListEvent.OnShowEditDialog -> {

                listItem = event.item
                openDialog.value = true
                editableText.value = listItem?.name ?: ""
                dialogTitle.value = "List name: "
                showEditableText.value = true
            }

            is ShoppingListEvent.OnShowDeleteDialog -> {
                listItem = event.item
                openDialog.value = true
                dialogTitle.value = "Delete this item?"
                showEditableText.value = false
            }
        }
    }


    override fun onDialogEvent(event: DialogEvent){

        when(event){
            is DialogEvent.OnCancel -> {
                openDialog.value = false
            }

            is DialogEvent.OnConfirm -> {

                if(showEditableText.value){
                    onEvent(ShoppingListEvent.OnItemSave)
                } else {
                    viewModelScope.launch {
                        listItem?.let { repository.deleteItem(it) }
                    }
                }
                openDialog.value = false

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