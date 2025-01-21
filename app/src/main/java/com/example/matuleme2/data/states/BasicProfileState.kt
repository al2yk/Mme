package com.example.matuleme2.data.states

import kotlinx.serialization.SerialName

data class BasicProfileState (
    val name: String = "",
    val email: String = "",
    val password: String = "● ● ● ● ● ● ●",
    val id:String=""

)