package com.example.stayhome

import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

interface TextObserver {
    companion object textCom{
        fun checkUp(editText: TextInputEditText, inputLayout: TextInputLayout, massage: String){
            if (editText.text.toString().trim().isEmpty()){
                inputLayout.helperText = massage
            }else{
                inputLayout.helperText = null
            }
        }
    }
}