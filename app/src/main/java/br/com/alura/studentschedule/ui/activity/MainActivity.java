package br.com.alura.studentschedule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.studentschedule.R;
import br.com.alura.studentschedule.dao.StudentDAO;

public class MainActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "List of Students";
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle(APPBAR_TITLE);

        configureFabAddStudent();
    }

    private void configureFabAddStudent() {
        FloatingActionButton fabAddStudent = findViewById(R.id.activity_main_add_students);
        fabAddStudent.setOnClickListener(view -> {
            openStudentFormActivity();
        });
    }

    private void openStudentFormActivity() {
        startActivity(new Intent(this, StudentFormActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        ConfigureStudentList();
    }

    private void ConfigureStudentList() {
        ListView studentsListView = findViewById(R.id.activity_main_student_list);
        studentsListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.getAll()));
    }
}
