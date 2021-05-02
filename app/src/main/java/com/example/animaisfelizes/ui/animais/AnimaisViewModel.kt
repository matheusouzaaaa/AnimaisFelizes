package com.example.animaisfelizes.ui.animais

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animaisfelizes.R
import com.example.animaisfelizes.repository.AnimalRepository
import kotlinx.coroutines.launch

class AnimaisViewModel(private val repository: AnimalRepository) : ViewModel() {

    private val _animalStateEventData = MutableLiveData<AnimalState>()
    val animalStateEventData: LiveData<AnimalState>
        get() = _animalStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageStateEventData: LiveData<Int>
        get() = _messageEventData


    fun addAnimal(nome: String, idade: String, proprietario: String) = viewModelScope.launch {
        try{
            val id = repository.insertAnimal(nome,idade,proprietario)

            if(id > 0){
                //foi inserido
                _animalStateEventData.value = AnimalState.Inserted
                _messageEventData.value = R.string.animal_inserido_sucesso
            }
        } catch (ex: Exception){
            _messageEventData.value = R.string.animal_inserido_erro
            Log.e(TAG,ex.toString())
        }
    }

    sealed class AnimalState{
        object Inserted: AnimalState()
    }

    companion object {
        private val TAG = AnimaisViewModel::class.java.simpleName
    }
}