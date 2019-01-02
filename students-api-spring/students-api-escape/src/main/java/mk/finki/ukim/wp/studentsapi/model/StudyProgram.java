package mk.finki.ukim.wp.studentsapi.model;

import javax.persistence.*;

@Entity
public class StudyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String name;
}
