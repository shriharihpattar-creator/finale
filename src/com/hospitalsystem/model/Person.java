package com.hospitalsystem.model;

/**
 * Member 4 - Base Person Model.
 * Provides abstraction and common identity attributes.
 */
public abstract class Person {

    private final String id;
    private final String name;
    private final int age;

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract String getRole();

    public String getDetails() {
        return getRole() + " #" + id + ": " + name + " (" + age + " yrs)";
    }
}
