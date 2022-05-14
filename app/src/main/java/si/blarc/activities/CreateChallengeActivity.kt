package si.blarc.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import si.blarc.R
import si.blarc.fragments.CreateChallengeFragment

class CreateChallengeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_challenge)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.create_challenge_fragment_container, CreateChallengeFragment.newInstance())
                .commitNow()
        }
    }
}