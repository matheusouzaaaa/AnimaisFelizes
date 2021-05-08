package com.example.animaisfelizes.ui.animais

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animaisfelizes.R
import com.example.animaisfelizes.repository.AnimalRepository
import kotlinx.coroutines.launch

/*
* -> Recebe o repository.
* -> Para fazer as ações, deve-se iniciar o escopo dos Coroutines. Para isso deve-se utilizar um extension do lifecycle. (viewModelScope.launch)
*   -> assim não precisa ficar se preocupando com os lifecycle, pois isso é tratado por baixo dos panos.
* */

class AnimaisViewModel(private val repository: AnimalRepository) : ViewModel() {

    /*
        -> Atribui valores para os MutableLiveData para a view receber os valores.
     */
    private val _animalStateEventData = MutableLiveData<AnimalState>()
    val animalStateEventData: LiveData<AnimalState>
        get() = _animalStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageStateEventData: LiveData<Int>
        get() = _messageEventData

    fun addOrUpdateAnimal(nome: String, idade: String, proprietario: String, id: Long = 0) {
        if (id > 0) {
            //eh atualização
            updateAnimal(id, nome, idade, proprietario)
        } else {
            // eh inserção
            addAnimal(nome, idade, proprietario)
        }
    }

    private fun updateAnimal(id: Long, nome: String, idade: String, proprietario: String) =
        viewModelScope.launch {
            try {
                repository.updateAnimal(id, nome, idade, proprietario)

                _animalStateEventData.value = AnimalState.Updated
                _messageEventData.value = R.string.animal_atualizado_sucesso

            } catch (ex: Exception) {
                _messageEventData.value = R.string.animal_atualizado_erro
                Log.e(TAG, ex.toString())
            }
        }

    private fun addAnimal(nome: String, idade: String, proprietario: String) =
        viewModelScope.launch {
            try {
                val id = repository.insertAnimal(nome, idade, proprietario)

                if (id > 0) {
                    //foi inserido
                    _animalStateEventData.value = AnimalState.Inserted
                    _messageEventData.value = R.string.animal_inserido_sucesso
                }
            } catch (ex: Exception) {
                _messageEventData.value = R.string.animal_inserido_erro
                Log.e(TAG, ex.toString())
            }
        }

    fun removeAnimal(id: Long) = viewModelScope.launch {
        try {
            if (id > 0) {
                repository.deleteAnimal(id)
                _animalStateEventData.value = AnimalState.Deleted
                _messageEventData.value = R.string.animal_excluido_sucesso
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.animal_excluido_erro
            Log.e(TAG, ex.toString())
        }
    }

    /*
        -> Eventos
     */

    sealed class AnimalState {
        object Inserted : AnimalState()
        object Updated : AnimalState()
        object Deleted : AnimalState()
    }

    /*
        -> Criado o companion object para criar uma constante, que vai ser usada nos Logs.
    */

    companion object {
        private val TAG = AnimaisViewModel::class.java.simpleName
    }
}