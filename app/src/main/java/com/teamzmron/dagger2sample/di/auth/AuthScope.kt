package com.teamzmron.dagger2sample.di.auth

import java.lang.annotation.Documented
import javax.inject.Scope

@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthScope {
}