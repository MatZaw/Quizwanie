package com.example.myapplication;

public class ResultDto {
    private String username;
    private String title;
    private int score;
    private int max;
    private String date;

    public ResultDto(String username, String title, int score, int max, String date) {
        this.username = username;
        this.title = title;
        this.score = score;
        this.max = max;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
