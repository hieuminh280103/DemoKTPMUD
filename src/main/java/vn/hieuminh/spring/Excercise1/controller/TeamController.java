package vn.hieuminh.spring.Excercise1.controller;

import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.hieuminh.spring.Excercise1.dao.ContactDAO;
import vn.hieuminh.spring.Excercise1.dao.TeamDAO;
import vn.hieuminh.spring.Excercise1.entity.Contact;
import vn.hieuminh.spring.Excercise1.entity.Team;

import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {
    private TeamDAO teamDAO;
    private ContactDAO contactDAO;

    public TeamController() {
    }

    @Autowired
    public TeamController(TeamDAO teamDAO, ContactDAO contactDAO) {
        this.teamDAO = teamDAO;
        this.contactDAO = contactDAO;
    }

    @GetMapping("create")
    public String createTeam(Model model){
        Team team = new Team();
        model.addAttribute("team",team);
        return "team/team-form";
    }

    @PostMapping("/save")
    public String saveTeam(@ModelAttribute("team")Team team, Model model){
        try {
            Team teamExisting = this.teamDAO.findTeamByName(team.getName());
            if(teamExisting!=null){
                model.addAttribute("error","Đã tồn tại nhóm có tên "+team.getName());
                return "team/team-form";
            }
            this.teamDAO.save(team);
            return "redirect:/";
        }catch (Exception e){
            this.teamDAO.save(team);
            return "redirect:/";
        }
    }

    @GetMapping("/list")
    public String showListTeam(Model model){
        List<Team> teams = teamDAO.selectAll();
        model.addAttribute("teams",teams);
        return "team/team-list";
    }

    @GetMapping("/delete")
    public String deleteTeam(@RequestParam("id") int id){
        this.contactDAO.updateContactWithDeleteTeam(id);
        this.teamDAO.deleteById(id);
        return "redirect:/team/list";
    }

    @GetMapping("/list/information")
    public String teamInformation(@RequestParam("id")int id, Model model){
        Team team = this.teamDAO.selectById(id);
        model.addAttribute("team",team);
        return "team/team-information";
    }

    @PostMapping("/update")
    public String updateTeam(@ModelAttribute("team")Team team, @RequestParam("id")int id, Model model){
        try {
            Team teamExisting = this.teamDAO.findTeamByName(team.getName());
            if(teamExisting!=null){
                model.addAttribute("error","Đã tồn tại nhóm có tên : "+team.getName());
            }
            return "team/team-information";
        }catch (Exception e){
            Team team1 = this.teamDAO.selectById(id);
            team1.setName(team.getName());
            try {
                Contact contact = contactDAO.selectByTeamId(id);
                contact.setTeam_name(team.getName());
                contactDAO.update(contact);
            }catch (Exception e1){

            }
            teamDAO.update(team1);
            model.addAttribute("success","Cập nhật thành công !");
            return "team/team-information";
        }
    }

    @GetMapping("member")
    public String member(@RequestParam("id") int id, Model model){
        List<Contact> contacts = this.teamDAO.selectMember(id);
        Team team = this.teamDAO.selectById(id);
        model.addAttribute("contacts",contacts);
        model.addAttribute("team",team);
        return "team/team-member";
    }
}
