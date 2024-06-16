package com.example.listbuyapp.Screens.AddItemScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listbuyapp.Data.Entity.AddItem
import com.example.listbuyapp.Data.Entity.ShoppingListItem
import com.example.listbuyapp.Data.Repository.AddRepository
import com.example.listbuyapp.DataStoreManager.DataStore
import com.example.listbuyapp.Dialog.DialogController
import com.example.listbuyapp.Dialog.DialogEvent
import com.example.listbuyapp.MainScreen.MainScreenEvent
import com.example.listbuyapp.Utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//4.35.
@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: AddRepository,
    dataStore: DataStore,
    savedStateHandle: SavedStateHandle
): ViewModel(), DialogController {


    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var addItem: AddItem? = null
    var listId: Int = -1
    var shoppingListItem: ShoppingListItem? = null

    var itemsList: Flow<List<AddItem>>? = null

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

        //Switch theme
        viewModelScope.launch {
            dataStore.getStringPreference(
                DataStore.THEME_COLOR,
                "#FFE0E0E0"
            ).collect{color ->

                bgColor.value = color

            }
        }
    }

    init {
        listId = savedStateHandle.get<String>("listId")?.toInt()!!
        itemsList = listId?.let { repository.getAllItemById(it) }
        Log.d("myLog", "List id viewmodel: $listId")

        viewModelScope.launch {
            shoppingListItem = repository.getListItemById(listId)
        }
    }

    var itemText =  mutableStateOf("")
        private set

    override var dialogTitle = mutableStateOf("Edit name: ")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set

    override var showEditableText = mutableStateOf(true)
        private set


    fun onEvent(event: AddItemEvent){
        when(event){

            is AddItemEvent.onItemSave -> {
                viewModelScope.launch {

                    if(listId == -1) return@launch


                    if(addItem != null){
                        if (addItem!!.name.isEmpty()){
                            sendUiEvent(UIEvent.ShowSnackBar("Name must not be empty"))
                            return@launch
                        }
                    }else{
                        if (itemText.value.isEmpty()){
                            sendUiEvent(UIEvent.ShowSnackBar("Name must not be empty"))
                            return@launch
                        }
                    }

                    repository.insertItem(
                        AddItem(
                            addItem?.id,
                            addItem?.name ?: itemText.value,
                            addItem?.isChecked ?: false,
                            listId
                        )
                    )
                    itemText.value = ""
                    addItem = null
                }
                updateShoppingListCount()
            }

            is AddItemEvent.OnShowEditDialog -> {

                addItem = event.item
                openDialog.value = true
                editableText.value = addItem?.name ?: ""

            }

            is AddItemEvent.OnTextChange -> {

                itemText.value = event.text

            }

            is AddItemEvent.OnDelete -> {

                viewModelScope.launch {
                    repository.deleteItem(event.item)
                }
                updateShoppingListCount()
            }

            is AddItemEvent.OnCheckedChange -> {

                viewModelScope.launch {
                    repository.insertItem(event.item)
                }
                updateShoppingListCount()
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
                openDialog.value = false
                addItem = addItem?.copy(name = editableText.value)
                editableText.value = ""
                onEvent(AddItemEvent.onItemSave)
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }

    }

    //функция чтобы перезаписывать счетчик сделанных задач и линии прогресса
    private fun updateShoppingListCount(){

        viewModelScope.launch {
            itemsList?.collect{list ->
                var counter = 0
                list.forEach{item ->
                    if(item.isChecked) counter++
                }
                shoppingListItem?.copy(
                    allItemsCount = list.size,
                    allSelectedItemsCount = counter
                )?.let { shItem ->
                    repository.updateItem(
                        shItem
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}