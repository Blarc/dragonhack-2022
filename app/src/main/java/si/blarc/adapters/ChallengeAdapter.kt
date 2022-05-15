package si.blarc.adapters

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import si.blarc.R
import si.blarc.activities.DetailsChallengeActivity
import si.blarc.entity.Challenge
import si.blarc.fragments.ARG_CHALLENGE_DETAILS
import si.blarc.fragments.avatarsList
import si.blarc.inflate
import java.util.*
import kotlin.collections.ArrayList

class ChallengeAdapter(
    private var challenges: ArrayList<Challenge>
): RecyclerView.Adapter<ChallengeAdapter.ChallengeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeHolder {
        return ChallengeHolder(parent.inflate(R.layout.challenge_item, false))
    }

    override fun getItemCount() = challenges.size

    override fun onBindViewHolder(holder: ChallengeHolder, position: Int) {
        if (challenges.isNotEmpty()) {
            val challenge = challenges[position]
            holder.bindChallenge(challenge)
        }
    }

    fun setChallenges(challenges: List<Challenge>) {
        this.challenges.clear()
        this.challenges.addAll(challenges)
        notifyDataSetChanged()
    }

    inner class ChallengeHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var challenge: Challenge? = null;

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(v!!.context, DetailsChallengeActivity::class.java)
            intent.putExtra(ARG_CHALLENGE_DETAILS, challenge)
            startActivity(v.context, intent, null)
        }

        fun bindChallenge(challenge: Challenge) {
            this.challenge = challenge

            val creatorName: TextView = view.findViewById(R.id.challenge_item_creator_name)
            creatorName.text = challenge.assignedFrom?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

            val titleTextView: TextView = view.findViewById(R.id.challenge_item_challenge_title)
            titleTextView.text = challenge.title.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

            val backgroundShapes = arrayListOf(
                R.drawable.challenge_shape_green,
                R.drawable.challenge_shape_red,
                R.drawable.challenge_shape_orange,
                R.drawable.challenge_shape_purple,
            )

            val linearLayout: LinearLayout = view.findViewById(R.id.challenge_item_layout)
            linearLayout.setBackgroundResource(backgroundShapes[challenge.colorId?: 0])

            val avatarImageView: ImageView = view.findViewById(R.id.challenge_item_avatar)
            avatarImageView.setImageResource(avatarsList[challenge.avatarId?: 0])

            val dueTextView: TextView = view.findViewById(R.id.challenge_item_due)
            if (challenge.completed == true) {
                dueTextView.text = "Completed on: today"
            }
            else {
                dueTextView.text = "Due to: today"
            }
        }

    }

}