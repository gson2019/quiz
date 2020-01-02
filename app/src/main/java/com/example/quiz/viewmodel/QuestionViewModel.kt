package com.example.quiz.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.model.Question
import com.example.quiz.model.Quiz
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlin.reflect.typeOf

class QuestionViewModel : ViewModel() {
     var questionList = MutableLiveData<MutableList<Question>>()
     var curQuestionIndex = 0
    fun getQuestions(mContext:Context){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val inputString = mContext.assets.open("questions.json").bufferedReader().use{
                    it.readText()
                }
                val quiz = Gson().fromJson(inputString, Quiz::class.java)
                withContext(Dispatchers.Main){
                    questionList.value = quiz.quiz.toMutableList()
                }
            }
            catch (e:IOException){

            }
        }
    }
}
