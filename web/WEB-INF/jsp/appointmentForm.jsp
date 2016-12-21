<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script>
            $(function () {
                var dateToday = new Date();
                $(function () {
                    $("#date").datepicker({
                        numberOfMonths: 2,
                        showButtonPanel: true,
                        minDate: dateToday
                    });
                });
            });
        </script>
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
                <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
                <form:form method="POST" action="appointmentform.htm" modelAttribute="appointment" class="form-horizontal">
                    <form:input type="hidden" path="accountId" id="accountId" name="accountId" value="${sessionScope.accountId}" />
                    <div class="form-group">
                        <form:label path="departmentId">Department:</form:label>
                        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
                        <form:select path="departmentId" class="form-control" id="departmentId" name="departmentId">
                            <c:forEach items="${departments}" var="item">
                                <option value="${item.departmentId}">${item.departmentName}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <form:label path="date">Date</form:label>
                        <form:input type="text" path="date" class="form-control" id="date" name="date"/>
                    </div>
                    <div class="form-group">
                        <form:label path="message">Message</form:label>
                        <form:textarea type="text" path="message" class="form-control" id="message" name="message" rows="6" cols="45"></form:textarea>
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                </form:form>
            </div>
            <div class="col-sm-7">
                <br><br>
                <img src="http://royal-circle.com/en/images/MedicalCare/medical-care-1.jpg" alt="Smiley face" height="70%" width="70%">
            </div>
        </div>
    </wrapper>
</body>



</html>
