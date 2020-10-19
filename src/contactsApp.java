
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class contactsApp {
    public static void main(String[] args) throws IOException {
        Path contactsPath = Paths.get("contacts.txt");

        File file = new File("contacts.txt");
        ArrayList<Contact> contactsList = new ArrayList<Contact>();
        if (file.length() > 0) {
            contactsList = readContacts(contactsPath, contactsList);
            System.out.println("this ran");
        }

        Input userInput = new Input();
        boolean wantsToUseApp = true;
        while (wantsToUseApp) {
            System.out.println("1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5): ");
            int userChoice = userInput.getInt(1, 5);
            if (userChoice == 1) {
                displayAll(contactsList);
            } else if (userChoice == 2) {
                addContact(contactsList);
            } else if (userChoice == 3) {
                contactSearch(contactsList);
            } else if (userChoice == 4) {
                contactsList = deleteContact(contactsList);
            } else if (userChoice == 5) {
                writeFile(contactsPath, contactsList);
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    public static void displayAll(ArrayList <Contact> contactsList) throws IOException {
        System.out.printf("%-20s |*| %-20s\n-------------------------------------------------\n", "Name", "        Number");
        for (int i = 0; i < contactsList.size(); i++) {
            contactsList.get(i).printContact();
        }
        System.out.println();
    }

    public static void addContact(ArrayList <Contact> contactsList) throws IOException {
        Input userInput = new Input();
        System.out.println("Please, enter the new contact's name: ");
        String name = userInput.getString();
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getName().equals(name)) {
                System.out.printf("There's already a contact named %s. Do you want to overwrite it? (yes/no) ", name);
                 boolean overwrite = userInput.yesNo();
                 if (overwrite) {
                     System.out.println("Please, enter the new contact's number: ");
                     userInput.getString();
                     contactsList.get(i).setNumber(userInput.getString());
                     return;
                 }
            }
        }
        System.out.println("Please, enter the new contact's number: ");
        String number = userInput.getString();
        Contact newContact = new Contact(name, number);
        contactsList.add(newContact);
    }

    public static void contactSearch(ArrayList <Contact> contactsList) throws IOException {
        System.out.println("Search a name");
        Input userInput = new Input();
        String nameToSearchFor = userInput.getString();
        boolean containsContact = false;
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getName().equals(nameToSearchFor)) {
                containsContact = true;
            }
        }
        if (containsContact) {
            for (int i = 0; i < contactsList.size(); i++) {
                if (contactsList.get(i).getName().equals(nameToSearchFor)) {
                    contactsList.get(i).printContact();
                }
            }
        } else {
            System.out.println("Contact not found!");
        }
    }

    public static ArrayList<Contact> deleteContact(ArrayList <Contact> contactsList) throws IOException {
        ArrayList<Contact> modifiedList = new ArrayList<>();
        System.out.print("Enter which contact you'd like to delete: ");
        Input userInput = new Input();
        String unwantedContact = userInput.getString();
        boolean containsContact = false;
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getName().equals(unwantedContact)) {
                containsContact = true;
            }
        }
        if (!containsContact) {
            System.out.println("Contact not found!");
        } else {
            for (int i = 0; i < contactsList.size(); i++) {
                if (!contactsList.get(i).getName().equals(unwantedContact)) {
                    modifiedList.add(contactsList.get(i));
                }
            }
        }
       return modifiedList;
    }

    public static ArrayList<Contact> readContacts(Path contactsPath, ArrayList <Contact> contactsList) throws IOException {
        List<String> linesList = Files.readAllLines(contactsPath);

        for (String line : linesList) {
            String name = line.substring(0, line.indexOf("|")).trim();
            String number = line.substring(line.indexOf("|") + 2).trim();
            Contact thisContact = new Contact(name, number);
            contactsList.add(thisContact);
        }
        return contactsList;
    }

    public static void writeFile(Path contactsPath, ArrayList<Contact> contactsList) throws IOException {
        List<String> writeList = new ArrayList<>();
        for (int i = 0; i < contactsList.size(); i++) {
            Contact thisContact = contactsList.get(i);
            writeList.add(thisContact.getName() + " | " + thisContact.getNumber());
        }
        Files.write(Paths.get("contacts.txt"), writeList);
    }

}