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
public class TeamDAOImpl implements TeamDAO{
    private EntityManager entityManager;

    public TeamDAOImpl() {
    }

    @Autowired
    public TeamDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Team> selectAll() {
        String jpql = "SELECT t FROM Team t";
        TypedQuery<Team> query = this.entityManager.createQuery(jpql,Team.class);
        List<Team> list = query.getResultList();
        return list;
    }

    @Override
    public Team selectById(int id) {
        return this.entityManager.find(Team.class,id);
    }

    @Override
    @Transactional
    public void save(Team team) {
        this.entityManager.persist(team);
    }

    @Override
    @Transactional
    public Team update(Team team) {
        return this.entityManager.merge(team);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        this.entityManager.remove(entityManager.find(Team.class,id));
    }

    @Override
    public Team findTeamByName(String name) {
        String jpql = "SELECT t FROM Team t WHERE t.name=:x";
        TypedQuery<Team> query = this.entityManager.createQuery(jpql,Team.class);
        query.setParameter("x",name);
        return query.getSingleResult();
    }

    @Override
    public List<Contact> selectMember(int id) {
        String jpql = "SELECT t.contacts FROM Team t WHERE t.id=:x";
        TypedQuery<Contact> query = this.entityManager.createQuery(jpql,Contact.class);
        query.setParameter("x",id);
        return query.getResultList();
    }
}
