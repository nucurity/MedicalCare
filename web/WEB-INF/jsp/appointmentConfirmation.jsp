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
                %>
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> ${sessionScope.user}</a>
                
            </ul>
        </div>
    </nav>

    <wrapper id="wrapper">
        <div class="row">
            <div class="col-sm-1">
            </div>
            <div class="col-sm-4">
                ${content}
            </div>
            <div class="col-sm-7">
                <br><br>
                <img src="http://royal-circle.com/en/images/MedicalCare/medical-care-1.jpg" alt="Smiley face" height="70%" width="70%">
            </div>
        </div>
    </wrapper>
</body>



</html>
