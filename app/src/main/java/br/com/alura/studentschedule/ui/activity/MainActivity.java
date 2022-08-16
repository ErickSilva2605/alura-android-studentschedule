package br.com.alura.studentschedule.ui.activity;

import static br.com.alura.studentschedule.ui.activity.ConstantsActivities.KEY_SELECTED_STUDENT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.alura.studentschedule.R;
import br.com.alura.studentschedule.dao.StudentDAO;
import br.com.alura.studentschedule.model.Student;

public class MainActivity extends AppCompatActivity {

    private static final String APPBAR_TITLE = "List of Students";
    private final StudentDAO dao = new StudentDAO();
    private ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle(APPBAR_TITLE);

        configureFabAddStudent();
    }

    private void configureFabAddStudent() {
        FloatingActionButton fabAddStudent = findViewById(R.id.activity_main_add_students);
        fabAddStudent.setOnClickListener(view -> openStudentFormActivityToCreate());
    }

    private void openStudentFormActivityToCreate() {
        startActivity(new Intent(this, StudentFormActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConfigureStudentList();
    }

    private void ConfigureStudentList() {
        ListView studentsListView = findViewById(R.id.activity_main_student_list);
        final List<Student> studentList = dao.getAll();
        configureAdapter(studentsListView, studentList);
        configureItemClickListener(studentsListView);
        configureItemLongClickListener(studentsListView);
    }

    private void configureAdapter(ListView studentsListView, List<Student> studentList) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                studentList);
        studentsListView.setAdapter(adapter);
    }

    private void configureItemClickListener(ListView studentsListView) {
        studentsListView.setOnItemClickListener((adapterView, view, position, id) -> {
            Student selectedStudent = (Student) adapterView.getItemAtPosition(position);
            openStudentFormActivityToEdit(selectedStudent);
        });
    }

    private void configureItemLongClickListener(ListView studentsListView) {
        studentsListView.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Student selectedStudent = (Student) adapterView.getItemAtPosition(position);
            dao.remove(selectedStudent);
            adapter.remove(selectedStudent);
            return true;
        });
    }

    private void openStudentFormActivityToEdit(Student student) {
        startActivity(new Intent(this, StudentFormActivity.class)
                .putExtra(KEY_SELECTED_STUDENT, student));
    }
}
