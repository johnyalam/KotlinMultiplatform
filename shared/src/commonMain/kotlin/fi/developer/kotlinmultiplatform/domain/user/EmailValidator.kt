package fi.developer.kotlinmultiplatform.domain.user

class EmailValidator {
    fun isValid(email: String): Boolean {
        if (email.isBlank()) return false
        return email.contains("@") && email.contains(".")
    }
}