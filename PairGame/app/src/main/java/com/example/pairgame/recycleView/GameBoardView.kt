package com.example.pairgame.recycleView


import android.graphics.Color
import android.graphics.Color.LTGRAY
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pairgame.R
import com.example.pairgame.databinding.GameCardItemBinding


class GameBoardView (private val listener : Listener
    //private val onCardClicked: (GameCard) -> Unit
    ) : RecyclerView.Adapter<GameBoardView.ViewHolder>() {

    private var findPair = 0
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
                binding.imageView.visibility = View.INVISIBLE

            if(!card.isSelected)
                binding.imageView.setBackgroundColor(Color.TRANSPARENT)
            itemView.setOnClickListener {onCardClicked(card)}
        }
        private fun onCardClicked(card: GameCard){
            card.isSelected = true
            Thread.sleep(100)
            binding.imageView.setBackgroundColor(Color.LTGRAY)
            selectedPosition.add(adapterPosition)
            if(selectedPosition.size == 2){
                val fisrtPosition = selectedPosition[0]
                val secondPosition = selectedPosition[1]
                if(fisrtPosition != secondPosition){
                    if(gameCards[fisrtPosition] == gameCards[secondPosition])
                    {
                        gameCards[fisrtPosition].isOpen = false
                        gameCards[secondPosition].isOpen = false
                        findPair++
                    }
                    else
                    {
                        gameCards[fisrtPosition].isSelected = false
                        gameCards[secondPosition].isSelected = false
                    }

                    notifyItemChanged(fisrtPosition, null)
                    notifyItemChanged(secondPosition, null)

                }
                else
                    notifyDataSetChanged()

                selectedPosition.clear()
                Log.d("findPair", findPair.toString())
                if(findPair==8)
                    listener.finishGame()
            }
        }
    }
    fun addItem(card: GameCard){
        gameCards.add(card)
        notifyItemChanged(gameCards.size-1, null)
    }

    interface Listener{
        fun finishGame()
    }
}