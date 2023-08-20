package com.example.vactionproject_eju

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.*
import com.google.firebase.database.core.Context

var btnClk:Int = 0
var getFlag: Int = 0
class schedule : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    //private lateinit var database2: DatabaseReference
    private var selectedDate: String? = null

    private lateinit var calendarView: CalendarView
    private lateinit var diaryTextView: TextView
    private lateinit var contextEditText: EditText
    private lateinit var saveBtn: Button
    private lateinit var chaBtn: Button
    private lateinit var delBtn: Button
    private lateinit var textView2: TextView
    private lateinit var backView: TextView
    private lateinit var parametersHeader: ParametersHeader

    var note1:String = ""
    var note2:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        // Firebase 초기화
        database = FirebaseDatabase.getInstance().reference.child("calendar_data")
//        database2 = FirebaseDatabase.getInstance().reference.child("category2")
//
//        database2.child(selectedDate!!).setValue("아룡")

        calendarView = findViewById<CalendarView>(R.id.calendarView)
        diaryTextView = findViewById<TextView>(R.id.diaryTextView)
        contextEditText = findViewById<EditText>(R.id.contextEditText)
        saveBtn = findViewById<Button>(R.id.save_Btn)
        chaBtn = findViewById<Button>(R.id.cha_Btn)
        delBtn = findViewById<Button>(R.id.del_Btn)
        textView2 = findViewById<TextView>(R.id.textView2)
        backView = findViewById(R.id.backtoclass)

        parametersHeader = ViewModelProvider(this).get(ParametersHeader::class.java)



        // 캘린더 날짜 선택 이벤트 처리
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$year-${month + 1}-$dayOfMonth"
            diaryTextView.text = "$year / ${month + 1} / $dayOfMonth"
            textView2.text = "" // 날짜를 선택할 때마다 textView2 초기화
            checkDataExists()
        }
        backView.setOnClickListener{
            super.onBackPressed()
        }

        // 저장 버튼 클릭 이벤트 처리
        saveBtn.setOnClickListener {
            val inputText = contextEditText.text.toString()
            if (inputText.isNotEmpty() && selectedDate != null) {
                // 데이터베이스에 저장
                database.child(selectedDate!!).setValue(inputText)
                    .addOnSuccessListener {
                        Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show()
                        contextEditText.text.clear()
                        btnClk = 1
                        checkDataExists() // 저장 후 다시 확인
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "네트워크 오류", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        // 수정 버튼 클릭 이벤트 처리
        chaBtn.setOnClickListener {
            contextEditText.text.clear() // editText 값 초기화
            textView2.visibility = View.INVISIBLE
            diaryTextView.visibility = View.VISIBLE
            contextEditText.visibility = View.VISIBLE
            contextEditText.setText(textView2.text.toString())
            saveBtn.visibility = View.VISIBLE
            chaBtn.visibility = View.INVISIBLE
            delBtn.visibility = View.INVISIBLE
        }

        // 삭제 버튼 클릭 이벤트 처리
        delBtn.setOnClickListener {
            if (selectedDate != null) {
                // 데이터베이스에서 삭제
                database.child(selectedDate!!).removeValue()
                    .addOnSuccessListener {
                        Toast.makeText(this, "삭제되었습니다", Toast.LENGTH_SHORT).show()
                        contextEditText.text.clear()
                        textView2.text = ""
                        chaBtn.visibility = View.INVISIBLE
                        delBtn.visibility = View.INVISIBLE
                        contextEditText.visibility = View.VISIBLE
                        saveBtn.visibility = View.VISIBLE
                        diaryTextView.visibility = View.VISIBLE
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "네트워크 오류", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun checkDataExists() {
        if (selectedDate != null) {
            // Firebase에서 데이터 조회
            database.child(selectedDate!!)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val value = dataSnapshot.getValue(String::class.java)
                        if (value != null) {
                            // 데이터가 존재하는 경우
                            if(btnClk == 1)
                            {
                                if(note1 == "")
                                {
                                    note1 = value

                                    Toast.makeText(applicationContext, "note1 = $value", Toast.LENGTH_SHORT).show()
                                }
                                else if(note2 == "")
                                {
                                    note2 = note1
                                    note1 = value
                                    getFlag = 1
                                    parametersHeader.flag = true
                                    parametersHeader.PH_note1 = note1
                                    parametersHeader.PH_note2 = note2
                                    Toast.makeText(applicationContext, "note1 = $value / note2 = $note1", Toast.LENGTH_SHORT).show()
                                }
                                btnClk = 0
                            }
                            textView2.visibility = View.VISIBLE
                            textView2.text = value // 데이터를 textView2에 설정
                            contextEditText.setText(textView2.text.toString())
                            contextEditText.visibility = View.INVISIBLE
                            saveBtn.visibility = View.INVISIBLE
                            chaBtn.visibility = View.VISIBLE
                            delBtn.visibility = View.VISIBLE
                            contextEditText.text.clear() // editText 값 초기화
                        } else {
                            // 데이터가 없는 경우
                            contextEditText.visibility = View.VISIBLE
                            saveBtn.visibility = View.VISIBLE
                            chaBtn.visibility = View.INVISIBLE
                            delBtn.visibility = View.INVISIBLE
                            contextEditText.text.clear() // editText 값 초기화
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Toast.makeText(this@schedule, "데이터 조회 실패", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}
//class throwNotes : ViewModel() {
//    // schedule 클래스의 인스턴스 생성
//    val scheduleNote: schedule = schedule()
//
//    // note1이랑 note2 값
//    var throwN1: String = ""
//    var throwN2: String = ""
//
//    fun saveData() {
//        if (getFlag == 1) {
//            throwN1 = scheduleNote.note1
//            throwN2 = scheduleNote.note2
//        }
//    }
//}
