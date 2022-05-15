package si.blarc.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import si.blarc.R
import si.blarc.activities.CreateChallengeActivity
import si.blarc.entity.User
import si.blarc.fragments.AssignChallengeFragment
import si.blarc.inflate

class UserAdapter(
    private var users: ArrayList<User>,
    private var layoutId: Int,
    private var context2: Context?
): RecyclerView.Adapter<UserAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {

        return UserHolder(parent.inflate(layoutId, false))
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        if (users.isNotEmpty()) {
            val user = users[position]
            holder.bindUser(user)
        }
    }

    fun setUsers(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var user: User? = null
        private var assignChallengeToFriendBtn: ImageButton?
        private var btnChecked: Boolean = false

        init {
            assignChallengeToFriendBtn = view.findViewById(R.id.assign_challenge_to_friend_btn)

            if (assignChallengeToFriendBtn != null) {
                assignChallengeToFriendBtn!!.setOnClickListener() {
                    if (!btnChecked) {
                        assignChallengeToFriendBtn!!.setImageResource(R.drawable.ic_checked)
                    } else {
                        assignChallengeToFriendBtn!!.setImageResource(R.drawable.ic_krog)
                    }

                    if (context2 != null) {
                        (context2 as CreateChallengeActivity).onBtnSelected(user!!);
                    }

                    btnChecked = !btnChecked;
                }
            }

            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }

        fun bindUser(user: User) {
            this.user = user
        }
    }
}