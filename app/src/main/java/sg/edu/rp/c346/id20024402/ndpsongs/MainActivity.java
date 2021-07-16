package sg.edu.rp.c346.id20024402.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etTitle;
    EditText etSingers;
    EditText etYear;
    RadioGroup rgStars;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    RadioButton rb5;
    Button btnInsert;
    Button btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);

        btnInsert = findViewById(R.id.btnInsert);
        btnList = findViewById(R.id.btnList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String singers = etSingers.getText().toString().trim();
                String strYear = etYear.getText().toString().trim();
                int year = Integer.valueOf(strYear);

                int stars = 0;
                switch (rgStars.getCheckedRadioButtonId()){
                    case R.id.radioButton1:
                        stars = 1;
                        break;
                    case R.id.radioButton2:
                        stars = 2;
                        break;
                    case R.id.radioButton3:
                        stars = 3;
                        break;
                    case R.id.radioButton4:
                        stars = 4;
                        break;
                    case R.id.radioButton5:
                        stars = 5;
                        break;

                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                long result = dbh.insertSong(title, singers, year, stars);
                Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_LONG).show();
            }
        });




        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SongListActivity.class);
                startActivity(intent);
            }
        });


    }

}