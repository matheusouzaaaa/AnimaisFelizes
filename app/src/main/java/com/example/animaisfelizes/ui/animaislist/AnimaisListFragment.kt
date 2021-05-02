package com.example.animaisfelizes.ui.animaislist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.animaisfelizes.R
import com.example.animaisfelizes.data.db.AppDatabase
import com.example.animaisfelizes.data.db.dao.AnimalDAO
import com.example.animaisfelizes.data.db.entity.AnimalEntity
import com.example.animaisfelizes.extension.navigateWithAnimations
import com.example.animaisfelizes.repository.AnimalRepository
import com.example.animaisfelizes.repository.DatabaseDataSource
import com.example.animaisfelizes.ui.animais.AnimaisViewModel
import kotlinx.android.synthetic.main.animais_list_fragment.*

class AnimaisListFragment : Fragment(R.layout.animais_list_fragment) {

    private val viewModel: AnimaisListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val animalDAO: AnimalDAO = AppDatabase.getInstance(requireContext()).animalDAO
                val repository: AnimalRepository = DatabaseDataSource(animalDAO)
                return AnimaisListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // lista que esta vindo do banco de dados
        observeViewModelEvents()
        //configuração dos listeners dos clicks dos botões
        configureViewListeners()
    }

    private fun observeViewModelEvents() {
        viewModel.allAnimaisEvent.observe(viewLifecycleOwner){ allAnimais ->
            val animaisListAdapter = AnimaisListAdapter(allAnimais)

            //conectar o adaptar ao recyclerview
            recycler_animais.run{
                //todos items do mesmo tamanho
                setHasFixedSize(true)
                adapter = animaisListAdapter
            }
        }


    }

    override fun onResume() {
        super.onResume()
        viewModel.getAnimais()
    }

    private fun configureViewListeners(){
        // quando o botao de mais receber um click, altera para a próxima tela.
        fabAddAnimal.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.animaisFragment)
        }
    }
}