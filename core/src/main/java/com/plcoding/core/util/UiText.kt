package com.plcoding.core.util

import android.content.Context

sealed class UiText {
    data class StringRes(val stringRes : Int) : UiText()
    data class DynamicString(val text : String) : UiText()


    fun asString(context: Context) : String {
      return  when(this) {
            is StringRes -> context.getString(stringRes)
            is DynamicString -> text
        }
    }
}
