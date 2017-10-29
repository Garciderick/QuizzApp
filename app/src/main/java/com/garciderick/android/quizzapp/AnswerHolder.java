package com.garciderick.android.quizzapp;

/**
 * Created by Garciderix on 22/10/2017.
 * This class will work as a holder for all the answers, is used as a singleton
 * so all the classes that instantiates
 */

public class AnswerHolder {
    private String name;
    private int score;
    private int[] answers;
    private static AnswerHolder answerHolder = null;

    private AnswerHolder()
    {
        name = null;
        score = 0;
        answers = new int[4];

        for (int i = 0; i < answers.length; i++)
        {
            answers[i] = 0;
        }
    }

    public static AnswerHolder newAnswerHolder()
    {
        if (answerHolder == null)
        {
            answerHolder = new AnswerHolder();
        }
        return answerHolder;
    }

    public void restartQuiz()
    {
        name = null;
        score = 0;
        answers = new int[4];

        for (int i = 0; i < answers.length; i++)
        {
            answers[i] = 0;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int[] getAnswers() {
        return answers;
    }

    public void setAnswers(int[] answers) {
        this.answers = answers;
    }
}
