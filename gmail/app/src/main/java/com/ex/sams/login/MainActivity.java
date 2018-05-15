package com.ex.sams.login;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
private CallbackManager callbackManager;
private TextView textView;
private AccessTokenTracker accessTokenTracker;
private ProfileTracker profileTracker;
LoginButton loginButton;
ImageView imageView;
FacebookCallback<LoginResult> callback;

    private SignInButton signIn;
    private GoogleApiClient mGoogleApiClient;
    private Button signout;
    private TextView Name, Email;
    private static final int REQ_CODE = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton=(LoginButton)findViewById(R.id.loginbutton);
textView=(TextView) findViewById(R.id.textView);
          imageView =(ImageView)findViewById(R.id.imageView);
        signIn = findViewById(R.id.bn_In);
        signout = findViewById(R.id.bn_out);
        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        signIn.setOnClickListener(this);
        signout.setOnClickListener(this);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager= CallbackManager.Factory.create();

        accessTokenTracker=new AccessTokenTracker() {



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


        GoogleSignInOptions signInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
        private void signIn(){
        Intent intent= Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent,REQ_CODE);

    }
    private void signOut(){


        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }
    private void handleResult(GoogleSignInResult result)

    {
        if(result.isSuccess()){
            GoogleSignInAccount account= result.getSignInAccount();
            String name=account.getDisplayName();
            String email= account.getEmail();
            Name.setText(name);
            Email.setText(email);
            updateUI(true);
        }
        else{
            updateUI(false);
        }
    }
    private void updateUI(boolean isLogin){

        if(isLogin){
            signIn.setVisibility(View.GONE);

        }
        else{
            signIn.setVisibility(View.VISIBLE);
        }
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

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bn_In:
                signIn();
                break;
            case R.id.bn_out:
                signOut();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
