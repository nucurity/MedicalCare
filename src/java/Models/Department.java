/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author nucur
 */
public class Department {

    private int departmentId;
    private String departmentName;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void generateList() {

        Connection connection;

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/medicalCareDataSource");
            connection = ds.getConnection();

            Statement stmt = connection.createStatement();
            PreparedStatement pstmt;

            pstmt = connection.prepareStatement("INSERT INTO departments (departmentName)\n"
                    + "VALUES ('?');");

            String[] doctors = {"Audiologist","Allergist","Anesthesiologist","Cardiologist","Dentist","Dermatologist"};
            
            for(String doctor : doctors){
                pstmt.setString(1, doctor);
                pstmt.execute();
            }
            stmt.close();
        } catch (NamingException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}
