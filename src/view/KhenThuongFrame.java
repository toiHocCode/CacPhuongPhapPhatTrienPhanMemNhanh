/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import controller.QuanLyCanBoController;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.Observable;
import javafx.collections.ObservableList;

import javax.swing.JPanel;
import models.CanBo;

/**
 *
 * @author Admin
 */
public class KhenThuongFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    CardLayout cl = new CardLayout();
    CardLayout cl2 = new CardLayout();
    
    public KhenThuongFrame() {
        initComponents();
        
        this.capNhatPanel = new CapNhatPanel();
        
        System.out.println("1");
        this.thongKeJPanel = new ThongKeJPanel();
        System.out.println("2");
        this.inputUpdate = new InputCapNhatPanel();
        System.out.println("3");
        this.locPanel = new LocCanBoNghienCuuJPanel1();
        
        addPanel();
        System.out.println("4");
        
        
        cl = (CardLayout) servicePanel.getLayout();
        cl2 = (CardLayout) UpdatejPanel1.getLayout();
        System.out.println("5");
        this.UpdatejPanel1.setLayout(cl2);
        System.out.println("6");
        this.servicePanel.setLayout(cl);
        System.out.println("setLayout done");
        System.out.println("7");
        
        setDataForTable();
        System.out.println("8");
        
        handleTable();
        
        handleButton();
        

    }
    
    private void addPanel(){
        // add  panel
        this.servicePanel.add(this.capNhatPanel, "cap nhat");
        this.servicePanel.add(this.thongKeJPanel, "thong ke");
        this.servicePanel.add(this.locPanel, "loc");
        this.UpdatejPanel1.add(inputUpdate,"cap nhat");
        
    }
    
    private void setDataForTable(){
        QuanLyCanBoController.getInstance().loadData();
        
    //-----------CAP NHAT PANEL---------------
        CapNhatPanel.getModel().setRowCount(0);
        this.capNhatPanel.setTable(QuanLyCanBoController.getInstance().getDanhSachCanBo());
    
    //-----------THONG KE PANEL---------------
    
    }
    
    private void handleTable(){
    }
    
    private void handleButton(){
    
    //-------------CAP NHAT PANEL------------------
        // set button action
        capNhat.addActionListener((e) -> {
            cl.show(this.servicePanel, "cap nhat");
        });
        
        thongKeBUtton.addActionListener((e) -> {
            cl.show(this.servicePanel, "thong ke");
        });
        
        this.LocButton.addActionListener((e)->{
            cl.show(this.servicePanel, "loc");
        });
        
        this.capNhatPanel.getAddButton().addActionListener((e)->{
            this.inputUpdate.getTextButton().setText("confirm add");
            this.inputUpdate.getTextButton().setEnabled(true);
            this.inputUpdate.setAllTextFieldEnable(true);
        });
        
        this.capNhatPanel.getDeleteButton().addActionListener((e)->{
//            this.inputUpdate.getTextButton().setText("delete");
            this.inputUpdate.getTextButton().setEnabled(false);
            this.inputUpdate.setAllTextFieldEnable(false);
            int selectedRow = this.capNhatPanel.getUpdateTable().getSelectedRow();
            if(selectedRow != -1) {
                QuanLyCanBoController.getInstance().delete(selectedRow);
                this.setDataForTable();
            }
   
        });
        
        this.capNhatPanel.getUpdateButton().addActionListener((e)->{
            int choosen = this.capNhatPanel.getUpdateTable().getSelectedRow();
            System.out.println("--choosen = "+choosen);
            if(choosen != -1){
                this.inputUpdate.getTextButton().setText("confirm update");
                this.inputUpdate.getTextButton().setVisible(true);
                this.inputUpdate.setAllTextFieldEnable(true);
                this.inputUpdate.getIdTextField().setEnabled(false);
            
                CanBo cb = QuanLyCanBoController.getInstance().getDanhSachCanBo().get(choosen);
                this.inputUpdate.setAllTextFileContent(cb.getTen(),""+cb.getMaCanBo(), cb.getNgaySinh(), cb.getKieuCanBo(), cb.getCong());
                this.inputUpdate.setSelectedRow(choosen);
            }
            
        });
        
        this.inputUpdate.getTextButton().addActionListener((e)->{
            String Ten,MaCanBo,KieuCanBo,NgaySinh,soGioGiangDay,soBaiBaoNghienCuu,soGioPhucVu;
            Ten = this.inputUpdate.getNameTextField().getText();
            MaCanBo = this.inputUpdate.getIdTextField().getText();
            KieuCanBo = this.inputUpdate.getTypeComboBox().getSelectedItem().toString();
            NgaySinh = this.inputUpdate.getNgaySinhTextField().getText();
            soBaiBaoNghienCuu = soGioGiangDay = soGioPhucVu = "0";
            int type = this.inputUpdate.getTypeComboBox().getSelectedIndex();
            System.out.println("type: "+type);
            switch (type) {
            case 0:{
                soGioGiangDay = this.inputUpdate.getSoCongTextField().getText();
                break;
            }
            case 1:{
                soGioPhucVu = this.inputUpdate.getSoCongTextField().getText();
                break;
            }
            case 2:{
                soBaiBaoNghienCuu = this.inputUpdate.getSoCongTextField().getText();
                break;
            }
            default:
                return;
            };
            switch (this.inputUpdate.getUpdatejButton1().getText()) {
                case "confirm add":{
                    QuanLyCanBoController.getInstance().add(Ten, MaCanBo, KieuCanBo, NgaySinh, soGioGiangDay, soBaiBaoNghienCuu, soGioPhucVu);
                    break;
                }
                case "confirm update":{
                    QuanLyCanBoController.getInstance().update(this.inputUpdate.getSelectedRow(), Ten, MaCanBo, KieuCanBo, NgaySinh, soGioGiangDay, soBaiBaoNghienCuu, soGioPhucVu);
                    break;
                }
                default:
                    throw new AssertionError();
            }
            this.setDataForTable();
        });
    
    //------------------------THONG KE PANEL---------------------------------
        this.thongKeJPanel.getGiangDayButton().addActionListener((e)->{
            System.out.println("--giang day Button--");
            this.thongKeJPanel.removeAllRow();
            this.thongKeJPanel.setTableContent(QuanLyCanBoController.getInstance().locBangHDay(1));
        });
        
        this.thongKeJPanel.getNghienCuuButton().addActionListener((e)->{
            System.out.println("--nghienCuuButton");
            this.thongKeJPanel.removeAllRow();
            this.thongKeJPanel.setTableContent(QuanLyCanBoController.getInstance().locBangSoBaoCao(1));
            
        });
        
        this.thongKeJPanel.getTapVuButton().addActionListener((e)->{
            this.thongKeJPanel.removeAllRow();
            this.thongKeJPanel.setTableContent(QuanLyCanBoController.getInstance().locBangSoGioPhucVu(1));
        });
        
        this.thongKeJPanel.getTatCaButton().addActionListener((e)->{
            this.thongKeJPanel.removeAllRow();
            this.thongKeJPanel.setTableContent(QuanLyCanBoController.getInstance().locBangHDay(1));
            this.thongKeJPanel.setTableContent(QuanLyCanBoController.getInstance().locBangSoBaoCao(1));
            this.thongKeJPanel.setTableContent(QuanLyCanBoController.getInstance().locBangSoGioPhucVu(1));
        });
        
        //-------------LOC PANEL----------------
        this.locPanel.getLocButton().addActionListener((e)->{
            this.locPanel.removeAllRow();
            String soBaoCao = this.locPanel.getLocTextField().getText();
            if(soBaoCao!=""){
                this.locPanel.setTableContent(QuanLyCanBoController.getInstance().locBangSoBaoCaoNhoHon(Integer.parseInt(soBaoCao)));
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capNhat = new javax.swing.JButton();
        thongKeBUtton = new javax.swing.JButton();
        LocButton = new javax.swing.JButton();
        servicePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        UpdatejPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        capNhat.setText("Cập nhật");
        capNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                capNhatMouseClicked(evt);
            }
        });

        thongKeBUtton.setText("Thống kê");

        LocButton.setText("Loc");

        servicePanel.setBackground(new java.awt.Color(255, 51, 51));
        servicePanel.setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setText("Quản lý khen thưởng cán bộ");

        UpdatejPanel1.setPreferredSize(new java.awt.Dimension(457, 130));
        UpdatejPanel1.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(thongKeBUtton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(capNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LocButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(servicePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(UpdatejPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(capNhat)
                        .addGap(18, 18, 18)
                        .addComponent(thongKeBUtton)
                        .addGap(18, 18, 18)
                        .addComponent(LocButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(servicePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(UpdatejPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void capNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capNhatMouseClicked

    }//GEN-LAST:event_capNhatMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LocButton;
    private javax.swing.JPanel UpdatejPanel1;
    private javax.swing.JButton capNhat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel servicePanel;
    private javax.swing.JButton thongKeBUtton;
    // End of variables declaration//GEN-END:variables

    private ThongKeJPanel thongKeJPanel;
    private CapNhatPanel capNhatPanel;
    private InputCapNhatPanel inputUpdate;
    private LocCanBoNghienCuuJPanel1 locPanel;
}
