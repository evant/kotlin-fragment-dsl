package me.tatarka.fragstack.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private val ARC_NUMBER = "number"

fun StackedFragment(number: Int): StackedFragment = StackedFragment().apply {
    arguments = Bundle().apply {
        putInt(ARC_NUMBER, number)
    }
}

class StackedFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.stacked_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val number = arguments.getInt(ARC_NUMBER)
        val textView = view.findViewById<TextView>(R.id.text)
        textView.text = "Stacked: $number"
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
