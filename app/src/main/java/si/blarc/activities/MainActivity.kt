package si.blarc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import si.blarc.fragments.AddFriendFragment
import si.blarc.fragments.UserChallengesFragment
import si.blarc.utils.UIUtils.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, UserChallengesFragment.newInstance())
                .commitNow()
        }

        bottomNavBar = findViewById(R.id.main_bottom_nav_bar)
        bottomNavBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.bottom_nav_home -> replaceFragment(
                    this,
                    R.id.main_fragment_container,
                    UserChallengesFragment::class.java
                )
                R.id.bottom_nav_add_friend -> replaceFragment(
                    this,
                    R.id.main_fragment_container,
                    AddFriendFragment::class.java
                )
                R.id.bottom_nav_completed_challenges -> replaceFragment(
                    this,
                    R.id.main_fragment_container,
                    AddFriendFragment::class.java
                )
            }
            true
        }
    }
}