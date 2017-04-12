/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comdotcom.bookwebapp.model;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ryan Schissel
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "com.comdotcom_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
    public void addNew(String title, String isbn, Author author) {
        Book b = new Book();
        b.setTitle(title);
        b.setIsbn(isbn);
        b.setAuthor(author);
        this.create(b);
    }

    public void update(String id, String field, Object objValue) {
        Integer iId = Integer.parseInt(id);
        String jpql = "UPDATE Book b SET b." + field + " = :value WHERE b.bookId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        if (field.equals("author")){
            Author author = (Author) objValue;
            q.setParameter("value", author.getAuthorId());
        }
        else{
            String value = (String) objValue;
            q.setParameter("value", value);
        }
        q.setParameter("id", iId);
        q.executeUpdate();
    }

    public void deleteById(String id) {
        Integer iId = Integer.parseInt(id);
        String jpql = "DELETE FROM Book b WHERE b.bookId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", iId);
        q.executeUpdate();
    }
    
}
