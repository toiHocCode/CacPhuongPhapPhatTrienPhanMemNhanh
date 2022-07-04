/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import controller.QuanLyCanBoController;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.CanBo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Admin
 */
public class Until {
    
    public static ObservableList<CanBo> readFileContent(String path){
        String csvFile = path;
        List<CanBo> danhSachCanBo = readCanBoFromCSV(csvFile);
        try //(Reader reader = new FileReader(csvFile);
//               CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT))
        {
//            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for(CanBo csvRecord : danhSachCanBo){
                System.out.println("CanBo[ten= "+ csvRecord.getTen()
                      +", maCanBo= " + csvRecord.getMaCanBo()
                       +", ngaySinh= " + csvRecord.getNgaySinh()
                       +", kieuCanBo= " + csvRecord.getKieuCanBo()
                        +", soHGiangDay= " + csvRecord.getSoGioGiangDay()
                        +", soBaiBaoCao= " + csvRecord.getSoBaiBaoNghienCuu()
                        +", soHPhucVu= " + csvRecord.getSoGioPhucVu()
                        + "]");
                
            }
             
        } catch (Exception e) {
            System.out.println("reFileContentFail() fail!!");
        }
        return FXCollections.observableArrayList(danhSachCanBo);
    }   
    
    // lay tat ca thong tin can bo ra ngoai
    public static ObservableList<CanBo> readCanBoFromCSV(String path){
        List<CanBo> danhSachCanBo = new ArrayList<>();
        try(Reader reader = new FileReader(path);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);){
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            System.out.println("for loop ----------------");
            for(CSVRecord csvRecord : csvRecords){
                System.out.println("111111111111");
                System.out.println("CanBo[ten= "+ csvRecord.get(0)
                      +", maCanBo= " + csvRecord.get(1)
                       +", ngaySinh= " + csvRecord.get(2)
                       +", kieuCanBo= " + csvRecord.get(3)
                        +", soHGiangDay= " + csvRecord.get(4)
                        +", soBaiBaoCao= " + csvRecord.get(5)
                        +", soHPhucVu= " + csvRecord.get(6)
                        + "]");
                System.out.println("----------- call");
                QuanLyCanBoController quanLyCanBo = QuanLyCanBoController.getInstance();
                String Ten = csvRecord.get(0);
                String MaCanBo = csvRecord.get(1);
                String NgaySinh = csvRecord.get(2);
                String KieuCanBo = csvRecord.get(3);
                String soHGiangDay = csvRecord.get(4);
                String soBaiBaoCao = csvRecord.get(5);
                String soHPhucVu = csvRecord.get(6);
                CanBo canBo = quanLyCanBo.taoMoiCanBo(Ten, MaCanBo, KieuCanBo, NgaySinh, soHGiangDay, soBaiBaoCao, soHPhucVu);
                danhSachCanBo.add(canBo);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return FXCollections.observableArrayList(danhSachCanBo);
    }
    
    public static String chooseDirectoryOfNewFile(String extension){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("File Chooser");
        
        String fileType = extension.replace(".","");
        String desc = String.format("%s files (*%s)", fileType, extension);
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(desc,"*"+extension));
        
        Stage stage = new Stage();
        File saveFile = fileChooser.showOpenDialog(stage);
        
        if(saveFile == null){
            return null;
        }
        
        String path = saveFile.getAbsolutePath();
        
        if(!path.endsWith(extension)){
            path+=extension;
        }
        
        return path;
    }
    
    public static void writeContentToFile(ObservableList<CanBo> danhSachSinhVien, String filePath){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            for(CanBo cb : danhSachSinhVien){
                fileWriter.append(cb.getStringValue()+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }
    
    public static void saveContentToNewFile(ObservableList<CanBo> danhSachCanBo){
        String filePath = chooseDirectoryOfNewFile(".csv");
        if(filePath != null) writeContentToFile(danhSachCanBo, filePath);
    }
}
