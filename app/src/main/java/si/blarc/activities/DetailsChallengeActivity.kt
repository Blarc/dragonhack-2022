package si.blarc.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import si.blarc.R
import si.blarc.entity.Challenge
import si.blarc.fragments.ARG_CHALLENGE_DETAILS
import si.blarc.fragments.DetailsChallengeFragment

class DetailsChallengeActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_challenge)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.details_challenge_fragment_container,
                    DetailsChallengeFragment.newInstance(
                        intent.getSerializableExtra(ARG_CHALLENGE_DETAILS) as Challenge
                ))
                .commitNow()
        }

        toolbar = findViewById(R.id.details_challenge_top_toolbar)
        toolbar.title = "Details"
        toolbar.setTitleTextAppearance(this, R.style.montserrat_semibold_style)
        setSupportActionBar(toolbar)
    }
}