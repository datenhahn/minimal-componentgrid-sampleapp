package org.test;

import org.fluttercode.datafactory.impl.DataFactory;

import java.util.*;

import static com.google.gwt.thirdparty.guava.common.collect.Sets.newHashSet;

class Person {
    String name;
    String favouriteAnimal;
    public static final Set<String> availableAnimals = newHashSet("Bear","Giraffe","Fish","Dog");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavouriteAnimal() {
        return favouriteAnimal;
    }

    public void setFavouriteAnimal(String favouriteAnimal) {
        this.favouriteAnimal = favouriteAnimal;
    }

    public static final List<Person> createDummyPersons(int number) {
        List<Person> persons = new LinkedList<>();
        DataFactory df = new DataFactory();

        for(int i = 0; i < number; i++) {
            Person person = new Person();
            person.setName(df.getName());
            Iterator<String> iterator = availableAnimals.iterator();
            String animal = "";
            for(int j = 0; j <= df.getNumberUpTo(4); j++) {
                animal = iterator.next();
            }
            person.setFavouriteAnimal(animal);
            persons.add(person);
        }

        return persons;
    }
}
