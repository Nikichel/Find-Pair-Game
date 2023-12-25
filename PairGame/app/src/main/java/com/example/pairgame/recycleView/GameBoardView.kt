package com.example.pairgame.recycleView


import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pairgame.R
import com.example.pairgame.databinding.GameCardItemBinding


class GameBoardView (
    //private val onCardClicked: (GameCard) -> Unit
    ) : RecyclerView.Adapter<GameBoardView.ViewHolder>() {

    private val gameCards = arrayListOf<GameCard>()
    private val selectedPosition = arrayListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = gameCards[position]
        holder.bind(card)
        //holder.itemView.setOnClickListener { onCardClicked(card) }
    }

    override fun getItemCount(): Int = gameCards.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = GameCardItemBinding.bind(itemView)
        fun bind(card: GameCard) {
            binding.imageView.setImageResource(card.imgId)
            if(card.isOpen)
                binding.imageView.visibility = View.VISIBLE
            else
                binding.imageView.visibility = View.GONE
            itemView.setOnClickListener {
                selectedPosition.add(adapterPosition)
                if(selectedPosition.size == 2){
                    if(gameCards[selectedPosition[0]] == gameCards[selectedPosition[1]]
                        && selectedPosition[0]!=selectedPosition[1]){
                        gameCards[selectedPosition[0]].isOpen = false
                        gameCards[selectedPosition[1]].isOpen = false
                        notifyDataSetChanged()
                    }
                    selectedPosition.clear()
                }
            }

        }
    }

    fun addItem(card: GameCard){
        gameCards.add(card)
        notifyDataSetChanged()
    }
}