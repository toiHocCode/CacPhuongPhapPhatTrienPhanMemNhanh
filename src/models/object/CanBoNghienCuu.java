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
public class CanBoNghienCuu extends CanBo{
    public CanBoNghienCuu(String ten, int maCanBo, String kieuCanBo, Date ngaySinh,int soBaiBaoCao){
        super(ten, maCanBo, kieuCanBo, ngaySinh,0, soBaiBaoCao, 0);
    }
    
    @Override
    public String getStringValue(){
        return getTen()+","+getMaCanBo()+","+getNgaySinh()
                +","+getKieuCanBo()+","+getSoGioGiangDay()
                +","+getSoBaiBaoNghienCuu()+","+getSoGioPhucVu()+",";
    }

    @Override
    public String getCong() {
        return ""+this.getSoBaiBaoNghienCuu();
    }
}
