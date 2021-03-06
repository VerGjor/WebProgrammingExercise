package mk.finki.ukim.wp.studentsapi.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudyProgramNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public StudyProgramNotFoundException(){
        System.out.println("Study program doesn't exist!");
    }
}
