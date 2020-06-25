package com.ciemiorek.users.controllers;

import com.ciemiorek.users.models.UserTest;
import com.ciemiorek.users.services.AdminService;
import com.ciemiorek.users.services.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/download/users.xlsx")
    public ResponseEntity<InputStreamResource> excelUsersRaport () throws IOException {

        return adminService.excelUserRaport();
    }

}
