/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package preventmanagement;

import controller.QuanLyCanBoController;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.CanBo;
import view.KhenThuongFrame;

/**
 *
 * @author Admin
 */
public class PreventManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhenThuongFrame().setVisible(true);
//                QuanLyCanBoController qlCb = new QuanLyCanBoController();
//                qlCb.loadData();
//                List<CanBo> list = new ArrayList<>();
//                list = (List<CanBo>) qlCb.timKiem("12/08/2000", FXCollections.observableArrayList(list));
//                qlCb.add(qlCb.taoMoiCanBo("Khanh13", "123", "GiaoVien", "01/03/1999", "0", "0", "0"));
            }
        });
    }
    
}
