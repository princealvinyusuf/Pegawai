package com.intent.pegawai.feature.cuti.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.intent.pegawai.R;
import com.intent.pegawai.feature.cuti.helper.DbHelper;

public class AddEditActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txt_id, txt_name, txt_time;
    Button btn_submit, btn_cancel;
    DbHelper SQLite = new DbHelper(this);
    String id, name, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_id = findViewById(R.id.txt_id);
        txt_name = findViewById(R.id.txt_name);
        txt_time = findViewById(R.id.txt_time);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        address = getIntent().getStringExtra(MainActivity.TAG_TIME);


        if (id == null || id == "") {
            setTitle("Ajukan Data Cuti");
        } else {
            setTitle("Edit Data Cuti");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_time.setText(address);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_submit:
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("submit", e.toString());
                }
                break;

            case R.id.btn_cancel:
                blank();
                finish();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_time.setText(null);
    }

    private void save() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_time.getText()).equals(null) || String.valueOf(txt_time.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please Insert Name, Time for Rest", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_name.getText().toString().trim(), txt_time.getText().toString().trim());
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_time.getText()).equals(null) || String.valueOf(txt_time.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please Insert Name, Time for Rest", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_name.getText().toString().trim(),
                    txt_time.getText().toString().trim());
            blank();
            finish();
        }
    }


}
