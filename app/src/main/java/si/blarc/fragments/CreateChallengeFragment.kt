package si.blarc.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import si.blarc.LoginActivity
import si.blarc.MainActivity
import si.blarc.R
import si.blarc.UI.BaseViewModel
import si.blarc.utils.UIUtils.replaceFragment


class CreateChallengeFragment : Fragment() {
    private lateinit var createChallengeUserBtn: Button;
    private lateinit var createChallengeFriendBtn: Button;

    private val baseViewModel: BaseViewModel by activityViewModels()

    companion object {
        @JvmStatic
        fun newInstance() = CreateChallengeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_challenge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //15 dp

        createChallengeUserBtn = view.findViewById(R.id.create_challenge_create_user_btn)
        createChallengeFriendBtn = view.findViewById(R.id.create_challenge_create_friend_btn)

        createChallengeUserBtn.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        createChallengeFriendBtn.setOnClickListener {
            replaceFragment(requireActivity(), R.id.create_challenge_fragment_container, AssignChallengeFragment::class.java)
        }
    }
}
