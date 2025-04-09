package com.xiangxiongfly.customview.contacts

import com.xiangxiongfly.core.utils.PinyinUtils

data class Contact(val name: String) {
    val firstLetter: String = PinyinUtils.getPinyinFirstLetter(name).uppercase()

    val groupName: String
        get() {
            return if (firstLetter.matches(Regex("[A-Z]", RegexOption.IGNORE_CASE))) {
                firstLetter
            } else {
                "#"
            }
        }
}