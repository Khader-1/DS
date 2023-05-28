package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import models.Student;

public class StudentTableOptionPane {

    public static void show(List<Student> students) {
        // Create a table model with the column names and 0 rows initially
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[]{"ID", "First Name", "Last Name", "Mid Exam", "Final Exam", "Total", "Estimation"}, 0);

        // Add the student data to the table model
        for (Student student : students) {
            Object[] rowData = {
                    student.id,
                    student.getFirstName(),
                    student.getLastName(),
                    student.getMidExam(),
                    student.getFinalExam(),
                    student.getTotal(),
                    student.getEstimation()
            };
            tableModel.addRow(rowData);
        }

        // Create a table with the populated table model
        JTable table = new JTable(tableModel);

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Show the option pane with the table
        JOptionPane.showMessageDialog(null, scrollPane, "Student List", JOptionPane.INFORMATION_MESSAGE);
    }
}
