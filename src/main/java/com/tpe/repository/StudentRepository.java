package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.service.IStudentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Obje üretsin
public class StudentRepository implements IStudentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    // 1-B: Tablodan tüm satırları getirme
    @Override
    public List<Student> findAll() {
        session = sessionFactory.openSession();
        List<Student> studentList = session.createQuery("FROM Student", Student.class).getResultList();
        session.close();
        return studentList;
    }

    // 2-D
    @Override
    public void saveOrUpdate(Student student) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(student);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Student student) {

    }

    // 3-A
    @Override
    public Optional<Student> findById(Long id) {
        session = sessionFactory.openSession();
        Student student = session.get(Student.class, id);
        Optional<Student> optional = Optional.ofNullable(student); // Attention!!!
        session.close();
        return optional;
    }
}
