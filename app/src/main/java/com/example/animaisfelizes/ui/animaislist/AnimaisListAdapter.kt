package com.example.animaisfelizes.ui.animaislist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animaisfelizes.R
import com.example.animaisfelizes.data.db.entity.AnimalEntity
import kotlinx.android.synthetic.main.animal_item.view.*

class AnimaisListAdapter(
    private val animais: List<AnimalEntity>
): RecyclerView.Adapter<AnimaisListAdapter.AnimalListViewHolder>() {

    var onItemClick: ((entity: AnimalEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)

        return AnimalListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalListViewHolder, position: Int) {
        holder.bindView(animais[position])
    }

    override fun getItemCount() = animais.size

    inner class AnimalListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val textViewAnimalNome: TextView = itemView.text_animal_nome
        private val textViewAnimalIdade: TextView = itemView.text_animal_idade
        private val textViewAnimalProprietario: TextView = itemView.text_animal_proprietario

        fun bindView(animal: AnimalEntity){
            textViewAnimalNome.text = animal.nome
            textViewAnimalIdade.text = animal.idade
            textViewAnimalProprietario.text = animal.proprietario

            itemView.setOnClickListener {
                onItemClick?.invoke(animal)
            }
        }
    }
}