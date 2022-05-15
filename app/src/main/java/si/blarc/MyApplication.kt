package si.blarc

import android.app.Application

class MyApplication : Application() {
    companion object {
        var curUserId : String = "";
    }
}