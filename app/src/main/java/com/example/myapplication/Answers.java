package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private String answer_a;
    private String answer_b;
    private String answer_c;
    private String answer_d;
    private String answer_e = null;
    private String answer_f = null;

    public List<String> getAnswers(){
        ArrayList<String> answers = new ArrayList<>();
        answers.add(getAnswer_a());
        answers.add(getAnswer_b());
        answers.add(getAnswer_c());
        answers.add(getAnswer_d());
        answers.add(getAnswer_e());
        answers.add(getAnswer_f());
        return answers;
    }
    // Getter Methods

    public String getAnswer_a() {
        return answer_a;
    }

    public String getAnswer_b() {
        return answer_b;
    }

    public String getAnswer_c() {
        return answer_c;
    }

    public String getAnswer_d() {
        return answer_d;
    }

    public String getAnswer_e() {
        return answer_e;
    }

    public String getAnswer_f() {
        return answer_f;
    }

    // Setter Methods

    public void setAnswer_a(String answer_a) {
        this.answer_a = answer_a;
    }

    public void setAnswer_b(String answer_b) {
        this.answer_b = answer_b;
    }

    public void setAnswer_c(String answer_c) {
        this.answer_c = answer_c;
    }

    public void setAnswer_d(String answer_d) {
        this.answer_d = answer_d;
    }

    public void setAnswer_e(String answer_e) {
        this.answer_e = answer_e;
    }

    public void setAnswer_f(String answer_f) {
        this.answer_f = answer_f;
    }
}