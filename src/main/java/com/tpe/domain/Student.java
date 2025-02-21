package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Please provide firstname!") // null,"","    "(blank) kabul etmez
    @Column(nullable = false)
    private String firstName;
    @NotEmpty(message = "Please provide lastname!") // null,"" kabul etmez
    @Column(nullable = false)
    private String lastName;
    @NotNull(message = "Please provide grade!") // null kabul etmez
    @Column(nullable = false)
    private Integer grade;

    // jsonformat:JSON a dönüştürürken belirli bir format kullanılmasını sağlar
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate = LocalDateTime.now(); // Objenin oluşturulduğu tarih // Objenin kaydedildiği tarihi almak istesek: prepersist

    // getter-setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

//    public void setCreateDate(LocalDateTime createDate) {
//        this.createDate = createDate;
//    }
}
