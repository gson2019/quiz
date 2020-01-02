package com.example.quiz.view

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer

import com.example.quiz.R
import com.example.quiz.model.Question
import com.example.quiz.ui.view.MainActivity
import com.example.quiz.viewmodel.QuestionViewModel
import kotlinx.android.synthetic.main.question_fragment.*

class QuestionFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionFragment()
    }

    private lateinit var mContext: Context
    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.question_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    fun updateQuestion(question: Question){
        val questionText = viewModel.curQuestionIndex.plus(1).toString() + ". " + question.question
        questionTv.text = questionText
        answersRg.removeAllViews()
        question.options.forEachIndexed { qIndex, answer ->
            run {
                val radioButton = RadioButton(mContext)
                val ansIdx : Char = 'A' + qIndex
                val ans: String = ansIdx.toString() + ". " + answer
                radioButton.text = ans
                radioButton.id = View.generateViewId()
                answersRg.addView(radioButton)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(mContext as MainActivity).get(QuestionViewModel::class.java)
        viewModel.getQuestions(mContext as MainActivity)
        // TODO: Use the ViewModel

        viewModel.questionList.observe(mContext as MainActivity, Observer {
            updateQuestion(it[viewModel.curQuestionIndex])
        })
        nextBtn.setOnClickListener {
            val qsize = viewModel.questionList.value!!.size
            if(viewModel.curQuestionIndex == qsize){
                showDialog()
            }
            run {
                viewModel.curQuestionIndex += 1
            }
            if(viewModel.curQuestionIndex > qsize){
                viewModel.curQuestionIndex--;
            }
            if(viewModel.curQuestionIndex <= (qsize-1) ){
                updateQuestion(viewModel.questionList.value!!.get(viewModel.curQuestionIndex))
            }
        }
    }

    fun showDialog(){
        val builder = AlertDialog.Builder(mContext)
        builder.setTitle("End of Quiz Reminder")
        builder.setMessage("This is the last question, do you want to see your quiz summary?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Go Summary"){
            dialogInterface, which ->
            Toast.makeText(mContext, "clicked yes", Toast.LENGTH_LONG).show()
        }
//        builder.setNeutralButton("Back to Question"){
//            dialogInterface, which ->
//            Toast.makeText(mContext, "clicked cancel", Toast.LENGTH_LONG).show()
//        }
        builder.setNegativeButton("Back Question"){
            dialogInterface, which ->
            Toast.makeText(mContext, "back question", Toast.LENGTH_LONG).show()
        }
        val alertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
}
