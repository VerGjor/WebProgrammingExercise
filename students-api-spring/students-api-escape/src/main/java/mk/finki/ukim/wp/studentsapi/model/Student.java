package mk.finki.ukim.wp.studentsapi.model;

import javax.persistence.*;


@Entity
public class Student {

    @Id
    public String index;

    public String name;
    public String lastName;

    @ManyToOne
    public StudyProgram studyProgram;
}
