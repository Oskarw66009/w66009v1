import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserPanel extends JFrame {
    public UserPanel() {
        this.setTitle("User Panel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable(getGameData(), new String[]{"Tytuł", "Opis", "Kategoria", "Cena", "Zniżka"});
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton closeButton = new JButton("Zamknij panel");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(closeButton, BorderLayout.SOUTH);

        this.add(panel);
    }

    private Object[][] getGameData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:jtds:sqlserver://DESKTOP-LT1PDRG:1433/Steamv2;instance=SQLEXPRESS;sqlDialect=22", "user", "user123");
            Statement statement = connection.createStatement();

            // wykonanie zapytania SQL
            ResultSet resultSet = statement.executeQuery("SELECT * FROM games");

            // pobranie metadanych
            ResultSetMetaData metaData = resultSet.getMetaData();

            // wyświetlenie nazw kolumn
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                System.out.println(columnName);
            }

            // pobranie danych z ResultSet i zapisanie ich do tablicy dwuwymiarowej
            Object[][] data = new Object[100][5]; // przykładowy rozmiar tablicy
            int row = 0;
            while (resultSet.next()) {
                data[row][0] = resultSet.getString("title");
                data[row][1] = resultSet.getString("description");
                data[row][2] = resultSet.getString("category");
                data[row][3] = resultSet.getDouble("price");
                data[row][4] = resultSet.getDouble("discount");
                row++;
            }

            // zamknięcie połączenia i zwolnienie zasobów
            resultSet.close();
            statement.close();
            connection.close();

            return data;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd podczas odbierania danych", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return new Object[0][5];
        }
    }
}