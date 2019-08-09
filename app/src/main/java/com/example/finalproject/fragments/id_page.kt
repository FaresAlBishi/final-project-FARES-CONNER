package com.example.finalproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R


class id_page : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_id_page, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.login).setOnClickListener {
            val memberType = 2
            val playerName = addNickName(view)
            findNavController().navigate(R.id.action_id_page_to_id_content)
        }

    }


    private fun addNickName(view: View): String {
        val editText = view.findViewById<EditText>(R.id.editTextBoxStart)
        val playerName = editText.text.toString()
        editText.visibility = View.GONE
        return playerName
    }
}

