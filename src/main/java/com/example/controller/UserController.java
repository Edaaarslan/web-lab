package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Constructor Injection: Spring tarafından otomatik olarak UserService sağlanır.
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Tüm kullanıcıları listeleme
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";  // user-list.jsp sayfasına yönlendirme
    }

    // Yeni kullanıcı formu
    @GetMapping("/new")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";  // user-form.jsp sayfasına yönlendirme
    }

    // Yeni kullanıcı ekleme
    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        try {
            userService.addUser(user);
        } catch (Exception e) {
            // Hata durumunda loglama yapabilir veya bir hata sayfasına yönlendirebilirsiniz.
            return "error-page";
        }
        return "redirect:/users";  // Kullanıcı ekleme işlemi sonrası kullanıcılar listesini gösterir.
    }

    // Kullanıcı düzenleme formu
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            // Kullanıcı bulunamadıysa bir hata sayfasına yönlendirebilirsiniz.
            return "error-page";
        }
        model.addAttribute("user", user);
        return "user-form";  // Düzenleme formu sayfasına yönlendirir
    }

    // Kullanıcı güncelleme
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        try {
            userService.updateUser(user);  // Kullanıcı bilgilerini günceller
        } catch (Exception e) {
            // Hata durumunda loglama yapabilir veya bir hata sayfasına yönlendirebilirsiniz.
            return "error-page";
        }
        return "redirect:/users";  // Güncelleme sonrası kullanıcılar listesini gösterir.
    }

    // Kullanıcı silme
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        try {
            userService.deleteUser(id);  // ID'ye göre kullanıcıyı siler
        } catch (Exception e) {
            // Hata durumunda loglama yapabilir veya bir hata sayfasına yönlendirebilirsiniz.
            return "error-page";
        }
        return "redirect:/users";  // Silme işlemi sonrası kullanıcılar listesini gösterir.
    }
}
