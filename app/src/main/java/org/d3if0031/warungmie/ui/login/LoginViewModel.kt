package org.d3if0031.warungmie.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if0031.warungmie.data.User
import org.d3if0031.warungmie.data.InMemoryUserDataStore

class LoginViewModel : ViewModel() {

    private val inMemoryUser = InMemoryUserDataStore

    private val _viewState = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState> = _viewState

    private val _isLoginSuccessful = MutableLiveData(false)
    val isLoginSuccessful: LiveData<Boolean> = _isLoginSuccessful

    fun login(username: String, password: String) {
        val user = inMemoryUser.login(username, password)

        if (user == null) {
            _viewState.value = LoginViewState(error = "Username or Password incorrect")
            return
        }

        _viewState.value = LoginViewState(isLoginSuccess = true)
    }

    fun register(username: String, email: String, password: String) {
        val registerUser = User(username = username, email = email, password = password)

        val isSuccess = inMemoryUser.register(registerUser)

        if (!isSuccess) {
            _viewState.value = LoginViewState(error = "Username already registered")
            return
        }

        _viewState.value = LoginViewState(isLoginSuccess = true)
    }

    fun resetState() {
        _viewState.value = LoginViewState()
    }

}