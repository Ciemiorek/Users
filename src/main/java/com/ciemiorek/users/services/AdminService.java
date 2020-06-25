package com.ciemiorek.users.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface AdminService {

    ResponseEntity<InputStreamResource> excelUserRaport() throws IOException;
}
