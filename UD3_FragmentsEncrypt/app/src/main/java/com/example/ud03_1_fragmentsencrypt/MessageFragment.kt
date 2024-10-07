package com.example.ud03_1_fragmentsencrypt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController


class MessageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view2 = inflater.inflate(R.layout.fragment_message, container,false)
        val btnNext = view2.findViewById<Button>(R.id.BtnNext)
        btnNext.setOnClickListener{
            view2.findNavController().navigate(R.id.action_messageFragment_to_encryptFragment)
        }
        return  view
    }

}