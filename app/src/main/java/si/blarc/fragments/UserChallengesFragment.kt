package si.blarc.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import si.blarc.R
import si.blarc.activities.CreateChallengeActivity


class UserChallengesFragment : Fragment() {
    private lateinit var createChallengeBtn: FloatingActionButton;


    companion object {
        @JvmStatic
        fun newInstance() = UserChallengesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_challenges, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createChallengeBtn = view.findViewById(R.id.user_challenges_create_btn)

        createChallengeBtn.setOnClickListener {
            val intent = Intent(context, CreateChallengeActivity::class.java)
            startActivity(intent)
        }
    }
}