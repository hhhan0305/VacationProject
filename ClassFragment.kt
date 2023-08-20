package com.example.vactionproject_eju

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ClassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ClassFragment : Fragment() {
   // private lateinit var parametersHeader: ParametersHeader
   // private lateinit var note1:String
    //private lateinit var note2:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_class, container, false)
        val noticeLayout: LinearLayout = rootView.findViewById(R.id.notice)
//        val getNotes: throwNotes = ViewModelProvider(this).get(throwNotes::class.java)
        val NotePrint1: Button = rootView.findViewById(R.id.printS1)
        val NotePrint2: Button = rootView.findViewById(R.id.printS2)


        // note1과 note2를 가져오고, 토스트 메시지를 통해 출력
//        val note1: String = parametersHeader.PH_note1
//        val note2: String = parametersHeader.PH_note2
//        if(parametersHeader.flag == true)
//        {
//            note1 = parametersHeader.PH_note1
//            note2 = parametersHeader.PH_note2
//            Toast.makeText(context, "note1 = $note1 / note2 = $note2", Toast.LENGTH_SHORT).show()
//        }

        // NotePrint1과 NotePrint2 버튼에 note1과 note2를 설정
        NotePrint1.text = "아룡"
        NotePrint2.text = "아룡"

        noticeLayout.setOnClickListener {
            // 다른 액티비티로 전환하는 코드
            val intent = Intent(activity, schedule::class.java)
            startActivity(intent)
        }
        return rootView
    }
}
