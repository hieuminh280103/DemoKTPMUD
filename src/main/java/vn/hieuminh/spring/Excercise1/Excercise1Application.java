package vn.hieuminh.spring.Excercise1;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.hieuminh.spring.Excercise1.dao.ContactDAO;
import vn.hieuminh.spring.Excercise1.dao.TeamDAO;
import vn.hieuminh.spring.Excercise1.entity.Contact;
import vn.hieuminh.spring.Excercise1.entity.Team;

import java.util.List;

@SpringBootApplication
public class Excercise1Application {

	public static void main(String[] args) {
		SpringApplication.run(Excercise1Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ContactDAO contactDAO, TeamDAO teamDAO){
		return runner->{
//			update(teamDAO,contactDAO);
//			createContact(contactDAO,teamDAO);
//			findTeam(teamDAO);
//			deleteContact(contactDAO);
//			findTeamById(contactDAO);
//			deleteTeam(teamDAO);
		};
	}

	private void findTeamById(ContactDAO contactDAO) {
		Team team = contactDAO.findTeamById(20210333);
		System.out.println(team);
	}

	private void deleteContact(ContactDAO contactDAO) {
		contactDAO.deleteById(12542);
		System.out.println("done");
	}

	private void findTeam(TeamDAO teamDAO) {
		System.out.println(teamDAO.findTeamByName("nhom 2"));
	}

	private void createContact(ContactDAO contactDAO,TeamDAO teamDAO) {
		Contact contact = new Contact();
		Team team = teamDAO.selectById(1);
		contact.setId(20210352);
		contact.setLastName("Hieu");
		contactDAO.save(contact);
		System.out.println("DONE");
	}

	private void update(TeamDAO teamDAO,ContactDAO contactDAO) {
		Team team = teamDAO.selectById(1);
		team.addContact(contactDAO.selectById(20210352));
		teamDAO.update(team);
		System.out.println(team);
	}

	private void deleteTeam(TeamDAO teamDAO) {
		teamDAO.deleteById(3);
		System.out.println("DOne");
	}


}
