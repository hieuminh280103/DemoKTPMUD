package vn.hieuminh.spring.Excercise1.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.hieuminh.spring.Excercise1.entity.Contact;
import vn.hieuminh.spring.Excercise1.entity.Team;

import java.util.List;

@Repository
public class ContactDAOImpl implements ContactDAO{
    private EntityManager entityManager;

    public ContactDAOImpl() {
    }

    @Autowired
    public ContactDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Contact> selectAll() {
        String jpql = "SELECT c FROM Contact c";
        TypedQuery<Contact> query = this.entityManager.createQuery(jpql,Contact.class);
        List<Contact> list = query.getResultList();
        return list;
    }

    @Override
    public Contact selectById(int id) {
        return this.entityManager.find(Contact.class,id);
    }

    @Override
    @Transactional
    public void save(Contact contact) {
        this.entityManager.persist(contact);
    }

    @Override
    @Transactional
    public Contact update(Contact contact) {
        return this.entityManager.merge(contact);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        String jpql="DELETE FROM Contact t WHERE t.id=:x";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("x",id);
        query.executeUpdate();
    }

    @Override
    public Team findTeamById(int id) {
        String jpql = "SELECT c.team FROM Contact c WHERE c.id=:x";
        TypedQuery<Team> query = this.entityManager.createQuery(jpql,Team.class);
        query.setParameter("x",id);
        Team team = query.getSingleResult();
        return team;
    }

    @Override
    @Transactional
    public void updateContactWithDeleteTeam(int id) {
        String jpql = "UPDATE Contact c SET c.team=null,c.team_name=null WHERE c.team.id=:x";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("x",id);
        query.executeUpdate();
    }

    @Override
    public Contact selectByTeamId(int id) {
        String jpql = "SELECT c FROM Contact c WHERE c.team.id=:x";
        TypedQuery<Contact> query = this.entityManager.createQuery(jpql,Contact.class);
        query.setParameter("x",id);
        Contact contact = query.getSingleResult();
        return contact;
    }
}
