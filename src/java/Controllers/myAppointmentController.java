/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Appointment;
import Models.Patient;
import Models.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
@RequestMapping("/myappointment.htm")
public class myAppointmentController {
    
      Connection connection;
      
      
      @RequestMapping(method = RequestMethod.GET)
    public String showReport(ModelMap modelMap) {
 String content = "Please, select date for reports";
 
        Appointment appoint = new Appointment();
        modelMap.addAttribute("report", appoint);
        modelMap.addAttribute("content", content);

        return "myAppointment";
    }


   @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String AddApp(@ModelAttribute("appointments") Appointment appoint, BindingResult result,
            ModelMap modelMap) {
        
        Date fulldate = appoint.getDate();
        if (fulldate == null) {
            fulldate = new Date();
        }
        java.sql.Date date = new java.sql.Date(fulldate.getTime());

        //add validation of is it a doctor or not
        String content = "";
        Appointment[] appoints = fetchAppointments(date);
        if (appoints.length != 0) {
            content+="<table class=\"table\">"
                + "<thead><tr><th>First Name</th><th>Last Name</th><th>Claim</th></tr></thead><tbody>";
            for (Appointment appointment : appoints) {
                content += "<tr>\n"
                        + "<td>"
                        + appointment.getAppointmentId()
                        + "</td><td>"
                        + appointment.getMessage()
                        + "</td><td>"
                        + appointment.getDepartmentId()
                        + "</td>";
            }
            content += "</tbody></table>";
        }
        
        else{
            content += "There is no appointments for " + date;
        }

        

        modelMap.addAttribute("appoints", appoints);
        modelMap.addAttribute("content", content);

        return "myAppointment";
    }

public Appointment[] fetchAppointments(java.sql.Date date) {

        Appointment[] Appoint = null;

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/medicalCareDataSource");
            connection = ds.getConnection();

            PreparedStatement pstmt = connection.prepareStatement("SELECT appointments.message,"
                    + "FROM appointments JOIN accounts\n"
                    + "WHERE appointments.date = ? and email=${sessionScope.user};");
            
            
            pstmt.setDate(1, date);

            ResultSet resultSet = pstmt.executeQuery();

            List<Appointment> appointmentsList = new ArrayList<Appointment>();
            while (resultSet.next()) {
                Appointment appoint = new Appointment();
                appoint.setAppointmentId(resultSet.getInt("appointmentId"));
                appoint.setMessage(resultSet.getString("message"));
                appoint.setDepartmentId(resultSet.getInt("depoartmentId"));

                appointmentsList.add(appoint);
            }

            Appoint = new Appointment[appointmentsList.size()];
            Appoint = appointmentsList.toArray(Appoint);

            pstmt.close();

        } catch (NamingException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Appoint;
    }
    
   
   
}
