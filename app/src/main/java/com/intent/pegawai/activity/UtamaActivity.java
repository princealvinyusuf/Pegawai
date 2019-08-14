package com.intent.pegawai.activity;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.intent.pegawai.feature.feedback.Feedback;
import com.intent.pegawai.model.Foodie;
import com.intent.pegawai.model.FoodieData;
import com.intent.pegawai.adapter.CardViewFoodieAdapter;
import com.intent.pegawai.R;

import java.util.ArrayList;

public class UtamaActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<Foodie> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(FoodieData.getListData());

        tampilCardView();
        setActionBarTitle("Pusat Informasi");
    }

    private void tampilCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewFoodieAdapter cardViewFoodieAdapter = new CardViewFoodieAdapter(this);
        cardViewFoodieAdapter.setListFoodie(list);
        rvCategory.setAdapter(cardViewFoodieAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.action_logout:
                tampilkanDialogKonfirmasiLogout();
                break;
            case R.id.action_note:
                tampilkanNote();
                break;
            case R.id.action_settting:
                settingIntent();
                break;

            case R.id.action_map:
                mapIntent();
                break;

            case R.id.action_cuti:
                cutiIntent();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void tampilkanNote() {
        Intent intent = new Intent(UtamaActivity.this, Feedback.class);
        startActivity(intent);
    }

    private void cutiIntent() {
        Intent intent = new Intent(UtamaActivity.this, com.intent.pegawai.feature.cuti.activity.MainActivity.class);
        startActivity(intent);
    }
    private void settingIntent() {
        Intent intent = new Intent(UtamaActivity.this, SettingActivity.class);
        startActivity(intent);
    }

    private void mapIntent() {
        Intent intent = new Intent(UtamaActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    void tampilkanDialogKonfirmasiLogout(){
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda Yakin Ingin Logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(UtamaActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}
