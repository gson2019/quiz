package com.example.quiz.view


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.quiz.R
import com.example.quiz.ui.view.MainActivity
import kotlinx.android.synthetic.main.fragment_welcome.*

/**
 * A simple [Fragment] subclass.
 */
class WelComeFragment : Fragment() {

    lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startBtn.setOnClickListener {
            run {
                welcomeTv.visibility = View.GONE
                startBtn.visibility = View.GONE
                (mContext as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.container, QuestionFragment.newInstance())
                    .commit()
            }
        }
    }

    companion object {
        fun newInstance() = WelComeFragment()
    }
}
