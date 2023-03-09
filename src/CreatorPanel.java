import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreatorPanel extends JFrame {
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JComboBox<String> categoryCombo;
    private JComboBox<String> genreCombo;
    private JTextField priceField;
    private JTextField discountField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton closeButton;

    public CreatorPanel() {
        // Inicjalizacja okna Creator Panel
        this.setTitle("Creator Panel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        // Tworzenie formularza
        JPanel formPanel = new JPanel(new GridLayout(9, 3));
        JLabel titleLabel = new JLabel("Tytuł:");
        titleField = new JTextField();

        JLabel descriptionLabel = new JLabel("Opis:");
        descriptionArea = new JTextArea();
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        JLabel categoryLabel = new JLabel("Kategoria:");
        categoryCombo = new JComboBox<>(new String[]{"Konsola", "PC", "Multiplayer"});
        JLabel genreLabel = new JLabel("Genre:");
        genreCombo = new JComboBox<>(new String[]{"Akcja", "Przygoda", "Symulacja"});
        JLabel priceLabel = new JLabel("Cena:");
        priceField = new JTextField();
        JLabel discountLabel = new JLabel("Przecena:");
        discountField = new JTextField();
        JLabel iconLabel = new JLabel("Ikona:");
        JLabel iconField = new JLabel("Soon...");
        addButton = new JButton("Dodaj grę");
        addButton.addActionListener(e -> addGame());
        deleteButton = new JButton("Usuń grę");
        deleteButton.addActionListener(e -> deleteGame());
        closeButton = new JButton("Zamknij panel");
        closeButton.addActionListener(e -> this.dispose());



        // Dodawanie elementów formularza do panelu
        formPanel.add(titleLabel);
        formPanel.add(titleField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionScrollPane);
        formPanel.add(categoryLabel);
        formPanel.add(categoryCombo);
        formPanel.add(genreLabel);
        formPanel.add(genreCombo);
        formPanel.add(priceLabel);
        formPanel.add(priceField);
        formPanel.add(discountLabel);
        formPanel.add(discountField);
        formPanel.add(iconLabel);
        formPanel.add(iconField);
        formPanel.add(addButton);
        formPanel.add(deleteButton);
        formPanel.add(new JLabel());
        formPanel.add(closeButton);

        // Wycentrowanie komponentów panelu
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Dodawanie panelu do okna
        this.add(formPanel);
    }

    private void addGame() {
        // Pobieranie danych z formularza
        String title = titleField.getText();
        String description = descriptionArea.getText();
        String category = categoryCombo.getSelectedItem().toString();
        String genre = genreCombo.getSelectedItem().toString();
        double price = Double.parseDouble(priceField.getText());
        double discount = Double.parseDouble(discountField.getText());

        try {
            // Łączenie z bazą danych i dodawanie gry
            Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://DESKTOP-LT1PDRG:1433/Steamv2;instance=SQLEXPRESS", "Creator", "creator123");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO games (title, description, category, genre, price, discount) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, category);
            stmt.setString(4, genre);
            stmt.setDouble(5, price);
            stmt.setDouble(6, discount);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Gra została pomyślnie dodana");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas dodawania gry: " + ex.getMessage());
        }
    }

    private void deleteGame() {
        String title = titleField.getText();

        try {
            // Łączenie z bazą danych i dodawanie gry
            Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://DESKTOP-LT1PDRG:1433/Steamv2;instance=SQLEXPRESS", "Creator", "creator123");
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM games WHERE title = ?");
            stmt.setString(1, title);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Gra usunięta pomyślnie");
            } else {
                JOptionPane.showMessageDialog(this, "Gra nieznaleźiona");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas usuwania gry: " + ex.getMessage());
        }
    }}