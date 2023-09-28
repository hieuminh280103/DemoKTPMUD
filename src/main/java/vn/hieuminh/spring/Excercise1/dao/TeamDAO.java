package vn.hieuminh.spring.Excercise1.dao;

import vn.hieuminh.spring.Excercise1.entity.Contact;
import vn.hieuminh.spring.Excercise1.entity.Team;

import java.util.List;

public interface TeamDAO {
    public List<Team> selectAll();
    public Team selectById(int id);
    public void save(Team team);
    public Team update(Team team);
    public void deleteById(int id);
    public Team findTeamByName(String name);
    public List<Contact> selectMember(int id);
}
