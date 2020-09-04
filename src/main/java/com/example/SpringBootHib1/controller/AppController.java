package com.example.SpringBootHib1.controller;

import com.example.SpringBootHib1.entity.Admin;
import com.example.SpringBootHib1.entity.Student;
import com.example.SpringBootHib1.entityImpl.AdminImpl;
import com.example.SpringBootHib1.entityImpl.StudentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("id")
public class AppController {

    private StudentImpl studentImpl;
    private AdminImpl adminImpl;

    @Autowired(required = true)
    public void setUsers(StudentImpl studentImpl, AdminImpl adminImpl){
        this.studentImpl = studentImpl;
        this.adminImpl = adminImpl;
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("register")
    public String register(Model model){
        return "register";
    }

    @GetMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("welcomeAdmin")
    public String welcomeAdmin(HttpSession httpSession) {
        httpSession.setAttribute("user", "admin");
        return "welcome";
    }

    @GetMapping("welcomeStudent")
    public String welcomeStudent(HttpSession httpSession){
        httpSession.setAttribute("user","student");
        return "welcome";
    }

    @GetMapping("firstPage")
    public String firstPage(){
        return "firstPage";
    }

    @GetMapping("addAssignment")
    public String addAssignment(){
        return "addAssignment";
    }

    @GetMapping("logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("id");
        httpSession.removeAttribute("user");
        return "redirect:firstPage";
    }

    @GetMapping("studentAssignment")
    public String studentAssignment(Model model){

        List<String> assignments = adminImpl.getAssignments();

        String assignment="";

        for(int i=0;i<assignments.size();i++){
            if(assignments.get(i)!= null){
                assignment += "-> ";
                assignment += assignments.get(i);
                assignment += "\r\n";
            }
        }

        model.addAttribute("assignment",assignment);
        return "studentAssignment";
    }

    @GetMapping("assignment")
    public String assignment(HttpSession httpSession){
        if(httpSession.getAttribute("user").equals("admin")){
            return "redirect:addAssignment";
        }else{
            return "redirect:studentAssignment";
        }
    }

    @PostMapping("addAssignment")
    public String addAssignment(@RequestParam("assignment")String assignment, HttpSession httpSession, Model model){

        if(adminImpl.addAssignment((String) httpSession.getAttribute("id"),assignment)){
            model.addAttribute("addAssignment","Assignment Added :)" );
            return "redirect:addAssignment";
        }
        else{
            model.addAttribute("addAssignment","Error Add Again :(");
            return "addAssignment";
        }
    }

    @PostMapping("login")
    public String login(@RequestParam("email")String email, @RequestParam("password")String password,
                        HttpSession httpSession){

        if(httpSession.getAttribute("user").equals("admin")){
            if(adminImpl.checkStudent(email,password)){
                httpSession.setAttribute("id", email);
                return "redirect:assignment";
            }else{
                return "redirect:login";
            }
        }else{
            System.out.println(studentImpl.checkStudent(email, password));
            if(studentImpl.checkStudent(email, password)){
                httpSession.setAttribute("id", email);

                return "redirect:assignment";
            }else{
                return "redirect:login";
            }
        }
    };


    @PostMapping("register")
    public String register(@RequestParam("name")String name,
                      @RequestParam("email")String email, @RequestParam("password")String password,
                           HttpSession httpSession){

        // check user is admin or student
        if(httpSession.getAttribute("user").equals("admin")){
            adminImpl.saveAdmin(new Admin(email,name, password,""));
        }else{
            studentImpl.saveStudent(new Student(email,name,password));
        }
        return "redirect:login";

    }

}
