package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.repository.IStudentRepository;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
// Bu class'ın objesi Spring tarafından üretilecek yani bir bean oluşturulacak. // requestler bu classta karşılanacak ve ilgili metodlarla maplenecek
@RequestMapping("/students") // http:localhost:8080/SpringMvc/students/....
// class:tüm metodlar için geçerli olur
// method:sadece ilgili metodu requestle mapler
public class StudentController {

    @Autowired
    private IStudentService service;
    // NOT:controllerda metodlar geriye mav veya String data tipi döndürebilir.

    //http:localhost:8080/SpringMvc/students/hi + GET -- Okuma
    //http:localhost:8080/SpringMvc/students/hi + POST -- Kayıt
    // @RequestMapping("/students")
    @GetMapping("/hi")
    public ModelAndView sayHi() {
        // response'u hazırlayacak
        ModelAndView mav = new ModelAndView("hi");
        mav.addObject("message", "Hi,");
        mav.addObject("messagebody", "I'm a Student Management System");
        mav.setViewName("hi");
        return mav;
    }

    // view resolver : /WEB-INF/views/hi.jsp dosyasını bulur ve mav içindeki modalı
    // dosyaya bind eder ve clienta gönderir.

    // 1-tüm öğrencileri listeleme:
    // http://localhost:8080/SpringMvc/students + GET
    @GetMapping
    public ModelAndView getStudents() {
        List<Student> allStudent = service.listAllStudents();
        ModelAndView mav = new ModelAndView();
        mav.addObject("studentList", allStudent);
        mav.setViewName("students");
        return mav;
    }

    // 2-A: Öğrenciyi kaydetme
    // request: http://localhost:8080/SpringMvc/students/new + GET
    // response: form göstermek
    @GetMapping("/new")
    public String sendForm(@ModelAttribute("student") Student student) { // Boş sayfa göndereceğimiz için String seçtik
        return "studentForm";
    }
    // ModelAttribute anatasyonu view katmanı ile controller arasında
    // modelın transferini sağlar.

    //işlem sonunda: studentın firstname,lastname ve grade değerleri set edilmiş halde
    //controller classında yer alır

    // 2-B: Formun içindeki öğrenciyi kaydetme
    // request:http://localhost:8080/SpringMvc/students/saveStudent + POST
    // response:öğrenciyi tabloya ekleyeceğiz ve liste göndereceğiz

    @PostMapping("/saveStudent")
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) { // Dataları alırken bir hata alırsak BindingResult içerisine alınabiliyor.
        // Valid: Kullanmış olduğumuz anatasyonlara göre doğrulama gerçekleştirir.
        // Eğer hata varsa BindingResult içerisindeki bu hataları kontrol et, hata varsa yeni bir sayfaya gitme.
        // studentForm içerisinde kal.
        if (bindingResult.hasErrors()) {
            return "studentForm";
        }
        service.addOrUpdateStudent(student);
        // redirect: Gelen isteği yeni bir isteğe yönlendirir.
        return "redirect:/students"; // http://localhost:8080/SpringMvc/students + GET
    }

    // 3- Öğrenciyi güncelleme
    // request:http://localhost:8080/SpringMvc/students/update?id=3 + GET
    // response:update için id si verilen öğrencinin bilgileri ile formu gösterme
    // id'si verilen öğrenciyi bulmamız gerekir...
    @GetMapping("/update")
    public ModelAndView sendFormUpdate(@RequestParam("id") Long identity) {

        Student foundStudent = service.findStudentById(identity);

        ModelAndView mav = new ModelAndView();
        mav.addObject("student", foundStudent);
        mav.setViewName("studentForm");
        return mav;
    }
}
