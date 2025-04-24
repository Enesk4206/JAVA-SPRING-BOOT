package com.config.CustomerRestAPI.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CustomerDto {

    @NotBlank(message="Ad alanı boş olamaz")
    private String adi;
    @NotBlank(message="Soyad alanı boş olamaz")
    private String soyadi;
    @NotBlank(message="Müşteri numarası boş olamaz")
    private String musteriNo;
    @Min(value=18, message="Yaş en  az 18 olamalıdır")
    private int yas;
    @NotNull(message="Medeni durum boş olamaz")
    private Boolean medeniDurum;


    //Getter ve Setter
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

    public void setMusteriNo(String MusteriNo) {
        musteriNo = MusteriNo;
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

    public void setAdi(Boolean medeniDurum) {
        this.medeniDurum = medeniDurum;
    }

}
