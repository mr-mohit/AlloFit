package com.example.mv.allofit.Diet_Plan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mv.allofit.MainActivity;
import com.example.mv.allofit.R;

import java.text.DecimalFormat;

public class Bmi extends AppCompatActivity {

    EditText foot,inch,weight,age;
    String sd;
    TextView status,bmi;
    Button b1;
    int flag=0;
    double feet = 30.48d;
    double inch1 = 2.54d;
    float h1;
    DecimalFormat f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        getSupportActionBar().setTitle("Body Mass Index");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        f=new DecimalFormat("#0.#");
        age=findViewById(R.id.age);
        foot=findViewById(R.id.foot);
        inch=findViewById(R.id.inch);
        weight=findViewById(R.id.weight);
        bmi=findViewById(R.id.bmi);
        status=findViewById(R.id.status);
        b1=findViewById(R.id.button);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onClick(View view) {
        String ft = foot.getText().toString();
        String in = inch.getText().toString();
        String weightStr = weight.getText().toString();
        if (ft != null && !"".equals(ft) && in != null && !"".equals(in) && weightStr != null  &&  !"".equals(weightStr))
        {
            sd=Double.toString((Bmi.this.feet * Double.parseDouble(foot.getText().toString())) + (Bmi.this.inch1 * Double.parseDouble(inch.getText().toString())));

            h1 = Float.parseFloat(sd);
            Double weightValue = Double.parseDouble(weightStr);
            Double bmicalculated = weightValue / (h1 * h1);
            bmicalculated*=10000;
            bmicalculated=Double.parseDouble(f.format(bmicalculated));
            bmi.setText(String.valueOf(bmicalculated));
            flag=1;
            if (bmicalculated >= 0&&bmicalculated<16f)
            {
                status.setText("Severely Under Weight");
            }
            else if (bmicalculated >= 16f&&bmicalculated<18.5f)
            {
                status.setText("Under Weight");
            }
            else if (bmicalculated >= 18.5f&&bmicalculated<25f)
            {
                status.setText("Normal");
            }
            else
            {
                status.setText("Obese");
            }
        }
        else
        {
            Toast.makeText(this, "Please enter correct data!", Toast.LENGTH_SHORT).show();
        }

    }

    public void next(View view) {

        if(flag==1) {
            Intent i = new Intent(Bmi.this,CalorieNeededDietPlan.class);
            Bundle b = new Bundle();
            b.putString("weightValue", weight.getText().toString());
            b.putString("heightValue", sd);
            b.putString("ageValue", age.getText().toString());
            b.putString("bmrValue", bmi.getText().toString());
            i.putExtras(b);
            startActivity(i);
        }else
        {
            Toast.makeText(this, "Please first calculate your Bmi!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
      /*  AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) "Warning..!!");
        builder.setMessage((CharSequence) "Do you want to Exit ?");
        builder.setNegativeButton((CharSequence) "No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton((CharSequence) "Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Bmi.this.finish();
            }
        });
        builder.create().show(); */
      super.onBackPressed();
    }
}
