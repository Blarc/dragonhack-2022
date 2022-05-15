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
import si.blarc.ui.BaseViewModel
import si.blarc.adapters.UserAdapter
import si.blarc.entity.User
import si.blarc.firebase.FirebaseUtils.getIdOfCurUser


class AddFriendFragment : Fragment() {
    private lateinit var usersList: RecyclerView
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
        return inflater.inflate(R.layout.fragment_add_friend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Add friend"

        swipeRefreshLayout = view.findViewById(R.id.add_friend_swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
//            Toast.makeText(context, "Refreshed", Toast.LENGTH_SHORT).show()
            // TODO @Blarc: Refresh user array
            swipeRefreshLayout.isRefreshing = false
        }

        usersList = view.findViewById(R.id.add_friend_users_list)

        baseViewModel.users.observe(viewLifecycleOwner) {
            val filtered = it.filter { user ->
                user.id != getIdOfCurUser() && !baseViewModel.isAlreadyFriend(user)
            }
            setupFriendsList(ArrayList(filtered))
        }
    }

    private fun setupFriendsList(users: ArrayList<User>) {
        linearLayoutManager = LinearLayoutManager(context)

        setupUserListAdapter(users)

        usersList.layoutManager = linearLayoutManager
        usersList.adapter = adapter
    }

    private fun setupUserListAdapter(users: ArrayList<User>) {
        adapter = UserAdapter(users, R.layout.user_item, null)
    }
}