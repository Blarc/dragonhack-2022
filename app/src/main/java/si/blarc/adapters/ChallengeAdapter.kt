package si.blarc.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import si.blarc.R
import si.blarc.entity.Challenge
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
        }

        fun bindChallenge(challenge: Challenge) {
            this.challenge = challenge;

            val textView: TextView = view.findViewById(R.id.challenge_item_challenge_title)
//            textView.text = challenge.description
        }

    }

}