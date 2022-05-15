package si.blarc.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import si.blarc.MainActivity
import si.blarc.R
import si.blarc.activities.CreateChallengeActivity
import si.blarc.entity.Challenge
import si.blarc.ui.BaseViewModel
import si.blarc.utils.UIUtils.replaceFragment
import java.time.LocalDate


class CreateChallengeFragment : Fragment() {
    private lateinit var challengeTitle: EditText
    private lateinit var challengeDescription: EditText
    private lateinit var challengesDate: EditText

    private lateinit var assignChallengeToMeBtn: Button;
    private lateinit var assignChallengeToFirend: Button;

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
        (activity as AppCompatActivity).supportActionBar?.title = "Create challenge"

        challengeTitle = view.findViewById(R.id.challenge_title_input)
        challengeDescription = view.findViewById(R.id.challenge_title_desc)
        challengesDate = view.findViewById(R.id.challenge_title_date)

        assignChallengeToMeBtn = view.findViewById(R.id.create_challenge_create_user_btn)
        assignChallengeToFirend = view.findViewById(R.id.create_challenge_create_friend_btn)

        assignChallengeToMeBtn.setOnClickListener {

            val challenge = Challenge(challengeTitle.text.toString(), challengeDescription.text.toString(), 10,
                baseViewModel.getCurrentUser().id, "", "", false, LocalDate.now()!!.toString(), "")

            baseViewModel.addChallenge(challenge)

            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        assignChallengeToFirend.setOnClickListener {
            (activity as CreateChallengeActivity).challenge = Challenge(challengeTitle.text.toString(), challengeDescription.text.toString(), 10, "", baseViewModel.getCurrentUser().id.toString(), "", false, LocalDate.now()!!.toString(), "")

            replaceFragment(requireActivity(), R.id.create_challenge_fragment_container, AssignChallengeFragment::class.java)
        }
    }
}
