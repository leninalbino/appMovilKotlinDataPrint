package idat.dadmi.appmovilkotlindataprint.utilitarios

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import idat.dadmi.appmovilkotlindataprint.R

class SharedPreferencesManager() {

    private val APP_SETTINGS_FILE = "APP_SETTINGS"
    //private var prefs: SharedPreferences = context!!.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    companion object {
        const val USER_TOKEN = "token"
    }
    private fun getSharedPreferences(): SharedPreferences{
        return MiApp.applicationContext.getSharedPreferences(APP_SETTINGS_FILE, MODE_PRIVATE)
    }
    fun setSomeBooleanValue(nombre:String, valor:Boolean){
        val editor = getSharedPreferences().edit()
        editor.putBoolean(nombre,valor)
            editor.commit()
    }
    fun getSomeBooleanValue(nombre: String):Boolean{
        return getSharedPreferences().getBoolean(nombre,false)
    }
    fun deletePreference(nombre:String){
        getSharedPreferences().edit().remove(nombre).apply()
    }


    fun setSomeStringValue(nombre:String, valor:String){
        val editor = getSharedPreferences().edit()
        editor.putString(nombre,valor)
        editor.commit()

    }
    fun getSomeStringValue(token:String): String?{
        return getSharedPreferences().getString(token,"").toString()
    }



    /**
     * Function to fetch auth token
     */
   // fun fetchAuthToken(): String? {
     //   return prefs.getString(USER_TOKEN, null)
   // }
}