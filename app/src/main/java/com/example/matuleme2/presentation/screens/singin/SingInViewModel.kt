package com.example.matuleme2.presentation.screens.singin

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme2.data.models.states.signinstate
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.domain.repository.UserRepository
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.validation.isEmailValid
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(@ApplicationContext private val context: Context) : ViewModel() {

    //переменная для состояния объекта на странице
    private val _state = mutableStateOf(signinstate())
    val state: signinstate get() = _state.value

    //функция обновления состояни, то есть вводим значение и оно запоминается
    fun updatestate(newstate: signinstate)
    { _state.value = newstate }

    //авторизация
    fun signIn(controller: NavHostController) {
        viewModelScope.launch {
            try {
                //Если email соответсвует патерну то supabase принимает значения
                if(state.email.isEmailValid()) {
//                     Попытка отправки кода на email
/*                    Constants.supabase.auth.signUpWith(OTP) {
                        createUser = false
                        email = "sharonovaas@mail.ru"
                    }*/
                    Constants.supabase.auth.signOut()
                    Constants.supabase.auth.signInWith(Email) {
                        email = state.email
                        password = state.password
                    }
                    //Запись в лог
                    Log.d("sign in success", "супер")

                    //Текущий пользователь
                    val currentUser = Constants.supabase.auth.currentUserOrNull()
                    //Вывод текущего пользователя в лог
                    Log.d("curUser", currentUser.toString())

                    //Есил текущий пользователь не пустая строка, то устанавливается новый пользователь систеиы
                    //То есть профиль в котором сейчас происходит работа
                    if(currentUser != null) {
                        UserRepository.uuidCurrentUser = currentUser.id
                    }

                    Log.d("uuid current user", UserRepository.uuidCurrentUser)

                    UserRepository.act = 2

                    controller.navigate(NavigationRoutes.MAIN) {
                        popUpTo(NavigationRoutes.SIGNIN) {
                            inclusive = true
                        }
                    }
                } else {
                    //Вывод сообщения если формат почты не соответсвует патерну
                    Toast.makeText(context, "Неверный формат почты", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                if(e.message == "Invalid login credentials") {
                    Toast.makeText(context, "Неверные данные входа", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("sign in error", e.message.toString())
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}