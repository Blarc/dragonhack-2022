package si.blarc.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import si.blarc.R
import si.blarc.entity.User
import si.blarc.inflate

class UserAdapter(
    private var users: ArrayList<User>
): RecyclerView.Adapter<UserAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(parent.inflate(R.layout.user_item, false))
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

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bindUser(user: User) {
            this.user = user
        }
    }
}