package com.paulacr.cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paulacr.cats.ui.list.CatsListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // call fragment
        supportFragmentManager.beginTransaction().replace(R.id.container, CatsListFragment())
            .commit()
    }
}