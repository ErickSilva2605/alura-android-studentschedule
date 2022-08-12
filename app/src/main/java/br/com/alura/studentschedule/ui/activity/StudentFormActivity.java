package br.com.alura.studentschedule.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.studentschedule.R;
import br.com.alura.studentschedule.dao.StudentDAO;
import br.com.alura.studentschedule.model.Student;

public class StudentFormActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "New Student";
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPhone;
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_form);
        setTitle(APPBAR_TITLE);

        initFields();
        configureButtonSave();
    }

    private void initFields() {
        edtName = findViewById(R.id.activity_student_form_edt_name);
        edtEmail = findViewById(R.id.activity_student_form_edt_email);
        edtPhone = findViewById(R.id.activity_student_form_edt_phone);
    }

    private void configureButtonSave() {
        Button btnSave = findViewById(R.id.activity_student_form_btn_save);
        btnSave.setOnClickListener(view -> {
            saveStudent(getStudent());
        });
    }

    @NonNull
    private Student getStudent() {
        return new Student(
                edtName.getText().toString(),
                edtPhone.getText().toString(),
                edtEmail.getText().toString()
        );
    }

    private void saveStudent(Student student) {
        dao.save(student);
        finish();
    }
}