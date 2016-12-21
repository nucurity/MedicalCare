<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Medical Care</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                <li class="active"><a href="index.htm">Home</a></li>
                <li><a href="#">about</a></li>
                <li><a href="#">contact us</a></li> 
            </ul>
            <ul class="nav navbar-nav navbar-right">
                
                               
                 <%
                String username= (String) session.getAttribute("user");                     
                if (username == null) {
                %>
                <li><a href="registration.htm"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="loginform.htm"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                <% } else {
                %>
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> ${sessionScope.user}</a>
                    <%
                    
                    
                    if(((Boolean)session.getAttribute("profile"))== true){%> 
                    <ul class="dropdown-menu">
                    <li>
                    <a href="appointmentform.htm" >Set Appointment</a>	
                    <a href="${pageContext.request.contextPath}/myappointment.htm" >My Appointment</a>	
                    <a href="${pageContext.request.contextPath}/logout.htm">Logout</a>
                    </li>
                    </ul> 
                    
                <% } else{%>
                <ul class="dropdown-menu">
                    <li>
                    <a href="reportform.htm" >View Reports</a>	
                    <a href="${pageContext.request.contextPath}/logout.htm">Logout</a>
                    </li>
                    </ul> 
                <% }%>
            <% }%>
            </ul>
        </div>
    </nav>

    <wrapper id="wrapper">
        <center>
            <img src="http://www.medicaltourism.video/wp-content/uploads/Medical-Tourism-Videos-37.jpg" alt="Smiley face" height="70%" width="70%">
        </center>
        <div class="row">
            <div class="col-sm-4">
                <h1>
                    About Our Clinic
                </h1>
                <p>
                    The clinic’s goal is to improve health, by treating obesity and diabetes and decreasing cardiovascular risk factors, such as high blood pressure and high cholesterol.
                </p>
            </div>
            <div class="col-sm-4">
                <h1>
                    Brain-Heart Clinic
                </h1>
                <p>
                    People with serious mental illness die at least 10–15 years earlier than the general population. The majority of these deaths are not related to suicide but to cardiovascular disease (CVD), which occur at rates that are two to three times higher than in the general population. This may be because many of the modifiable risk factors associated with CVD, such as smoking, obesity, poor diet, diabetes, lipid problems and lack of exercise are highly prevalent among this population. Certain medications used to treat mental illness can also increase this risk.
                </p>
            </div>
            <div class="col-sm-4">
                <h1>
                    Appointments
                </h1>
                <p>
                    If you would like to set up a visit in our clinic, please login or register and then you will be able to go threw simple appointment form.<br>
                <center>
                    <input type="button" value="Set up an appointment" onClick='location.href = "appointmentform.htm"'>
                </center>

                </p>
            </div>
        </div>
    </wrapper>
</body>



</html>
