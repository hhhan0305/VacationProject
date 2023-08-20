package com.example.vactionproject_eju

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class AttendFragment : Fragment() {

    private lateinit var print3: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_attend, container, false)

        // ViewModel을 초기화하고 데이터 설정
        val parametersHeader: ParametersHeader = ViewModelProvider(this).get(ParametersHeader::class.java)
        parametersHeader.par2 = 2

        print3 = view.findViewById(R.id.pggg)
        print3.text = parametersHeader.par2.toString() // 1이 표시되어야 합니다.

        return view
    }
}


