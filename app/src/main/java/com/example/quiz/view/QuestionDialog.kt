package com.example.quiz.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.quiz.ui.view.MainActivity

class QuestionDialog : DialogFragment() {
    lateinit var mContext:Context
    companion object{
        fun newInstance():QuestionDialog{
            val fragment = QuestionDialog()
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(mContext)


        return super.onCreateDialog(savedInstanceState)
    }
}