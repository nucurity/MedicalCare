
package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import Models.User;
import java.util.regex.Pattern;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author nucur
 */

@Controller
//@SessionAttributes("username")
@RequestMapping("/registration.htm")
public class RegistrationController {
    DataSource dataSource;
    Connection connection;
    Connection conn=null;
    PreparedStatement pstmt;
    int count=0;
    Context ctx;
    Statement stmt;
    
   @RequestMapping(method = RequestMethod.GET)
    public String showForm(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("medicalcare",user);
        return "registration";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(  @ModelAttribute("medicalcare") User userModel,  BindingResult result,
            ModelMap model) throws ClassNotFoundException, NamingException {
        
        
                try {
                    
                    
                    int accountId=userModel.getUserId();
                    String userFirstName=null;
                    userFirstName=userModel.getUserFirstName();
                    String regex = "[0-9]";
                    String text2="Invalid input";
                    String text="Please enter details";
                        
                    
                    String userLastName = userModel.getUserLastName();
                    String email=userModel.getEmail();
                    int phone=userModel.getPhone();
                    String password=userModel.getPassword();
                    boolean profile=userModel.getProfile();
                    
                   
                    
                    model.addAttribute("userFirstName", userFirstName);
                    model.addAttribute("userLastName", userLastName);
                    model.addAttribute("email", email);
                    model.addAttribute("password", password);
                    model.addAttribute("profile", profile);
                    model.addAttribute("phone", phone);
                    //model.addAttribute("text2",text2);
                    
                    if(userFirstName.equals("")||userLastName.equals("")||email.equals("")||password.equals("")||phone==0){
                        model.addAttribute("text",text);
                       return "registration";
                    }
                    
                    
                    
                   
                    
                    Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/medicalCareDataSource");
            connection = ds.getConnection();

            Statement stmt = connection.createStatement();
            PreparedStatement pstmt;

                    
                    String query = "INSERT INTO Accounts(accountId, firstName, lastName, email,"
                            + "phone, password, profile) VALUES (?,?,?,?,?,?,?);";
                    
                    pstmt = connection.prepareStatement(query);
                    
                    pstmt.setInt(1,accountId);
                    pstmt.setString(2,userFirstName);
                    pstmt.setString(3,userLastName);
                    pstmt.setString(4,email);
                    pstmt.setInt(5,phone);
                    pstmt.setString(6,password);
                    pstmt.setBoolean(7,profile);
                    if(pstmt.execute())
                        {
                        System.out.println("New user added!");
                        return "index";
                    }
                    else
                    {
                        System.out.println("Could not add new user");
                    }
                    
                        
                    
             
                
                } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return "index";
        }
   
    

   }
 