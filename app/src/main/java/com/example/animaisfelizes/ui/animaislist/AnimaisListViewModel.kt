package com.example.animaisfelizes.ui.animaislist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animaisfelizes.data.db.entity.AnimalEntity
import com.example.animaisfelizes.repository.AnimalRepository
import kotlinx.coroutines.launch

class AnimaisListViewModel(
    private val repository: AnimalRepository
) : ViewModel() {

    private val _allAnimaisEvent = MutableLiveData<List<AnimalEntity>>()
    val allAnimaisEvent: LiveData<List<AnimalEntity>>
        get() = _allAnimaisEvent

    /*
        -> Serve para listar os animais recentemente inseridos no banco.
     */
    fun getAnimais() = viewModelScope.launch {
        _allAnimaisEvent.postValue(repository.getAllAnimais())
    }

}