package com.example.pairgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pairgame.R
import com.example.pairgame.databinding.FragmentFinalGameBinding

class FinalGameFragment : Fragment() {

    private lateinit var binding: FragmentFinalGameBinding
    private lateinit var myData: String
    private var addCoins = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            myData = it.getString(ARG_DATA).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFinalGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bMainMenu.setOnClickListener{
            requireActivity().supportFragmentManager.
            beginTransaction().
            replace(R.id.fragmentHolder, MenuFragment.newInstance(addCoins.toString())).
            commit()
        }

        val time = myData.toInt()
        addCoins = when {
            time <= 20 -> 100
            time in 21..35 -> (100 - 5 * (time - 20))
            else -> 20
        }

        binding.countCoins.text = addCoins.toString()
    }

    companion object {
        private const val ARG_DATA = "data"

        @JvmStatic
        fun newInstance(data: String): FinalGameFragment{
            val fragment = FinalGameFragment()
            val args = Bundle()
            args.putString(ARG_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }
}