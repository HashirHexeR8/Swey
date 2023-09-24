package com.business.swey.core.views

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.business.swey.R


class OTPTextWatcher private constructor(
    private val view: EditText,
    private val nextView: EditText?,
    private val prevView: EditText?,
) :
    TextWatcher {

    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()

        when (view.id) {
            R.id.editText1 -> if (text.length == 1) {
                nextView?.requestFocus()
            }

            R.id.editText2 -> if (text.length == 1) {
                nextView?.requestFocus()
            } else if (text.isEmpty()) {
                prevView?.requestFocus()
            }

            R.id.editText3 -> if (text.length == 1) {
                nextView?.requestFocus()
            } else if (text.isEmpty()) {
                prevView?.requestFocus()
            }

            R.id.editText4 -> if (text.length == 1) {
                nextView?.requestFocus()
            } else if (text.isEmpty()) {
                prevView?.requestFocus()
            }

            R.id.editText5 -> if (text.length == 1) {
                nextView?.requestFocus()
            } else if (text.isEmpty()) {
                prevView?.requestFocus()
            }

            R.id.editText6 -> if (text.isEmpty()) {
                prevView?.requestFocus()
            }
        }
    }

    override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}

    companion object {
        fun createWatcher(view: EditText, nextView: EditText?, prevView: EditText?): OTPTextWatcher {
            return OTPTextWatcher(view, nextView, prevView)
        }
    }
}
