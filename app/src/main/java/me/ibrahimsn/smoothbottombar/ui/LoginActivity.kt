package me.ibrahimsn.smoothbottombar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import me.ibrahimsn.smoothbottombar.R

private lateinit var firebaseAuth: FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        checkUser()

        loginbutton.setOnClickListener {
            val emaill = email.text.toString().trim()
            val password = password.text.toString().trim()

            if(emaill.isEmpty()){
                Toast.makeText(this,"E-mail can not be empty!", Toast.LENGTH_SHORT).show()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()){
                Toast.makeText(this,"Invalid E-mail adress!", Toast.LENGTH_SHORT).show()

            }
            else if(password.isEmpty()){
                Toast.makeText(this,"Password can not be empty!", Toast.LENGTH_SHORT).show()
            }
            else{
                firebaseAuth.signInWithEmailAndPassword(emaill,password).addOnSuccessListener {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener{
                    Toast.makeText(this,"User didn't find!", Toast.LENGTH_SHORT).show()

                }
            }
        }

        registerTextview.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkUser() {
        val firebase = firebaseAuth.currentUser
        if (firebase != null){
            Toast.makeText(this,"Welcome", Toast.LENGTH_SHORT).show()
           val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}