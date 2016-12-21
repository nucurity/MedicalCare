/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Appointment;
import Models.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author nucur
 */
@Controller
@RequestMapping("/appointmentform.htm")
public class AppointmentController {

    Connection connection;

    /**
     * 
     * @param modelMap
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showForm(ModelMap modelMap) {
        Appointment appointment = new Appointment();
        modelMap.addAttribute("appointment", appointment);

        modelMap.addAttribute("departments", fetchDepartments());
        return "appointmentForm";
    }

    /**
     *
     * @param appointment
     * @param result
     * @param modelMap
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String AddApp(@ModelAttribute("appointment") Appointment appointment, BindingResult result,
            ModelMap modelMap) {
        
        int accountId = appointment.getAccountId();
        int departmentId = appointment.getDepartmentId();
        Date fulldate = appointment.getDate();
        java.sql.Date date = new java.sql.Date(fulldate.getTime());
        String message = appointment.getMessage();

        String content = "";

        if (departmentId == 0 || date == null) {
            content = "Sorry, you didn't fill some of fields. Please, try again.";
            modelMap.addAttribute("content", content);
            return "appointmentConfirmation";
        }
        Date m = null;
        int appointmentcounter = 0;
        Appointment[] appointments = fetchAppointments();
        for(int i = 0; i<appointments.length; i++){
            if(appointments[i].getDate().compareTo(date) == 0 && appointments[i].getDepartmentId() == departmentId)
                appointmentcounter++;
        }
        if (appointmentcounter >= 2) {
            content = "Sorry, there is no any free spaces for your appointment on " +
                    date +
                    " to visit " +
                    findDepartmentName(departmentId) +
                    ". Please, select another day.<br>";
            modelMap.addAttribute("content", content);
            return "appointmentConfirmation";
        }

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/medicalCareDataSource");
            connection = ds.getConnection();

            Statement stmt = connection.createStatement();
            PreparedStatement pstmt;

            

            pstmt = connection.prepareStatement("INSERT INTO appointments (accountId, departmentId, date, message)\n"
                    + "VALUES (?,?,?,?);");
            pstmt.setInt(1, accountId);
            pstmt.setInt(2, departmentId);
            pstmt.setDate(3, date);
            pstmt.setString(4, message);
            pstmt.execute();

            content = "<h4>Appontment have been setted.</h4><br><h5>Please, check information below.</h5><br>"
                    + "<table>"
                    + "<tr><td>Seleted department:</td><td>" + findDepartmentName(departmentId) + "</td></tr>"
                    + "<tr><td>Seleted date:</td><td>" + new SimpleDateFormat("yyyy-MM-dd").format(date) + "</td></tr>"
                    + "<tr><td>Attached message</td><td>" + message + "</td></tr>"
                    + "</table>";

            stmt.close();
        } catch (NamingException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelMap.addAttribute("content", content);

        return "appointmentConfirmation";
    }

    /**
     * Return list of all departments from db.
     *
     * @return
     */
    public Department[] fetchDepartments() {

        Department[] dep = null;

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/medicalCareDataSource");
            connection = ds.getConnection();

            Statement stmt = connection.createStatement();

            String smtquery = "SELECT * FROM departments";
            ResultSet resultSet;
            resultSet = stmt.executeQuery(smtquery);

            List<Department> departments = new ArrayList<Department>();
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("departmentId"));
                department.setDepartmentName(resultSet.getString("departmentName"));
                departments.add(department);
            }

            dep = new Department[departments.size()];
            dep = departments.toArray(dep);

            stmt.close();

        } catch (NamingException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dep;
    }

    /**
     * return list on all appointments.
     *
     * @return
     */
    public Appointment[] fetchAppointments() {

        Appointment[] appointments = null;

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/medicalCareDataSource");
            connection = ds.getConnection();

            Statement stmt = connection.createStatement();

            String smtquery = "SELECT * FROM appointments";
            ResultSet resultSet;
            resultSet = stmt.executeQuery(smtquery);

            List<Appointment> appointmentsList = new ArrayList<Appointment>();
            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAccountId(resultSet.getInt("accountId"));
                appointment.setAppointmentId(resultSet.getInt("appointmentId"));
                appointment.setDate(resultSet.getDate("date"));
                appointment.setDepartmentId(resultSet.getInt("departmentId"));
                appointment.setMessage(resultSet.getString("message"));
                appointmentsList.add(appointment);
            }

            appointments = new Appointment[appointmentsList.size()];
            appointments = appointmentsList.toArray(appointments);

            stmt.close();

        } catch (NamingException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return appointments;
    }
    
    public String findDepartmentName(int id){
        Department[] departments = fetchDepartments();
        
        String name ="";
        
        for(int i = 0; i<departments.length; i++)
            if(departments[i].getDepartmentId() == id){
                name = departments[i].getDepartmentName();
                break;
            }
        return name;
    }

}
