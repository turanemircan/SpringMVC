package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Bu classta restful serviceler geliştirilecek
@RequestMapping("/api") // Class veya Method seviyesinde kullanabiliriz.
public class StudentRestController {

    private final IStudentService service;

    @Autowired // Sadece 1 constructor var ise @Autowired kullanımı opsiyoneldir.
    public StudentRestController(IStudentService service) {
        this.service = service;
    }

    // 1-tüm öğrencileri listeleme:
    // http://localhost:8080/SpringMvc/api/all + GET

    @GetMapping("/all")
    public List<Student> allStudent() {
        return service.listAllStudents();
    }
}
