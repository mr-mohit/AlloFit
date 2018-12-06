package com.example.mv.allofit;

import android.app.AlertDialog;
import android.app.NotificationChannel;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mv.allofit.Diet_Plan.Bmi;
import com.example.mv.allofit.Workout.Workout_main;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.util.ExtraConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main_page extends AppCompatActivity implements SensorEventListener
{
    private static final int PROFILE_SETTING = 1;
    private static final String TAG="Main_page";
    Drawer result;
    AccountHeader headerResult;
    private IProfile profile;
    TextView stepcount,miles;
    SensorManager mSensorManager;
    private Sensor mSensor;
    boolean isrunning=false , fresh_run=true;
    MaterialButton reset;
    Chip start_btn;
    int stepsInsensor,startingCount,steps;
    float distance;
    DecimalFormat f;
    SharedPreferences prefs,goal_pref;
    View Rootview;
    Intent i;
    PackageManager pm;
    NotificationCompat.Builder mBuilder;
    NotificationManagerCompat notificationManager;
    public static final String ANDROID_CHANNEL_ID = "com.mv.allofit.ANDROID";
    @NonNull
    public static Intent createIntent(@NonNull Context context, @Nullable IdpResponse response) {
        return new Intent().setClass(context, Main_page.class).putExtra(ExtraConstants.IDP_RESPONSE, response);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        stepcount=findViewById(R.id.stepcount);
        miles=findViewById(R.id.miles);
        reset=findViewById(R.id.reset);
        Rootview=this.findViewById(android.R.id.content);
        start_btn=findViewById(R.id.start_btn);
       /* start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartStepCounter();
            }
        }); */
        f=new DecimalFormat("#0.#");
        setSupportActionBar(toolbar);
        notificationManager= NotificationManagerCompat.from(getBaseContext());
        createNotificationChannel();
            mBuilder = new NotificationCompat.Builder(this, ANDROID_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_icon)
                    .setContentTitle("New Milestone Reached!!!")
                    .setContentText("You have Successfully Completed your Step goal")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

        goal_pref=getSharedPreferences("com.example.mv.allofit.goal_settings",MODE_PRIVATE);
        pm= getApplicationContext().getPackageManager();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            startActivity(MainActivity.createIntent(this));
            finish();
            return;
        }
        IdpResponse response = getIntent().getParcelableExtra(ExtraConstants.IDP_RESPONSE);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Home Remedies");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Nearby Hospitals");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Body Mass Index(BMI)");
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("WorkOut Plan");
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName("Water Reminder");
       // SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(5).withName(R.string.drawer_item_settings);
        populateProfile(response,savedInstanceState);
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4,
                        item6,
                        item7,
                        new DividerDrawerItem()
                       // ,item5
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if(drawerItem.getIdentifier()==2){
                            i=new Intent(Main_page.this,home_remedy.class);
                            startActivity(i);
                        }
                        if(drawerItem.getIdentifier()==4){
                            i=new Intent(Main_page.this,com.example.mv.allofit.Diet_Plan.Bmi.class);
                            startActivity(i);
                        }
                        if(drawerItem.getIdentifier()==3){
                            i=new Intent(Main_page.this,MapsActivity.class);
                            startActivity(i);
                        }
                        if(drawerItem.getIdentifier()==6){
                            i=new Intent(Main_page.this,Workout_main.class);
                            startActivity(i);
                        }
                        if(drawerItem.getIdentifier()==7){
                            i=new Intent(Main_page.this, a2dv606.androidproject.MainWindow.MainActivity.class);
                            startActivity(i);
                        }
                        //Toast.makeText(Main_page.this, "You clicked - "+position, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();
       DataBaseHelper db=new DataBaseHelper(this);
       //db.createDataBase();
        //Toast.makeText(this, "DB Location: "+DataBaseHelper.DB_PATH, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        isrunning=true;
        if(mSensorManager!=null) {
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
        } /* else{
            new AlertDialog.Builder(this).setTitle("No sensor Found")
                    .setMessage("You device does not have a dedicated sensor to run this Application")
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(final DialogInterface dialogInterface) {
                            finish();
                        }
                    }).setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        } */
    }

    @Override
    protected void onPause() {
        super.onPause();
        isrunning=false;
        if(mSensorManager!=null)
        mSensorManager.unregisterListener(this);
    }

    /**
     * small helper method to reuse the logic to build the AccountHeader
     * this will be used to replace the header of the drawer with a compact/normal header
     */
    private void populateProfile(@Nullable IdpResponse response, Bundle savedInstanceState) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        profile = new ProfileDrawerItem().withName(TextUtils.isEmpty(user.getDisplayName()) ? "No display name" : user.getDisplayName()).withEmail(TextUtils.isEmpty(user.getEmail()) ? "No email" : user.getEmail()).withIcon(getResources().getDrawable(R.drawable.profile_temp));

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withCompactStyle(false)
                .addProfiles(
                        profile
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        //new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new Account").withIcon(getResources().getDrawable(R.drawable.ic_add)).withIdentifier(PROFILE_SETTING),
                       // ,new ProfileSettingDrawerItem().withName("Manage Account").withIcon(R.drawable.ic_settings).withIdentifier(PROFILE_SETTING)
                )
                .withTextColor(ContextCompat.getColor(this, R.color.material_drawer_dark_primary_text))
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        //sample usage of the onProfileChanged listener
                        //if the clicked item has the identifier 1 add a new profile ;)
                        if (profile instanceof IDrawerItem && ((IDrawerItem) profile).getIdentifier() == PROFILE_SETTING) {
                            //IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Batman").withEmail("batman@gmail.com").withIcon(getResources().getDrawable(R.drawable.ic_anon_user_48dp));
                            if (headerResult.getProfiles() != null) {
                                //we know that there are 2 setting elements. set the new profile above them ;)
                                //headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                            } else {
                                //headerResult.addProfiles(newProfile);
                            }
                        }
                        if(profile instanceof ProfileSettingDrawerItem){

                        }

                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(MainActivity.createIntent(Main_page.this));
                            finish();
                        } else {
                            Log.w(TAG, "SignOut:failure", task.getException());
                            showSnackbar("Sign out Failed");
                        }
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,PreferenceSetting.class));
            return true;
        }
        if(id==R.id.logout){
            signOut();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public void StartStepCounter(View v){
        //mSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
    if(start_btn.getText().toString().equalsIgnoreCase("Start Pedometer")){
        if(hasSensors(pm)){
            isrunning=true;
            mSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
            start_btn.setText("Stop Pedometer");
            start_btn.setChipIcon(getResources().getDrawable(R.drawable.ic_stop));
            Toast.makeText(this, "Sensor Manager Intialized", Toast.LENGTH_SHORT).show();
            prefs=getSharedPreferences("Sensor_Data",MODE_PRIVATE);
            if(!prefs.contains("Sensor_Data")){
                SharedPreferences.Editor editor=prefs.edit();
                editor.putInt("step_count",stepsInsensor);
                editor.apply();
            }
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor=prefs.edit();
                    editor.putInt("step_count",stepsInsensor);
                    editor.apply();
                    stepcount.setText("0");
                    miles.setText("0");
                }
            });
        }else
        {
            new AlertDialog.Builder(this).setTitle("No sensor Found")
                    .setMessage("You device does not have a dedicated sensor to run this Application")
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(final DialogInterface dialogInterface) {
                            finish();
                        }
                    }).setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }
      }else if(start_btn.getText().toString().equalsIgnoreCase("Stop Pedometer")){
         mSensorManager.unregisterListener(this);
         isrunning=false;
         start_btn.setText("Start Pedometer");
         start_btn.setChipIcon(getResources().getDrawable(R.drawable.ic_play));
         showSnackbar("Pedometer Stopped");
     }
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(isrunning) {
            if(fresh_run){
                SharedPreferences.Editor editor=prefs.edit();
                editor.putInt("step_goal",(int)sensorEvent.values[0]).commit();
                fresh_run=false;
            }
            //Toast.makeText(this, "Sensor Activated -"+sensorEvent.values[0], Toast.LENGTH_SHORT).show();     <-- For debugging Purposes only
            stepsInsensor=(int) sensorEvent.values[0];
            startingCount=prefs.getInt("step_count",0);
            steps=stepsInsensor-startingCount;
            stepcount.setText(String.valueOf(steps));
            distance = (float)(steps*78)/(float)100000;
            miles.setText(String.valueOf(f.format(distance)));
            if(steps==goal_pref.getInt("goal",-1)){
                //notificationManager=NotificationManagerCompat.from(this);
                notificationManager.notify(111,mBuilder.build());
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    public boolean hasSensors(PackageManager pm) {

        // Require at least Android KitKat
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        // Check that the device supports the step counter and detector sensors
        return currentApiVersion >= 19
                && pm.hasSystemFeature (PackageManager.FEATURE_SENSOR_STEP_COUNTER)
                && pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR);

    }
    private void showSnackbar(String errorMessage) {
        Snackbar.make(Rootview, errorMessage, Snackbar.LENGTH_LONG).show();
    }
    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(ANDROID_CHANNEL_ID, "AlloFit Notification", importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
