package com.intent.pegawai.model;

import java.util.ArrayList;

public class FoodieData {
    public static String[][] data = new String[][]{
            {"Perpanjangan Kontrak", "HRD", "https://i.ibb.co/nkV4JWg/recruiting-Image.png", "perpanjangan kontrak dapat dilakukan mulai bulan agustus"},
            {"Magang Staff", "Accounting", "https://i.ibb.co/3T7yxv2/accounting.jpg", "Kepada karyawan yang sedang magang untuk dapat mengikuti sosialisasi pada hari rabu 12 agustus 2019"},
            {"Revitalisasi Perangkat Komputer", "IT", "https://i.ibb.co/StkMkMW/aaa.jpg", "Perangkat Komputer di office lantai 3 akan mengalami perbaikan selama 3 minggu, dimohon para staff untuk tidak mengganggu proses revitasilasi di lantai terseburt, terima kasih"}
    };

    public static ArrayList<Foodie> getListData(){
        Foodie foodie = null;
        ArrayList<Foodie> list = new ArrayList<>();
        for (int i =0; i< data.length; i++){
            foodie = new Foodie();
            foodie.setTitle(data[i][0]);
            foodie.setDepartment(data[i][1]);
            foodie.setPhoto(data[i][2]);
            foodie.setContent(data[i][3]);

            list.add(foodie);
        }
        return list;
    }
}
