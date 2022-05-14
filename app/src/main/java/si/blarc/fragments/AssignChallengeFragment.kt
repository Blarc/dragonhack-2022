
package si.blarc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import si.blarc.R
import si.blarc.adapters.UserAdapter
import si.blarc.entity.User
import si.blarc.ui.BaseViewModel


class AssignChallengeFragment : Fragment() {
    private lateinit var friendsList: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: UserAdapter

    private val baseViewModel: BaseViewModel by activityViewModels()

    companion object {
        @JvmStatic
        fun newInstance() = AssignChallengeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assign_challenge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Assign challenge"

        swipeRefreshLayout = view.findViewById(R.id.assign_challenge_swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(context, "Refreshed", Toast.LENGTH_SHORT).show()
            // TODO @Blarc: Refresh user array
            swipeRefreshLayout.isRefreshing = false
        }

        friendsList = view.findViewById(R.id.assign_challenge_users_list)

        baseViewModel.friends.observe(viewLifecycleOwner) {
            setupFriendsList(ArrayList(it))
        }
    }

    private fun setupFriendsList(users: ArrayList<User>) {
        linearLayoutManager = LinearLayoutManager(context)

        setupFriendsListAdapter(users)

        friendsList.layoutManager = linearLayoutManager
        friendsList.adapter = adapter
    }

    private fun setupFriendsListAdapter(users: ArrayList<User>) {
        adapter = UserAdapter(users)
    }
}