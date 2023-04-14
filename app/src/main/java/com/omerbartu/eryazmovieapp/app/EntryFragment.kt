package com.omerbartu.eryazmovieapp.app

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.omerbartu.eryazmovieapp.R


class EntryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_entry, container, false)

        val sp = requireActivity().applicationContext.getSharedPreferences("X", Context.MODE_PRIVATE)
        sp.edit().clear().apply()

        Handler(Looper.getMainLooper()).postDelayed({

            findNavController().navigate(R.id.action_entryFragment_to_movies)

        },3000)

        return view
    }

}