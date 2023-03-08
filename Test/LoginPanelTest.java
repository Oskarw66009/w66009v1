import org.junit.Test;

import static org.junit.Assert.*;

public class LoginPanelTest {

    @Test
    public void testLoginAsCreator() {
        LoginPanel panel = new LoginPanel();

        // Testowanie loginu
        assertTrue(panel.loginAsCreator("Creator", "creator123"));

        // Test nieprawidłowy loginu
        assertFalse(panel.loginAsCreator("Creator", "wrongpassword"));

        // Test niepraidłowy hasła
        assertFalse(panel.loginAsCreator("WrongUsername", "creator123"));
    }

    @Test
    public void testLoginAsUser() {
        LoginPanel panel = new LoginPanel();

        // Testowanie loginu
        assertTrue(panel.loginAsUser("user", "user123"));

        // Test nieprawidłowy hasła
        assertFalse(panel.loginAsUser("user", "wrongpassword"));

        // Test niepraidłowy loginu
        assertFalse(panel.loginAsUser("WrongUsername", "user123"));
    }
}