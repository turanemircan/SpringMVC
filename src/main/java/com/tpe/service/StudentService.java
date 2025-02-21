package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository repository;

    // 1-C
    @Override
    public List<Student> listAllStudents() {
        return repository.findAll();
    }

    // 2-C
    @Override
    public void addOrUpdateStudent(Student student) {
        repository.saveOrUpdate(student);
    }

    // 3-B
    @Override
    public Student findStudentById(Long id) {
        Student student = repository.findById(id).
                orElseThrow(() -> new StudentNotFoundException("Student not found by ID: " + id)); // supplier:get => içerisinde implemente edilmesi gereken sadece 1 method var demektir.
        // Bu yüzden obje oluşturmaya gerek yok lambda expression kullanarak get methodunun bodysinde yapmak istenilen işlem verebiliriz.

        //supplier interfaceini implemente eden bir class oluşturup , objesinin
        //get metodunu kullanmak yerine kısaca lambda exp ile get metodunu override ediyoruz.

        // findById metodunun geriye döndürdüğü optional içinde
        // student varsa student değişkenine atar.
        // optional objesi boşsa orElseThrow custom exception fırlatılabilir.
        // 2. seçenek get():NoSuchElementException() kullanılabilir. Kendi Exceptionı fırlatır.
        return student;
    }

    // 4-B
    @Override
    public void deleteStudent(Long id) {
        // id'si verilen öğrenciyi bulalım
        Student student = findStudentById(id);
        repository.delete(student);
    }
}
