package com.example.bloodlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    EditText Name,blgrp,Unit,Hos,Phone;
    Button btadd,BtnShow,btnUpdate;
    DatabaseReference dbRef;

    Request RE;

    private void clearControls() {

        Name.setText("");
        blgrp.setText("");
        Unit.setText("");
        Hos.setText("");
        Phone.setText("");


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.name);
        blgrp = findViewById(R.id.bgrp);
        Unit=findViewById(R.id.units);
        Hos=findViewById(R.id.hos);
        Phone=findViewById(R.id.Pno);

        btadd = findViewById(R.id.add);
        BtnShow=findViewById(R.id.btnShow);
        btnUpdate=findViewById(R.id.btnUp);


        RE=new Request();

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Request");

                try {
                    if (TextUtils.isEmpty(Name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Name", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(blgrp.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the blood group", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(Unit.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Units", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(Hos.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Hospital  ", Toast.LENGTH_SHORT);

                    else {

                        RE.setBlgrp(blgrp.getText().toString().trim());
                        RE.setPno(Integer.parseInt(Phone.getText().toString().trim()));
                        RE.setName(Name.getText().toString().trim());
                        RE.setHos(Hos.getText().toString().trim());
                        RE.setUnits(Unit.getText().toString().trim());



                        //dbRef.push().setValue(RE);
                        dbRef.child("R1").setValue(RE);
                        Toast.makeText(getApplicationContext(), "Data entered success", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid ContactNumber", Toast.LENGTH_SHORT).show();
                }
            }

        });
     //show the data what is in the database
        BtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Request").child("R1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.hasChildren()){
                           Name.setText(dataSnapshot.child("name").getValue().toString());
                            blgrp.setText(dataSnapshot.child("blgrp").getValue().toString());
                            Unit.setText(dataSnapshot.child("units").getValue().toString());
                            Hos.setText(dataSnapshot.child("hos").getValue().toString());
                            Phone.setText(dataSnapshot.child("pno").getValue().toString());



                        }
                        else
                            Toast.makeText(getApplicationContext(),"NO DATA SOURCE TO DISPLAY",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference upRef=FirebaseDatabase.getInstance().getReference().child("Request");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("R1"))
                      try {
                          RE.setName(Name.getText().toString().trim());
                          RE.setBlgrp(blgrp.getText().toString().trim());
                          RE.setUnits(Unit.getText().toString().trim());
                          RE.setHos(Hos.getText().toString().trim());
                          RE.setPno(Integer.parseInt(Phone.getText().toString().trim()));

                          dbRef = FirebaseDatabase.getInstance().getReference().child("Request");
                          dbRef.setValue(RE);
                          clearControls();
                          Toast.makeText(getApplicationContext(), "Data Updated successfully", Toast.LENGTH_SHORT).show();
                      }
                        catch (NumberFormatException e){
                          Toast.makeText(getApplicationContext(),"Invalid Contact Number",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });






             }
         }


