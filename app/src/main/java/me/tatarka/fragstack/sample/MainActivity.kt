package me.tatarka.fragstack.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.tatarka.fragstack.fragmentStack

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        fragmentStack(R.id.container) {
            startWith(MainFragment())
        }
    }
}
