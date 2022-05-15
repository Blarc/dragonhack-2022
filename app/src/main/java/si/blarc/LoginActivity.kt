package si.blarc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import si.blarc.entity.Challenge
import si.blarc.entity.User
import si.blarc.firebase.FirebaseUtils
import si.blarc.ui.BaseViewModel
import si.blarc.MyApplication.Companion.curUserId

class LoginActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    lateinit var userId: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.login_activity_login_btn)
        userId = findViewById(R.id.editTextTextPersonName)
        loginButton.setOnClickListener {

            FirebaseUtils.getUsersRef().get().addOnCompleteListener {
                var userIdExists = false

                for (postSnapshot in it.result.children) {
                    //val curChallenge = Challenge("", "", 0, "", "", "");
                    if (postSnapshot.key!! == userId.text.toString()) {
                        userIdExists = true
                    }
                }

                if (userIdExists) {
                    curUserId = userId.text.toString();

                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                } else {
                    //We actually don't have to create user
                    curUserId = userId.text.toString();

                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
        }
    }
}