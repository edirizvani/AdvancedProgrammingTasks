package aud2.lock.finki;

import java.util.Objects;

public class Person implements Comparable<Person> {
    public String name;
    public String lastname;
    private int age;

    public Person(String name, String lastName,int age){
        this.name=name;
        this.lastname=lastName;
        this.age=age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(lastname, person.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, age);
    }

    @Override
    public int compareTo(Person o) {

        return Integer.compare(this.age,age);
    }

    public static void main(String[] args) {
        Person pq=new Person("EDI","Rizvani",21);
        Person pq2=pq;
        System.out.println(pq2.age);
        pq.age=20;
        System.out.println(pq2.age);
    }
}
