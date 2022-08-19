package br.com.alura.studentschedule.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import br.com.alura.studentschedule.dao.StudentDAO;
import br.com.alura.studentschedule.model.Student;
import br.com.alura.studentschedule.ui.adapter.ListStudentAdapter;

public class ListStudentView {

    private static final String ALERT_TITLE_REMOVING_STUDENT = "Removing Student";
    private static final String ALERT_MESSAGE_REMOVING_STUDENT = "Are you sure you want to remove the student?";
    private static final String ALERT_POSITIVE_BUTTON_REMOVING_STUDENT = "Yes";
    private static final String ALERT_NEGATIVE_BUTTON_REMOVING_STUDENT = "No";
    private final Context context;
    private final ListStudentAdapter adapter;
    private final StudentDAO dao;

    public ListStudentView(@NonNull Context context) {
        this.context = context;
        this.adapter = new ListStudentAdapter(this.context);
        this.dao = new StudentDAO();
    }


    public void updateStudentList() {
        adapter.update(dao.getAll());
    }

    public void confirmRemoval(@NonNull final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle(ALERT_TITLE_REMOVING_STUDENT)
                .setMessage(ALERT_MESSAGE_REMOVING_STUDENT)
                .setPositiveButton(ALERT_POSITIVE_BUTTON_REMOVING_STUDENT, (dialogInterface, i) -> positiveRemoveStudent(item))
                .setNegativeButton(ALERT_NEGATIVE_BUTTON_REMOVING_STUDENT, null)
                .show();
    }

    private void positiveRemoveStudent(@NonNull final MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Student selectedStudent = adapter.getItem(menuInfo.position);
        removeStudent(selectedStudent);
    }

    private void removeStudent(Student student) {
        dao.remove(student);
        adapter.remove(student);
    }

    public void configureAdapter(@NonNull ListView studentsListView) {
        studentsListView.setAdapter(adapter);
    }
}
