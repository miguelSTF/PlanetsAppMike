package com.mikestf.planetsappmike.ui.register_user.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikestf.planetsappmike.domain.model.User
import com.mikestf.planetsappmike.domain.use_case.GetUsersUseCase
import com.mikestf.planetsappmike.domain.use_case.InsertUserUseCase
import com.mikestf.planetsappmike.ui.register_user.components.RegisterUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
    private val getUserUseCase: GetUsersUseCase
) : ViewModel() {
    private val _isUserSaved = MutableSharedFlow<Unit>()
    val isUserSaved = _isUserSaved.asSharedFlow()

    private val _allUsers = MutableSharedFlow<List<User>>()
    val allUsers = _allUsers.asSharedFlow()

    init {
        viewModelScope.launch {
            getUserUseCase.getUsers().map { list ->
                _allUsers.emit(list)
            }.launchIn(viewModelScope)
        }
    }

    fun insertUser(state: RegisterUserState) {
        viewModelScope.launch {
            val userAdded = insertUserUseCase.insertUser(state.userState)
            _isUserSaved.emit(userAdded)
        }
    }

    fun createUser(
        name: String,
        lastname: String,
        motherLastName: String,
        birthdate: Date,
        country: String
    ): User {
        return User(
            id = 0,
            name = name,
            lastName = lastname,
            motherLastName = motherLastName,
            birthDate = birthdate,
            country = country
        )
    }
}