/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comdotcom.bookwebapp.controller;

import com.comdotcom.bookwebapp.model.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Ryan Schissel
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    private static final String SELECTED_ITEM = "selectedItem";
    private static final String SELECTED_FIELD = "selectedField";
    private static final String ERR_PAGE = "/errorpage.html";
    private static final String ACTION = "action";
    private static final String INDEX = "/index.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String EDIT_ACTION = "edit";
    private static final String DELETE_ACTION = "delete";
    private static final String TITLE = "title";
    private static final String ISBN = "isbn";
    private static final String AUTHOR_ID = "authorId";
    @EJB
    private BookFacade bf;
 
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDateTime update = LocalDateTime.now();
        response.setContentType("text/html;charset=UTF-8");
        String destination = INDEX;
        try {
            request.setAttribute("formType", "book");
            String action = request.getParameter(ACTION);
            switch (action) {
                case ADD_ACTION:
                    addBook(request);
                    refreshList(request);
                    break;
                case EDIT_ACTION:
                    editBook(request);
                    refreshList(request);
                    break;
                case LIST_ACTION:
                    refreshList(request);
                    break;
                case DELETE_ACTION:
                    removeBook(request);
                    refreshList(request);
                    break;
                default:
                    refreshList(request);
                    break;
            }

        } catch (Exception ex) {
            request.setAttribute("errMsg", ex.getMessage());
        }

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);

    }

    private void editBook(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        String itemId = request.getParameter(SELECTED_ITEM);
        String field = request.getParameter(SELECTED_FIELD);
        Object value = request.getParameter(field + "Edit");
        bf.update(itemId, field, value);
        
    }

    private void addBook(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        String title = request.getParameter(TITLE);
        String isbn = request.getParameter(ISBN);
        String authId = request.getParameter(AUTHOR_ID + "Add");
        Author author = null;
        if (!title.isEmpty()) {
            bf.addNew(title, isbn, author);
        }
    }

    private void removeBook(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        String bookId = request.getParameter(SELECTED_ITEM);
        if (!bookId.isEmpty()) {
            bf.deleteById(bookId);
        }
    }

    private void refreshList(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        List<Book> books = bf.findAll();
        request.setAttribute("books", books);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    @Override
    public void init() throws ServletException {

    }// </editor-fold>

}
