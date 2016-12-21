/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.Appointment;
import Models.Department;
import Models.Doctor;
import Models.Patient;
import Models.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * @author Samaya
 */

@Controller
@RequestMapping("/reportform.htm")

public class ReportController {
    Connection connection;

    /**
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showReport(ModelMap modelMap) {

        //add if statment. If (!profile)
        //return "index";
       //else
        
        String content = "Please, select date for reports";
        Report report = new Report();
        modelMap.addAttribute("report", report);
        modelMap.addAttribute("content", content);

        return "reportForm";
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
    public String AddApp(@ModelAttribute("report") Report report, BindingResult result,
            ModelMap modelMap) {
        
        int accountId = report.getAccountId();
        Date fulldate = report.getDate();
        if (fulldate == null) {
            fulldate = new Date();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        java.sql.Date date = new java.sql.Date(fulldate.getTime());

        //add validation of is it a doctor or not
        String content = "";
        Patient[] patients = fetchAppointments(fetchDepartmentId(accountId), date);
        if (patients.length != 0) {
            content+="<table class=\"table\">"
                + "<thead><tr><th>First Name</th><th>Last Name</th><th>Claim</th></tr></thead><tbody>";
            for (Patient patient : patients) {
                content += "<tr>\n"
                        + "<td>"
                        + patient.getPatientFirstName()
                        + "</td><td>"
                        + patient.getPatientSecondName()
                        + "</td><td>"
                        + patient.getPatientClaim()
                        + "</td>";
            }
            content += "</tbody></table>";
        }
        
        else{
            content += "There is no appointments for " + date;
        }

        

        modelMap.addAttribute("report", report);
        modelMap.addAttribute("content", content);

        return "reportForm";
    }

    public Patient[] fetchAppointments(int departmentId, java.sql.Date date) {

        Patient[] patients = null;

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/medicalCareDataSource");
            connection = ds.getConnection();

            PreparedStatement pstmt = connection.prepareStatement("SELECT appointments.message, \n" +
                    "accounts.firstName, accounts.lastName FROM appointments INNER JOIN accounts ON appointments.accountId = accounts.accountId \n" +
                    "WHERE appointments.departmentId = ? AND appointments.date = ?;");
            
            pstmt.setInt(1, departmentId);
            pstmt.setDate(2, date);

            ResultSet resultSet = pstmt.executeQuery();

            List<Patient> appointmentsList = new ArrayList<Patient>();
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setPatientFirstName(resultSet.getString("firstName"));
                patient.setPatientSecondName(resultSet.getString("lastName"));
                patient.setPatientClaim(resultSet.getString("message"));

                appointmentsList.add(patient);
            }

            patients = new Patient[appointmentsList.size()];
            patients = appointmentsList.toArray(patients);

            pstmt.close();

        } catch (NamingException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return patients;
    }
    
    public int fetchDepartmentId(int accountId) {

        int departmentId = 0;

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/medicalCareDataSource");
            connection = ds.getConnection();

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM doctors"
                    + " WHERE accountId = ?;");
            pstmt.setInt(1, accountId);
            ResultSet resultSet = pstmt.executeQuery();

            List<Doctor> appointmentsList = new ArrayList<Doctor>();
            while (resultSet.next()) {
                departmentId = resultSet.getInt("departmentId");
            }
            pstmt.close();

        } catch (NamingException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return departmentId;
    }

}
