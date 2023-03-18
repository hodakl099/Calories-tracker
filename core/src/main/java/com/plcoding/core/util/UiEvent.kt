package com.plcoding.core.util

sealed class UiEvent{
    object Success : UiEvent()
    data class ShowSnackBar(val message : UiText) : UiEvent()
    object NavigateUp : UiEvent()
}
