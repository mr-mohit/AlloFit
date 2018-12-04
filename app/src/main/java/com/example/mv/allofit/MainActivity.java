package com.example.mv.allofit;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikepenz.materialize.color.Material;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    Button txt;
    private int REQUEST_CODE=123;
    private static final int RC_SIGN_IN = 123;
    CircularImageView female,male;
    RelativeLayout relativeLayout,relativeLayout2;
    SharedPreferences prefs;
    AppCompatImageView female_tick,male_tick;
    TextView male_txt,female_txt,height_txt,weight_txt;
    boolean female_selected=false, male_selected=false;
    AlertDialog.Builder builder_h,builder_w;
    @NonNull
    public static Intent createIntent(@NonNull Context context) {
        return new Intent(context, MainActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntroManager introManager=new IntroManager(getBaseContext());
        if(introManager.isFirstTimeLaunch()){
            introManager.setFirstTimeLaunch(false);
            startActivity(new Intent(getBaseContext(),Intro.class));
            finish();
        }
        //Toast.makeText(this, "out of IF", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPermissions();
        }
        txt=findViewById(R.id.login_btn);
        female_tick=findViewById(R.id.iv_female_check);
        female=findViewById(R.id.iv_female);
        male_tick=findViewById(R.id.iv_male_check);
        male=findViewById(R.id.iv_male);
        relativeLayout=findViewById(R.id.relativeLayout);
        relativeLayout2=findViewById(R.id.relativeLayout2);
        female_txt=findViewById(R.id.textView7);
        male_txt=findViewById(R.id.textView9);
        height_txt=findViewById(R.id.height_txt);
        height_txt.setCompoundDrawablePadding(30);
        weight_txt=findViewById(R.id.weight_txt);
        weight_txt.setCompoundDrawablePadding(30);
        final ColorStateList oldcolor=female_txt.getTextColors();
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female_selected=true;
                female_tick.setVisibility(View.VISIBLE);
                txt.setVisibility(View.VISIBLE);
                female.setBackgroundColor(Color.parseColor("#FF69B4"));
                male_tick.setVisibility(View.INVISIBLE);
                male.setBackgroundColor(0);
                male_txt.setTextColor(Color.parseColor("#C0C0C0"));
                female_txt.setTextColor(oldcolor);
            }
        });
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male_selected=true;
                male_tick.setVisibility(View.VISIBLE);
                txt.setVisibility(View.VISIBLE);
                male.setBackgroundColor(Color.BLUE);
                female_tick.setVisibility(View.INVISIBLE);
                female.setBackgroundColor(0);
                female_txt.setTextColor(Color.parseColor("#C0C0C0"));
                male_txt.setTextColor(oldcolor);
            }
        });
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt.getText().equals("Next")&&(female_selected || male_selected)){
                    prefs=getSharedPreferences("User_Data",MODE_PRIVATE);
                    SharedPreferences.Editor editor=prefs.edit();
                    if(female_selected)
                        editor.putString("gender","female");
                    if(male_selected)
                        editor.putString("gender","male");
                    editor.apply();
                    relativeLayout.setVisibility(View.INVISIBLE);
                    txt.setText("Start");
                    relativeLayout2.setVisibility(View.VISIBLE);
                //    show_height_dialog();
                //    show_weight_dialog();
                }else if(txt.getText().equals("Start")) {
                    createSignInIntent();
                }
            }
        });
        height_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_height_dialog();
                builder_h.show();
            }
        });
        weight_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_weight_dialog();
                builder_w.show();
            }
        });

    }
    public void show_height_dialog(){
        builder_h=new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        final View view=inflater.inflate(R.layout.height_dialog,null);
       /* if(view.getParent()!=null)
            ((ViewGroup)view.getParent()).removeView(view); */
        builder_h.setView(view);
        final NumberPicker height_picker=view.findViewById(R.id.height_picker);
        final NumberPicker height_picker2=view.findViewById(R.id.height_picker2);
        height_picker.setMinValue(1);
        height_picker.setMaxValue(8);
        height_picker2.setMinValue(0);
        height_picker2.setMaxValue(11);
        height_picker.setValue(5);
        height_picker2.setValue(0);
        height_txt.setText(height_picker.getValue()+" ft "+height_picker2.getValue()+" in");
        builder_h.setTitle("Select Height")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            height_txt.setText(height_picker.getValue()+" ft"+height_picker2.getValue()+" In");
                            SharedPreferences.Editor editor=prefs.edit();
                            prefs=getSharedPreferences("User_Data",MODE_PRIVATE);
                            editor.putInt("height_feet",height_picker.getValue());
                            editor.putInt("height_inches",height_picker2.getValue());
                            editor.apply();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                    }
                })
                .setCancelable(false).create();
    }
    public void show_weight_dialog(){
        builder_w=new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        final View view=inflater.inflate(R.layout.weight_dialog,null);
        builder_w.setView(view);
        final NumberPicker weight_picker=view.findViewById(R.id.weight_picker);
        final NumberPicker weight_picker2=view.findViewById(R.id.weight_picker2);
        weight_picker.setMinValue(15);
        weight_picker.setMaxValue(300);
        weight_picker2.setMinValue(0);
        weight_picker2.setMaxValue(9);
        weight_picker.setValue(69);
        weight_picker2.setValue(5);
        weight_txt.setText(weight_picker.getValue()+"."+weight_picker2.getValue()+" kg");
        builder_w.setTitle("Select Weight")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        height_txt.setText(weight_picker.getValue()+"."+weight_picker2.getValue()+" kg");
                        SharedPreferences.Editor editor=prefs.edit();
                        prefs=getSharedPreferences("User_Data",MODE_PRIVATE);
                        editor.putFloat("weight",Float.parseFloat(String.valueOf(weight_picker.getValue()+"."+weight_picker2.getValue())));
                        editor.apply();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false).create();
    }
    public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
               // new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
             //   new AuthUI.IdpConfig.FacebookBuilder().build(),
               // new AuthUI.IdpConfig.TwitterBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(!BuildConfig.DEBUG /* credentials */, true /* hints */)
                        .build(),
                RC_SIGN_IN);
        // [END auth_fui_create_intent]
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                startActivity(Main_page.createIntent(this, response));
                finish();

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                if(response!=null){
                    Toast.makeText(this, "Sign In Cancelled", Toast.LENGTH_LONG).show();
                }
                if(response.getError().getErrorCode()==ErrorCodes.NO_NETWORK)
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
                if(response.getError().getErrorCode()==ErrorCodes.UNKNOWN_ERROR)
                    Toast.makeText(this, "Unknown Error: "+response.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(Main_page.createIntent(this, null));
            finish();
        }
    }
    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
        // [END auth_fui_signout]
    }

    public void delete() {
        // [START auth_fui_delete]
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
        // [END auth_fui_delete]
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissions() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            // Fire off an async request to actually get the permission
            // This will show the standard permission request height_dialog UI
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE);

        }
    }
}
