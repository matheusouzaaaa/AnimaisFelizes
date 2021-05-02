package com.example.animaisfelizes.ui.animais

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animaisfelizes.R
import com.example.animaisfelizes.data.db.AppDatabase
import com.example.animaisfelizes.data.db.dao.AnimalDAO
import com.example.animaisfelizes.extension.hideKeyboard
import com.example.animaisfelizes.repository.AnimalRepository
import com.example.animaisfelizes.repository.DatabaseDataSource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.animais_fragment.*

class AnimaisFragment : Fragment(R.layout.animais_fragment) {

    private val viewModel: AnimaisViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val animalDAO: AnimalDAO = AppDatabase.getInstance(requireContext()).animalDAO
                val repository: AnimalRepository = DatabaseDataSource(animalDAO)
                return AnimaisViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.animalStateEventData.observe(viewLifecycleOwner){ animalState ->
            when (animalState){
                is AnimaisViewModel.AnimalState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                }
            }
        }

        viewModel.messageStateEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        input_name.text?.clear()
        input_idade.text?.clear()
        input_proprietario.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if(parentActivity is AppCompatActivity){
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_animal.setOnClickListener {
            val nome = input_name.text.toString()
            val idade = input_idade.text.toString()
            val proprietario = input_proprietario.text.toString()

            viewModel.addAnimal(nome, idade, proprietario)
        }
    }
}