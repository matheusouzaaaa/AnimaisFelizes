package com.example.animaisfelizes.ui.animaislist

import androidx.lifecycle.ViewModel
import com.example.animaisfelizes.repository.AnimalRepository

class AnimaisListViewModel(
    private val repository: AnimalRepository
) : ViewModel() {

    val allAnimaisEvent = repository.getAllAnimais()

}