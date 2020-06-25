package com.ciemiorek.users.services.imp;

import com.ciemiorek.users.models.UserTest;
import com.ciemiorek.users.repository.UserRepository;
import com.ciemiorek.users.services.AdminService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class AdminServiceIMP implements AdminService {

    @Autowired
    UserRepository userRepository;


    @Override
    public ResponseEntity<InputStreamResource> excelUserRaport() throws IOException {
        List<UserTest> userTestList = userRepository.findAll();
        ByteArrayInputStream in = usersToExcel(userTestList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment; filename= User.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }


    private ByteArrayInputStream usersToExcel(List<UserTest> usersList) throws IOException {

        String [] Columns = {"Id","Name","Surname","email","number ob books"};

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();


        Sheet sheet = workbook.createSheet("Users");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        CellStyle headarCellStyle = workbook.createCellStyle();
        headarCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

       //Header
       for(int col=0;col<Columns.length;col++) {
           Cell cell = headerRow.createCell(col);
           cell.setCellValue(Columns[col]);
           cell.setCellStyle(headarCellStyle);
       }

       int rowIdx =1;

       for(UserTest userTest : usersList) {
           Row row = sheet.createRow(rowIdx++);

           row.createCell(0).setCellValue(userTest.getId());
           row.createCell(1).setCellValue(userTest.getName());
           row.createCell(2).setCellValue(userTest.getSurname());
           row.createCell(3).setCellValue(userTest.getEmail());
           row.createCell(4).setCellValue(userTest.getBooks().size());

       }

       workbook.write(out);


        return new ByteArrayInputStream(out.toByteArray());
        }
    }



