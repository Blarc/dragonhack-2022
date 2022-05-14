package si.blarc.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import si.blarc.R
import si.blarc.UI.BaseViewModel
import si.blarc.activities.CreateChallengeActivity
import si.blarc.adapters.ChallengeAdapter
import si.blarc.entity.Challenge


class UserChallengesFragment : Fragment() {
    private lateinit var createChallengeBtn: FloatingActionButton;
    private lateinit var challengesList: RecyclerView;
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val baseViewModel: BaseViewModel by activityViewModels()

    private lateinit var adapter: ChallengeAdapter;

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

        swipeRefreshLayout = view.findViewById(R.id.user_challenges_swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(context, "Refreshed", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout.isRefreshing = false
        }

        createChallengeBtn = view.findViewById(R.id.user_challenges_create_btn)
        createChallengeBtn.setOnClickListener {
            val intent = Intent(context, CreateChallengeActivity::class.java)
            startActivity(intent)
        }

        challengesList = view.findViewById(R.id.user_challenges_challenges_list)
        setupChallengeList()
    }

    private fun setupChallengeList() {
        linearLayoutManager = LinearLayoutManager(context)

        setupChallengeListAdapter()

        challengesList.layoutManager = linearLayoutManager
        challengesList.adapter = adapter
    }

    private fun setupChallengeListAdapter() {
//        TODO @martinb: Fill with data from Firebase
//        adapter = ChallengeAdapter(
//            challengeViewModel.getChallenges()
//        )
        adapter = ChallengeAdapter(
            arrayListOf(
                Challenge("Title 1", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 2", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 3", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 4", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 5", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color"),
                Challenge("Title 6", "Description", 1, "assignedTo", "assignedFrom", "color")
            )
        )
    }
}