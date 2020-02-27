package com.example.mytestfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    DatabaseReference myDatabaseReference;
    ArrayList<person> dataList ;
    RecyclerView myRecyclerView;
    RecAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecyclerView = findViewById(R.id.myRec);


        myDatabaseReference = FirebaseDatabase.getInstance().getReference("Person_Data/person");
        myDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = iterable.iterator();
                dataList = new ArrayList<>();
                while (iterator.hasNext()){
                    person result = iterator.next().getValue(person.class);
                    dataList.add(new person(result.getName(),result.getEmail(),result.getPhone()));
                    Log.d("RESULT",String.valueOf(result.getName()));

                }


                adapter = new RecAdapter(dataList, getApplicationContext());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                myRecyclerView.setLayoutManager(layoutManager);
                myRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


                for(int i=0; i<dataList.size();i++){
                   String name =  dataList.get(i).getName();
                    String emial = dataList.get(i).getEmail();
                    String phone = dataList.get(i).getPhone();
                    Log.d("RESULT","this is "  + name + "this is "  + emial + "this is "  + phone);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





          /*recyclerView.bind(list, R.layout.raw_redeem){ data : redeem ->
            this.imgRedeem.load(data.imgaeId)
        this.textRedeem.text = data.name
    }
            .layoutManager(GridLayoutManager(context,2))
            recyclerView.update(list);
            */


    }





}
