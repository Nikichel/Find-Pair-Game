package com.example.pairgame.recycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pairgame.R
import com.example.pairgame.databinding.GameCardItemBinding

class GameBoardView (
    private val onCardClicked: (GameCard) -> Unit
    ) : RecyclerView.Adapter<GameBoardView.ViewHolder>() {

    private val gameCards = arrayListOf<GameCard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = gameCards[position]
        holder.bind(card)
        holder.itemView.setOnClickListener { onCardClicked(card) }
    }

    override fun getItemCount(): Int = gameCards.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = GameCardItemBinding.bind(itemView)
        fun bind(card: GameCard) = with(binding) {
            // Отображение состояния карточки
        }
    }

    fun addItem(card: GameCard){
        gameCards.add(card)
        notifyDataSetChanged()
    }
}