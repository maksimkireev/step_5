package com.example.step_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }


    public void clic(View view){
        AlertDialog.Builder builder =  new AlertDialog.Builder(this );
        builder.setTitle(R.string.dialog_title);
        ConstraintLayout cl = (ConstraintLayout) getLayoutInflater().inflate(R.layout.dialog_layout, null);

        builder.setPositiveButton(R.string.dialog_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AlertDialog ad = (AlertDialog) dialog;
                EditText ed = ad.findViewById(R.id.edText); //Эту переменую необходимо передать на второй экран


                if(ed!= null){
                    ed.getText().toString();// MainActivity.setDistance(ed.getText().toString());
                    // inf.setText(ed.getText().toString());
                }
            }
        });
        builder.setView(cl);
        builder.show();
    }


    public void layout1(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("edKey", ed);
        startActivity(intent);
    }
}