package com.example.androidappesiee;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidappesiee.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    List<ToDoTacheModel> TachesListes = new ArrayList<ToDoTacheModel>();

    private RecyclerView mRecyclerView;
    CustomAdapter mCustomAdapter;


    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    DataBaseHelper db;
    ArrayList<String> tache_id, tache_nom, tache_description, tache_type, tache_priorite, tache_isdone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Appel de l'activity NewTask quand bouton appuyé
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newtaskIntent = new Intent(MainActivity.this, NewTask.class);
                startActivity(newtaskIntent);
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        mRecyclerView = findViewById(R.id.recyclerToDoView);

        db = new DataBaseHelper(MainActivity.this);
        tache_id = new ArrayList<>();
        tache_nom = new ArrayList<>();
        tache_description = new ArrayList<>();
        tache_type = new ArrayList<>();
        tache_priorite = new ArrayList<>();
        tache_isdone = new ArrayList<>();

        storeDataInArrays();

        mCustomAdapter = new CustomAdapter(MainActivity.this,
                tache_id,
                tache_nom,
                tache_description,
                tache_type,
                tache_priorite,
                tache_isdone);

        mRecyclerView.setAdapter(mCustomAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

/*
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        TachesListes.getEveryone();
        mAdapter = new CustomAdapter(TachesListes, MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
*/


    }

    void storeDataInArrays() {
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "No Data in database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                tache_id.add(cursor.getString(0));
                tache_nom.add(cursor.getString(1));
                tache_description.add(cursor.getString(2));
                tache_type.add(cursor.getString(3));
                tache_priorite.add(cursor.getString(4));
                tache_isdone.add(cursor.getString(5));
            }
        }
    }


    /*private void remplitachelist() {

        ToDoTacheModel tache1 = new ToDoTacheModel(0,"sport","10 pompes","Sport",0,false);
        ToDoTacheModel tache2 = new ToDoTacheModel(1,"sport1","11 pompes","Sport",0,false);
        ToDoTacheModel tache3 = new ToDoTacheModel(2,"sport2","12 pompes","Sport",0,false);

        TachesListes.addAll(Arrays.asList(new ToDoTacheModel[]{ tache1,tache2,tache3 }));

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    void ToastMessage(String message) {
        Toast.makeText(MainActivity.this, "" + message, Toast.LENGTH_SHORT).show();
    }

}