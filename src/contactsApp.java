
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class contactsApp {
    public static void main(String[] args) throws IOException {

        Path dataFilePath = Paths.get("contacts.txt");

        Path contactsPath = Paths.get("contacts.txt");
        List<String> contactsList = Files.readAllLines(contactsPath);

        //add a new contact

        Input userInput = new Input();
        Files.write(dataFilePath, Arrays.asList(userInput.getString()), StandardOpenOption.APPEND);
        System.out.println("Please, enter a new contact");

        displayAll(contactsPath);
        contactSearch(contactsPath);
    }

    public static void displayAll(Path contactsPath) throws IOException {
        List<String> contactsList = Files.readAllLines(contactsPath);
        for (int i = 0; i < contactsList.size(); i++) {
            System.out.println(contactsList.get(i));

        }

    }

    public static boolean contactSearch(Path contactsPath) throws IOException {
        List<String> contactsList = Files.readAllLines(contactsPath);
        System.out.println("Search a name");
        Input userInput = new Input();
        String nameToSearchFor = userInput.getString();
        if (contactsList.contains(nameToSearchFor)) {
            System.out.println(contactsList.get(contactsList.indexOf(nameToSearchFor)));
            return true;
        } else {
            System.out.println("Sorry, not found");
            return false;

        }
    }

}