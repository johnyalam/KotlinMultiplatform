package fi.developer.kotlinmultiplatform

import fi.developer.kotlinmultiplatform.domain.user.EmailValidator
import kotlin.test.Test
import kotlin.test.assertTrue

class EmailValidatorTest {

    @Test
    fun perfect_email_returns_true() {
        val validator = EmailValidator()
        val result = validator.isValid("johny@example.com")

        assertTrue(result)
    }
}