import javax.swing.*;

import repo.StudentsRepo;
import views.HomeView;

public class App {

    public static void main(String[] args) {
        // Run the GUI code on the Event Dispatch Thread (EDT)
        StudentsRepo repo = new StudentsRepo();
        SwingUtilities.invokeLater(() -> {
            HomeView homeView = new HomeView(repo);
            homeView.show();
        });
    }
}
