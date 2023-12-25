package com.example.pairgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pairgame.R
import com.example.pairgame.databinding.FragmentGameBinding
import com.example.pairgame.recycleView.GameBoardView
import com.example.pairgame.recycleView.GameCard

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val adapter = GameBoardView(::onCardClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(inflater)

        binding.cardBoard.layoutManager = GridLayoutManager(context, 4)
        binding.cardBoard.adapter = adapter

        return binding.root
    }

    private fun onCardClicked(card: GameCard) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bGoMenu.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentHolder, MenuFragment.newInstance())
                .commit()
        }

        for(i in 0..19)
            adapter.addItem(GameCard(i,i))
    }

    companion object {

        @JvmStatic
        fun newInstance() = GameFragment()
    }
}