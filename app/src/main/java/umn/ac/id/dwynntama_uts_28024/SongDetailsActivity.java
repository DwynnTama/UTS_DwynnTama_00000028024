package umn.ac.id.dwynntama_uts_28024;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.LinkedList;

public class SongDetailsActivity extends AppCompatActivity {
    private TextView songTitle, songSinger;
    private Button skipBefore, playSong, skipAfter, pauseBtn;
    private SeekBar onPlayingCounter;
    private MediaPlayer mediaPlayer;
    private Context konteks;
    private int songPos;
    private ArrayList<Songs> listLagu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);
        songTitle        = (TextView) findViewById(R.id.namajudul);
        skipBefore       = findViewById(R.id.skip_before);
        playSong         = findViewById(R.id.play_song);
        skipAfter        = findViewById(R.id.skip_after);
        onPlayingCounter = findViewById(R.id.progressBar);
        songSinger       = findViewById(R.id.songSinger);
        Toolbar toolbar = (Toolbar)findViewById(R.id.menuToolbarSongDetails);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        songPos = (int) bundle.getSerializable("songPosition");
        listLagu = (ArrayList<Songs>) bundle.getSerializable("TestList");
        konteks = this;

        playSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playSong.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                    onPlayingCounter.removeCallbacks(runnable);
                } else {
                    mediaPlayer.start();
                    playSong.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                    songProgressBar();
                }
            }
        });

        skipBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songPos --;
                if(songPos < 0) {
                    songPos++;
                } else {
                    changeSong(songPos);
                }
            }
        });

        skipAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(songPos < 4) {
                    songPos ++;
                } else {
                    songPos = 0;
                }
                if(songPos < 0) {
                    songPos--;
                } else {
                    changeSong(songPos);
                }
            }
        });
        changeSong(this.songPos);
    }

    private void changeSong(int songPos) {
        Songs songsong = listLagu.get(songPos);
        try {
            mediaPlayer.release();
            mediaPlayer = null;
        } catch (Exception e) {

        }
        mediaPlayer = MediaPlayer.create(konteks, Uri.parse(songsong.getSongUri()));
        mediaPlayer.start();
        songProgressBar();
        onPlayingCounter.setMax(mediaPlayer.getDuration());
        songTitle.setText(songsong.getJudul());
        songSinger.setText(songsong.getKeterangan());
        playSong.setBackgroundResource(R.drawable.ic_baseline_pause_24);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void songProgressBar() {
        onPlayingCounter.setProgress(mediaPlayer.getCurrentPosition());
        onPlayingCounter.postDelayed(runnable, 100);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            songProgressBar();
        }
    };
}
