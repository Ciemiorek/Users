package com.ciemiorek.users.swager;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class test {


    public static void main(String[] args) {

        person person1 = new person("kupa","gowo",3);
        person person2 = new person("kupaKupa","gowo",6);
        person person3 = new person("kupaKupa","gowokal",3);
        person person4 = new person("kupa","gowokalik",7);

        List<person> personList = Arrays.asList(person1,person2,person3,person4);

        personList.stream().forEach(person -> System.out.println(person));
        personList = personList.stream().filter(x-> "kupa".equals(x.getSurname())).collect(Collectors.toList());
        personList.stream().forEach(person -> System.out.println(person));

    }



}


@Getter
@Setter
class person{

    private String surname;
    private String name;
    private int number;

    public person(String surname, String name, int number) {
        this.surname = surname;
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "person{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
