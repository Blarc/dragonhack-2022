package si.blarc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import si.blarc.R
import si.blarc.ui.BaseViewModel
import si.blarc.adapters.ChallengeAdapter
import si.blarc.entity.Challenge

class HistoryChallengesFragment : Fragment() {
    private lateinit var challengesList: RecyclerView;
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: ChallengeAdapter;

    private val baseViewModel: BaseViewModel by activityViewModels()

    companion object {
        @JvmStatic
        fun newInstance() = HistoryChallengesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history_challenges, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "History"

        swipeRefreshLayout = view.findViewById(R.id.history_challenges_swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(context, "Refreshed", Toast.LENGTH_SHORT).show()
            // TODO @Blarc: Refresh challenges array
            swipeRefreshLayout.isRefreshing = false
        }

        challengesList = view.findViewById(R.id.history_challenges_challenges_list)

        baseViewModel.completedChallenges.observe(viewLifecycleOwner) {
            setupHistoryChallengeList(ArrayList(it))
        }

    }

    private fun setupHistoryChallengeList(challenges: ArrayList<Challenge>) {
        linearLayoutManager = LinearLayoutManager(context)

        setupHistoryChallengeListAdapter(challenges)

        challengesList.layoutManager = linearLayoutManager
        challengesList.adapter = adapter
    }

    private fun setupHistoryChallengeListAdapter(challenges: ArrayList<Challenge>) {
        adapter = ChallengeAdapter(challenges)
    }
}