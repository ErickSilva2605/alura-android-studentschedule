package br.com.alura.studentschedule.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.studentschedule.model.Student;

public class StudentDAO {

    private static int countId = 1;
    private final static List<Student> students = new ArrayList<>();

    public void save(Student student) {
        student.setId(countId);
        students.add(student);
        updateCountId();
    }

    private void updateCountId() {
        countId++;
    }

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }

    public void edit(Student student) {
        Student selectedStudent = findStudentById(student);
        if (selectedStudent != null) {
            int position = students.indexOf(selectedStudent);
            students.set(position, student);
        }
    }

    public void remove(Student student) {
        Student selectedStudent = findStudentById(student);
        if (selectedStudent != null)
            students.remove(student);
    }

    @Nullable
    private Student findStudentById(Student student) {
        for (Student s :
                students) {
            if (s.getId() == student.getId())
                return s;
        }
        return null;
    }
}
