package com.gsatria.myapplication.domain.usecase

import com.gsatria.myapplication.domain.repository.AuthRepository

class LoginUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = repo.login(email, password)
}