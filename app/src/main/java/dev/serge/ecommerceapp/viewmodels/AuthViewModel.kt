package dev.serge.ecommerceapp.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.serge.ecommerceapp.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth: FirebaseAuth
): ViewModel() {

    sealed class AuthState {
        object Idle: AuthState()
        object Loading: AuthState()
        data class Success(val user: String): AuthState()
        data class Error(val message: String): AuthState()
    }

    private val _authState = MutableStateFlow<AuthState> (
        if (auth.currentUser != null) AuthState
            .Success(auth.currentUser!!.uid)
        else AuthState.Idle
    )

    val authState: StateFlow<AuthState> = _authState

    val isLoggedIn: Boolean get() = authState.value is AuthState.Success

    val currentUser = auth.currentUser?.let {firebaseUser ->
        UserProfile(
            uid = firebaseUser.uid,
            name = firebaseUser.displayName ?: "",
            email = firebaseUser.email ?: ""
        )
    }
}