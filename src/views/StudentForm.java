package views;

import javax.swing.*;
import actions.StudentCreatedAction;
import models.Student;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentForm {
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JComboBox<Integer> midExamComboBox;
    private JComboBox<Integer> finalExamComboBox;
    private JTextField totalField;
    private JTextField estimationField;

    public static void show(StudentCreatedAction studentCreatedAction) {
        new StudentForm(studentCreatedAction);
    }

    public StudentForm(StudentCreatedAction studentCreatedAction) {
        // Input dialog for student details
        idField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        midExamComboBox = createScoreComboBox(0, 30);
        finalExamComboBox = createScoreComboBox(0, 60);
        totalField = new JTextField();
        estimationField = new JTextField();

        // Disable total and estimation fields
        totalField.setEnabled(false);
        estimationField.setEnabled(false);

        // Update total and estimation when other fields change
        ActionListener updateListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTotalAndEstimation();
            }
        };
        idField.addActionListener(updateListener);
        firstNameField.addActionListener(updateListener);
        lastNameField.addActionListener(updateListener);
        midExamComboBox.addActionListener(updateListener);
        finalExamComboBox.addActionListener(updateListener);

        Object[] fields = {
                "ID:", idField,
                "First Name:", firstNameField,
                "Last Name:", lastNameField,
                "Midterm Exam Score (0-30):", midExamComboBox,
                "Final Exam Score (0-60):", finalExamComboBox,
                "Total Score:", totalField,
                "Estimation:", estimationField
        };

        int option = JOptionPane.showOptionDialog(null, fields, "Student Information", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, new Object[] { "OK", "Ignore", "More" }, null);
        if (option == JOptionPane.OK_OPTION || option == 2) {
            // Retrieve the values entered by the user
            int id = 0;
            try {
                id = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid ID! Please enter a valid integer.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            double midExam = midExamComboBox.getItemAt(midExamComboBox.getSelectedIndex());
            double finalExam = finalExamComboBox.getItemAt(finalExamComboBox.getSelectedIndex());

            // Validate inputs
            if (!isValidInput(id, firstName, lastName, midExam, finalExam)) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid information.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create a new Student object
            Student student = new Student(id, firstName, lastName, midExam, finalExam);

            // Update total and estimation fields
            totalField.setText(String.valueOf(student.total));
            estimationField.setText(student.estimation);

            // Invoke the callback method
            studentCreatedAction.onStudentCreated(student);

            // Clear form fields
            clearFormFields();
            if (option == 2) {
                show(studentCreatedAction);
            }
        } else if (option == 1) {
            clearFormFields();
        }
    }

    private JComboBox<Integer> createScoreComboBox(int start, int end) {
        Integer[] scores = new Integer[end - start + 1];
        for (int i = start; i <= end; i++) {
            scores[i - start] = i;
        }
        return new JComboBox<>(scores);
    }

    private void updateTotalAndEstimation() {
        double midExam = midExamComboBox.getItemAt(midExamComboBox.getSelectedIndex());
        double finalExam = finalExamComboBox.getItemAt(finalExamComboBox.getSelectedIndex());
        double total = midExam + finalExam;
        String estimation = Student.totalToEstimation(total);

        totalField.setText(String.valueOf(total));
        estimationField.setText(estimation);
    }

    private boolean isValidInput(int id, String firstName, String lastName, double midExam, double finalExam) {
        return id > 0 && !firstName.isEmpty() && !lastName.isEmpty() && midExam >= 0 && midExam <= 30 && finalExam >= 0
                && finalExam <= 60;
    }

    private void clearFormFields() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        midExamComboBox.setSelectedIndex(0);
        finalExamComboBox.setSelectedIndex(0);
        totalField.setText("");
        estimationField.setText("");
    }
}
