package midterm2;

import java.util.*;

class DuplicateNumberException extends Exception {
    public DuplicateNumberException(String message) {
        super(message);
    }
}
class BuilderKeys{
    static List<String> buildKeys(String fullNumber) {

        List<String> keys = new ArrayList<>();
        keys.add(fullNumber);
        for (int i = 3; i <fullNumber.length(); i++) {
            for (int j = 0; j < fullNumber.length(); ++j) {
                if(j+i>fullNumber.length()){
                    break;
                }
                keys.add(fullNumber.substring(j, j + i));
            }
        }

        return keys;
    }
}
class Contact{
    String name;
    String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return name+" "+number;
    }
}
class PhoneBook {
    Map<String, Map<String,Contact>> numbers;
    Map<String,Set<Contact>> phoneName;
    public PhoneBook() {
        this.numbers = new HashMap<>();
        phoneName = new HashMap<>();
    }
    static Comparator<Contact> compareContact=Comparator.comparing(Contact::getName).thenComparing(Contact::getNumber);
    void addContact(String name, String number) throws DuplicateNumberException {
        if (!this.numbers.containsKey(name)) {
            this.numbers.put(name, new TreeMap<>());
        }
        if(this.numbers.get(name).containsKey(number)) {
            throw new DuplicateNumberException(String.format("Duplicate number: %s",number));
        }
        Contact contact = new Contact(name, number);

        List<String> subKeys=BuilderKeys.buildKeys(number);
        subKeys.forEach(k -> {
            if(!this.phoneName.containsKey(k)) {
                this.phoneName.put(k, new TreeSet<>(compareContact));
            }
            this.phoneName.get(k).add(contact);
        });
        this.numbers.get(name).put(number, contact);
    }


    void contactsByNumber(String number){
        if(!this.phoneName.containsKey(number)) {
            System.out.println("NOT FOUND");
            return;
        }
        phoneName.get(number).stream().forEach(k-> {
            System.out.println(k);
        });
    }
    void contactsByName(String name){
        if(!this.numbers.containsKey(name)) {
            System.out.println("NOT FOUND");
            return;
        }
        this.numbers.get(name).forEach((k, v) -> System.out.println(v.name + " " + v.number));
    }
}
public class PhoneBookTest {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            try {
                phoneBook.addContact(parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] parts = line.split(":");
            if (parts[0].equals("NUM")) {
                phoneBook.contactsByNumber(parts[1]);
            } else {
                phoneBook.contactsByName(parts[1]);
            }
        }
    }

}