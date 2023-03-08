package com.plcoding.core.domain.preferences

import com.plcoding.core.domain.model.Gender

interface Preferences {
    fun saveGender(gender : Gender) {}
}