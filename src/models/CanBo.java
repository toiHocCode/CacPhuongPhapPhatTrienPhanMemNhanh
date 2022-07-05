/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author Admin
 */
public abstract class CanBo {
    public static int hDayChuan = 10;
    public static int soBaiNghienCuuChuan = 8;
    public static int soHPhucVu = 10;
    
    private String Ten;
    private int MaCanBo;
    private String kieuCanBo;
    private Date ngaySinh;
    private int soGioGiangDay;
    private int soBaiBaoNghienCuu;
    private int soGioPhucVu;

    public CanBo(String Ten, int MaCanBo, String kieuCanBo, Date ngaySinh, int soGioGiangDay, int soBaiBaoNghienCuu, int soGioPhucVu) 
    {
        this.Ten = Ten;
        this.MaCanBo = MaCanBo;
        this.kieuCanBo = kieuCanBo;
        this.ngaySinh = ngaySinh;
        this.soGioGiangDay = soGioGiangDay;
        this.soBaiBaoNghienCuu = soBaiBaoNghienCuu;
        this.soGioPhucVu = soGioPhucVu;
    }
     
    
    public String getTen(){
        return Ten;
    }
    public void setTen(String Ten){
        this.Ten=Ten;
    }
    public int getMaCanBo(){
        return MaCanBo;
    }
    public void setMaCanBo(int MaCanBo){
        this.MaCanBo=MaCanBo;
    }
    public String getKieuCanBo(){
        return kieuCanBo;
    }
    public void setKieuCanBo(String kieuCanBo){
        this.kieuCanBo= kieuCanBo;
    }

    public void setNgaySinh(Date NgaySinh){
        this.ngaySinh= ngaySinh;
    }
    public int getSoGioGiangDay(){
        return soGioGiangDay;
    }
    public void setSoGioGiangDay(int soGioGiangDay){
        this.soGioGiangDay= soGioGiangDay;
    }
    public int getSoBaiBaoNghienCuu(){
        return soBaiBaoNghienCuu;
    }
    public void setSoBaiBaoNghienCuu(int soBaiBaoNghienCuu){
        this.soBaiBaoNghienCuu= soBaiBaoNghienCuu;
    }
    public int getSoGioPhucVu(){
        return soGioPhucVu;
    }
    public void setSoGioPhucVu(int soGioPhucVu){
        this.soGioPhucVu= soGioPhucVu;
    }
    
    public abstract String getCong();
    
    public abstract String getStringValue();
    
    public String getNgaySinh(){
        return ""+this.ngaySinh.getDate()+"/"+this.ngaySinh.getMonth()+"/"+(this.ngaySinh.getYear()+1900);
    }
    
    public Date getNgaySinhDate(){
        return this.ngaySinh;
    }
    
    public boolean duocKhenThuong(){
        double rateHDay = this.getSoGioGiangDay()*1.0/CanBo.hDayChuan;
        double rateHPhucVu = this.getSoGioPhucVu()*1.0/CanBo.soHPhucVu;
        return rateHDay>= 1.2||rateHPhucVu>=1.5||soBaiBaoNghienCuu-2>=CanBo.soBaiNghienCuuChuan;
    }
    
    @Override 
    public boolean equals(Object o){
        if(this==o) return true;
        if(this == null || this.getClass() != o.getClass()) 
                return false;
        
        CanBo canbo = (CanBo) o;
        
        return this.getMaCanBo() == canbo.getMaCanBo();
    }
    
    @Override
    public int hashCode(){
        return this.getMaCanBo();
    }
}
