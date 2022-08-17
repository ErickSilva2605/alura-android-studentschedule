package br.com.alura.studentschedule.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.studentschedule.R;
import br.com.alura.studentschedule.model.Student;

public class ListStudentAdapter extends BaseAdapter {
    private final List<Student> students = new ArrayList<>();
    private final Context context;

    public ListStudentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewInflated = LayoutInflater
                .from(context)
                .inflate(R.layout.item_list_student, viewGroup, false);

        Student selectedStudent = students.get(position);
        TextView studentName = viewInflated.findViewById(R.id.item_list_student_name);
        TextView studentPhone = viewInflated.findViewById(R.id.item_list_student_phone);
        studentName.setText(selectedStudent.getName());
        studentPhone.setText(selectedStudent.getPhone());

        return viewInflated;
    }

    public void clear() {
        students.clear();
    }

    public void addAll(List<Student> students) {
        this.students.addAll(students);
    }

    public void remove(Student student) {
        students.remove(student);
    }
}
