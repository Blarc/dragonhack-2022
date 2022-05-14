package si.blarc.Firebase

import com.google.firebase.database.*
import si.blarc.entity.Challenge


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

    fun getChallengesRef() : DatabaseReference {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(getIdOfCurUser()).child("challenges")

        return myRef
    }

}