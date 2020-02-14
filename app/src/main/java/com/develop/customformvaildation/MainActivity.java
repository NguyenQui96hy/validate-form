package com.develop.customformvaildation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.develop.customformvaildation.formvalidation.Validator;

public class MainActivity extends AppCompatActivity {
    private ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewGroup= findViewById(R.id.viewGroup);


    }

    public void onClickLister(View view) {
        Validator validator = new Validator(viewGroup, getApplicationContext());
        validator.validate();
    }
}
