package sg.edu.rp.c346.id20024402.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {
    ListView lv;
    Button btn5Stars;
    ArrayList<Song> songList;
    ArrayAdapter adapter;
    String moduleCode;
    int requestCode = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        setTitle(getTitle().toString() + " ~ " + "Show Song");

        lv = findViewById(R.id.lv);
        btn5Stars = findViewById(R.id.btnShow5Stars);

        DBHelper dbh = new DBHelper(this);
        songList = dbh.getAllSongs();
        dbh.close();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(SongListActivity.this, ImproveSongListActivity.class);
            intent.putExtra("song", songList.get(position));
            startActivityForResult(intent, requestCode);
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SongListActivity.this);
                songList.clear();
                songList.addAll(dbh.get5StarSong());
                adapter.notifyDataSetChanged();
            }
        });

    }
}