package com.example.alexy.tubtabbar.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.alexy.tubtabbar.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Connection extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;
    String name;
    ProfilePictureView profilePictureView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_connection);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.alexy.webservice",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {


        }
        sharedPreferences = getBaseContext().getSharedPreferences("PREF", MODE_PRIVATE);
        if (sharedPreferences.contains("isConnected")) {
            Boolean isConnected = sharedPreferences.getBoolean("isConnected", false);
        }
        profilePictureView = (ProfilePictureView) findViewById(R.id.image);
        if (isLoggedIn()){
            profilePictureView.setVisibility(View.VISIBLE);
        }
        else {
            profilePictureView.setVisibility(View.INVISIBLE);
        }
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile"));
        callbackManager = CallbackManager.Factory.create();
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null){
                    updateView();
                }
            }
        };
    }

    private boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateView();
    }

    private void updateView(){
        if (isLoggedIn()){
            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            Log.v("LoginActivity", response.toString());

                            // Application code
                            try {
                                name = object.getString("name");
                                profilePictureView.setProfileId(object.getString("id"));
                                profilePictureView.setVisibility(View.VISIBLE);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name");
            request.setParameters(parameters);
            request.executeAsync();
            sharedPreferences
                        .edit()
                        .putBoolean("isConnected", true)
                        .putString("facebookName", name) // FIXME : The task is an async, so sometime, name is null.
                        .apply();

        }
        else {
            profilePictureView.setVisibility(View.INVISIBLE);
            sharedPreferences
                    .edit()
                    .putBoolean("isConnected", false)
                    .putString("facebookName", null)
                    .apply();
        }
    }

}
