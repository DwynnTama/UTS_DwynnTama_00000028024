package umn.ac.id.dwynntama_uts_28024;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class ListOfSongActivity extends AppCompatActivity {
    RecyclerView rvlistLagu;
    SongAdapter mAdapter;
    LinkedList<Songs> Songlist = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        rvlistLagu = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new SongAdapter(this, Songlist);
        rvlistLagu.setAdapter(mAdapter);
        rvlistLagu.setLayoutManager(new LinearLayoutManager(this));
        SongsList();
        Toolbar toolbar = (Toolbar)findViewById(R.id.menuToolbarSongList);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.nama_user) + "\n" + getString(R.string.nim_user))
                .setTitle(getString(R.string.songlisttitle))
                .setPositiveButton(getString(R.string.acc), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { dialog.dismiss(); }
                });
        builder.create().show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuOptionProfile:
                Intent gotoProfile = new Intent(ListOfSongActivity.this, ProfileActivity.class);
                startActivityForResult(gotoProfile, 1);
                break;

            case R.id.menuOptionOut:
                Intent gotoMain = new Intent(ListOfSongActivity.this, MainActivity.class);
                startActivityForResult(gotoMain, 2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void SongsList() {
        Songlist.add(new Songs("Ao to Natsu", "Mrs. Green Apple", "android.resource://" +getPackageName() + "/"+
                R.raw.mrsgreenapple_aotonatsu));
        Songlist.add(new Songs("Wanted! Wanted!", "Mrs. Green Apple", "android.resource://" +getPackageName() + "/"+
                R.raw.mrsgreenapple_wantedwanted));
        Songlist.add(new Songs("Koe?", "Hatena", "android.resource://" +getPackageName() + "/"+
                R.raw.hatena_koe));
        Songlist.add(new Songs("Beautiful", "Treasure", "android.resource://" +getPackageName() + "/"+
                R.raw.treasure_beautiful));
        Songlist.add(new Songs("Vivid Ice", "Who Ya", "android.resource://" +getPackageName() + "/"+
                R.raw.whoya_vividice));
    }
}
