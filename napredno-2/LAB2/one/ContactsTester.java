package LAB2.one;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;


abstract class Contact{

    LocalDate date;

    public Contact(String datestr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date=LocalDate.parse(datestr,formatter);
    }

    public boolean isNewerThan(Contact c){

        return date.isAfter(c.date);
    }
    abstract String getType();


}

class EmailContact extends Contact{
     String email;
    public EmailContact(String datestr,String email) {
        super(datestr);
        this.email=email;
    }

    @Override
    String getType() {
        return "Email";
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "\""+email+"\"";
    }
}
enum Operator { VIP, ONE, TMOBILE }
class PhoneContact extends Contact{
    String phone;
    Operator operator;
    public PhoneContact(String datestr,String phone) {
        super(datestr);
        this.phone=phone;
        String[] first=phone.split("/");
        if(first[0].charAt(2)=='1' || first[0].charAt(2)=='0' || first[0].charAt(2)=='2'){
            operator=Operator.TMOBILE;
        } else if (first[0].charAt(2)=='5' || first[0].charAt(2)=='6') {
            operator=Operator.ONE;
        } else if (first[0].charAt(2)=='7' || first[0].charAt(2)=='8') {
            operator=Operator.VIP;
        }
    }

    public Operator getOperator() {
        return operator;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    String getType() {
        return "Phone";
    }
    @Override
    public String toString() {
        return "\""+phone+"\"";
    }



}


class Student{
    ArrayList<Contact> contacts;
    String ime;
    String prezime;
    String city;
    int vozrast;
    long indeks;

    public Student( String ime, String lastName, String city, int age, long index) {
        contacts=new ArrayList<>();
        this.contacts = contacts;
        this.ime = ime;
        this.prezime = lastName;
        this.city = city;
        this.vozrast = age;
        this.indeks = index;
    }
    public void addEmailContact(String date,String email){

        contacts.add(new EmailContact(date,email));
    }
    public void addPhoneContact(String date, String phone){
        contacts.add(new PhoneContact(date,phone));
    }
    public ArrayList<Contact> getEmailContacts(){
        ArrayList<Contact> EmailContacts=new ArrayList<>();

//        for (Contact contact:contacts){
//            if(contact.getType().equals("Email")){
//                EmailContacts.add(contact);
//            }
//        }

        return contacts.stream().filter(contact -> contact.getType().equals("Email")).collect(Collectors.toCollection(ArrayList::new));
    }
    public ArrayList<Contact> getPhoneContacts(){
//        ArrayList<Contact> PhoneContacts=new ArrayList<>();
//        for (Contact contact:contacts){
//            if(contact.getType().equals("Phone")){
//                PhoneContacts.add(contact);
//            }
//        }
        return contacts.stream().filter(phone->phone.getType().equals("Phone")).collect(Collectors.toCollection(ArrayList::new));
    }

    public String getCity() {
        return city;
    }

    public String getFullName() {
        return ime +" "+ prezime;
    }

    public long getIndeks() {
        return indeks;
    }
    Contact getLatestContact(){
       // return contacts.stream().max((c1,c2)->c1.date.compareTo(c2.date)).orElse(null);
//        Contact latest=contacts.get(0);
//        for (Contact contact : contacts) {
//            if(contact.isNewerThan(latest)){
//                latest=contact;
//            }
//        }
        return contacts.stream().max((cont1,cont2)->cont1.isNewerThan(cont2) ? 1 :-1).orElse(null);
    }

    @Override
    public String toString() {


        ArrayList<Contact> phoneContacts=getPhoneContacts();

        ArrayList<Contact> emailContacts=getEmailContacts();

        return "{" +
                "\"ime\":\"" + ime + "\", " +
                "\"prezime\":\"" + prezime + "\", " +
                "\"vozrast\":" + vozrast + ", " +
                "\"grad\":\"" + city + "\", " +
                "\"indeks\":" + indeks + ", " +
                "\"telefonskiKontakti\":" + phoneContacts.toString() + ", " +
                "\"emailKontakti\":" + emailContacts.toString() +
                "}";

    }
}
class Faculty{
    String fakultet;
    Student[] students;

