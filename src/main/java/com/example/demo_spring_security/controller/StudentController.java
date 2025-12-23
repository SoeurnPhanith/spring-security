package com.example.demo_spring_security.controller;
import com.example.demo_spring_security.entity.Student;
import com.example.demo_spring_security.utils.BaseEndPoint;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/api/students")
public class StudentController {

    List<Student> studentList = new ArrayList<>(
            List.of(
                    new Student(1, "Meng Heng", "Male"),
                    new Student(2, "Nuth ass", "male")
            )
    );

    @GetMapping
    public List<Student> getStudentList(){
        return studentList;
    }

    //get CSRF token
    @GetMapping ("csrf-token")
    public CsrfToken getCsrfToken (HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping
    public ResponseEntity<?> addStudents(
            @RequestBody Student student
    ){
        //check if it exists
        boolean existsStudent = studentList.stream().anyMatch(
                exist -> exist.getId().equals(student.getId())
        );
        if(existsStudent){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    "This student already exists"
            );
        }

        studentList.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    //way1
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Integer id
    ){
        //get all student in list
        for(int i=0; i<studentList.size(); i++){
            Student findStudent = studentList.get(i);
            if(findStudent.getId().equals(id)){
                return ResponseEntity.ok().body(findStudent);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //way2
    @GetMapping("/id/{id}")
    public ResponseEntity<Student> findStudetById(
            @PathVariable Integer id
    ){
        //use for each
        for(Student stu : studentList){
            if(stu.getId().equals(id)){
                return ResponseEntity.ok().body(stu);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(
            @RequestBody Student student,
            @PathVariable Integer id
    ){
        //get all data
        for(int i=0; i<studentList.size(); i++){
            Student updateStudent = studentList.get(i);
            if(updateStudent.getId().equals(id)){
                updateStudent.setId(student.getId());
                updateStudent.setName(student.getName());
                updateStudent.setGender(student.getGender());

                //save update
                studentList.set(i,updateStudent);
                return ResponseEntity.ok().body(updateStudent);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                "This student id is not found!"
        );
    }
}
