package br.com.alura.studentschedule.ui.activity;

import static br.com.alura.studentschedule.ui.activity.ConstantsActivities.KEY_SELECTED_STUDENT;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.studentschedule.R;
import br.com.alura.studentschedule.model.Student;
import br.com.alura.studentschedule.ui.ListStudentView;

public class MainActivity extends AppCompatActivity {

    private static final String APPBAR_TITLE = "List of Students";
    private final ListStudentView listStudentView =  new ListStudentView(this);

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
        if (itemId == R.id.activity_main_menu_remove)
            listStudentView.confirmRemoval(item);
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
        listStudentView.updateStudentList();
    }

    private void ConfigureStudentList() {
        ListView studentsListView = findViewById(R.id.activity_main_student_list);
        listStudentView.configureAdapter(studentsListView);
        configureItemClickListener(studentsListView);
        registerForContextMenu(studentsListView);
    }

    private void configureItemClickListener(ListView studentsListView) {
        studentsListView.setOnItemClickListener((adapterView, view, position, id) -> {
            Student selectedStudent = (Student) adapterView.getItemAtPosition(position);
            openStudentFormActivityToEdit(selectedStudent);
        });
    }

    private void openStudentFormActivityToEdit(Student student) {
        startActivity(new Intent(this, StudentFormActivity.class)
                .putExtra(KEY_SELECTED_STUDENT, student));
    }
}
