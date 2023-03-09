/*import java.util.Scanner;

public class login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (loginAsCreator(username, password)) {
            loginAsCreator();
        } else if (loginAsUser(username, password)) {
            loginAsUser();
        } else {
            System.out.println("Invalid username or password");
        }
    }

    private static void loginAsCreator() {
        LoadingBar loadingBar = new LoadingBar(5);
        loadingBar.showProgressBar();
        CreatorPanel creatorPanel = new CreatorPanel();

        creatorPanel.setVisible(true);
        loadingBar.hideProgressBar();

    }

    private static void loginAsUser() {
        LoadingBar loadingBar = new LoadingBar(5);
        loadingBar.hideProgressBar();
        UserPanel userPanel = new UserPanel();
        loadingBar.showProgressBar();
        userPanel.setVisible(true);


    }

    private static boolean loginAsCreator(String username, String password) {
        // Check username and password against creator credentials in database
        return username.equals("Creator") && password.equals("creator123");
    }

    private static boolean loginAsUser(String username, String password) {
        // Check username and password against user credentials in database
        return username.equals("user") && password.equals("user123");
    }
}
*/