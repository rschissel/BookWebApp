<%--
    Document   : index
    Created on : Feb 6, 2017, 1:42:17 PM
    Author     : Ryan Schissel
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <form class="bookForm" method="post">
            <input type="hidden" name="formType" value="book">
            <table id="bookTable" border="2">
                <c:forEach items="${books}" var="currentBook">
                    <tr>
                        <td><input type="checkbox" name="selectedItem" id="selectedItem" value="${currentBook.bookId}"><c:out value=" ${currentBook.bookId}"/><td>
                        <td><c:out value="${currentBook.title}"/></td>
                        <td><c:out value="${currentBook.isbn}"/></td>
                        <td><c:out value="${currentBook.author.authorId} ${currentBook.author.authorName}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <div id="bookUpdateModal" class="modal fade" role="dialog">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Add</h4>
                            <div class="modal-body">
                                <input type="radio" name="selectedField" value="title">
                                <label for="title" class="ui-hidden-accessible">Title:</label>
                                <input type="text" name="title" id="title" placeholder="Title"><br>
                                <input type="radio" name="selectedField" value="isbn">
                                <label for="isbn" class="ui-hidden-accessible">ISBN:</label>
                                <input type="text" name="isbn" id="isbn" placeholder="ISBN"><br>
                                <input type="radio" name="selectedField" value="author">
                                <label for="author" class="ui-hidden-accessible">Author:</label><br>
                                <div class="dropdown">
                                    <button class="btn btn-default dropdown-toggle" type="button" id="authorMenu" data-toggle="dropdown">
                                        <span class="caret"></span></button>
                                    <ul class="dropdown-menu" role="menu" aria-labelledby="authorMenu">
                                        <c:forEach items="${authors}" var="currentAuthor">
                                            <li role="presentation"><a role="menuitem" name="authorName"><input type="radio" name="author" value="${currentAuthor.authorId}"/>${currentAuthor.authorName}</a></li>
                                            <li role="presentation" class="divider"></li>
                                            </c:forEach>
                                    </ul>
                                </div>      
                            </div>
                            <div class="modal-footer">
                                <input type="button" name="add" class="btn btn-default" onclick='$(".bookForm").attr("action", "BookController?action=add").submit();' data-dismiss="modal" value="Save"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <div id="bookDeleteModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Delete</h4>
                        <div class="modal-body">
                            <p>Are you sure you want to delete the selected book?<br></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" name="delete" class="btn btn-default" onclick='$(".bookForm").attr("action", "BookController?action=delete").submit();' data-dismiss="modal" value="Yes"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="resources/js/bookwebapp.js" type="text/javascript"></script>
</body>
</html>

