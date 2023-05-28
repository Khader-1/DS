package views;

import javax.swing.*;

import actions.DeleteStudentAction;
import actions.StudentCreatedAction;
import models.Student;
import repo.StudentsRepo;
import java.awt.*;
import java.awt.event.*;

public class HomeView {

    StudentsRepo repo;

    public HomeView(StudentsRepo repo) {
        this.repo = repo;
    }

    public void show() {
        // Create the window
        JFrame frame = new JFrame("DATA Structure Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with center alignment
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        // Create a label with the "DATA Structure Project" message
        JLabel label = new JLabel("DATA Structure Project");
        label.setFont(new Font("Arial", Font.BOLD, 24));

        // Create a label with the "By: Khader M Khudair" message
        JLabel authorLabel = new JLabel("By: Khader M Khudair");
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Create GridBagConstraints to center the labels
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;

        // Add labels to the panel
        panel.add(label, gbc);
        gbc.gridy = 1;
        panel.add(authorLabel, gbc);

        // Add the panel to the frame
        frame.getContentPane().add(panel);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the "Add" menu and sub-menu items
        JMenu addMenu = new JMenu("Add");
        JMenuItem addFirstMenuItem = new JMenuItem("Add First");
        JMenuItem addAfterSecondMenuItem = new JMenuItem("After Second");
        JMenuItem addLastMenuItem = new JMenuItem("Add Last");
        addMenu.add(addFirstMenuItem);
        addMenu.add(addAfterSecondMenuItem);
        addMenu.add(addLastMenuItem);

        // Create the "Delete" menu and sub-menu items
        JMenu deleteMenu = new JMenu("Delete");
        JMenuItem deleteAtFirstMenuItem = new JMenuItem("At First");
        deleteAtFirstMenuItem.setEnabled(false);
        JMenuItem deleteAfterSecondMenuItem = new JMenuItem("After Second");
        deleteAfterSecondMenuItem.setEnabled(false);
        JMenuItem deleteAtLastMenuItem = new JMenuItem("At Last");
        deleteAtLastMenuItem.setEnabled(false);
        deleteMenu.add(deleteAtFirstMenuItem);
        deleteMenu.add(deleteAfterSecondMenuItem);
        deleteMenu.add(deleteAtLastMenuItem);

        repo.addStudentCreatedAction(new StudentCreatedAction() {
            @Override
            public void onStudentCreated(Student student) {
                deleteAtFirstMenuItem.setEnabled(true);
                deleteAfterSecondMenuItem.setEnabled(true);
                deleteAtLastMenuItem.setEnabled(true);
            }
        });

        repo.addDeleteStudentAction(new DeleteStudentAction() {
            @Override
            public void onDelete() {
                deleteAtFirstMenuItem.setEnabled(!repo.isEmpty());
                deleteAfterSecondMenuItem.setEnabled(!repo.isEmpty());
                deleteAtLastMenuItem.setEnabled(!repo.isEmpty());
            }
        });

        // Create the "Print" menu and sub-menu items
        JMenu printMenu = new JMenu("Print");
        JMenuItem printSortedListMenuItem = new JMenuItem("Print Sorted List");
        JMenuItem printUnsortedListMenuItem = new JMenuItem("Print Unsorted List");
        JMenuItem printInorderMenuItem = new JMenuItem("Print Inorder");
        JMenuItem printPreorderMenuItem = new JMenuItem("Print Preorder");
        JMenuItem printPostorderMenuItem = new JMenuItem("Print Postorder");
        printMenu.add(printSortedListMenuItem);
        printMenu.add(printUnsortedListMenuItem);
        printMenu.add(printInorderMenuItem);
        printMenu.add(printPreorderMenuItem);
        printMenu.add(printPostorderMenuItem);

        // Create the "Exit" menu item
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        // Add action listeners to the menu items
        addFirstMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentForm.show(new StudentCreatedAction() {
                    @Override
                    public void onStudentCreated(Student student) {
                        repo.addFirst(student);
                    }
                });
            }
        });

        addAfterSecondMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentForm.show(new StudentCreatedAction() {
                    @Override
                    public void onStudentCreated(Student student) {
                        repo.addSecond(student);
                    }
                });
            }
        });

        addLastMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentForm.show(new StudentCreatedAction() {
                    @Override
                    public void onStudentCreated(Student student) {
                        repo.addLast(student);
                    }
                });
            }
        });

        deleteAtFirstMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeletePopup.show(new DeleteStudentAction() {

                    @Override
                    public void onDelete() {
                        repo.removeFirst();
                    }

                });
            }
        });

        deleteAfterSecondMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeletePopup.show(new DeleteStudentAction() {
                    @Override
                    public void onDelete() {
                        repo.removeSecond();
                    }
                });
            }
        });

        deleteAtLastMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeletePopup.show(new DeleteStudentAction() {
                    @Override
                    public void onDelete() {
                        repo.removeLast();
                    }
                });
            }
        });

        printSortedListMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentTableOptionPane.show(repo.sorted());
            }
        });

        printUnsortedListMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentTableOptionPane.show(repo.unSorted());
            }
        });

        printInorderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentTableOptionPane.show(repo.inOrderList());
            }
        });

        printPreorderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentTableOptionPane.show(repo.preOrderList());
            }
        });

        printPostorderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentTableOptionPane.show(repo.postOrderList());
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Exit action logic here
                System.exit(0);
            }
        });

        // Add menus to the menu bar
        menuBar.add(addMenu);
        menuBar.add(deleteMenu);
        menuBar.add(printMenu);
        menuBar.add(exitMenuItem);

        // Set the menu bar to the frame
        frame.setJMenuBar(menuBar);

        // Set the frame to be centered on the screen
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 600);
        // center the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
