package mk.finki.ukim.wp.studentsapi.web.rest;

import mk.finki.ukim.wp.studentsapi.model.*;
import mk.finki.ukim.wp.studentsapi.model.exceptions.DuplicateStudentException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.InvalidFormatException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.MissingParamException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import mk.finki.ukim.wp.studentsapi.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin({ "*", "localhost:3306" })
@RestController
@RequestMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentResource{

    private final StudentService studentService;

    @Autowired
    public StudentResource(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
        public List<Student> getStudents() {
            return studentService.getAllStudents();
        }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return getStudents();
    }

    @GetMapping("/by_study_program/{studyProgram}")
    public List<Student> getStudentByProgram(@PathVariable("studyProgram") long studyProgram) {
        return studentService.getStudentsByProgram(studyProgram);
    }


    @GetMapping("/{index}")
    public Student getStudentByIndex(@PathVariable("index") String index){
        return studentService.getStudentByIndex(index);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student, HttpServletResponse response) throws DuplicateStudentException, InvalidFormatException, MissingParamException, StudyProgramNotFoundException {
        Student s = studentService.addNewStudent(student);
        response.setHeader("Location", "/students/" + s.index);
    }

    @PatchMapping("/{index}")
    public void updateStudent(@PathVariable("index") String index, @RequestParam String name, @RequestParam String lastName,
                              @RequestParam String studyProgram) {
        studentService.updateStudent(index, name, lastName, studyProgram);
    }

    @DeleteMapping("/{index}")
    public void deleteStudent(@PathVariable String index){
        studentService.deleteStudent(index);
    }


}

