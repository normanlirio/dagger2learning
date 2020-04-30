package com.teamzmron.dagger2sample.ui.auth

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.teamzmron.dagger2sample.models.User

class AuthResource<T>(
    @NonNull val status: AuthStatus,
    @Nullable val data: T? = null,
    @Nullable val message: String?= null
) {

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }

    companion object {
        fun <T> authenticated(@Nullable data: T): AuthResource<T> {
            return AuthResource(AuthStatus.AUTHENTICATED, data, null)
        }

        fun <T> error(@NonNull msg: String, @Nullable data: T?): AuthResource<T> {
            return AuthResource(AuthStatus.ERROR, data, msg)
        }

        fun <T> loading(@Nullable data: T?): AuthResource<T> {
            return AuthResource(AuthStatus.LOADING, data, null)
        }

        fun <T> logout(): AuthResource<T> {
            return AuthResource(AuthStatus.NOT_AUTHENTICATED, null , null)
        }
    }

}