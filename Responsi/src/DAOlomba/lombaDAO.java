/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOlomba;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.*;
import java.util.logging.*;

/**
 *
 * @author Lab Informatika
 */
public class lombaDAO implements lombaimplement{
    Connection connection;
    
    final String select = "SELECT * FROM `lomba`";
    final String insert = "INSERT INTO `lomba` (`judul`, `alur`, `orisinalitas`, `pemilihanKata`, `nilai`) VALUES (?, ?, ?, ?, ?);";
    final String update = "UPDATE `lomba` SET `alur` = ?, `orisinalitas` = ?, `pemilihanKata` = ?, `nilai` = ? WHERE `lomba`.`judul` = ?;";
    final String delete = "DELETE FROM `lomba` WHERE `lomba`.`judul` = ?";
    
    public lombaDAO(){
        connection = connector.connection();
    }
    
    @Override
    public void insert(lomba l) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,l.getJudul());
            statement.setDouble(2,l.getAlur());
            statement.setDouble(3,l.getOrisinalitas());
            statement.setDouble(4,l.getPemilihanKata());
            statement.setDouble(5,l.getNilai());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
            ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(lomba l) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            
            statement.setDouble(1,l.getAlur());
            statement.setDouble(2,l.getOrisinalitas());
            statement.setDouble(3,l.getPemilihanKata());
            statement.setDouble(4,l.getNilai());
            statement.setString(5,l.getJudul());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
            ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String jd) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            statement.setString(1,jd);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
            ex.printStackTrace();
            }
        }    
    }
    
    @Override
    public List<lomba> getAll(){
        List<lomba> lb = null;
        try{
            lb = new ArrayList<lomba>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                lomba hp = new lomba();
                hp.setJudul(rs.getString("judul"));
                hp.setAlur(rs.getDouble("alur"));
                hp.setOrisinalitas(rs.getDouble("orisinalitas"));
                hp.setPemilihanKata(rs.getDouble("pemilihanKata"));
                hp.setNilai(rs.getDouble("nilai"));
                lb.add(hp);
            }
        }catch(SQLException ex){
            Logger.getLogger(lombaDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return lb;
    }
}
