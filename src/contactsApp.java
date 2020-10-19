
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
        Path contactsPath = Paths.get("contacts.txt");

        List<String> linesList = Files.readAllLines(contactsPath);
        List<Contact> contactsList = new ArrayList<Contact>();
        for (String line : linesList) {
            String name = line.substring(0, line.indexOf("|")).trim();
            String number = line.substring(line.indexOf("|")).trim();
            Contact thisContact = new Contact(name, number);
            contactsList.add(thisContact);
        }


        Input userInput = new Input();
        boolean wantstoUseApp = true;
        while(wantstoUseApp) {
            System.out.println("1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5): ");
            int userChoice = userInput.getInt(1, 5);
            if(userChoice == 1) {
                displayAll(contactsPath);
            } else if (userChoice == 2) {
                addContact(contactsList);
            } else if (userChoice == 3) {
                contactSearch(contactsPath);
            } else if (userChoice == 4) {
                deleteContact(contactsPath);
            } else if (userChoice == 5) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    public static void displayAll(Path contactsPath) throws IOException {
        List<String> contactsList = Files.readAllLines(contactsPath);
        System.out.println("Name  |  Phone Number\n------------------------");
        for (int i = 0; i < contactsList.size(); i++) {
            System.out.println(contactsList.get(i));

        }
        System.out.println();
    }

    public static void addContact(List contactsList) throws IOException {
        Input userInput = new Input();
        System.out.println("Please, enter the new contact's name: ");
        String name = userInput.getString();
        System.out.println("Please, enter the new contact's number: ");
        String number = userInput.getString();
        Contact newContact = new Contact(name, number);
        contactsList.add(newContact);
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

    public static void deleteContact(Path contactPath) throws IOException {
        List<String> fileContents = Files.readAllLines(contactPath);
        List<String> modifiedList = new ArrayList<>();
        System.out.print("Enter which contact you'd like to delete: ");
        Input userInput = new Input();
        String unwantedContact = userInput.getString();
        if (!fileContents.contains(unwantedContact)) {
            System.out.println("Contact not found!");
        }
        for (String item: fileContents) {
            if(!item.equals(unwantedContact)) {
                modifiedList.add(item);
            }
        }
        Files.write(contactPath, modifiedList);
    }

}