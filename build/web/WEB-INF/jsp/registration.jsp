
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Medical Care</title>
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
       
         <style>
            body{
                margin-left: 20%;
                margin-right: 20%;
            }
            #wrapper{
                background-color: teal;
            }

        </style>
    </head>

    <body>
        <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.htm">Medical Care</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="index.htm">Home</a></li>
                <li><a href="#">about</a></li>
                <li><a href="#">contact us</a></li> 
            </ul>
            <ul class="nav navbar-nav navbar-right">
                
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>
    </nav>
    <wrapper id="wrapper">
        <div class="row">
            <div class="col-sm-12">
                <div class="row">
                    
                    
                           <div class="col-sm-7">
                <img src="http://www.vhha.com/programs/wp-content/uploads/sites/19/2015/09/healthcare-it-banner.png" alt="Smiley face" height="100%" width="100%" style="margin-top: 25%">

                </div>
            
                <div class="col-sm-5">
                    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

                    <form:form method="POST" action="registration.htm" modelAttribute="medicalcare">

                            <h2>Registration form</h2>
                            <h3>${text}</h3>
                            <h3>${text3}</h3>
                            
                            
                            <table>

                                <form:input type="hidden" path="userId"  value=""/>

                                <div class="form-group">
                                    <form:label path="userFirstName">First name:</form:label>
                                    <form:input type="text" class="form-control" path="userFirstName" /> <div style="color:red;font-size: medium">${text2}</div>
                                </div>

                                <div class="form-group">
                                    <form:label path="userLastName">Last name:</form:label>
                                    <form:input type="text" class="form-control" path="userLastName"/> <div style="color:red;font-size: medium">${text2}</div>
                                </div>

                                <div class="form-group">
                                    <form:label path="email">Email:</form:label>
                                    <form:input type="text" class="form-control" path="email"/> <div style="color:red;font-size: medium"></div>
                                </div>

                                <div class="form-group">
                                        <form:label path="phone">Phone:</form:label>
                                        <form:input type="text" class="form-control" path="phone"/>
                                </div>

                                <div class="form-group">
                                        <form:label path="password">Password:</form:label>
                                        <form:input type="password" class="form-control" path="password" />
                                </div>

                                    <form:input type="hidden" path="profile" value="false" />

                                <div class="form-group">
                                    <input type="submit" class="form-control" value="Register"/>
                                </div>

                                    <td colspan="2">

                    </form:form>
            
                </div>
           
                
      
             </div>
                    
                 
                    
        </div>
                     
                    
                    </div>
                    
    </div>
      
    </wrapper>
    </body>
</html>

    
               
            

