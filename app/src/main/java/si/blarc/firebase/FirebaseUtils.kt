package si.blarc.firebase

import com.google.firebase.database.*
import si.blarc.entity.Challenge
import si.blarc.entity.User


object FirebaseUtils {

    val database = FirebaseDatabase.getInstance()
    var curUserId : String = "YEVKpig4RVerMg3763lSdiKrzBs1";

    fun getIdOfCurUser() : String {
        return curUserId
    }

    fun addChallenge(challenge: Challenge) {
        val myRef = database.getReference(getIdOfCurUser()).child("challenges").push()

        myRef.setValue(challenge);
    }

    fun addChallengeToFriend(challenge: Challenge, user: User) {
        val myRef = database.getReference(user.id).child("challenges").push()

        myRef.setValue(challenge);

        var myChallenge = Challenge(challenge.title, challenge.description, challenge.reward, user.id, getIdOfCurUser(), challenge.color, challenge.completed, challenge.dateToDo, challenge.id)

        addChallenge(myChallenge)
    }

    fun updateChallenge(challenge: Challenge) {
        val myRef = database.getReference(getIdOfCurUser())
            .child("challenges")

        // TODO @martinb: Implement this method
    }

    fun getUsersRef() : DatabaseReference {
        val myRef = database.reference

        return myRef
    }

    fun getChallengesRef() : DatabaseReference {
        val myRef = database.getReference(getIdOfCurUser()).child("challenges")

        return myRef
    }

    fun getFriendsRef() : DatabaseReference {
        val myRef = database.getReference(getIdOfCurUser()).child("friends")

        return myRef
    }

    fun addFriend(user: User) {
        val myRef = database.getReference(getIdOfCurUser()).child("friends").push()

        myRef.setValue(user);
    }



}