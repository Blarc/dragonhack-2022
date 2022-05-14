package si.blarc.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import si.blarc.Firebase.FirebaseUtils.getChallengesRef
import si.blarc.entity.Challenge

class BaseViewModel : ViewModel() {

    //val challenges: MutableList<Challenge> = ArrayList()
    val challenges = MutableLiveData<MutableList<Challenge>>(mutableListOf())

    init {
        listenForChallengesOnFirebase()
    }

    private fun listenForChallengesOnFirebase() {
        val challengesIn = this.challenges
        getChallengesRef().addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                //snapshot.getValue(List<Challenge::class.java>)
                val challengesList = ArrayList<Challenge>()

                for (postSnapshot in snapshot.children) {
                    //val curChallenge = Challenge("", "", 0, "", "", "");
                    val challenge = postSnapshot.getValue(Challenge::class.java)
                    challengesList.add(challenge!!)
                }

                challengesIn.value = challengesList
            }

            override fun onCancelled(databaseError: DatabaseError) {
                print("The read of all challenges failed!")
            }
        })
    }

    private fun getChallengesFromFirebase() {
        getChallengesRef().get().addOnCompleteListener {
            val challengesList = ArrayList<Challenge>()

            for (postSnapshot in it.result.children) {
                //val curChallenge = Challenge("", "", 0, "", "", "");
                val challenge = postSnapshot.getValue(Challenge::class.java)
                challengesList.add(challenge!!)
            }

            this.challenges.value = challengesList
        }
    }

}