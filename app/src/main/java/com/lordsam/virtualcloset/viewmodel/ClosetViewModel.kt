package com.lordsam.virtualcloset.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lordsam.virtualcloset.data.ClosetData
import com.lordsam.virtualcloset.database.repo.ClosetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosetViewModel @Inject constructor(val repository: ClosetRepository): ViewModel() {

    private val _closetList = MutableStateFlow<List<ClosetData>>(emptyList())
    val noteList = _closetList.asStateFlow()
    //private var noteList = mutableStateListOf<Note>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllClosets().distinctUntilChanged()
                .collect{ listOfClosets ->
                    if (listOfClosets.isNullOrEmpty()){
                        Log.d("Empty", "Empty List")
                    }else{
                        _closetList.value = listOfClosets
                    }
                }
        }
    }

    fun addCloset(closet: ClosetData) = viewModelScope.launch { repository.addCloset(closet) }

    fun updateCloset(closet: ClosetData) = viewModelScope.launch { repository.updateCloset(closet) }

    fun deleteCloset(closet: ClosetData) = viewModelScope.launch { repository.deleteCloset(closet) }

    fun getClosetByCategory(category: String) = viewModelScope.launch { repository.getClosetsByCategory(category) }


}