package sk.stuba.fei.uim.vsa.pr2.service;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final EntityManager em;
    private final EntityTransaction et;

    public UserService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }
    public User createUser(String firstname, String lastname, String email) {
        try{
            et.begin();
            User user = new User(firstname, lastname, email);
            em.persist(user);
            et.commit();
            return user;
        }catch(RollbackException | NoResultException e){
            return null;
        }
    }
    public User getUser(Long userId) {
        try{
            return em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", userId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    public User getUser(String email) {
        try{
            return em.createNamedQuery(User.Queries.findByEmail, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    public List<User> getUsers() {
        try{
            List<User> userList = em.createNamedQuery(User.Queries.findAll, User.class)
                    .getResultList();
            return new ArrayList<>(userList);
        }catch (NoResultException e){
            return null;
        }
    }
    public User updateUser(Object user) {
        try{
            et.begin();
            em.merge(user);
            et.commit();
            return em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", ((User) user).getId())
                    .getSingleResult();
        }catch (NoResultException | RollbackException e){
            return null;
        }
    }

    public User deleteUser(Long userId) {
        return null;
    }
}
