package com.gsatria.myapplication.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsatria.myapplication.domain.usecase.LoginUseCase
import com.gsatria.myapplication.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _authState = MutableLiveData<Result<Unit>>()
    val authState: LiveData<Result<Unit>> = _authState

    fun login(email: String, password: String) = viewModelScope.launch {
        _authState.value = loginUseCase(email, password)
    }

    fun register(email: String, password: String) = viewModelScope.launch {
        _authState.value = registerUseCase(email, password)
    }
}