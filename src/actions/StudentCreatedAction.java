package actions;

import models.Student;

public interface StudentCreatedAction {
    void onStudentCreated(Student student);
}
