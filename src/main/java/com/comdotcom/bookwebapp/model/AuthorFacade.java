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
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "com.comdotcom_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }

    public void addNew(String name) {
        Author a = new Author();
        a.setAuthorName(name);
        Date creationDate = new Date();
        a.setDateAdded(creationDate);
        this.create(a);
    }

    public void update(String id, String name) {
        //look for more efficient way to accomplish
//        Author a = this.find(Integer.parseInt(id));
//        a.setAuthorName(name);
//        this.edit(a);
        Integer iId = Integer.parseInt(id);
        String jpql = "UPDATE Author a SET a.authorName = :name WHERE a.authorId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("name", name);
        q.setParameter("id", iId);
        q.executeUpdate();
    }

    public void deleteById(String id) {
        Integer iId = Integer.parseInt(id);
        String jpql = "DELETE FROM Author a WHERE a.authorId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", iId);
        q.executeUpdate();
    }

    public void addOrUpdate(String id, String name) {
        if (id == null || id.equals("0")) {
            //new record
        } else {
            //update record
        }
    }
}
