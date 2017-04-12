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
<form class="authorForm" method="post">
    <input type="hidden" name="formType" value="author">
    <table border="2">
        <c:forEach items="${authors}" var="current">
            <tr>
                <td><input type="checkbox" name="selectedAuthorId" id="selectedAuthorId" value="${current.authorId}"><c:out value=" ${current.authorId}"/><td>
                <td><c:out value="${current.authorName}"/></td>
                <td><c:out value="${current.dateAdded}"/></td>
            </tr>
        </c:forEach>
    </table>
    <div id="authorAddModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Add</h4>
                    <div class="modal-body">
                        <label for="authorName" class="ui-hidden-accessible">Author Name:</label>
                        <input type="text" name="authorNameAdd" id="authorNameAdd" placeholder="Author Name"><br
                    </div>
                    <div class="modal-footer">
                        <input type="button" name="add" class="btn btn-default" onclick='$(".authorForm").attr("action", "AuthorController?action=add").submit();' data-dismiss="modal" value="Save"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="authorEditModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit</h4>
                <div class="modal-body">
                    <label for="authorName" class="ui-hidden-accessible">Author Name:</label>
                    <input type="text" name="authorNameEdit" id="authorNameEdit" placeholder="Author Name"><br>
                </div>
                <div class="modal-footer">
                    <input type="button" name="edit" class="btn btn-default" onclick='$(".authorForm").attr("action", "AuthorController?action=edit").submit();' data-dismiss="modal" value="Save"/>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="authorDeleteModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Delete</h4>
                <div class="modal-body">
                    <p>Are you sure you want to delete the selected author?<br></p>
                </div>
                <div class="modal-footer">
                    <input type="button" name="delete" class="btn btn-default" onclick='$(".authorForm").attr("action", "AuthorController?action=delete").submit();' data-dismiss="modal" value="Yes"/>
                </div>
            </div>
        </div>
    </div>
</div>
</form>
     <c:out value="${errMsg}"/>
