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
        View viewInflated = inflateView(viewGroup);
        Student selectedStudent = students.get(position);
        link(viewInflated, selectedStudent);
        return viewInflated;
    }

    private void link(View view, Student student) {
        TextView studentName = view.findViewById(R.id.item_list_student_name);
        TextView studentPhone = view.findViewById(R.id.item_list_student_phone);
        studentName.setText(student.getName());
        studentPhone.setText(student.getPhone());
    }

    private View inflateView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_list_student, viewGroup, false);
    }

    public void update(List<Student> students){
        this.students.clear();
        this.students.addAll(students);
        notifyDataSetChanged();
    }

    public void remove(Student student) {
        students.remove(student);
        notifyDataSetChanged();
    }
}
