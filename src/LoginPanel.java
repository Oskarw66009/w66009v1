import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usernameField; // pole tekstowe do wprowadzania nazwy użytkownika
    private JPasswordField passwordField; // pole tekstowe do wprowadzania hasła użytkownika

    public LoginPanel() {
        setLayout(new GridLayout(3, 2)); // ustawienie siatki 3x2 dla elementów panelu

        JLabel usernameLabel = new JLabel("Nazwa Użytkownika:"); // etykieta dla pola tekstowego nazwy użytkownika
        add(usernameLabel);

        usernameField = new JTextField(); // utworzenie pola tekstowego nazwy użytkownika
        add(usernameField);

        JLabel passwordLabel = new JLabel("Hasło:"); // etykieta dla pola tekstowego hasła użytkownika
        add(passwordLabel);

        passwordField = new JPasswordField(); // utworzenie pola tekstowego hasła użytkownika
        add(passwordField);

        JButton loginButton = new JButton("Zaloguj"); // przycisk logowania
        add(loginButton);

        loginButton.addActionListener(new ActionListener() { // dodanie reakcji na kliknięcie przycisku logowania
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText(); // pobranie nazwy użytkownika wprowadzonej w polu tekstowym
                String password = new String(passwordField.getPassword()); // pobranie hasła użytkownika wprowadzonego w polu tekstowym

                if (loginAsCreator(username, password)) { // jeśli nazwa użytkownika i hasło zgadzają się z twórcą, zaloguj jako twórca
                    Window window = SwingUtilities.getWindowAncestor(LoginPanel.this);
                    window.dispose(); // usuwanie okienka logowania
                    loginAsCreator();
                } else if (loginAsUser(username, password)) { // jeśli nazwa użytkownika i hasło zgadzają się z użytkownikiem, zaloguj jako użytkownik
                    Window window = SwingUtilities.getWindowAncestor(LoginPanel.this);
                    window.dispose(); // usuwanie okienka logowania
                    loginAsUser();
                } else { // w przeciwnym razie, wyświetl komunikat o błędnych danych logowania
                    JOptionPane.showMessageDialog(LoginPanel.this, "Nieprawidłowa nazwa lub hasło", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void loginAsCreator() { // logowanie jako twórca
        LoadingBar loadingBar = new LoadingBar(5); // utworzenie paska postępu
        loadingBar.hideProgressBar(); // ukrycie paska postępu
        loadingBar.showProgressBar(); // wyświetlenie paska postępu
        CreatorPanel creatorPanel = new CreatorPanel(); // utworzenie panelu twórcy
        creatorPanel.setVisible(true); // ustawienie panelu twórcy jako widoczny

    }

    private void loginAsUser() { // logowanie jako użytkownik
        LoadingBar loadingBar = new LoadingBar(5); // utworzenie paska postępu
        loadingBar.hideProgressBar(); // ukrycie paska postępu
        UserPanel userPanel = new UserPanel(); // utworzenie panelu użytkownika
        loadingBar.showProgressBar(); // wyświetlenie paska postępu
        userPanel.setVisible(true); // ustawienie panelu użytkownika jako widoczny

    }

    public boolean loginAsCreator(String username, String password) {
        //Sprawdź nazwę użytkownika i hasło z poświadczeniami twórcy w bazie danych
        return username.equals("Creator") && password.equals("creator123");
    }

    public boolean loginAsUser(String username, String password) {
        //Sprawdź nazwę użytkownika i hasło z poświadczeniami użytkownika w bazie danych
        return username.equals("user") && password.equals("user123");
    }
}