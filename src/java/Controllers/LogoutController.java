/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Samaya
 */
@Controller
@RequestMapping("/logout.htm")
public class LogoutController {
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String logout( HttpServletRequest request,HttpServletResponse response) {
            HttpSession session = request.getSession();

            if (session != null) {
                //session.removeAttribute("username");
                session.invalidate();
            
      }
        return "redirect:/index.htm";
      }
    
}
