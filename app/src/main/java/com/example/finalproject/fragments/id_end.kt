package com.example.finalproject.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_id_end.*


class id_end : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )
            : View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_id_end, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.PVEBack).setOnClickListener {
            findNavController().navigate(R.id.action_id_end_to_id_content)

        }
//        var text_name = ""
//var text_id_id =0
//var text_salary =""
//        var text_job = ""
//        var text_email = ""
//        var text_age = 0
        arguments?.let {
         text_name.text = it.getString(getString(R.string.EMP_NAME))!!
            text_id_id.text = it.getInt(getString(R.string.EMP_ID)).toString()
            text_salary.text = it.getString(getString(R.string.EMP_SALARY))!!
            text_age.text = it.getInt(getString(R.string.EMP_AGE)).toString()
            text_job.text = it.getString(getString(R.string.EMP_JOB))!!
            text_email.text = it.getString(getString(R.string.EMP_EMAIL))!!

        }

    }
}


