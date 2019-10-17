package com.abhijeet.firebasedatabasepoc.activities;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.firebasedatabasepoc.R;
import com.abhijeet.firebasedatabasepoc.adapters.SampleAdapter;
import com.abhijeet.firebasedatabasepoc.models.SampleModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myInfoRef;
    RecyclerView sampleList;

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        myInfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //clearing the previous artist list
                ArrayList<SampleModel> sampleModels = new ArrayList<>();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    SampleModel sampleModel = postSnapshot.getValue(SampleModel.class);
                    //adding artist to the list
                    sampleModels.add(sampleModel);
                }
                SampleAdapter sampleAdapter = new SampleAdapter(sampleModels);
                sampleList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                sampleList.setAdapter(sampleAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        database = FirebaseDatabase.getInstance();
        myInfoRef = database.getReference("myInfoRef");
        sampleList = findViewById(R.id.sampleList);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = myInfoRef.push().getKey();
                ArrayList<String> hobies = new ArrayList<>();
                hobies.add("Listening music");
                hobies.add("Playing music");
                String name = "Abhijeet";
                SampleModel sampleModel = new SampleModel();
                sampleModel.setName(name);
                sampleModel.setUniqueId(id);
                sampleModel.setHobbies(hobies);
                // myInfoRef.child(id).setValue(sampleModel);
                myInfoRef.child(id).setValue(sampleModel);
            }
        });
    }
}
