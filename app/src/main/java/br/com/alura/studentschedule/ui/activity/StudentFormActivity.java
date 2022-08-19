package br.com.alura.studentschedule.ui.activity;

import static br.com.alura.studentschedule.ui.activity.ConstantsActivities.KEY_SELECTED_STUDENT;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.studentschedule.R;
import br.com.alura.studentschedule.dao.StudentDAO;
import br.com.alura.studentschedule.model.Student;

public class StudentFormActivity extends AppCompatActivity {

    private static final String APPBAR_TITLE_NEW_STUDENT = "New Student";
    private static final String APPBAR_TITLE_EDIT_STUDENT = "Edit Student";
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPhone;
    private Student selectedStudent = null;
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        initFields();
        configureIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_student_form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_student_form_menu_save)
        {
            getStudent();
            finishForm();
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureIntent() {
        Intent intentData = getIntent();

        if (intentData.hasExtra(KEY_SELECTED_STUDENT)) {
            setTitle(APPBAR_TITLE_EDIT_STUDENT);
            selectedStudent = (Student) intentData.getSerializableExtra(KEY_SELECTED_STUDENT);
            configureFields();
        } else {
            setTitle(APPBAR_TITLE_NEW_STUDENT);
            selectedStudent = new Student();
        }
    }

    private void initFields() {
        edtName = findViewById(R.id.activity_student_form_edt_name);
        edtEmail = findViewById(R.id.activity_student_form_edt_email);
        edtPhone = findViewById(R.id.activity_student_form_edt_phone);
    }

    private void configureFields() {
        if (selectedStudent != null) {
            edtName.setText(selectedStudent.getName());
            edtPhone.setText(selectedStudent.getPhone());
            edtEmail.setText(selectedStudent.getEmail());
        }
    }

    private void getStudent() {
        selectedStudent.setName(edtName.getText().toString());
        selectedStudent.setPhone(edtPhone.getText().toString());
        selectedStudent.setEmail(edtEmail.getText().toString());
    }

    private void finishForm() {
        if (selectedStudent.hasValidId())
            editStudent();
        else
            saveStudent();

        finish();
    }

    private void saveStudent() {
        dao.save(selectedStudent);
    }

    private void editStudent() {
        dao.edit(selectedStudent);
    }
}