package com.intent.pegawai.activity;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intent.pegawai.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "login";
    EditText edtUsername, edtPassword, edtEmail, edtNama, edtAsalSekolah, edtAlamat;
    Button btnSimpan;
    TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Akun Yang Sedang Aktif");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        }


        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtNama = findViewById(R.id.edtNama);
        edtAlamat = findViewById(R.id.edtAlamat);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setVisibility(View.GONE);
        tvPassword = findViewById(R.id.tvPassword);

        edtUsername.setEnabled(false);
        edtPassword.setVisibility(View.GONE);
        tvPassword.setVisibility(View.GONE);
        edtEmail.setEnabled(false);
        edtNama.setEnabled(false);
        edtAlamat.setEnabled(false);

        bacaFileLogin();
    }

    void bacaFileLogin(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                while(line != null){
                    text.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            } catch (IOException e){
                System.out.println("Error "+e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");
            bacaDataUser(dataUser[0]);
        }
    }

    void bacaDataUser(String fileName){
        File sdcard = getFilesDir();
        File file = new File(sdcard, fileName);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                while(line != null){
                    text.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            } catch (IOException e){
                System.out.println("Error "+e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");

            edtUsername.setText(dataUser[0]);
            edtEmail.setText(dataUser[2]);
            edtNama.setText(dataUser[3]);
            edtAlamat.setText(dataUser[4]);
        } else{
            Toast.makeText(this, "User Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_logout:
                tampilkanDialogKonfirmasiLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()){
            file.delete();
        }
    }

    void tampilkanDialogKonfirmasiLogout(){
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda Yakin Ingin Logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hapusFile();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    @Override
    public boolean onSupportNavigateUp(){
        Intent intent = new Intent(MainActivity.this, UtamaActivity.class);
        startActivity(intent);
        return true;
    }


}
