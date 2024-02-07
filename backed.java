package com.example.dare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextInputEditText title, info;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_title = title.getText().toString().trim();
                String txt_info = info.getText().toString().trim();
                HashMap<String, String> data = new HashMap<>();
                data.put("title", txt_title);
                data.put("Info", txt_info);

                databaseReference.setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @SuppressLint("WrongViewCast")
    public void initialize() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        //here we are creating the child node in firebase
        databaseReference = firebaseDatabase.getReference().child("MyData");
        title = findViewById(R.id.inp_box_title); //first box
        info = findViewById(R.id.inp_box_one); //second box
        btn = findViewById(R.id.mybutton); //BUTTON
    }
}
