package si.blarc.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import si.blarc.R
import si.blarc.entity.Challenge
import si.blarc.entity.User
import si.blarc.fragments.CreateChallengeFragment

class CreateChallengeActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    var selectedFriend: User? = null
    var challenge: Challenge? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_challenge)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.create_challenge_fragment_container, CreateChallengeFragment.newInstance())
                .commitNow()
        }

        toolbar = findViewById(R.id.create_challenge_top_toolbar)
        toolbar.title = "Create challenge"
        setSupportActionBar(toolbar)
    }

    fun onBtnSelected(friend: User) {
        selectedFriend = friend
    }
}