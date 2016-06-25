package com.tutorials.hp.firebasesimplerecyclerview;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tutorials.hp.firebasesimplerecyclerview.m_FireBase.FirebaseHelper;
import com.tutorials.hp.firebasesimplerecyclerview.m_Model.Spacecraft;
import com.tutorials.hp.firebasesimplerecyclerview.m_UI.MyAdapter;

public class MainActivity extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    RecyclerView rv;
    EditText nameEditTxt;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //SETUP RV
        rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        //SETUP FB
        db=FirebaseDatabase.getInstance().getReference();
        helper=new FirebaseHelper(db);

        //ADAPTER
        adapter=new MyAdapter(this,helper.retrieve());
        rv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  displayInputDialog();
            }
        });
    }

    private void displayInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.input_dialog);

        nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);

        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET DATA
                String name=nameEditTxt.getText().toString();

                //SET DATA
                Spacecraft s=new Spacecraft();
                s.setName(name);

                //VALIDATE
                if(name.length()>0 && name != null)
                {
                    if(helper.save(s))
                    {
                        nameEditTxt.setText("");
                         adapter=new MyAdapter(MainActivity.this,helper.retrieve());
                        rv.setAdapter(adapter);
                    }
                }else
                {
                    Toast.makeText(MainActivity.this, "Name Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        d.show();
    }

}
