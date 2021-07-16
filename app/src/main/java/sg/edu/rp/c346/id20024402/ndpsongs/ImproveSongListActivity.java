package sg.edu.rp.c346.id20024402.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ImproveSongListActivity extends AppCompatActivity {
    EditText etID, etTitle, etSingers, etYear;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnCancel, btnUpdate, btnDelete;
    RadioGroup rg;
    Song currentSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_song_list);

        setTitle(getTitle().toString() + " ~ " + "Modify Song");
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        rg = findViewById(R.id.rgStars);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);

        Intent intent = getIntent();
        currentSong = (Song) intent.getSerializableExtra("song");
        etID.setText(currentSong.get_id()+"");
        etTitle.setText(currentSong.getTitle());
        etSingers.setText(currentSong.getSingers());
        etYear.setText(currentSong.getYear()+"");
        switch (currentSong.getStar()){
            case 1:
                rb1.setChecked(true);
                break;
            case 2:
                rb2.setChecked(true);
                break;
            case 3:
                rb3.setChecked(true);
                break;
            case 4:
                rb4.setChecked(true);
                break;
            case 5:
                rb5.setChecked(true);
                break;
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ImproveSongListActivity.this);
                currentSong.setTitle(etTitle.getText().toString().trim());
                currentSong.setSingers(etSingers.getText().toString().trim());
                int year = Integer.valueOf(etYear.getText().toString().trim());
                currentSong.setYear(year);

                int selectedRB = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedRB);
                currentSong.setStars(Integer.parseInt(rb.getText().toString()));

                int result = dbh.updateSong(currentSong);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ImproveSongListActivity.this);
                int result = dbh.deleteSong(currentSong.get_id());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}