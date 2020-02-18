package com.example.cynix.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cynix.R
import com.example.cynix.character.Character
import com.example.cynix.remote.CharacterApiContract
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_characters.view.*

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var data: List<Character> = emptyList()
    private var itemClickListener: ItemClickListener? = null

    interface ItemClickListener {
        fun onClick(charactersResults: Character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_characters,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (data.isNotEmpty()) {
            holder.bind(data[position])
        }
    }

    fun setData(charactersResults: List<Character>) {
        data = charactersResults
        this.notifyDataSetChanged()
    }

    fun setItemClickListener(listener: ItemClickListener) {
        itemClickListener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(charactersResults: Character) {
            itemView.characterId.text = "#${charactersResults.id}"
            itemView.characterName.text = charactersResults.name
            itemView.characterStatus.text = charactersResults.status
            itemView.characterSpecies.text = charactersResults.species
            itemView.characterLocation.text = charactersResults.location.name

            Picasso.get().load(charactersResults.image).into(itemView.characterImage)

            itemView.characterConstraintLayout.setOnClickListener {
                itemClickListener?.onClick(charactersResults)
            }
        }
    }
}