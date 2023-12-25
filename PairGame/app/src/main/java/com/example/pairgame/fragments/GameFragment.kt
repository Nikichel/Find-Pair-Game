package com.example.pairgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pairgame.R
import com.example.pairgame.databinding.FragmentGameBinding
import com.example.pairgame.recycleView.GameBoardView
import com.example.pairgame.recycleView.GameCard
import kotlin.random.Random

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val adapter = GameBoardView(/*::onCardClicked*/)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(inflater)

        binding.cardBoard.layoutManager = GridLayoutManager(context, 4)
        binding.cardBoard.adapter = adapter

        return binding.root
    }

/*    private fun onCardClicked(card: GameCard) {

    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bGoMenu.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentHolder, MenuFragment.newInstance())
                .commit()
        }
        val pairGameCard = arrayListOf<GameCard>()
        for(i in 0..9){
            lateinit var gameCard: GameCard
            when(Random.nextInt(1,4)){
                1 -> {
                    gameCard= GameCard(R.drawable.gamecard1)
                }
                2 -> {
                    gameCard = GameCard(R.drawable.gamecard2)
                }
                3 -> {
                    gameCard= GameCard(R.drawable.gamecard3)
                }
            }

            pairGameCard.add(gameCard)
            adapter.addItem(gameCard)
        }

        for(gameCard in pairGameCard.shuffled()){
            adapter.addItem(gameCard)
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = GameFragment()
    }
}