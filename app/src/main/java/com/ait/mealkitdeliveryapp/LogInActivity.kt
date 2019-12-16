package com.ait.mealkitdeliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
    }

    fun loginClick(v: View) {
        if (!isFormValid()) {
            Toast.makeText(this@LogInActivity, getString(R.string.invalid_form_mes), Toast.LENGTH_LONG).show()
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            etEmail.text.toString(), etPassword.text.toString()
        ). addOnSuccessListener {
            Toast.makeText(this@LogInActivity, getString(R.string.logged_in_mes), Toast.LENGTH_LONG).show()
            // open forum activity
            finish()
            startActivity(Intent(this@LogInActivity, ExploreActivity::class.java))
        }.addOnFailureListener {
            Toast.makeText(
                this@LogInActivity, "Error: ${it.message}",
                Toast.LENGTH_LONG).show()
        }
    }

    fun registerClick(v: View) {
        // check if field is empty
        if (!isFormValid()) {
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            etEmail.text.toString(), etPassword.text.toString()
        ).addOnSuccessListener {
            val user = it.user
            user.updateProfile(
                UserProfileChangeRequest.Builder()
                    .setDisplayName(userNameFromEmail(etEmail.text.toString()))
                    .build()
            )
            Toast.makeText(this@LogInActivity, getString(R.string.register_mes), Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this@LogInActivity, "ErrorL ${it.message}", Toast.LENGTH_LONG).show()

        }
    }

    fun userNameFromEmail(email: String) = email.substringBefore("@")

    private fun isFormValid() : Boolean {
        return when {
            etEmail.text.isEmpty() -> {
                etEmail.error = getString(R.string.form_error)
                false
            }
            etPassword.text.isEmpty() -> {
                etPassword.error = getString(R.string.form_pass_error)
                false
            }
            else -> true
        }
    }

}
