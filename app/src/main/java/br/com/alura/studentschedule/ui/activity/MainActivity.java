package br.com.alura.studentschedule.ui.activity;

import static br.com.alura.studentschedule.ui.activity.ConstantsActivities.KEY_SELECTED_STUDENT;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.studentschedule.R;
import br.com.alura.studentschedule.dao.StudentDAO;
import br.com.alura.studentschedule.model.Student;
import br.com.alura.studentschedule.ui.adapter.ListStudentAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String APPBAR_TITLE = "List of Students";
    private final StudentDAO dao = new StudentDAO();
    private ListStudentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle(APPBAR_TITLE);

        configureFabAddStudent();
        ConfigureStudentList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_main_menu_remove) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Student selectedStudent = adapter.getItem(menuInfo.position);
            removeStudent(selectedStudent);
        }
        return super.onContextItemSelected(item);
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
        updateStudentList();
    }

    private void updateStudentList() {
        adapter.clear();
        adapter.addAll(dao.getAll());
    }

    private void ConfigureStudentList() {
        ListView studentsListView = findViewById(R.id.activity_main_student_list);
        configureAdapter(studentsListView);
        configureItemClickListener(studentsListView);
        registerForContextMenu(studentsListView);
    }

    private void configureAdapter(ListView studentsListView) {
        adapter = new ListStudentAdapter(this);
        studentsListView.setAdapter(adapter);
    }

    private void configureItemClickListener(ListView studentsListView) {
        studentsListView.setOnItemClickListener((adapterView, view, position, id) -> {
            Student selectedStudent = (Student) adapterView.getItemAtPosition(position);
            openStudentFormActivityToEdit(selectedStudent);
        });
    }

    private void removeStudent(Student student) {
        dao.remove(student);
        adapter.remove(student);
    }

    private void openStudentFormActivityToEdit(Student student) {
        startActivity(new Intent(this, StudentFormActivity.class)
                .putExtra(KEY_SELECTED_STUDENT, student));
    }
}
