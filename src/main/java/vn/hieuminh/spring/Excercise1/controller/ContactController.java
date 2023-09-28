package vn.hieuminh.spring.Excercise1.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.hieuminh.spring.Excercise1.dao.ContactDAO;
import vn.hieuminh.spring.Excercise1.dao.TeamDAO;
import vn.hieuminh.spring.Excercise1.entity.Contact;
import vn.hieuminh.spring.Excercise1.entity.Team;

import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private ContactDAO contactDAO;
    private TeamDAO teamDAO;

    public ContactController() {
    }

    @Autowired
    public ContactController(ContactDAO contactDAO, TeamDAO teamDAO) {
        this.contactDAO = contactDAO;
        this.teamDAO = teamDAO;
    }

    @GetMapping("/list")
    public String showListContact(Model model) {
        List<Contact> contacts = this.contactDAO.selectAll();
        model.addAttribute("contacts", contacts);
        return "contact/member-list";
    }

    @GetMapping("/delete")
    public String deleteContact(@RequestParam("id") int id){
        this.contactDAO.deleteById(id);
//        if(team==null){
//            return "redirect:/contact/list";
//        }
        return "redirect:/contact/list";
//        return "redirect:/team/member";
    }

    @GetMapping("/list/information")
    public String information(@RequestParam("id")int id, Model model){
        Contact contact = this.contactDAO.selectById(id);
        List<Team> teams = this.teamDAO.selectAll();
        model.addAttribute("contact",contact);
        model.addAttribute("teams",teams);
        return "contact/member-information";
    }

    @PostMapping("/update")
    public String updateContact(@ModelAttribute("contact") Contact contact, @RequestParam("id")int id, Model model){
        Contact contact1 = this.contactDAO.selectById(id);
        Team team = teamDAO.findTeamByName(contact.getTeam_name());
        teamDAO.update(team);
        contact1.setFirstName(contact.getFirstName());
        contact1.setLastName(contact.getLastName());
        contact1.setGender(contact.getGender());
        contact1.setPhoneNumber(contact.getPhoneNumber());
        contact1.setDate(contact.getDate());
        contact1.setAddress(contact.getAddress());
        contact1.setHobby(contact.getHobby());
        contact1.setTeam_name(contact.getTeam_name());
        contact1.setTeam(team);

        model.addAttribute("success", "Cập nhật thông tin thành công ! ");
        this.contactDAO.update(contact1);
        return "contact/member-information";
    }

    @GetMapping("/create")
    public String create(Model model){
        Contact contact = new Contact();
        List<Team> teams = this.teamDAO.selectAll();
        Team team = new Team();
        model.addAttribute("teams",teams);
        model.addAttribute("team_existing",team);
        model.addAttribute("contact",contact);
        return "contact/member-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("contact") Contact contact){
        Team team = teamDAO.findTeamByName(contact.getTeam_name());
        contact.setTeam(team);
        this.contactDAO.save(contact);
        return "redirect:/";
    }
}
