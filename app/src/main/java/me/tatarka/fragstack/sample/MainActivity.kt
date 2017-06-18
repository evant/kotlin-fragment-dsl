package me.tatarka.fragstack.sample

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import me.tatarka.fragstack.fragmentStack

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentStack(R.id.container) {
            startWith(InitialFragment())
        }

        findViewById<View>(R.id.push).setOnClickListener {
            val count = supportFragmentManager.backStackEntryCount + 1
            fragmentStack(R.id.container) {
                push(StackedFragment(count), backStackName = count.toString()) {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                    popEnter = android.R.animator.fade_in
                    popExit = android.R.animator.fade_out
                }
            }
        }

        findViewById<View>(R.id.pop).setOnClickListener {
            fragmentStack(R.id.container) {
                pop()
            }
        }

        findViewById<View>(R.id.pop_all).setOnClickListener {
            fragmentStack(R.id.container) {
                popAll()
            }
        }

        findViewById<View>(R.id.pop_to).setOnClickListener {
            val count = supportFragmentManager.backStackEntryCount + 1
            if (count > 1) {
                val choices: Array<CharSequence> = (1 until count).map(Int::toString).toTypedArray()
                var choice = 1

                AlertDialog.Builder(this)
                        .setTitle("Which")
                        .setSingleChoiceItems(choices, 0) { dialog, which ->
                            choice = which + 1
                        }
                        .setNeutralButton("Pop") { dialog, which ->
                            fragmentStack(R.id.container) {
                                popTo(choice.toString())
                            }
                        }
                        .setPositiveButton("Pop Inclusive") { dialog, which ->
                            fragmentStack(R.id.container) {
                                popTo(choice.toString(), inclusive = true)
                            }
                        }
                        .show()
            }
        }
    }
}
