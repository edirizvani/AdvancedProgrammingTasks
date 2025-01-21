//package LAB3;
//
//
//import java.io.*;
//import java.util.*;
//
//class InvalidNameException extends Exception{
//    String name;
//    public InvalidNameException(String message,String name) {
//        super(message+name);
//        this.name=name;
//
//
//    }
//}
//class InvalidNumberException extends Exception{
//    String number;
//    public InvalidNumberException(String message,String num) {
//        super(message);
//        this.number=num;
//
//    }
//
//
//}
//class  MaximumSizeExceddedException extends Exception{
//    public  MaximumSizeExceddedException(String message) {
//        super(message);
//    }
//}
//
//
//class Contact implements Comparable<Contact>{
//    private final String name;
//    private String[] phonenumber;
//    static int max=5;
//    public Contact(String name) throws InvalidNameException {
//        if(!checkname(name)){
//            throw new InvalidNameException("Invalid name: ",name);
//        }
//        this.name=name;
//    }
//    public Contact(String name, String... phone_numbers) throws InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {
//        if(name.length()<4 || name.length()>10 || !checkname(name)){
//            throw new InvalidNameException("Invalid name: ",name);
//        }
//        phonenumber=new String[0];
//        int c=0;
//        for (String num:phone_numbers) {
//            if(phonenumber.length==5){
//                throw new  MaximumSizeExceddedException("Contacts full");
//            }
//            if(cecknumber(num)){
//                throw new InvalidNumberException("Invalid number ",num);
//            }
//            phonenumber=Arrays.copyOf(phonenumber,phonenumber.length+1);
//            phonenumber[c++]=num;
//        }
//        this.name = name;
//
//
//    }
//
//
//
//
//    private boolean cecknumber(String substring) {
//        if(!substring.startsWith("07") || substring.length()!=9 ){
//            return true;
//        }
//        char third=substring.charAt(2);
//        if(substring.charAt(2)=='3' || substring.charAt(2)=='4' || substring.charAt(2)=='9' || Character.isLetter(third)){
//            return true;
//        }
//        for (int i = 0; i < substring.length(); i++) {
//
//            if(Character.isLetter(substring.charAt(i))){
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    private Boolean checkname(String name){
//        if(name.length()<4 || name.length()>10){
//            return false;
//        }
//        for (int i = 0; i < name.length(); i++) {
//            if(!Character.isLetterOrDigit(name.charAt(i))){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String[] getPhonenumber() {
//        return phonenumber;
//    }
//    void addNumber(String phone_number) throws InvalidNumberException, MaximumSizeExceddedException {
//        if(cecknumber(phone_number)){
//            throw new InvalidNumberException("Invalid number ",phone_number);
//        }
//        if(phonenumber.length==5){
//            throw new  MaximumSizeExceddedException("Contacts full");
//        }
//        phonenumber=Arrays.copyOf(phonenumber, phonenumber.length + 1);
//        phonenumber[phonenumber.length - 1] = phone_number;
//
//        // Sort the phone numbers array to maintain order
//        Arrays.sort(phonenumber);
//
//    }
//
//    @Override
//    public String toString() {
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(name).append('\n');
//        sb.append(phonenumber.length).append('\n');
//        for(String s : getNumbers()){
//            sb.append(s).append('\n');
//        }
//        return sb.toString();
//    }
//    Contact valueOf(String s){
//        //TODO
//        return null; //throw InvalidFormatException
//    }
//
//    @Override
//    public int compareTo(Contact o) {
//        return this.name.compareTo(o.getName());
//    }
//
//    public String[] getNumbers() {
//        return phonenumber;
//    }
//}
//
//class PhoneBook{
//    ArrayList<Contact> contacts;
//
//    public PhoneBook() {
//        this.contacts = new ArrayList<>();
//    }
//
//    public static boolean saveAsTextFile(PhoneBook pb, String path) {
//        try {
//           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
//             oos.writeObject(pb);
//             oos.close();
//         } catch (IOException e) {
//             return false;
//         }
//
//        return true;
//    }
//
//    public static PhoneBook loadFromTextFile(String file) {
//        PhoneBook pb=null;
//        try {
//            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
//            pb=(PhoneBook) ois.readObject();
//            ois.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return pb;
//    }
//
//    public void addContact(Contact contact) throws MaximumSizeExceddedException, InvalidNameException {
//        if(contacts.size()==250){
//            throw new MaximumSizeExceddedException("we already have 250");
//        }
//        for(Contact con:contacts){
//            if(con.getName().equals(contact.getName())){
//                throw new InvalidNameException("Invalid name",contact.getName());
//            }
//        }
//        contacts.add(contact);
//
//    }
//
//    public boolean removeContact(String nextLine) {
//        Contact con=getContactForName(nextLine);
//        if (con!=null){
//            contacts.remove(con);
//            return true;
//        }
//        return false;
//
//    }
//
//    public int numberOfContacts() {
//        return contacts.size();
//    }
//
//    public Contact[] getContacts() {
//        ArrayList<Contact> contacts1=new ArrayList<>(contacts);
//        Collections.sort(contacts1);
//        return contacts1.toArray(new Contact[0]);
//    }
//
//    public Contact getContactForName(String nextLine) {
//        for (Contact con:contacts){
//            if (con.getName().equals(nextLine)) return con;
//        }
//        return null;
//    }
//
//    public Contact[] getContactsForNumber(String nextLine) {
//        ArrayList<Contact> prefix=new ArrayList<>();
//        for (Contact con:contacts){
//            for(String phone:con.getPhonenumber()){
//                if(phone.substring(0,3).equals(nextLine)){
//                    prefix.add(con);
//                }
//            }
//        }
//        return prefix.toArray(new Contact[0]);
//    }
//
//    @Override
//    public String toString() {
//      StringBuilder sb=new StringBuilder();
//      for(Contact con:contacts){
//          sb.append(con.toString());
//      }
//      return sb.toString();
//    }
//}
//
//
//
//
//
//
//
//
//public class PhonebookTester {
//
//    public static void main(String[] args) throws Exception {
//        Scanner jin = new Scanner(System.in);
//        String line = jin.nextLine();
//        switch( line ) {
//            case "test_contact":
//                testContact(jin);
//                break;
//            case "test_phonebook_exceptions":
//                testPhonebookExceptions(jin);
//                break;
//            case "test_usage":
//                testUsage(jin);
//                break;
//        }
//    }
//
//    private static void testFile(Scanner jin) throws Exception {
//        PhoneBook phonebook = new PhoneBook();
//        while ( jin.hasNextLine() )
//            phonebook.addContact(new Contact(jin.nextLine(),jin.nextLine().split("\\s++")));
//        String text_file = "phonebook.txt";
//        PhoneBook.saveAsTextFile(phonebook,text_file);
//        PhoneBook pb = PhoneBook.loadFromTextFile(text_file);
//        if ( ! pb.equals(phonebook) ) System.out.println("Your file saving and loading doesn't seem to work right");
//        else System.out.println("Your file saving and loading works great. Good job!");
//    }
//
//    private static void testUsage(Scanner jin) throws Exception {
//        PhoneBook phonebook = new PhoneBook();
//        while ( jin.hasNextLine() ) {
//            String command = jin.nextLine();
//            switch ( command ) {
//                case "add":
//                    phonebook.addContact(new Contact(jin.nextLine(),jin.nextLine().split("\\s++")));
//                    break;
//                case "remove":
//                    phonebook.removeContact(jin.nextLine());
//                    break;
//                case "print":
//                    System.out.println(phonebook.numberOfContacts());
//                    System.out.println(Arrays.toString(phonebook.getContacts()));
//                    System.out.println(phonebook.toString());
//                    break;
//                case "get_name":
//                    System.out.println(phonebook.getContactForName(jin.nextLine()));
//                    break;
//                case "get_number":
//                    System.out.println(Arrays.toString(phonebook.getContactsForNumber(jin.nextLine())));
//                    break;
//            }
//        }
//    }
//
//    private static void testPhonebookExceptions(Scanner jin) {
//        PhoneBook phonebook = new PhoneBook();
//        boolean exception_thrown = false;
//        try {
//            while ( jin.hasNextLine() ) {
//                phonebook.addContact(new Contact(jin.nextLine()));
//            }
//        }
//        catch ( InvalidNameException e ) {
//            System.out.println(e.name);
//            exception_thrown = true;
//        }
//        catch ( Exception e ) {}
//        if ( ! exception_thrown ) System.out.println("Your addContact method doesn't throw InvalidNameException");
//        /*
//exception_thrown = false;
//try {
//phonebook.addContact(new Contact(jin.nextLine()));
//} catch ( MaximumSizeExceddedException e ) {
//exception_thrown = true;
//}
//catch ( Exception e ) {}
//if ( ! exception_thrown ) System.out.println("Your addContact method doesn't throw MaximumSizeExcededException");
//        */
//    }
//
//    private static void testContact(Scanner jin) throws Exception {
//        boolean exception_thrown = true;
//        String names_to_test[] = { "And\nrej","asd","AAAAAAAAAAAAAAAAAAAAAA","Ð�Ð½Ð´Ñ€ÐµÑ˜A123213","Andrej#","Andrej<3"};
//        for ( String name : names_to_test ) {
//            try {
//                new Contact(name);
//                exception_thrown = false;
//            } catch (InvalidNameException e) {
//                exception_thrown = true;
//            }
//            if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw an InvalidNameException");
//        }
//        String numbers_to_test[] = { "+071718028","number","078asdasdasd","070asdqwe","070a56798","07045678a","123456789","074456798","073456798","079456798" };
//        for ( String number : numbers_to_test ) {
//            try {
//                new Contact("Andrej",number);
//                exception_thrown = false;
//            } catch (InvalidNumberException e) {
//                exception_thrown = true;
//            }
//            if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw an InvalidNumberException");
//        }
//        String nums[] = new String[10];
//        for ( int i = 0 ; i < nums.length ; ++i ) nums[i] = getRandomLegitNumber();
//        try {
//            new Contact("Andrej",nums);
//            exception_thrown = false;
//        } catch (MaximumSizeExceddedException e) {
//            exception_thrown = true;
//        }
//        if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw a MaximumSizeExceddedException");
//        Random rnd = new Random(5);
//        Contact contact = new Contact("Andrej",getRandomLegitNumber(rnd),getRandomLegitNumber(rnd),getRandomLegitNumber(rnd));
//        System.out.println(contact.getName());
//        System.out.println(Arrays.toString(contact.getNumbers()));
//        System.out.println(contact.toString());
//        contact.addNumber(getRandomLegitNumber(rnd));
//        System.out.println(Arrays.toString(contact.getNumbers()));
//        System.out.println(contact.toString());
//        contact.addNumber(getRandomLegitNumber(rnd));
//        System.out.println(Arrays.toString(contact.getNumbers()));
//        System.out.println(contact.toString());
//    }
//
//    static String[] legit_prefixes = {"070","071","072","075","076","077","078"};
//    static Random rnd = new Random();
//
//    private static String getRandomLegitNumber() {
//        return getRandomLegitNumber(rnd);
//    }
//
//    private static String getRandomLegitNumber(Random rnd) {
//        StringBuilder sb = new StringBuilder(legit_prefixes[rnd.nextInt(legit_prefixes.length)]);
//        for ( int i = 3 ; i < 9 ; ++i )
//            sb.append(rnd.nextInt(10));
//        return sb.toString();
//    }
//
//
//}