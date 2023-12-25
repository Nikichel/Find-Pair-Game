package com.example.pairgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pairgame.R
import com.example.pairgame.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bStart.setOnClickListener {
            requireActivity().supportFragmentManager.
            beginTransaction().
            replace(R.id.fragmentHolder, GameFragment.newInstance()).
            commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MenuFragment()
    }
}