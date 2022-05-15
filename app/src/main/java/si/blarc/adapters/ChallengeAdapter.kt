package si.blarc.adapters

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import si.blarc.MainActivity
import si.blarc.R
import si.blarc.activities.DetailsChallengeActivity
import si.blarc.entity.Challenge
import si.blarc.fragments.ARG_CHALLENGE_DETAILS
import si.blarc.inflate

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
            this.challenge = challenge;

            val textView: TextView = view.findViewById(R.id.challenge_item_challenge_title)
            val avatarImageView: ImageView = view.findViewById(R.id.challenge_item_avatar)

            val avatarsList = arrayListOf(
                R.drawable.ic_avatar_1,
                R.drawable.ic_avatar_2,
                R.drawable.ic_avatar_3,
                R.drawable.ic_avatar_4,
                R.drawable.ic_avatar_5,
                R.drawable.ic_avatar_6
            )

            avatarImageView.setImageResource(avatarsList.random())
        }

    }

}