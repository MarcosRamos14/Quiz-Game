package com.example.quizgame.ui.result

import android.os.Parcelable
import com.google.errorprone.annotations.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ResultViewArgs(
    val playerName: String? = null,
    val correctAnswers: Int,
    val incorrectAnswers: Int,
    val position: Int? = null
) : Parcelable
