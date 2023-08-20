package com.example.vactionproject_eju

import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    //private lateinit var parametersHeader: ParametersHeader
    private lateinit var print1: TextView
    private lateinit var print2: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // ViewModel을 초기화하고 데이터 설정
        val parametersHeader: ParametersHeader = ViewModelProvider(this).get(ParametersHeader::class.java)
        parametersHeader.par1 = 1

        print1 = view.findViewById(R.id.output1)
        print2 = view.findViewById(R.id.output2)

        print1.text = parametersHeader.par1.toString()
        print2.text = parametersHeader.par2.toString()

        return view
    }
}


