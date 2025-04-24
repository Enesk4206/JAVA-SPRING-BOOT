package com.config.CustomerRestAPI.model;

public class Customer {

    private String adi;
    private String soyadi;
    private String musteriNo;
    private int yas;
    private Boolean medeniDurum;

    //if you want this model in other class, you musst check the order
    public Customer(String adi, String soyadi, String musteriNo, int yas, boolean medeniDurum) {
        this.adi = adi;
        this.soyadi = soyadi;
        this.musteriNo = musteriNo;
        this.yas = yas;
        this.medeniDurum = medeniDurum;
    }

    //Getter Setter

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }
    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }
    public String getMusteriNo() {
        return musteriNo;
    }

    public void setMusteriNo(String musteriNo) {
        this.musteriNo = musteriNo;
    }
    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }
    public Boolean getMedeniDurum() {
        return medeniDurum;
    }

    public void setMedeniDurum(Boolean medeniDurum) {
        this.medeniDurum = medeniDurum;
    }
}
