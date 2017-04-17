<%-- 
    Document   : index
    Created on : Apr 2, 2017, 1:44:56 PM
    Author     : Ryan Schissel
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Book Web App</title>
        <link href="resources/css/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
            <jsp:include page="navBar.jsp"/>
            <jsp:include page="authorList.jsp"/>  
            
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="resources/js/bookwebapp.js" type="text/javascript"></script>
    </body>
</html>
