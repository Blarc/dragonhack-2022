package si.blarc.Firebase

import com.google.firebase.database.FirebaseDatabase
import si.blarc.entity.Challenge


object FirebaseUtils {

    var curUserId : String = "1234567890";

    fun addChallenge(challenge: Challenge) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(curUserId).child("challenges").push()

        myRef.setValue(challenge);
    }

}