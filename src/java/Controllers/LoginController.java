/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Department;
import Models.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.Cookie;

/**
 *
 * @author Samaya
 */
@Controller
@RequestMapping("/loginform.htm")
public class LoginController {

    Connection connection;

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(ModelMap modelMap) {
        Login login = new Login();
        modelMap.addAttribute("login", login);
        return "loginForm";
    }

    @RequestMapping(method = RequestMethod.POST)

    public String loginUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login, BindingResult result,
            ModelMap modelMap) {
        String username = login.getUsername();
        String password = login.getPassword();
        //int accountId = login.getAccountId();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/medicalCareDataSource");
            connection = ds.getConnection();

            String query = "Select * from accounts where email=? and password=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            
            Login loginTemp = new Login();
            while (rs.next()) {
                loginTemp.setAccountId(rs.getInt("accountId"));
                loginTemp.setUsername(rs.getString("email"));
                loginTemp.setPassword(rs.getString("password"));
                loginTemp.setProfile(rs.getBoolean("profile"));
            }

            modelMap.addAttribute("username", loginTemp.getUsername());
            modelMap.addAttribute("accountId", loginTemp.getAccountId());
            HttpSession session = request.getSession();
            //String user = request.getParameter("username");

            session.setAttribute("user", loginTemp.getUsername());
            session.setAttribute("accountId", loginTemp.getAccountId());
            session.setAttribute("profile", !loginTemp.isProfile());
            session.setMaxInactiveInterval(1 * 60);
            return "index";

            /*
            int accountId=rs.getInt("accountId");
            login.setAccountId(accountId);
            
            if (rs.next()) {
                String query1 = "Select profile from accounts where email=? and profile=0";
                PreparedStatement ps1 = connection.prepareStatement(query1);
                ps1.setString(1, username);
                // ps1.setString(2,accountId);

                ResultSet rs1 = ps1.executeQuery();

                if (rs1.next()) {
                    modelMap.addAttribute("username", username);
                    modelMap.addAttribute("accountId", accountId);
                    HttpSession session = request.getSession();
                    //String user = request.getParameter("username");

                    session.setAttribute("user", username);
                    session.setAttribute("accountId", accountId);
                    session.setAttribute("profile", new Boolean(true));
                    session.setMaxInactiveInterval(1 * 60);
                    return "index";
                } else {
                    modelMap.addAttribute("username", username);

                    HttpSession session = request.getSession();
                    //String user = request.getParameter("username");

                    session.setAttribute("user", username);
                    session.setAttribute("profile", new Boolean(false));
                    session.setMaxInactiveInterval(1 * 60);
                    return "index";
                }
            } else {
                modelMap.addAttribute("error", "Incorrect Credentials");

            }
             */
        } catch (NamingException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "loginForm";
    }

}
