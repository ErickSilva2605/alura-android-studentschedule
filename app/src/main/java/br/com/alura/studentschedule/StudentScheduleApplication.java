package br.com.alura.studentschedule;

import android.app.Application;

import br.com.alura.studentschedule.dao.StudentDAO;
import br.com.alura.studentschedule.model.Student;

public class StudentScheduleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        createTest();
    }

    private void createTest() {
        StudentDAO dao = new StudentDAO();
        dao.save(new Student("Erick Augusto","123456", "erick.augusto@email.com" ));
        dao.save(new Student("Erick Augusto da Silva","654321", "erick.augusto.silva@email.com" ));
    }
}
