package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserDetailServic;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserDetailServic userDetailService;

    public AdminController(UserService userService, RoleService roleService, UserDetailServic userDetailService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDetailService = userDetailService;
    }

    @GetMapping
    public String userList(Model model, Principal principal) {
        List<User> list = userService.findAll();
        List<Role> listRoles = roleService.findAll();
        model.addAttribute("userList", list);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("userNew", new User());
        model.addAttribute("userGet", userDetailService.findByUsername(principal.getName()));;
        return "pages/show";
    }

   @GetMapping("/new")
   public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "pages/new";
   }

  @PostMapping()
  public String create(@ModelAttribute("User")  User user, Model model) {
        userService.save(user);
        model.addAttribute("allRoles", roleService.findAll());
        return "redirect:/admin";
  }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("User", userService.findOne(id));
        return "pages/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("User") User user,
                         @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
