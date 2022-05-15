package si.blarc.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import si.blarc.R
import si.blarc.activities.CreateChallengeActivity
import si.blarc.entity.User
import si.blarc.firebase.FirebaseUtils
import si.blarc.inflate
import kotlin.random.Random

class UserAdapter(
    private var users: ArrayList<User>,
    private var layoutId: Int,
    private var context: Context?
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
        private var btnChecked: Boolean = false

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }

        fun bindUser(user: User) {
            this.user = user

            if (context is CreateChallengeActivity) {
                val assignBtn: ImageButton = view.findViewById(R.id.assign_challenge_to_friend_btn)
                val fireNumberTextView: TextView = view.findViewById(R.id.assign_item_fire_number)
                fireNumberTextView.text = Random.nextInt(12).toString()

                assignBtn.setOnClickListener {
                    if (!btnChecked) {
                        assignBtn.setImageResource(R.drawable.ic_checked)
                    } else {
                        assignBtn.setImageResource(R.drawable.ic_krog)
                    }
                    (context as CreateChallengeActivity).addOrRemoveUserFromSelectedList(user)
                }

            }
            // Context is MainActivity
            else {
                val addFriendBtn: Button = view.findViewById(R.id.user_item_follow_btn)
                addFriendBtn.setOnClickListener {
                    FirebaseUtils.addFriend(user)
                    addFriendBtn.isEnabled = false
                }
            }
        }
    }
}