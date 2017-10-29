package com.garciderick.android.quizzapp;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private AnswerHolder answerHolder;
    private ArrayList<String> fragmentTags;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerHolder = AnswerHolder.newAnswerHolder();
        initializeFragmentTags();
        manageFragments();

    }

    private void initializeFragmentTags()
    {
        if (fragmentTags == null)
        {
            fragmentTags = new ArrayList<>();
            fragmentTags.add("QuestionOne");
            fragmentTags.add("QuestionTwo");
            fragmentTags.add("QuestionThree");
            fragmentTags.add("QuestionFour");
            fragmentTags.add("StartQuizFragment");
            fragmentTags.add("ScoreKeeperFragment");
        }

    }

    public void manageFragments()
    {
        /**
         * This method works as a "workflow" for the fragments of the questions
         * this little workflow works along with the "fragmentTags" list, to do so,
         * it checks the answered questions, if the particular answer has the value of 0
         * it means the question has not been answered, so it replaces the fragment of
         * the container with the "next question to be answered"
         */
        int nextQuestion;
        boolean nextQuestionFound;
        boolean allQuestionsAnswered;

        nextQuestionFound = false;
        allQuestionsAnswered = true;

        if (fragmentManager == null)
        {
            fragmentManager = getFragmentManager();

        }

        if (answerHolder.getName() == null)
        {

            if (fragmentManager.findFragmentByTag(fragmentTags.get(4)) == null)
            {

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                fragmentTransaction.replace(R.id.main_container, new StartQuizFragment(), fragmentTags.get(4));
                fragmentTransaction.commit();
            }
        }
        else
        {

            for (nextQuestion = 0 ; nextQuestion < answerHolder.getAnswers().length; nextQuestion++)
            {
                if (answerHolder.getAnswers()[nextQuestion] == getResources().getInteger(R.integer.not_answered))
                {
                    nextQuestionFound = true;
                    allQuestionsAnswered = false;
                    break;
                }
            }
            if (allQuestionsAnswered)
            {
                nextQuestionFound = true;
                nextQuestion = 5;
            }
            if (nextQuestionFound)
            {

                if (fragmentManager.findFragmentByTag(fragmentTags.get(nextQuestion)) == null)
                {

                    try
                    {
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                        fragmentTransaction.replace(R.id.main_container, (Fragment)Class.forName(getPackageName() + "." + fragmentTags.get(nextQuestion)).newInstance(), fragmentTags.get(nextQuestion));
                        fragmentTransaction.commit();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }

            }

        }
    }

}
