package com.example.listbuyapp.Screens.NoteListScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listbuyapp.Data.Entity.NoteItem
import com.example.listbuyapp.Data.Repository.NoteRepository
import com.example.listbuyapp.DataStoreManager.DataStore
import com.example.listbuyapp.Dialog.DialogController
import com.example.listbuyapp.Dialog.DialogEvent
import com.example.listbuyapp.Screens.ShoppingListScreen.ShoppingListEvent
import com.example.listbuyapp.Utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


//4.41.


@HiltViewModel
class NoteListViewModel  @Inject constructor(
    private val repository: NoteRepository,
    dataStore: DataStore
): ViewModel(),DialogController{

    val noteList = repository.getAllItem()
    private var noteItem: NoteItem? = null

    val noteListFlow = repository.getAllItem()
    private var noteItem2: NoteItem? = null

    val noteList2 = mutableStateOf(listOf<NoteItem>())

    var originNoteList = listOf<NoteItem>()


    private val _uiEvent = Channel<UIEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    var titleColor = mutableStateOf("#000000")

    var searchText = mutableStateOf("")
        private set

    var bgColor = mutableStateOf("#FFE0E0E0")


    override var dialogTitle = mutableStateOf("Delete this note?")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set

    override var showEditableText = mutableStateOf(false)
        private set

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
            noteListFlow.collect{list ->
                noteList2.value = list
                originNoteList = list
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


    fun onEvent(event: NoteListEvent){
        when(event){
            is NoteListEvent.onShowDeleteDialog -> {
                openDialog.value = true
                noteItem = event.item
            }

            is NoteListEvent.onItemClick -> {
                sendUiEvent(UIEvent.Navigate(event.route))
            }

            is NoteListEvent.unDoneDeleteItem ->{
                viewModelScope.launch{
                    repository.insertItem(noteItem!!)
                }
            }

            is NoteListEvent.onTextSearchChange ->{
                searchText.value = event.text

                noteList2.value = originNoteList.filter {
                    it.title.lowercase().startsWith(searchText.value.lowercase())
                }
            }

        }

    }

    override fun onDialogEvent(event: DialogEvent){

        when(event){
            is DialogEvent.OnCancel -> {
                openDialog.value = false
            }

            is DialogEvent.OnConfirm -> {
                viewModelScope.launch {
                    repository.deleteItem(noteItem!!)
                    sendUiEvent(UIEvent.ShowSnackBar(
                        "Undone delete note?"
                    ))
                }
                openDialog.value = false

            }
            else -> {}
        }
    }

    private fun sendUiEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}