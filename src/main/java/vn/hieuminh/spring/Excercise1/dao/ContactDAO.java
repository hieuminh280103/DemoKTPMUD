package vn.hieuminh.spring.Excercise1.dao;

import jakarta.persistence.EntityManager;
import vn.hieuminh.spring.Excercise1.entity.Contact;
import vn.hieuminh.spring.Excercise1.entity.Team;

import java.util.List;

public interface ContactDAO {
    public List<Contact> selectAll();

    public Contact selectById(int id);
    public void save(Contact contact);
    public Contact update(Contact contact);
    public void deleteById(int id);

    public Team findTeamById(int id);

    public void updateContactWithDeleteTeam(int id);
    public Contact selectByTeamId(int id);
}
