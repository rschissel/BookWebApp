<%-- 
    Document   : navBar
    Created on : Mar 31, 2017, 7:08:17 PM
    Author     : Ryan Schissel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-inverse">
    <jsp:include page="navbarHeader.jsp"/>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav"> 
            <ul class="nav nav-pills">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Action<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a id="edit" data-toggle="modal" data-target="#${formType}AddModal">Add</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a id="edit" data-toggle="modal" data-target="#${formType}EditModal">Edit</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a id="delete"  data-toggle="modal" data-target="#${formType}DeleteModal">Delete</a></li>
                    </ul>
                <li><a href="#" id="homeTab">Home</a></li>
                <li><a href="AuthorController?action=list" id="authorsTab">Authors</a></li>
                <li><a href="BookController?action=list" id="booksTab">Books</a></li>
            </ul>
            </li>
        </ul>
    </div>
</nav>
