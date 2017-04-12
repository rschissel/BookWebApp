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
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    private static final String AUTHOR_NAME_ADD = "authorNameAdd";
    private static final String AUTHOR_NAME_EDIT = "authorNameEdit";
    private static final String DATE_ADDED = "dateAdded";
    private static final String TABLE_NAME = "author";
    private static final String AUTHOR_ID = "authorId";
    private static final String SELECTED_AUTHOR_ID = "selectedAuthorId";
    private static final String ERR_PAGE = "/errorpage.html";
    private static final String ACTION = "action";
   private static final String LIST_PAGE = "/index.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String EDIT_ACTION = "edit";
    private static final String DELETE_ACTION = "delete";
   
    @EJB
    private AuthorFacade af;
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
        String destination = LIST_PAGE;
        try {
            request.setAttribute("formType", "author");
            String action = request.getParameter(ACTION);
            switch (action) {
                case ADD_ACTION:
                    addAuthor(af, request);
                    refreshList(af, request);
                    break;
                case EDIT_ACTION:
                    editAuthor(af, request);
                    refreshList(af, request);
                    break;
                case LIST_ACTION:
                    refreshList(af, request);
                    break;
                case DELETE_ACTION:
                    removeAuthor(af, request);
                    refreshList(af, request);
                    break;
                default:
                    refreshList(af, request);
                    break;
            }

        } catch (Exception ex) {
            request.setAttribute("errMsg", ex.getMessage());
        }

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);

    }

    private void editAuthor(AuthorFacade af, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        String authorName = request.getParameter(AUTHOR_NAME_EDIT);
        String authorId = request.getParameter(SELECTED_AUTHOR_ID);
        if (!authorName.isEmpty() || authorName == null || authorId.isEmpty() || authorId == null) {
            af.update(authorId, authorName);
        }
    }

    private void addAuthor(AuthorFacade af, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        String authorName = request.getParameter(AUTHOR_NAME_ADD);
        if (!authorName.isEmpty() || authorName == null) {
            af.addNew(authorName);
        }
    }

    private void removeAuthor(AuthorFacade af, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        String authorId = request.getParameter(SELECTED_AUTHOR_ID);
        if (!authorId.isEmpty() || authorId == null) {
            af.deleteById(authorId);
        }
    }
    private void refreshList(AuthorFacade af, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        List<Author> authors = af.findAll();
        request.setAttribute("authors", authors);
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
