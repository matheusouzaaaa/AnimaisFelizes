package com.example.animaisfelizes.ui.animais

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.animaisfelizes.R

class AnimaisFragment : Fragment() {

    private lateinit var viewModel: AnimaisViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.animais_fragment, container, false)
    }
}