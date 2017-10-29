package com.garciderick.android.quizzapp;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFour.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class QuestionFour extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View contextView;
    private Button button;
    private boolean answered;
    private ViewGroup checkBoxGroup;

    public QuestionFour() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ImageView batmanImage;

        answered = false;

        contextView = inflater.inflate(R.layout.fragment_question_four, container, false);
        batmanImage = (ImageView)contextView.findViewById(R.id.q4_iv);
        checkBoxGroup = (ViewGroup)contextView.findViewById(R.id.q4_cg);
        button = (Button)contextView.findViewById(R.id.q4_nb);

        batmanImage.getDrawable().setColorFilter(getContext().getColor(android.R.color.holo_blue_bright), PorterDuff.Mode.SRC_ATOP);

        for(int i = 0; i < checkBoxGroup.getChildCount(); i++)
        {
            ((CheckBox)checkBoxGroup.getChildAt(i)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    button.setEnabled(true);
                }
            });
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox1;
                CheckBox checkBox2;
                CheckBox checkBox3;
                ValueAnimator valueAnimator;
                checkBox1 = (CheckBox)contextView.findViewById(R.id.q4_cb1);
                checkBox2 = (CheckBox)contextView.findViewById(R.id.q4_cb2);
                checkBox3 = (CheckBox)contextView.findViewById(R.id.q4_cb3);

                if (!checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked())
                {
                    AnswerHolder.newAnswerHolder().getAnswers()[3] = getResources().getInteger(R.integer.right_answer);
                }
                else
                {
                    AnswerHolder.newAnswerHolder().getAnswers()[3] = getResources().getInteger(R.integer.wrong_answer);
                }

                valueAnimator = ValueAnimator.ofInt(255,0);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        ImageView imageView;
                        int red;
                        int green;
                        int blue;
                        int alpha;

                        imageView = (ImageView)contextView.findViewById(R.id.q4_iv);
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
                    button.setText(R.string.finish_button);
                    button.setEnabled(false);
                    answered = true;
                    for(int i = 0; i < checkBoxGroup.getChildCount(); i++)
                    {
                        ((CheckBox)checkBoxGroup.getChildAt(i)).setEnabled(false);
                    }
                    if(AnswerHolder.newAnswerHolder().getAnswers()[3] == getResources().getInteger(R.integer.right_answer))
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
