package com.example.listbuyapp.Screens.NoteItemScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listbuyapp.Data.Entity.NoteItem
import com.example.listbuyapp.Data.Repository.NoteRepository
import com.example.listbuyapp.DataStoreManager.DataStore
import com.example.listbuyapp.Utils.UIEvent
import com.example.listbuyapp.Utils.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


//4.39.


@HiltViewModel
class NoteItemViewModel @Inject constructor(
    private val repository: NoteRepository,
    savedStateHandle: SavedStateHandle,
    dataStore: DataStore
): ViewModel() {


    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var noteId = -1
    private var noteItem: NoteItem? = null

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

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("") //
        private set


    init {
        noteId = savedStateHandle.get<String>("noteId")?.toInt() ?: -1
        if(noteId != -1){
            viewModelScope.launch {
                repository.getNoteItemById(noteId).let {noteItem ->
                    title = noteItem.title
                    description = noteItem.description
                    this@NoteItemViewModel.noteItem = noteItem
                }
            }
        }
    }

    fun onEvent(event: NoteEvent){
        when(event){
            is NoteEvent.onSave -> {
                viewModelScope.launch {

                    if(title.isEmpty()){
                        sendUiEvent(UIEvent.ShowSnackBar(
                            "Title can not be empty"
                        ))
                        return@launch
                    }

                    repository.insertItem(
                        NoteItem(
                        noteItem?.id,
                            title,
                            description,
                            getCurrentTime()
                        )
                    )

                    sendUiEvent(UIEvent.PopBackStack)
                }
            }

            is NoteEvent.onTitleChange -> {
                title = event.title
            }

            is NoteEvent.onDescriptionChange -> {
                description = event.description
            }
        }
    }


    private fun sendUiEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}