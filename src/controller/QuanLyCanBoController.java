/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.net.URL;
import java.util.Date;
import javafx.collections.ObservableList;
import models.CanBo;
import models.KieuCanBo;
import models.object.CanBoNghienCuu;
import models.object.CanBoTapVu;
import models.object.GiaoVien;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javafx.collections.FXCollections;
import services.Until;

/**
 *
 * @author Admin
 */
public class QuanLyCanBoController {
    private static QuanLyCanBoController instance;
    private ObservableList<CanBo> danhSachCanBo;
    private static String canBoCSVFilePath = "C:\\Users\\Admin\\Documents\\NetBeansProjects\\PreventManagement\\src\\resource\\data.csv"; 
    
    public static QuanLyCanBoController getInstance(){
        if(instance == null){
            instance = new QuanLyCanBoController();
        }
        return instance;
    }
    
    public KieuCanBo KieuCanBoStringToObj(String kieuCanBo){
        return switch (kieuCanBo) {
            case "GiaoVien" ->  KieuCanBo.GiaoVien;
            case "CanBoTapVu" -> KieuCanBo.CanBoTapVu;
            case "CanBoNghienCuu" -> KieuCanBo.CanBoNghienCuu;
            default -> KieuCanBo.None;
        };
    }
    
    // khoi tao CanBo
    public CanBo taoMoiCanBo(String Ten, int MaCanBo, KieuCanBo KieuCanBo, Date NgaySinh, 
            int soGioGiangDay, int soBaiBaoNghienCuu, int soGioPhucVu)
    {
//        System.out.println("--- tao moi can bo origin: "+soBaiBaoNghienCuu+"----");
        switch(KieuCanBo){
            case GiaoVien: return new GiaoVien(Ten, MaCanBo, "GiaoVien", NgaySinh, soGioGiangDay);
            case CanBoTapVu: return new CanBoTapVu(Ten, MaCanBo, "CanBoTapVu", NgaySinh, soGioPhucVu);
            case CanBoNghienCuu: return new CanBoNghienCuu(Ten, MaCanBo, "CanBoNghienCuu", NgaySinh, soBaiBaoNghienCuu);
            default:
        }
        System.out.println("create fail");
        return null;
    }
    public CanBo taoMoiCanBo(String Ten, String MaCanBo, String KieuCanBo, String NgaySinh, 
            String soGioGiangDay, String soBaiBaoNghienCuu, String soGioPhucVu) 
    {
        if(!dauVaoCoGiaTri(Ten, MaCanBo, KieuCanBo, NgaySinh, soGioGiangDay, soBaiBaoNghienCuu, soGioPhucVu)) return null;
        Date ngaySinh;
        try {
            ngaySinh = new SimpleDateFormat("dd/MM/yyyy").parse(NgaySinh);
        } catch (ParseException ex) {
            
            return null;
        }
//        System.out.println("--tao moi can bo String :"+soBaiBaoNghienCuu+"----");
        return taoMoiCanBo(Ten, Integer.parseInt(MaCanBo), KieuCanBoStringToObj(KieuCanBo), ngaySinh, Integer.parseInt(soGioGiangDay), Integer.parseInt(soBaiBaoNghienCuu),Integer.parseInt(soGioPhucVu));
    }
    
    
// kiem tra dau vao
    public boolean dauVaoCoGiaTri(String Ten, String MaCanBo, String KieuCanBo, String NgaySinh, 
            String soGioGiangDay, String soBaiBaoNghienCuu, String soGioPhucVu)
    {
        return !("".equals(Ten)||"".equals(MaCanBo)||"".equals(KieuCanBo)
                ||"".equals(NgaySinh)||"".equals(soBaiBaoNghienCuu)
                ||"".equals(soGioGiangDay)||"".equals(soGioPhucVu));
    }
    
    
    // get .. set ..
    public ObservableList<CanBo> getDanhSachCanBo()
    {
        if(this.danhSachCanBo==null){
            this.danhSachCanBo = FXCollections.observableArrayList();
        }
        return this.danhSachCanBo;
    }
    
    
    // load data from csv file
    public void loadData(){
        try {
//            URL resource = QuanLyCanBoController.class.getResource("/data.csv");
//            String path = Paths.get(resource.toURI()).toString();
            this.danhSachCanBo = Until.readCanBoFromCSV(canBoCSVFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }    
        System.out.println("loading finished");
    }
    
    
    // các chức năng tìm kiếm 
    public ObservableList<CanBo> timKiemBangTen(String ten, ObservableList<CanBo> danhSachCanBo){
        List<CanBo> danhSach = new ArrayList<>();
        for(CanBo cb: danhSachCanBo){
            if(cb.getTen().equals(ten)) danhSach.add(cb);
        }
        return FXCollections.observableArrayList(danhSach);
    }
    
    public ObservableList<CanBo> timKiemBangMaCanBo(int maCanBo, ObservableList<CanBo> danhSachCanBo){
        List<CanBo> danhSach = new ArrayList<>();
        for(CanBo cb: danhSachCanBo){
            if(cb.getMaCanBo()== maCanBo) danhSach.add(cb);
        }
        return FXCollections.observableArrayList(danhSach);
    }
    
    public ObservableList<CanBo> timKiemBangNgaySinh(Date NgaySinh, ObservableList<CanBo> danhSachCanBo){
        List<CanBo> danhSach = new ArrayList<>();
        for(CanBo cb: danhSachCanBo){
            if(cb.getNgaySinh().equals(NgaySinh)) danhSach.add(cb);
        }
        return FXCollections.observableArrayList(danhSach);
    }
    
    public ObservableList<CanBo> timKiemBangNgaySinh(String NgaySinh, ObservableList<CanBo> danhSachCanBo){
        Date ns;
        try {
            ns = new SimpleDateFormat("dd/MM/yyyy").parse(NgaySinh);
        } catch (ParseException ex) {
            return null;
        }
        return timKiemBangNgaySinh(ns, danhSachCanBo);
    }
    
    public ObservableList<CanBo> timKiemBangKieu(KieuCanBo kieuCanBo, ObservableList<CanBo> danhSachCanBo){
        List<CanBo> ds = new ArrayList<>();
        for(CanBo cb : danhSachCanBo){
            if(cb.getKieuCanBo().equals(instance)) ds.add(cb);
        }
        return FXCollections.observableArrayList(ds);
    }
    
    public ObservableList<CanBo> timKiemBangKieu(String kieuCanBo, ObservableList<CanBo> danhSachCanBo){
        return timKiemBangKieu(QuanLyCanBoController.getInstance().KieuCanBoStringToObj(kieuCanBo), danhSachCanBo);
    }
    
    public ObservableList<CanBo> timKiem(String infor, ObservableList<CanBo> danhsachCanBo){
        if(infor == "") return null;
        List<CanBo> ds = new ArrayList<>();
        if(isDate(infor)){
            System.out.println("is date");
            ds = timKiemBangNgaySinh(infor, danhsachCanBo);
        }else if(KieuCanBoStringToObj(infor)!=KieuCanBo.None){
            System.out.println("is type");
            ds = timKiemBangKieu(infor, danhsachCanBo);
        }else if(!includeNumber(infor)){
            System.out.println("is name");
            ds = timKiemBangTen(infor, danhsachCanBo);
        }else if(isNumber(infor)){
            System.out.println("is ID");
            int mcb = Integer.parseInt(infor);
            ds = timKiemBangMaCanBo(mcb, danhsachCanBo);
        }
        else{
            System.out.println("notthing");
            return null;
        }
        if(ds==null) return null;
        return FXCollections.observableArrayList(ds);
    }
    
    public ObservableList<CanBo> timKiem(String info){
        return timKiem(info, this.danhSachCanBo);
    }
    // timKiem
    
    
// check if string is date with "dd/MM/yyyy" format
    public boolean isDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);   
        try{
            format.parse(date.trim());
        }catch(Exception e){
            return false;
        }
        return true;
    }
    
