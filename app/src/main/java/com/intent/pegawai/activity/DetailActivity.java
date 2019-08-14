package com.intent.pegawai.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.intent.pegawai.model.Foodie;
import com.intent.pegawai.R;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{ // Membuat class DetailActivity yang mewarisi class AppCompatActivity dan mengimplementasikan method OnClickListener pada class View
    private Button kembali; // Deklarasi variabel secara global

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Method dipanggil dan dieksekusi  pertama kali saat life cycle dimulai
        super.onCreate(savedInstanceState); // override superclass
        setContentView(R.layout.activity_detail); //menggunakan layout dari activity_detail pada resource

        kembali = (Button)findViewById(R.id.btn_kembalis); //
        kembali.setOnClickListener(this);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        Foodie a = getIntent().getParcelableExtra("key");

        ImageView gambar = (ImageView)findViewById(R.id.img_item_photos);
        TextView name = (TextView)findViewById(R.id.names);
        TextView remarks = (TextView)findViewById(R.id.rema);
        TextView content = (TextView)findViewById(R.id.contents);

        Glide.with(this).load(a.getPhoto())
                .apply(new RequestOptions().override(350,550))
                .into(gambar);
        name.setText(a.getTitle());
        remarks.setText(a.getDepartment());
        content.setText(a.getContent());

        Log.i("photo", a.getPhoto());
        Log.i("content", a.getContent());
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btn_kembalis){
            finish();
        }
    }

}
