package com.example.project_game_prm392_v1.object;

public class Quiz {
    public String name, solution, image;

    public Quiz() {
    }

    public Quiz(String name, String solution, String image) {
        this.name = name;
        this.solution = solution;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
