package com.intent.pegawai.feature.feedback;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.intent.pegawai.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {
    public static final int REQUEST_CODE = 100;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan);
        getSupportActionBar().setTitle("Kirim Feedback");

        listView = findViewById(R.id.lv_main);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Feedback.this, AddFeedback.class);
                Map<String, Object> data = (Map<String, Object>)parent.getAdapter().getItem(position);
                Log.d("MainActivity", data.toString());
                intent.putExtra("filename",data.get("name").toString());
                Toast.makeText(Feedback.this, "You clicked " +data.get("name"),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> data =(Map<String, Object>)parent.getAdapter().getItem(position);
                tampilkanDialogKonfirmasiHapusCatatan(data.get("name").toString());
                return true;
            }
        });
    }

    private void tampilkanDialogKonfirmasiHapusCatatan(final String filename) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Form Ini?")
                .setMessage("Apakah anda ingin menghapus form "+filename+" ?")
                .setIcon(R.drawable.ic_launcher_background)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapusFile(filename);
                    }
                }).setNegativeButton(android.R.string.no,null).show();
    }

    void hapusFile(String filename) {
        String path = Environment.getExternalStorageDirectory().toString() + "/kominfo.proyek1";
        File file = new File(path, filename);
        if (file.exists()){
            file.delete();
        }
        mengambilListPadaFolder();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= 23){
            if(periksaIzinPenyimpanan()){
                mengambilListPadaFolder();
            };

        }else{
            mengambilListPadaFolder();
        }
    }

    boolean periksaIzinPenyimpanan() {
        if (Build.VERSION.SDK_INT >=23){
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                return true;
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
                return false;
            }
        }else{
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    mengambilListPadaFolder();
                    break;
                }
        }
    }
    void mengambilListPadaFolder(){
        String path= Environment.getExternalStorageDirectory().toString()+"/kominfo.proyek1";
        File directory = new File(path);

        if (directory.exists()){
            File[] files = directory.listFiles();
            String[] filenames = new String [files.length];
            String[] datecreated = new String[files.length];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
            ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String, Object>>();
            for (int i=0;i<files.length;i++){
                filenames[i] = files[i].getName();
                Date lastModeDate = new Date(files[i].lastModified());
                datecreated[i] = simpleDateFormat.format(lastModeDate);
                Map<String, Object> listItemMap = new HashMap<>();
                listItemMap.put("name", filenames[i]);
                listItemMap.put("date", datecreated[i]);
                itemDataList.add(listItemMap);
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, itemDataList, android.R.layout.simple_list_item_2, new String[]{"name","date"}, new int[]{android.R.id.text1, android.R.id.text2});
            listView.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_tambah){
            Intent intent = new Intent(this, AddFeedback.class);
            startActivity(intent);

        }
        return true;
    }
}
