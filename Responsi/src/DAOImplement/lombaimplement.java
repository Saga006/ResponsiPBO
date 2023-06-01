/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplement;
import model.*;
import java.util.List;
/**
 *
 * @author Lab Informatika
 */
public interface lombaimplement {
    public void insert(lomba l);
    public void update(lomba l);
    public void delete(String jd);
    public List<lomba> getAll();
}
