package aud4;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Person implements Comparable<Person>{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String line) {
       // String name=line.split(" ")[0];
       this.name=line.split(" ")[0];
       this.age=Integer.parseInt(line.split(" ")[1]);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age,o.age);
    }


    public Person older(Person other){
        if(this.age>=other.age){
            return this;
        }else{
            return other;
        }
    }
}


public class OldestPersonTest {


    public static List<Person> getPeople(InputStream InputStream){
        BufferedReader br=new BufferedReader(new InputStreamReader(InputStream));
        //Person finale=br.lines().map(line->new Person(line)).reduce(new Person("",0),(max,other)->max.older(other));
        return br.lines().map(line->new Person(line)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        File file=new File("C:\\Users\\Administrator\\eclipse-workspace\\Napredno-intro\\src\\aud4\\people");
        try {
            List<Person> persons=getPeople(new FileInputStream(file));
//            Person max=persons.stream().reduce(new Person("",0),(maxx,other)->maxx.older(other));
            Person max=persons.stream().max(Comparator.naturalOrder()).get();
            System.out.println(max);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