// check if string include number
    public static boolean includeNumber(String s){
        String n = s;
        int size = n.length();
        for(int i = 0; i<size; i++){
            if(Character.isDigit(n.charAt(i))){
               return true; 
            }
        }
        return false;
    }
// check if String is number format
    public static boolean isNumber(String s){
        String n = s;
        int size = n.length();
        for(int i = 0; i<size; i++){
            if(!Character.isDigit(n.charAt(i))){
               return false; 
            }
        }
        return true;
    }
    

    // loc ra thong tin can bo co so gio giang day lon hon so nhap vao
    public ObservableList<CanBo> locBangHDay(int hDay, ObservableList<CanBo> danhSachCanBo){
        List<CanBo> ds = new ArrayList<>();
        for(CanBo cb : danhSachCanBo){
            System.out.println("--so gio giang day"+cb.getSoGioGiangDay());
            if(cb.getSoGioGiangDay() >= hDay) ds.add(cb);
        }
        return FXCollections.observableArrayList(ds);
    }
    
    public ObservableList<CanBo> locBangHDay(int hDay){
        return locBangHDay(hDay, this.danhSachCanBo);
    }
    
    //loc ra thong tin can bo co so bai bao cao nho hon so cho truoc
    public ObservableList<CanBo> locBangSoBaoCaoNhoHon(int soBaoCao, ObservableList<CanBo> danhSachCanBo){
        List<CanBo> ds = new ArrayList<>();
        for(CanBo cb: danhSachCanBo){
            System.out.println("---so bai nghien cuu"+cb.getSoBaiBaoNghienCuu());
            if((cb.getSoBaiBaoNghienCuu()<= soBaoCao) && (cb.getKieuCanBo()=="CanBoNghienCuu")) ds.add(cb);
        }
        return FXCollections.observableArrayList(ds);
    }
    
    public ObservableList<CanBo> locBangSoBaoCaoNhoHon(int soBaoCao){
        return locBangSoBaoCaoNhoHon(soBaoCao, this.danhSachCanBo);
    }
    
    //loc ra thong tin can bo co so bai bao cao lo hon so cho truoc
    public ObservableList<CanBo> locBangSoBaoCao(int soBaoCao, ObservableList<CanBo> danhSachCanBo){
        List<CanBo> ds = new ArrayList<>();
        for(CanBo cb: danhSachCanBo){
            System.out.println("---so bai nghien cuu"+cb.getSoBaiBaoNghienCuu());
            if(cb.getSoBaiBaoNghienCuu()>= soBaoCao) ds.add(cb);
        }
        return FXCollections.observableArrayList(ds);
    }
    
    public ObservableList<CanBo> locBangSoBaoCao(int soBaoCao){
        return locBangSoBaoCao(soBaoCao, this.danhSachCanBo);
    }
    
    //loc ra thong tin tap vu co so gio phuc vu bang so cho truoc
    public ObservableList<CanBo> locBangSoGioPhucVu(int soHPhucVu, ObservableList<CanBo> danhSachCanBo){
        List<CanBo> ds = new ArrayList<>();
        for(CanBo cb: danhSachCanBo){
            System.out.println("---so bai nghien cuu"+cb.getSoBaiBaoNghienCuu());
            if(cb.getSoGioPhucVu()>= soHPhucVu) ds.add(cb);
        }
        return FXCollections.observableArrayList(ds);
    }
    
    public ObservableList<CanBo> locBangSoGioPhucVu(int soHPhucVu){
        return locBangSoGioPhucVu(soHPhucVu, this.danhSachCanBo);
    }
    
    public int add(String Ten, String MaCanBo, String KieuCanBo, String NgaySinh, String soGioGiangDay, String soBaiBaoNghienCuu, String soGioPhucVu){
        return add(QuanLyCanBoController.getInstance().taoMoiCanBo(Ten, MaCanBo, KieuCanBo, NgaySinh, soGioGiangDay, soBaiBaoNghienCuu, soGioPhucVu));
    }  
    public int add(CanBo canBo){
        if(canBo == null) return 0;
        System.out.println(canBo.getStringValue());
        this.danhSachCanBo.add(canBo);
        
        Until.writeContentToFile(this.danhSachCanBo, QuanLyCanBoController.canBoCSVFilePath);
        this.loadData();
        return 1;
    }
    
    public int update(int i,String Ten, String MaCanBo, String KieuCanBo, String NgaySinh, String soGioGiangDay, String soBaiBaoNghienCuu, String soGioPhucVu){
        return update(i,QuanLyCanBoController.getInstance().taoMoiCanBo(Ten, MaCanBo, KieuCanBo, NgaySinh, soGioGiangDay, soBaiBaoNghienCuu, soGioPhucVu));
    }
    public int update(int i,CanBo canBo){
        if(canBo == null) return 0;
        System.out.println(canBo.getStringValue());
        this.danhSachCanBo.get(i).setKieuCanBo(canBo.getKieuCanBo());
        this.danhSachCanBo.get(i).setTen(canBo.getTen());
        this.danhSachCanBo.get(i).setNgaySinh(canBo.getNgaySinhDate());
        this.danhSachCanBo.get(i).setSoBaiBaoNghienCuu(canBo.getSoBaiBaoNghienCuu());
        this.danhSachCanBo.get(i).setSoGioGiangDay(canBo.getSoGioGiangDay());
        this.danhSachCanBo.get(i).setSoGioPhucVu(canBo.getSoGioPhucVu());
        
        Until.writeContentToFile(this.danhSachCanBo, QuanLyCanBoController.canBoCSVFilePath);
        this.loadData();
        return 1;
    }
    
    public int delete(int i){
        if(i == -1) return 0;
        this.danhSachCanBo.remove(i);
        
        Until.writeContentToFile(this.danhSachCanBo, QuanLyCanBoController.canBoCSVFilePath);
        this.loadData();
        return 1;
    }
    
    
}
