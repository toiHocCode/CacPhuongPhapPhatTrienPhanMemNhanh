/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.object;

import models.CanBo;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class GiaoVien extends CanBo{
    public GiaoVien(String ten, int maCanBo, String kieuCanBo, Date ngaySinh,int soGioGiangDay){
        super(ten, maCanBo, kieuCanBo, ngaySinh, soGioGiangDay, 0, 0);
    }
    
    @Override
    public String getCong(){
        return ""+this.getSoGioGiangDay();
    }
    
    @Override
    public String getStringValue(){
        return getTen()+","+getMaCanBo()+","+getNgaySinh()
                +","+getKieuCanBo()+","+getSoGioGiangDay()
                +","+getSoBaiBaoNghienCuu()+","+getSoGioPhucVu()+",";
    }
}
