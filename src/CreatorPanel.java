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

    public CreatorPanel() {
        // Inicjalizacja okna Creator Panel
        this.setTitle("Creator Panel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        // Tworzenie formularza
        JPanel formPanel = new JPanel(new GridLayout(9, 2));
        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionArea = new JTextArea();
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        JLabel categoryLabel = new JLabel("Category:");
        categoryCombo = new JComboBox<>(new String[]{"Console", "PC", "Multiplayer"});
        JLabel genreLabel = new JLabel("Genre:");
        genreCombo = new JComboBox<>(new String[]{"Action", "Adventure", "Simulation"});
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        JLabel discountLabel = new JLabel("Discount:");
        discountField = new JTextField();
        JLabel iconLabel = new JLabel("Icon:");
        JLabel iconField = new JLabel("To be implemented");
        addButton = new JButton("Add game");
        addButton.addActionListener(e -> addGame());
        deleteButton = new JButton("Delete game");
        deleteButton.addActionListener(e -> deleteGame());

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

            JOptionPane.showMessageDialog(this, "Game added successfully");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding game: " + ex.getMessage());
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