package com.example.animaisfelizes.ui.vacinas

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
import com.example.animaisfelizes.ui.animais.AnimaisFragmentArgs
import com.example.animaisfelizes.ui.animais.AnimaisViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.animais_fragment.*
import kotlinx.android.synthetic.main.animais_fragment.button_vacina
import kotlinx.android.synthetic.main.vacinas_fragment.*

class VacinasFragment : Fragment(R.layout.vacinas_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        checkBox1.isChecked = true;
        checkBox2.isChecked = true;
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        // quando o botao de mais receber um click, altera para a próxima tela.
        button_vacina.setOnClickListener {
            hideKeyboard()
            findNavController().popBackStack()
            Snackbar.make(requireView(), "Atualizada com sucesso", Snackbar.LENGTH_LONG).show()
        }
    }
}