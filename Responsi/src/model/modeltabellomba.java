/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.List;
import javax.swing.table.*;

/**
 *
 * @author Lab Informatika
 */
public class modeltabellomba extends AbstractTableModel{
    List<lomba> lb;

    public modeltabellomba(List<lomba> lb) {
        this.lb = lb;
    }
    
    @Override
    public int getRowCount() {
        return lb.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "Judul";
            case 1:
                return "Alur";
            case 2:
                return "Orisinalitas";
            case 3:
                return "Pemilihan_Kata";
            case 4:
                return "Nilai";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return lb.get(row).getJudul();
            case 1:
                return lb.get(row).getAlur();
            case 2:
                return lb.get(row).getOrisinalitas();
            case 3:
                return lb.get(row).getPemilihanKata();
            case 4:
                return lb.get(row).getNilai();
            default:
                return null;
        }
    }
    
}
