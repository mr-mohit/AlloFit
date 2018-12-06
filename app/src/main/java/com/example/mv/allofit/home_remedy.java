package com.example.mv.allofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;

public class home_remedy extends AppCompatActivity implements RemedyAdapter.RemedyAdapterListener{

    private RecyclerView recyclerView;
    Toolbar toolbar;
    List<remedy> remedyList;
    RemedyAdapter mAdapter;
    SearchView searchView;
    DataBaseHelper db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_remedy);
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recycler_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Home Remedies");
        remedyList=new ArrayList<>();
        mAdapter=new RemedyAdapter(this,remedyList,this);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                readFromDB();
            }
        });
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }
    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
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

    public void readFromDB(){
        db=new DataBaseHelper(this);
        String sql="select name,description from remedies";
        try{
            db.createDataBase();

        }catch (Exception e){
            e.printStackTrace();
        }
        SQLiteDatabase sd=db.getReadableDatabase();
        cursor=sd.rawQuery(sql,null);
        while (cursor.moveToNext()){
            remedyList.add(new remedy(""+cursor.getString(cursor.getColumnIndex("name")),""+cursor.getString(cursor.getColumnIndex("description"))));
        }
        cursor.close();
        db.close();
    }

    @Override
    public void OnRemedySelected(remedy Remedy) {
        Intent i=new Intent(this,Remedy_desc.class);
        //Toast.makeText(this, "Selected: "+Remedy.getName(), Toast.LENGTH_LONG).show();
        Bundle bundle=new Bundle();
        bundle.putString("title",Remedy.getName());
        bundle.putString("desc",Remedy.getDesc());
        i.putExtras(bundle);
        startActivity(i);
    }
}
