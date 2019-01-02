package mk.finki.ukim.wp.studentsapi.model.exceptions;

public class DuplicateStudentException extends Exception {

    private static final long serialVersionUID = 1L;
    public DuplicateStudentException(){
        System.out.println("Student already exists!");
    }
}
