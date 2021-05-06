package com.example.animaisfelizes.ui.animais

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.animaisfelizes.R
import com.example.animaisfelizes.data.db.AppDatabase
import com.example.animaisfelizes.data.db.dao.AnimalDAO
import com.example.animaisfelizes.extension.hideKeyboard
import com.example.animaisfelizes.extension.navigateWithAnimations
import com.example.animaisfelizes.repository.AnimalRepository
import com.example.animaisfelizes.repository.DatabaseDataSource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.animais_fragment.*
import kotlinx.android.synthetic.main.animais_list_fragment.*

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

    private val args: AnimaisFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //se eh uma inserção ou não
        args.animal?.let { animal ->
            button_animal.text = getString(R.string.editar_button)

            input_name.setText(animal.nome)
            input_idade.setText(animal.idade)
            input_proprietario.setText(animal.proprietario)

            button_delete.visibility = View.VISIBLE
            button_vacina.visibility = View.VISIBLE
        }

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.animalStateEventData.observe(viewLifecycleOwner) { animalState ->
            when (animalState) {
                is AnimaisViewModel.AnimalState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()

                    findNavController().popBackStack()
                }

                is AnimaisViewModel.AnimalState.Updated -> {
                    clearFields()
                    hideKeyboard()
                    findNavController().popBackStack()
                }

                is AnimaisViewModel.AnimalState.Deleted -> {
                    clearFields()
                    hideKeyboard()
                    findNavController().popBackStack()
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
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_animal.setOnClickListener {
            val nome = input_name.text.toString()
            val idade = input_idade.text.toString()
            val proprietario = input_proprietario.text.toString()

            viewModel.addOrUpdateAnimal(nome, idade, proprietario, args.animal?.id ?: 0)
        }

        button_delete.setOnClickListener {
            viewModel.removeAnimal(args.animal?.id ?: 0)
        }

        // quando o botao de mais receber um click, altera para a próxima tela.
        button_vacina.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.action_animaisFragment_to_vacinasFragment)
        }
    }
}