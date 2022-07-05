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
public class CanBoTapVu extends CanBo{
    public CanBoTapVu(String ten, int maCanBo, String kieuCanBo, Date ngaySinh,int soGioPhucVu){
        super(ten, maCanBo, kieuCanBo, ngaySinh,0,0, soGioPhucVu);
    }
    
    @Override
    public String getStringValue(){
        return getTen()+","+getMaCanBo()+","+getNgaySinh()
                +","+getKieuCanBo()+","+getSoGioGiangDay()
                +","+getSoBaiBaoNghienCuu()+","+getSoGioPhucVu()+",";
    }

    @Override
    public String getCong() {
        return ""+this.getSoGioPhucVu();
    }
}
