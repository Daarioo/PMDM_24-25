package com.example.ud03_1_fragmentsencrypt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController


class MessageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message, container,false)
        val btnNext = view.findViewById<Button>(R.id.BtnNext)
        btnNext.setOnClickListener{
            view.findNavController().navigate(
                MessageFragmentDirections.actionMessageFragmentToEncryptFragment(
                    view.findViewById<EditText>(R.id.welcome_text).text.toString()
                )
            )

        }
        return  view
    }

}