/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comdotcom.bookwebapp.controller;

import com.comdotcom.bookwebapp.entity.Book;
import com.comdotcom.bookwebapp.entity.Author;
import com.comdotcom.bookwebapp.service.AuthorService;
import com.comdotcom.bookwebapp.service.BookService;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Ryan Schissel
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    private static final String SELECTED_BOOK = "selectedItem";
    private static final String SELECTED_FIELD = "selectedField";
    private static final String ERR_PAGE = "/errorpage.html";
    private static final String ACTION = "action";
    private static final String INDEX = "/bookList.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String EDIT_ACTION = "edit";
    private static final String DELETE_ACTION = "delete";
    private static final String TITLE = "title";
    private static final String ISBN = "isbn";
    private static final String AUTHOR_NAME = "authorName";
    private static final String AUTHOR_ID = "author";

    private AuthorService as;
    private BookService bs;

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
                    addOrEditBook(request);
                    refreshList(request);
                    break;
                case EDIT_ACTION:
                    addOrEditBook(request);
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
            ex.printStackTrace();
            request.setAttribute("errMsg", ex.getMessage());
        }

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);

    }

    private void addOrEditBook(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        String field = request.getParameter(SELECTED_FIELD);
        String bookId = request.getParameter(SELECTED_BOOK);
        String title = request.getParameter(TITLE);
        String isbn = request.getParameter(ISBN);
        String authorId = request.getParameter(AUTHOR_ID);
        Author author = as.findById(authorId);
        Book book = null;
        if (field == null) {
            book = new Book();
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setTitle(title);
        }
        else{
            book = bs.findById(bookId);
            switch (field){
                case "title":
                    book.setTitle(title);
                    break;
                case "isbn":
                    book.setIsbn(isbn);
                    break;
                case "author":
                    book.setAuthor(author);
                    break;
                default:
                    break;
                 
            }
        }
        bs.edit(book);
    }

//    private void addBook(HttpServletRequest request) throws ClassNotFoundException, SQLException {
//        String title = request.getParameter(TITLE);
//        String isbn = request.getParameter(ISBN);
//        String authId = request.getParameter(AUTHOR_ID + "Add");
//        Author author = null;
//        if (!title.isEmpty()) {
//            bf.addNew(title, isbn, author);
//        }
//    }
    private void removeBook(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        String bookId = request.getParameter(SELECTED_BOOK);
        Book book = bs.findById(bookId);
        if (!bookId.isEmpty()) {
            bs.remove(book);
        }
    }

    private void refreshList(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        List<Book> books = bs.findAll();
        request.setAttribute("books", books);
        List<Author> authors = as.findAll();
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
        ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils
                        .getWebApplicationContext(sctx);
        as = (AuthorService) ctx.getBean("authorService");
        bs = (BookService) ctx.getBean("bookService");
    }// </editor-fold>

}
