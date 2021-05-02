package com.example.animaisfelizes.ui.animaislist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.animaisfelizes.R
import com.example.animaisfelizes.data.db.entity.AnimalEntity
import kotlinx.android.synthetic.main.animais_list_fragment.*

class AnimaisListFragment : Fragment(R.layout.animais_list_fragment) {
    private lateinit var viewModel: AnimaisListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animaisListAdapter = AnimaisListAdapter(
            listOf(
                AnimalEntity(1,"Panqueca","5","Ana Carolina"),
                AnimalEntity(2,"Juju","2","Matheus Souza")
            )
        )

        //conectar o adaptar ao recyclerview
        recycler_animais.run{
            //todos items do mesmo tamanho
            setHasFixedSize(true)
            adapter = animaisListAdapter
        }


    }
}