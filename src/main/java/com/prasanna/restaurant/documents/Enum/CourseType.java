package com.prasanna.restaurant.documents.Enum;

public enum CourseType {
    STARTER("starter"),
    MAIN_COURSE("main_course"),
    DESERT("desert");

    private final String name;

    CourseType(String starter) {
        this.name = starter;
    }

    public String getName() {
        return name;
    }

    public static CourseType fromString(String name) {
        for (CourseType type : CourseType.values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + name);
    }

    @Override
    public String toString() {
        return name;
    }
}
