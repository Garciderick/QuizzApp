package com.garciderick.android.quizzapp;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionTwo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class QuestionTwo extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View contextView;
    private Button button;
    private boolean answered;
    private RadioGroup radioGroup;

    public QuestionTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ImageView batmanImage;

        answered = false;

        contextView = inflater.inflate(R.layout.fragment_question_two, container, false);
        batmanImage = (ImageView)contextView.findViewById(R.id.q2_iv);
        radioGroup = (RadioGroup)contextView.findViewById(R.id.q2_rg);
        button = (Button)contextView.findViewById(R.id.q2_nb);

        batmanImage.getDrawable().setColorFilter(getContext().getColor(android.R.color.holo_blue_bright), PorterDuff.Mode.SRC_ATOP);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                button.setEnabled(true);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton radioButton1;
                RadioButton radioButton2;
                RadioButton radioButton3;
                ValueAnimator valueAnimator;

                radioButton1 = (RadioButton)contextView.findViewById(R.id.q2_rb1);
                radioButton2 = (RadioButton)contextView.findViewById(R.id.q2_rb2);
                radioButton3 = (RadioButton)contextView.findViewById(R.id.q2_rb3);
                if (radioButton1.isChecked())
                {
                    AnswerHolder.newAnswerHolder().getAnswers()[1] = getResources().getInteger(R.integer.wrong_answer);
                }
                else if (radioButton2.isChecked())
                {
                    AnswerHolder.newAnswerHolder().getAnswers()[1] = getResources().getInteger(R.integer.right_answer);
                }
                else if (radioButton3.isChecked())
                {
                    AnswerHolder.newAnswerHolder().getAnswers()[1] = getResources().getInteger(R.integer.wrong_answer);
                }

                valueAnimator = ObjectAnimator.ofInt(255,0);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        ImageView imageView;
                        int red;
                        int green;
                        int blue;
                        int alpha;

                        imageView = (ImageView)contextView.findViewById(R.id.q2_iv);
                        red = Color.red(getContext().getColor(android.R.color.holo_blue_bright));
                        green = Color.green(getContext().getColor(android.R.color.holo_blue_bright));
                        blue = Color.blue(getContext().getColor(android.R.color.holo_blue_bright));
                        alpha = (int)valueAnimator.getAnimatedValue();

                        imageView.getDrawable().setColorFilter(Color.argb(alpha,red,green,blue),PorterDuff.Mode.SRC_ATOP);
                    }
                });
                /**
                 * the "answered" flag is used as an indicator to change the functionality of the "next question button"
                 * once the button is clicked, the answer is saved, and lets the animation run and finish
                 * so it prevents the choreographer to throw an exception while replacing the fragment
                 */
                if(answered)
                {
                    ((MainActivity)getActivity()).manageFragments();
                }
                else
                {
                    button.setText(R.string.next_button);
                    button.setEnabled(false);
                    answered = true;
                    for(int i = 0; i < radioGroup.getChildCount(); i++)
                    {
                        radioGroup.getChildAt(i).setEnabled(false);
                    }
                    if(AnswerHolder.newAnswerHolder().getAnswers()[1] == getResources().getInteger(R.integer.right_answer))
                    {
                        Toast.makeText(getContext(),R.string.right_answer,Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getContext(),R.string.wrong_answer,Toast.LENGTH_LONG).show();
                    }
                    valueAnimator.setDuration(1500);
                    valueAnimator.start();
                    valueAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            button.setEnabled(true);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }

            }
        });
        return contextView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
