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
    var selectedFriends: MutableList<User> = mutableListOf()
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
        toolbar.setTitleTextAppearance(this, R.style.montserrat_semibold_style)
        setSupportActionBar(toolbar)
    }

    fun addOrRemoveUserFromSelectedList(user: User)  {
        if (selectedFriends.contains(user)) {
            selectedFriends.remove(user)
        }
        else {
            selectedFriends.add(user)
        }
    }
}