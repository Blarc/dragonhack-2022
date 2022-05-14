package si.blarc.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import si.blarc.Firebase.FirebaseUtils
import si.blarc.Firebase.FirebaseUtils.addChallenge
import si.blarc.Firebase.FirebaseUtils.getChallengesRef
import si.blarc.Firebase.FirebaseUtils.getFriendsRef
import si.blarc.Firebase.FirebaseUtils.getUsersRef
import si.blarc.entity.Challenge
import si.blarc.entity.User

class BaseViewModel : ViewModel() {

    val challenges = MutableLiveData<MutableList<Challenge>>(mutableListOf())

    val users = MutableLiveData<ArrayList<User>>()

    val friends = MutableLiveData<ArrayList<User>>()

    init {
        listenForChallengesOnFirebase()

        getUsers()

        getFriends()
    }

    fun getCurrentUser() : User {
        return User(FirebaseUtils.getIdOfCurUser(), "")
    }

    private fun getCompletedChallenges() : List<Challenge> {
        return ArrayList(challenges.value).filter { challenge ->
            challenge.completed == true
        }
    }

    fun addChallenge(challenge: Challenge) {
        FirebaseUtils.addChallenge(challenge)
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

    private fun getUsers() {
        val usersIn = this.users

        getUsersRef().get().addOnCompleteListener {
            val usersList = ArrayList<User>()

            for (postSnapshot in it.result.children) {
                //val curChallenge = Challenge("", "", 0, "", "", "");
                val userId = postSnapshot.key
                usersList.add(User(userId!!, ""))
            }

            usersIn.value = usersList
        }
    }

    private fun getFriends() {
        val friendsIn = this.friends
        getFriendsRef().addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                //snapshot.getValue(List<Challenge::class.java>)
                val friendsList = ArrayList<User>()

                for (postSnapshot in snapshot.children) {
                    //val curChallenge = Challenge("", "", 0, "", "", "");
                    val friend = postSnapshot.getValue(User::class.java)
                    friendsList.add(friend!!)
                }

                friendsIn.value = friendsList
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