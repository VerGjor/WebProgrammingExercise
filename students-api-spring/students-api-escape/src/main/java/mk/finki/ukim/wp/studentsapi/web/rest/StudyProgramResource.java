package mk.finki.ukim.wp.studentsapi.web.rest;

import mk.finki.ukim.wp.studentsapi.model.*;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;

import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@CrossOrigin({ "*", "localhost:3306" })
@RestController
@RequestMapping(value = "/study_programs", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudyProgramResource {

    private final StudyProgramService programService;

    @Autowired
    public StudyProgramResource(StudyProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public List<StudyProgram> getPrograms() {
        return programService.getStudyPrograms();
    }

    @GetMapping("/study_programs")
    public List<StudyProgram> getAllStudyPrograms() {
        return getPrograms();
    }

    @GetMapping("/{name}")
    public StudyProgram getProgramByName(@PathVariable("name") String name){
        return programService.getProgramByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProgram(@RequestBody String program, HttpServletResponse response) {
        StudyProgram newProgram = programService.createStudyProgram(program);
        response.setHeader("Location", "/study_programs/" + newProgram.id);
    }


    @DeleteMapping("/{id}")
    public void deleteProgram(@PathVariable long id) {
        programService.deleteStudyProgram(id);
    }

}
