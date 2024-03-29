package com.example.pairgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pairgame.R
import com.example.pairgame.databinding.FragmentMenuBinding

var currentCoins = 100
class MenuFragment : Fragment() {

    private lateinit var myData: String
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            myData = it.getString(ARG_DATA).toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bStart.setOnClickListener {
            requireActivity().supportFragmentManager.
            beginTransaction().
            replace(R.id.fragmentHolder, GameFragment.newInstance()).
            commit()
        }

        currentCoins += myData.toInt()
        binding.countCoins.text = currentCoins.toString()
    }

    companion object {
        private const val ARG_DATA = "data"
        @JvmStatic
        fun newInstance(data: String): MenuFragment{
            val fragment = MenuFragment()
            val args = Bundle()
            args.putString(ARG_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }
}