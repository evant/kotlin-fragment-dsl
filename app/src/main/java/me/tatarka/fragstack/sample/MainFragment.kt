package me.tatarka.fragstack.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.tatarka.fragstack.fragmentStack

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentStack(R.id.container) {
            startWith(InitialFragment())
        }

        view.findViewById<View>(R.id.push).setOnClickListener {
            val count = childFragmentManager.backStackEntryCount + 1
            fragmentStack(R.id.container) {
                push(StackedFragment(count), backStackName = count.toString()) {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                    popEnter = android.R.animator.fade_in
                    popExit = android.R.animator.fade_out
                }
            }
        }

        view.findViewById<View>(R.id.pop).setOnClickListener {
            fragmentStack(R.id.container) {
                pop()
            }
        }

        view.findViewById<View>(R.id.pop_all).setOnClickListener {
            fragmentStack(R.id.container) {
                popAll()
            }
        }

        view.findViewById<View>(R.id.pop_to).setOnClickListener {
            val count = childFragmentManager.backStackEntryCount + 1
            if (count > 1) {
                val choices: Array<CharSequence> = (1 until count).map(Int::toString).toTypedArray()
                var choice = 1

                AlertDialog.Builder(context)
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
