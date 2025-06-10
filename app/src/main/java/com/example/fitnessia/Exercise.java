package com.example.fitnessia;

public class Exercise {
    private String name;
    private String sets;
    private String reps;

    public Exercise(String name, String sets, String reps) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
    }

    public String getName() { return name; }
    public String getSets() { return sets; }
    public String getReps() { return reps; }
}
