package views;

import actions.DeleteStudentAction;

import javax.swing.JOptionPane;

public class DeletePopup {
    public static void show(DeleteStudentAction deleteStudentAction) {
        // Display a dialog to confirm the deletion
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Do you confirm delete?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION
        );

        // If the deletion is confirmed, invoke the callback method
        if (confirm == JOptionPane.YES_OPTION) {
            deleteStudentAction.onDelete();
        }
    }
}
