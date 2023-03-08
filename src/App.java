import javax.swing.*;
import java.awt.BorderLayout;


public class App {
    public static void main(String[] args) {
        // Sprawdzenie połączenia z bazą danych
        if (!DatabaseConnectionChecker.checkConnection()) {
            JOptionPane.showMessageDialog(null, "Nie udało się nawiązać połączenia z bazą danych. Sprawdź ustawienia bazy danych i spróbuj ponownie", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tworzenie nowego okna JFrame z tytułem "Login"
        JFrame frame = new JFrame("Login");
        // Ustawienie działania przycisku zamknięcia okna na zakończenie działania programu
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tworzenie panelu menu
        MenuPanel menuPanel = new MenuPanel();
        // Dodanie panelu menu do okna
        frame.add(menuPanel, BorderLayout.NORTH);

        // Tworzenie panelu logowania
        LoginPanel loginPanel = new LoginPanel();
        // Dodanie panelu logowania do okna
        frame.add(loginPanel);

        // Dopasowanie rozmiaru okna do zawartości
        frame.pack();
        // Ustawienie położenia okna na środku ekranu
        frame.setLocationRelativeTo(null);
        // Wyświetlenie okna
        frame.setVisible(true);

    }
}

