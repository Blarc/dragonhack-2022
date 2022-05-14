package si.blarc.firebase

import com.google.firebase.database.*
import si.blarc.entity.Challenge
import si.blarc.entity.User


object FirebaseUtils {

    var curUserId : String = "YEVKpig4RVerMg3763lSdiKrzBs1";

    fun getIdOfCurUser() : String {
        return curUserId
    }

    fun addChallenge(challenge: Challenge) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(getIdOfCurUser()).child("challenges").push()

        myRef.setValue(challenge);
    }

    fun getUsersRef() : DatabaseReference {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference

        return myRef
    }

    fun getChallengesRef() : DatabaseReference {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(getIdOfCurUser()).child("challenges")

        return myRef
    }

    fun getFriendsRef() : DatabaseReference {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(getIdOfCurUser()).child("friends")

        return myRef
    }

    fun addFriend(user: User) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(getIdOfCurUser()).child("friends").push()

        myRef.setValue(user);
    }



}