package com.ex.sams.login;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.Login;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {
private CallbackManager callbackManager;
private TextView textView;
private AccessTokenTracker accessTokenTracker;
private ProfileTracker profileTracker;
LoginButton loginButton;
ImageView imageView;
FacebookCallback<LoginResult> callback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton=(LoginButton)findViewById(R.id.loginbutton);
textView=(TextView) findViewById(R.id.textView);
          imageView =(ImageView)findViewById(R.id.imageView);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager= CallbackManager.Factory.create();

        accessTokenTracker=new AccessTokenTracker() {


            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newToken) {

            }
        };

profileTracker=new ProfileTracker() {
    @Override
    protected void onCurrentProfileChanged(Profile oldProfile, Profile newprofile) {
displayMessage(newprofile);
    }

    private void displayMessage(Profile newprofile) {
    }
};




accessTokenTracker.startTracking();
profileTracker.startTracking();
callback=new FacebookCallback<LoginResult>() {
    @Override
    public void onSuccess(LoginResult loginResult) {
        AccessToken accessToken = loginResult.getAccessToken();
        Profile profile = Profile.getCurrentProfile();

    }



    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }
};
loginButton.setReadPermissions("user_friends");
loginButton.registerCallback(callbackManager,callback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
    private void displayMessage(Profile profile) {
        if (profile != null) {
            textView.setText(profile.getName());
           String url=profile.getProfilePictureUri(150,150).toString();
            Glide.with(getApplicationContext()).load(url).error(R.mipmap.ic_launcher).into((imageView));
        }
    }
    public void onStop(){
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }


    public void onResume(){
        super.onResume();
        Profile profile=Profile.getCurrentProfile();

        displayMessage(profile);

    }
}
