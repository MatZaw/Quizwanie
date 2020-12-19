package com.example.myapplication;

import java.util.ArrayList;

public class Question {
    private float id;
    private String question;
    private String description = null;
    Answers answers;
    private String multiple_correct_answers;
    Correct_answers correct_answers;
    private String correct_answer;
    private String explanation = null;
    private String tip = null;
    ArrayList< Object > tags = new ArrayList < Object > ();
    private String category;
    private String difficulty;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getDescription() {
        return description;
    }

    public Answers getAnswers() {
        return answers;
    }

    public String getMultiple_correct_answers() {
        return multiple_correct_answers;
    }

    public Correct_answers getCorrect_answers() {
        return correct_answers;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getTip() {
        return tip;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswers(Answers answersObject) {
        this.answers = answersObject;
    }

    public void setMultiple_correct_answers(String multiple_correct_answers) {
        this.multiple_correct_answers = multiple_correct_answers;
    }

    public void setCorrect_answers(Correct_answers correct_answersObject) {
        this.correct_answers = correct_answersObject;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}