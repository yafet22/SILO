package silo.com.silo.UI.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import silo.com.silo.UI.UI.Login;
import silo.com.silo.UI.UI.MainActivity;

public class SessionManager {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "SiloSession";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_STATUS = "status";

    public SessionManager(Context context)
    {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSessions(String phoneNumber, String username, String id, String status)
    {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_PHONE, phoneNumber);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_STATUS, status);
        editor.commit();
    }

    public HashMap<String, String> getUserSessionsDetails()
    {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
        user.put(KEY_ID, pref.getString(KEY_ID, null));

        return user;
    }

    public void checkLogin()
    {
        if (!this.isLoggedIn())
        {
            Intent intent = new Intent(context, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public String getKeyId()
    {
        return pref.getString(KEY_ID,"id");
    }

    public String getKeyName()
    {
        return pref.getString(KEY_USERNAME,"name");
    }

    public String getKeyPhone()
    {
        return pref.getString(KEY_PHONE,"phone");
    }

    public String getKeyStatus()
    {
        return pref.getString(KEY_STATUS,"status");
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
