package br.com.alura.studentschedule.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.studentschedule.model.Student;

public class StudentDAO {

    private final static List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }
}
