package si.blarc.UI

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import si.blarc.entity.Challenge
import com.google.firebase.database.ValueEventListener
import si.blarc.Firebase.FirebaseUtils.getChallengesRef

class BaseViewModel : ViewModel() {

    val challenges: MutableList<Challenge?> = ArrayList()


    init {
        getChallengesRef().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //snapshot.getValue(List<Challenge::class.java>)
                challenges.clear()

                for (postSnapshot in snapshot.children) {
                    //val curChallenge = Challenge("", "", 0, "", "", "");
                    val challenge = postSnapshot.getValue(Challenge::class.java)
                    challenges.add(challenge)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                print("The read of all challenges failed!")
            }
        })
    }

}