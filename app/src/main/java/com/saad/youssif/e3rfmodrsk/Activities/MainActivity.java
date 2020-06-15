package com.saad.youssif.e3rfmodrsk.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.saad.youssif.e3rfmodrsk.Adapter.TeachersAdapter;
import com.saad.youssif.e3rfmodrsk.Model.Teacher;
import com.saad.youssif.e3rfmodrsk.Presenter.TeachersListPresenter;
import com.saad.youssif.e3rfmodrsk.R;
import com.saad.youssif.e3rfmodrsk.View.TeachersView;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements TeachersView {

    RecyclerView teachersRecycler;
    TeachersListPresenter teachersListPresenter;
    RecyclerView.LayoutManager layoutManager;
    private Toolbar mTopToolbar;
    TeachersAdapter teachersAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        teachersRecycler=findViewById(R.id.teachersRecyclerView);
        layoutManager=new LinearLayoutManager(this);
        teachersRecycler.setLayoutManager(layoutManager);
        teachersListPresenter=new TeachersListPresenter(this,this);
        teachersListPresenter.getTeachers();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.home_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                teachersAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            Toast.makeText(MainActivity.this, "Filter Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.action_search) {
            Toast.makeText(MainActivity.this, "Search Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getTeachers(List<Teacher> teacherList) {
        if(teacherList.size()!=0)
        {
            teachersAdapter=new TeachersAdapter(teacherList,MainActivity.this);
            teachersRecycler.setAdapter(teachersAdapter);
        }
        else
        {
            Toast.makeText(MainActivity.this,"no data found ",Toast.LENGTH_LONG).show();
        }
    }
}
