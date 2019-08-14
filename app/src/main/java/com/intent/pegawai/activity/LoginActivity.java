package com.intent.pegawai.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.intent.pegawai.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String FILENAME = "login";
    Button btnLogin, btnRegister;
    EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnLogin:
                if (TextUtils.isEmpty(edtUsername.getText().toString())){
                    edtUsername.setError("Username Harus Diisi");
                } else if (TextUtils.isEmpty(edtPassword.getText().toString())){
                    edtPassword.setError("Password Harus Diisi");
                }
                else if (TextUtils.isEmpty(edtUsername.getText().toString()) && TextUtils.isEmpty(edtPassword.getText().toString())){
                    edtUsername.setError("Username Harus Diisi");
                    edtPassword.setError("Password Harus Diisi");
                }
                else{
                    login();
                }
                break;
            case R.id.btnRegister:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    void simpanFileLogin(){
        String isiFile = edtUsername.getText().toString() + ";" + edtPassword.getText().toString();
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try{
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    void login(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, edtUsername.getText().toString());
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                while (line != null){
                    text.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            } catch (IOException e){
                System.out.println("Error "+e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");

            if (dataUser[1].equals(edtPassword.getText().toString())){
                simpanFileLogin();;
                Intent intent = new Intent(this, UtamaActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "User Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

}
