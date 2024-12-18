package com.example.matuleme2.data.models.states

//Данные которые будут на странице
// email и password
//Они изменяются в signinviewmodel при вводе новых

data class signinstate (
    val email: String = "",
    val password: String = "",
)