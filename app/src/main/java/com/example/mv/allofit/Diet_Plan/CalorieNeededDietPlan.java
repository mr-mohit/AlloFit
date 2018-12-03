package com.example.mv.allofit.Diet_Plan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.example.mv.allofit.R;

public class CalorieNeededDietPlan extends AppCompatActivity {

    Button btnBurntCalorie;
    Button btnFoodCalorie;
    Button  next1;
    String txtWeight,txtAge,txtHeight,txtBMRData;
    Spinner spnActivity;
    String txtActivityValue,mWCalorie,iWCalorie,rWCalorie,txtAgeMultiple,txtHeightMultiple;
    TextView txtIncreaseCalorie,currWeight,txtReduceCalorie,txtCurrentCalorie;
    double genderBmr,weight,height,age,ageMultiple,normalCalorie,actValue;
    int increaseWCalorie,reduceWCalorie;
    String  txtWeightMultiple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_needed_diet_plan);
        getSupportActionBar().setTitle("Calories Needed");
        txtCurrentCalorie = findViewById(R.id.textView8);
        txtIncreaseCalorie =  findViewById(R.id.textView9);
        txtReduceCalorie =  findViewById(R.id.textView10);
        currWeight =  findViewById(R.id.textView2);
        next1 = findViewById(R.id.button2);
        //  TextView txtNormalBMRCalorie = (TextView) findViewById(C0239R.id.txtNormalBMRCalorie);
        Bundle b = getIntent().getExtras();
        txtWeight=(b.getString("weightValue"));
        txtAge=(b.getString("ageValue"));
        txtHeight=(b.getString("heightValue"));
        txtBMRData=(b.getString("bmrValue"));
        if (txtBMRData.equals("66.47")) {
            txtAgeMultiple="6.75";
            txtWeightMultiple="13.75";
            txtHeightMultiple="5";
        } else {
            txtAgeMultiple="4.67";
            txtHeightMultiple="1.8";
            txtWeightMultiple="9.4";
        }
        currWeight.setText(txtWeight);
        next1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent it=new Intent(CalorieNeededDietPlan.this,Diet_Foods.class);
                startActivity(it);

            }
        });
        List<String> list = new ArrayList<String>();
        list.add("1. Sedentary - No Exercise");
        list.add("2. Lightly Active - Little Bit Exercise");
        list.add("3. Moderately Active - Exercise/Sports");
        list.add("4. Very Active - Hard Exercise/Daily Sports");
        list.add("5. Extremely Active - Very hard Exercise/ Physical Job");

        spnActivity = findViewById(R.id.spinner);
        spnActivity.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (spnActivity.getSelectedItem().toString().equals("1. Sedentary - No Exercise")) {
                    txtActivityValue="1.2";
                    genderBmr = Double.parseDouble(txtBMRData);
                    weight = Double.parseDouble(txtWeight);
                    height = Double.parseDouble(txtHeight);
                    age = Double.parseDouble(txtAge);
                    ageMultiple = Double.parseDouble(txtAgeMultiple);
                    normalCalorie = (((Double.parseDouble(txtWeightMultiple) * weight) + genderBmr) + (Double.parseDouble(txtHeightMultiple) * height)) - (ageMultiple * age);
                    actValue = Double.parseDouble(txtActivityValue);
                    increaseWCalorie = (int) ((actValue * normalCalorie) + 500.0d);
                    reduceWCalorie = (int) ((actValue * normalCalorie) - 500.0d);
                    mWCalorie = Integer.toString((int) (actValue * normalCalorie));
                    iWCalorie = Integer.toString(increaseWCalorie);
                    rWCalorie = Integer.toString(reduceWCalorie);
                    txtCurrentCalorie.setText(mWCalorie);
                    txtIncreaseCalorie.setText(iWCalorie);
                    txtReduceCalorie.setText(rWCalorie);
                } else if (spnActivity.getSelectedItem().toString().equals("2. Lightly Active - Little Bit Exercise")) {
                    txtActivityValue="1.37";
                    genderBmr = Double.parseDouble(txtBMRData);
                    weight = Double.parseDouble(txtWeight);
                    height = Double.parseDouble(txtHeight);
                    age = Double.parseDouble(txtAge);
                    ageMultiple = Double.parseDouble(txtAgeMultiple);
                    normalCalorie = (((Double.parseDouble(txtWeightMultiple) * weight) + genderBmr) + (Double.parseDouble(txtHeightMultiple) * height)) - (ageMultiple * age);
                    actValue = Double.parseDouble(txtActivityValue);
                    increaseWCalorie = (int) ((actValue * normalCalorie) + 500.0d);
                    reduceWCalorie = (int) ((actValue * normalCalorie) - 500.0d);
                    mWCalorie = Integer.toString((int)(actValue * normalCalorie));
                    iWCalorie = Integer.toString(increaseWCalorie);
                    rWCalorie = Integer.toString(reduceWCalorie);
                    txtCurrentCalorie.setText(mWCalorie);
                    txtIncreaseCalorie.setText(iWCalorie);
                    txtReduceCalorie.setText(rWCalorie);
                } else if (spnActivity.getSelectedItem().toString().equals("3. Moderately Active - Exercise/Sports")) {
                    txtActivityValue="1.55";
                    genderBmr = Double.parseDouble(txtBMRData);
                    weight = Double.parseDouble(txtWeight);
                    height = Double.parseDouble(txtHeight);
                    age = Double.parseDouble(txtAge);
                    ageMultiple = Double.parseDouble(txtAgeMultiple);
                    normalCalorie = (((Double.parseDouble(txtWeightMultiple) * weight) + genderBmr) + (Double.parseDouble(txtHeightMultiple) * height)) - (ageMultiple * age);
                    actValue = Double.parseDouble(txtActivityValue);
                    increaseWCalorie = (int) ((actValue * normalCalorie) + 500.0d);
                    reduceWCalorie = (int) ((actValue * normalCalorie) - 500.0d);
                    mWCalorie = Integer.toString((int) (actValue * normalCalorie));
                    iWCalorie = Integer.toString(increaseWCalorie);
                    rWCalorie = Integer.toString(reduceWCalorie);
                    txtCurrentCalorie.setText(mWCalorie);
                    txtIncreaseCalorie.setText(iWCalorie);
                    txtReduceCalorie.setText(rWCalorie);
                } else if (spnActivity.getSelectedItem().toString().equals("4. Very Active - Hard Exercise/Daily Sports")) {
                    txtActivityValue="1.72";
                    genderBmr = Double.parseDouble(txtBMRData);
                    weight = Double.parseDouble(txtWeight);
                    height = Double.parseDouble(txtHeight);
                    age = Double.parseDouble(txtAge);
                    ageMultiple = Double.parseDouble(txtAgeMultiple);
                    normalCalorie = (((Double.parseDouble(txtWeightMultiple) * weight) + genderBmr) + (Double.parseDouble(txtHeightMultiple) * height)) - (ageMultiple * age);
                    actValue = Double.parseDouble(txtActivityValue);
                    increaseWCalorie = (int) ((actValue * normalCalorie) + 500.0d);
                    reduceWCalorie = (int) ((actValue * normalCalorie) - 500.0d);
                    mWCalorie = Integer.toString((int) (actValue * normalCalorie));
                    iWCalorie = Integer.toString(increaseWCalorie);
                    rWCalorie = Integer.toString(reduceWCalorie);
                    txtCurrentCalorie.setText(mWCalorie);
                    txtIncreaseCalorie.setText(iWCalorie);
                    txtReduceCalorie.setText(rWCalorie);
                } else if (spnActivity.getSelectedItem().toString().equals("5. Extremely Active - Very hard Exercise/ Physical Job")) {
                    txtActivityValue="1.9";
                    genderBmr = Double.parseDouble(txtBMRData);
                    weight = Double.parseDouble(txtWeight);
                    height = Double.parseDouble(txtHeight);
                    age = Double.parseDouble(txtAge);
                    ageMultiple = Double.parseDouble(txtAgeMultiple);
                    normalCalorie = (((Double.parseDouble(txtWeightMultiple) * weight) + genderBmr) + (Double.parseDouble(txtHeightMultiple) * height)) - (ageMultiple * age);
                    actValue = Double.parseDouble(txtActivityValue);
                    increaseWCalorie = (int) ((actValue * normalCalorie) + 500.0d);
                    reduceWCalorie = (int) ((actValue * normalCalorie) - 500.0d);
                    mWCalorie = Integer.toString((int) (actValue * normalCalorie));
                    iWCalorie = Integer.toString(increaseWCalorie);
                    rWCalorie = Integer.toString(reduceWCalorie);
                    txtCurrentCalorie.setText(mWCalorie);
                    txtIncreaseCalorie.setText(iWCalorie);
                    txtReduceCalorie.setText(rWCalorie);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
}
