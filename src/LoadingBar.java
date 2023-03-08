import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingBar {
    private JFrame frame; // okno dialogowe do wyświetlenia paska postępu
    private JProgressBar progressBar; // pasek postępu
    private JLabel label; // etykieta wyświetlająca tekst

    // konstruktor klasy LoadingBar przyjmuje czas trwania ładowania w sekundach
    public LoadingBar(int durationInSeconds) {
        frame = new JFrame("Ładowanie..."); // utworzenie nowego okna dialogowego z tytułem "Ładowanie..."
        progressBar = new JProgressBar(); // utworzenie nowego paska postępu
        label = new JLabel("Ładowanie, proszę czekać..."); // utworzenie nowej etykiety z tekstem

        // ustawienie elementów okna dialogowego
        progressBar.setIndeterminate(true); // ustawienie paska postępu jako nieokreślony (bez wyznaczonego maksimum i wartości)
        frame.add(BorderLayout.CENTER, progressBar); // dodanie paska postępu do środka okna dialogowego
        frame.add(BorderLayout.NORTH, label); // dodanie etykiety na górze okna dialogowego
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // ustawienie akcji zamknięcia okna na brak reakcji (okno można zamknąć tylko metodą "hideProgressBar()")
        frame.pack(); // spakowanie elementów w oknie
        frame.setLocationRelativeTo(null); // ustawienie pozycji okna na środek ekranu

        // utworzenie timera, który po zadanym czasie ukryje pasek postępu
        Timer timer = new Timer(durationInSeconds * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideProgressBar();
            }
        });
        timer.setRepeats(false); // ustawienie, że timer ma działać tylko raz
        timer.start(); // uruchomienie timera
    }

    // metoda wyświetlająca okno dialogowe z paskiem postępu
    public void showProgressBar() {
        frame.setVisible(true);
    }

    // metoda ukrywająca okno dialogowe z paskiem postępu
    public void hideProgressBar() {
        frame.dispose();
    }
}