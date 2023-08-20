package com.example.vactionproject_eju

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.vactionproject_eju.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FragmentPageAdapter
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FragmentPageAdapter(supportFragmentManager, lifecycle)
        viewPager2 = findViewById(R.id.fl_con)
        viewPager2.adapter = adapter
        viewPager2.isUserInputEnabled = false// 스와이프 비활성화

        var bnv_main = findViewById(R.id.bnv_main) as BottomNavigationView

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bnvMain.menu.getItem(position).isChecked = true
            }
        })

        bnv_main.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_main_home -> {
                        viewPager2.currentItem = 0
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_myclass -> {
                        viewPager2.currentItem = 2
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_more -> {
                        viewPager2.currentItem = 1
                        return@setOnNavigationItemSelectedListener true
                    }
                }
                false
            }
            selectedItemId = R.id.menu_main_home
        }
    }

}
