package com.koleychik.clothesstore

import com.koleychik.clothesstore.utils.SignUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SignUtilsTest {

    @Test
    fun shouldBeRightPassword() {
        val password1 = "chjsijpk"
        val password2 = "12345"
        val password3 = "MrHello12"
        val password4 = "MHJIWLIJ"
        val password5 = "hrevanewolmn"

        assertThat(SignUtils.checkPassword(password1)).isEqualTo(null)
        assertThat(SignUtils.checkPassword(password2)).isEqualTo(null)
        assertThat(SignUtils.checkPassword(password3)).isEqualTo(null)
        assertThat(SignUtils.checkPassword(password4)).isEqualTo(null)
        assertThat(SignUtils.checkPassword(password5)).isEqualTo(null)
    }

    @Test
    fun shouldBeWrongPassword() {
        val password1 = "chjsijpkhkdkkdc"
        val password2 = "1234"
        val password3 = "MrHell&o12"

        assertThat(SignUtils.checkPassword(password1)).isEqualTo(R.string.tooLongPassword)
        assertThat(SignUtils.checkPassword(password2)).isEqualTo(R.string.tooShortPassword)
        assertThat(SignUtils.checkPassword(password3)).isEqualTo(R.string.passwordContainsWrongCharacter)
    }

    @Test
    fun shouldBeRightEmail() {
        val email1 = "kji33jwd@k.ru"
        val email2 = "kj12idjcjdjwd@k.com"
        val email3 = "kjYjMd@k.com"
        val email4 = "MHDHU@k.com"

        assertThat(SignUtils.checkingEmail(email1)).isEqualTo(null)
        assertThat(SignUtils.checkingEmail(email2)).isEqualTo(null)
        assertThat(SignUtils.checkingEmail(email3)).isEqualTo(null)
        assertThat(SignUtils.checkingEmail(email4)).isEqualTo(null)
    }

    @Test
    fun shouldBeWrongEmail() {
        val email1 = "kji33jwdk.ru"
        val email2 = "kj12idjcjdjwd@kcom"
        val email3 = "kjYjMdkcom"
        val email4 = "123445"

        assertThat(SignUtils.checkingEmail(email1)).isEqualTo(R.string.emailField)
        assertThat(SignUtils.checkingEmail(email2)).isEqualTo(R.string.emailField)
        assertThat(SignUtils.checkingEmail(email3)).isEqualTo(R.string.emailField)
        assertThat(SignUtils.checkingEmail(email4)).isEqualTo(R.string.emailField)
    }

    @Test
    fun shouldBeRightPasswordMatch() {
        val password1 = "password1"
        val password1Repeat = "password1"
        val password2 = "MRETIUDH"
        val password2Repeat = "MRETIUDH"
        val password3 = "93794393"
        val password3Repeat = "93794393"
        val password4 = "937ds94sd39sd3"
        val password4Repeat = "937ds94sd39sd3"

        assertThat(SignUtils.isPasswordMatch(password1, password1Repeat)).isEqualTo(null)
        assertThat(SignUtils.isPasswordMatch(password2, password2Repeat)).isEqualTo(null)
        assertThat(SignUtils.isPasswordMatch(password3, password3Repeat)).isEqualTo(null)
        assertThat(SignUtils.isPasswordMatch(password4, password4Repeat)).isEqualTo(null)
    }

    @Test
    fun shouldBeWrongPasswordMatch() {
        val password1 = "passwrd1"
        val password1Repeat = "password"
        val password2 = "MREIUDH"
        val password2Repeat = "MRETIUD"
        val password3 = "3794393"
        val password3Repeat = "93794393"
        val password4 = "937ds94sd39sd3"
        val password4Repeat = "937zx43cs93"

        assertThat(
            SignUtils.isPasswordMatch(
                password1,
                password1Repeat
            )
        ).isEqualTo(R.string.passwordNotMatch)
        assertThat(
            SignUtils.isPasswordMatch(
                password2,
                password2Repeat
            )
        ).isEqualTo(R.string.passwordNotMatch)
        assertThat(
            SignUtils.isPasswordMatch(
                password3,
                password3Repeat
            )
        ).isEqualTo(R.string.passwordNotMatch)
        assertThat(
            SignUtils.isPasswordMatch(
                password4,
                password4Repeat
            )
        ).isEqualTo(R.string.passwordNotMatch)
    }

}