    public Faculty(String fakultet, Student[] students) {
        this.fakultet = fakultet;
        this.students = students;
    }
    int countStudentsFromCity(String cityName){
        return (int)Arrays.stream(students).filter(student -> student.getCity().equals(cityName)).count();
    }
    public Student getStudent(long index) {
        return Arrays.stream(students)
                .filter(student -> student.getIndeks() == index)
                .findFirst()
                .orElse(null); // Return null if no student found
    }
    public double getAverageNumberOfContacts() {
//       double sum=0;
//        for (Student st:students) {
//            sum+=st.contacts.size();
//        }
//        return sum/(double)students.length;
        return Arrays.stream(students).mapToInt(student->student.contacts.size()).average().orElse(0.0);
    }
    public Student getStudentWithMostContacts() {
//        Student stmax = students[0];
//        int max = students[0].contacts.size();
//        for (Student st : students) {
//            if (st.contacts.size() >= max) {
//                if(st.contacts.size()==max){
//                    if(stmax.indeks>st.indeks){
//                        continue;
//                    }
//                }
//                max = st.contacts.size();
//                stmax = st;
//            }
//        }
//        return stmax;
        return Arrays.stream(students).reduce((student1,student2)->{
           if(student1.contacts.size()>student2.contacts.size()){
               return student1;
           }else if(student1.contacts.size()<student2.contacts.size()){
               return student2;
           }
           return student1.getIndeks()>student2.getIndeks()?student1:student2;

        }).orElse(null);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append("\"fakultet\":\"").append(fakultet).append("\", ")
                .append("\"studenti\":[");

        for (int i = 0; i < students.length; i++) {
            sb.append(students[i].toString());
            if (i < students.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("]}");
        return sb.toString();
        }
    }

















public class ContactsTester {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        Faculty faculty = null;

        int rvalue = 0;
        long rindex = -1;

        DecimalFormat df = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            rvalue++;
            String operation = scanner.next();

            switch (operation) {
                case "CREATE_FACULTY": {
                    String name = scanner.nextLine().trim();
                    int N = scanner.nextInt();

                    Student[] students = new Student[N];

                    for (int i = 0; i < N; i++) {
                        rvalue++;

                        String firstName = scanner.next();
                        String lastName = scanner.next();
                        String city = scanner.next();
                        int age = scanner.nextInt();
                        long index = scanner.nextLong();

                        if ((rindex == -1) || (rvalue % 13 == 0))
                            rindex = index;

                        Student student = new Student(firstName, lastName, city,
                                age, index);
                        students[i] = student;
                    }

                    faculty = new Faculty(name, students);
                    break;
                }

                case "ADD_EMAIL_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String email = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addEmailContact(date, email);
                    break;
                }

                case "ADD_PHONE_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String phone = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addPhoneContact(date, phone);
                    break;
                }

                case "CHECK_SIMPLE": {
                    System.out.println("Average number of contacts: "
                            + df.format(faculty.getAverageNumberOfContacts()));

                    rvalue++;

                    String city = faculty.getStudent(rindex).getCity();
                    System.out.println("Number of students from " + city + ": "
                            + faculty.countStudentsFromCity(city));

                    break;
                }

                case "CHECK_DATES": {

                    rvalue++;

                    System.out.print("Latest contact: ");
                    Contact latestContact = faculty.getStudent(rindex)
                            .getLatestContact();
                    if (latestContact.getType().equals("Email"))
                        System.out.println(((EmailContact) latestContact)
                                .getEmail());
                    if (latestContact.getType().equals("Phone"))
                        System.out.println(((PhoneContact) latestContact)
                                .getPhone()
                                + " ("
                                + ((PhoneContact) latestContact).getOperator()
                                .toString() + ")");

                    if (faculty.getStudent(rindex).getEmailContacts().size() > 0
                            && faculty.getStudent(rindex).getPhoneContacts().size() > 0) {
                        System.out.print("Number of email and phone contacts: ");
                        System.out
                                .println(faculty.getStudent(rindex)
                                        .getEmailContacts().size()
                                        + " "
                                        + faculty.getStudent(rindex)
                                        .getPhoneContacts().size());

                        System.out.print("Comparing dates: ");
                        int posEmail = rvalue
                                % faculty.getStudent(rindex).getEmailContacts().size();
                        int posPhone = rvalue
                                % faculty.getStudent(rindex).getPhoneContacts().size();

                        System.out.println(faculty.getStudent(rindex)
                                .getEmailContacts().get(posEmail).isNewerThan(faculty
                                .getStudent(rindex).getPhoneContacts().get(posPhone)));
                    }

                    break;
                }

                case "PRINT_FACULTY_METHODS": {
                    System.out.println("Faculty: " + faculty.toString());
                    System.out.println("Student with most contacts: "
                            + faculty.getStudentWithMostContacts().toString());
                    break;
                }

            }

        }

        scanner.close();
    }
}