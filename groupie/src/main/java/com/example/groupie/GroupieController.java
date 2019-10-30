package com.example.groupie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GroupieController {
    @GetMapping("/mission_fake")
    public String createMission(Model model) {
        model.addAttribute("missionFake", new MissionFake());
        return "missionpage";
    }

    @PostMapping("/mission_fake")
    public String missionSubmit(@Valid MissionFake missionFake, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "missionpage";
        }
        return "resultpage";
    }

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @GetMapping("/user")
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Autowired
    private MissionRepository missionRepository;

    @GetMapping("/enterid")
    public String showIdForm() {
        return "enterid";
    }

    @GetMapping("/mission_real/{userid}")
    public String showForm() {
        return "newmission";
    }

    @PostMapping("/mission_real/{userid}")
    public String addMission(@PathVariable("userid") int userid, @RequestParam("content") String content, @RequestParam("deadline") String deadline, ModelMap modelMap) {
        Mission mis= new Mission();
        mis.setUserId(userid);
        mis.setContent(content);
        mis.setDeadline(deadline);
        missionRepository.save(mis);
        modelMap.addAttribute("user", userRepository.getOne(userid));
        return "temporary";
    }

//    @GetMapping("/missionlist")
//    public String viewList(@RequestParam("user_id") int user_id, ModelMap modelMap) {
//        modelMap.addAttribute("missionlist", missionRepository.findByUserid(user_id));
//        modelMap.addAttribute("userid", user_id);
//        return "missionlist";
//    }

    @PostMapping("/missionlist")
    public String viewList(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap modelMap) {
        User temp= userRepository.findOneByUsername(username);
        if (temp.getPassword().equals(password)) {
            int userid= temp.getId();
            modelMap.addAttribute("missionlist", missionRepository.findByUserid(userid));
            modelMap.addAttribute("userid", userid);
            return "missionlist";
        }
        return "enterid";
    }

    @GetMapping("/mission_delete/{id}&{userid}")
    public String deleteMission(@PathVariable("id") Integer id, @PathVariable("userid") Integer userid, ModelMap modelMap) {
        modelMap.addAttribute("user", userRepository.getOne(userid));
        missionRepository.deleteById(id);
        return "temporary";
    }

    @GetMapping("/mission_edit/{id}&{userid}")
    public String showEdit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("mission", missionRepository.getOne(id));
        return "editmission";
    }

    @PostMapping("/mission_edit/{id}&{userid}")
    public String editMission(@PathVariable("id") Integer id,@PathVariable("userid") Integer userid, @RequestParam("content") String content, @RequestParam("deadline") String deadline, ModelMap modelMap) {
        Mission mis= new Mission();
        mis.setId(id);
        mis.setUserId(userid);
        mis.setContent(content);
        mis.setDeadline(deadline);
        missionRepository.save(mis);
        modelMap.addAttribute("user", userRepository.getOne(userid));
        return "temporary";
    }

    @GetMapping("/signup")
    public String showSignUp() {return "signup";}

    @PostMapping("/signup")
    public String signUp(@RequestParam("username") String username, @RequestParam("password") String password) {
        User temp= new User();
        temp.setUsername(username);
        temp.setPassword(password);
        userRepository.save(temp);
        return "enterid";
    }

//    public String missionSubmit(@ModelAttribute Mission mission) {
//        return "resultpage";
//    }
}
