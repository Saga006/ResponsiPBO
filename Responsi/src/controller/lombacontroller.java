/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAOlomba.lombaDAO;
import DAOImplement.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import responsi.*;
import javax.swing.JOptionPane;
import koneksi.connector;

/**
 *
 * @author Lab Informatika
 */
public class lombacontroller {
    MainView frame;
    lombaimplement impllomba;
    List<lomba> lb;
    
    public lombacontroller(MainView frame){
        this.frame = frame;
        impllomba = new lombaDAO();
        lb = impllomba.getAll();
    }
    
    public void isitabel(){
        lb = impllomba.getAll();
        modeltabellomba np = new modeltabellomba(lb);
        frame.getTabelLomba().setModel(np);
    }
    
    public void insert(){
        try{
            double nilai, alur, orisinalitas, pemilihankata;
            lomba lb = new lomba();
            alur = Double.parseDouble(frame.getJTalur().getText());
            orisinalitas =Double.parseDouble(frame.getJTorisinalitas().getText());
            pemilihankata = Double.parseDouble(frame.getJTpemilihankata().getText());
            if(frame.getJTjudul().getText().trim().isEmpty()){
                throw new InterruptedException("Judul Masih Kosong");
            }else if(alur < 0 || orisinalitas < 0 || pemilihankata < 0){
                throw new InterruptedException("Minimal Inputan adalah 0");
            }else if(alur > 10 || orisinalitas > 10 || pemilihankata > 10){
                throw new InterruptedException("Maksimal Inputan adalah 10");
            }else{
                lb.setJudul(frame.getJTjudul().getText());
                lb.setAlur(alur);
                lb.setOrisinalitas(orisinalitas);
                lb.setPemilihanKata(pemilihankata);
                nilai = (alur + orisinalitas + pemilihankata)/3;

                lb.setNilai(nilai);
                impllomba.insert(lb);
                JOptionPane.showMessageDialog(frame, "Data Judul Berhasil Ditambah");
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Input Salah");
        }catch(InterruptedException er){
            String errorMessage = er.getMessage();
            JOptionPane.showMessageDialog(frame, errorMessage);
        }
    }
    
    public void update(){
        try{
            double nilai, alur, orisinalitas, pemilihankata;
            lomba lb = new lomba();
            alur = Double.parseDouble(frame.getJTalur().getText());
            orisinalitas =Double.parseDouble(frame.getJTorisinalitas().getText());
            pemilihankata = Double.parseDouble(frame.getJTpemilihankata().getText());
            if(frame.getJTjudul().getText().trim().isEmpty()){
                throw new InterruptedException("Judul Masih Kosong");
            }else if(alur < 0 || orisinalitas < 0 || pemilihankata < 0){
                throw new InterruptedException("Minimal Inputan adalah 0");
            }else if(alur > 10 || orisinalitas > 10 || pemilihankata > 10){
                throw new InterruptedException("Maksimal Inputan adalah 10");
            }else{
                lb.setJudul(frame.getJTjudul().getText());
                lb.setAlur(alur);
                lb.setOrisinalitas(orisinalitas);
                lb.setPemilihanKata(pemilihankata);
                nilai = (alur + orisinalitas + pemilihankata)/3;

                lb.setNilai(nilai);
                impllomba.update(lb);
                JOptionPane.showMessageDialog(frame, "Data Judul Berhasil Diupdate");
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Input Salah");
        }catch(InterruptedException er){
            String errorMessage = er.getMessage();
            JOptionPane.showMessageDialog(frame, errorMessage);
        }
    }
    
    public void delete(){
        String judul = frame.getJTjudul().getText();
        impllomba.delete(judul);
        JOptionPane.showMessageDialog(frame, "Data Judul Berhasil Dihapus");
    }
    
    public void search(){
        Connection connection = connector.connection();
        try{
            final String select = "SELECT * FROM `lomba`";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                if(rs.getString("judul").equals(frame.getJTjudul().getText())){
                    frame.getJTalur().setText(String.valueOf(rs.getDouble("alur")));
                    frame.getJTorisinalitas().setText(String.valueOf(rs.getDouble("orisinalitas")));
                    frame.getJTpemilihankata().setText(String.valueOf(rs.getDouble("pemilihanKata")));
                }
                
            }
        }catch(SQLException ex){
            Logger.getLogger(lombaDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
