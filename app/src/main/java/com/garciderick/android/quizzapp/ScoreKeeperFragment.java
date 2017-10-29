package com.garciderick.android.quizzapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScoreKeeperFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ScoreKeeperFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View contextView;
    private Button button;

    public ScoreKeeperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        int correctAnswerCounter;
        TextView score;
        ArrayList<String> resultMessages;

        resultMessages = loadResultMessages();

        correctAnswerCounter = 0;
        for(int i = 0; i < AnswerHolder.newAnswerHolder().getAnswers().length; i++)
        {
            if (AnswerHolder.newAnswerHolder().getAnswers()[i] == 1)
            {
                correctAnswerCounter++;
            }
        }

        contextView = inflater.inflate(R.layout.fragment_score_keeper, container, false);

        score = (TextView)contextView.findViewById(R.id.sk_score);
        button = (Button)contextView.findViewById(R.id.sk_retry);

        score.setText(String.valueOf(correctAnswerCounter));
        Toast.makeText(getContext(),resultMessages.get(correctAnswerCounter),Toast.LENGTH_LONG).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnswerHolder.newAnswerHolder().restartQuiz();
                ((MainActivity)getActivity()).manageFragments();
            }
        });

        return contextView;
    }

    private ArrayList<String> loadResultMessages()
    {
       ArrayList<String> messages;

       messages = new ArrayList<String>();
       messages.add(getContext().getResources().getString(R.string.bad_result0) + " " + AnswerHolder.newAnswerHolder().getName());
       messages.add(getContext().getResources().getString(R.string.bad_result1) + " "  + AnswerHolder.newAnswerHolder().getName());
       messages.add(getContext().getResources().getString(R.string.bad_result2) + " "  + AnswerHolder.newAnswerHolder().getName());
       messages.add(getContext().getResources().getString(R.string.bad_result3) + " "  + AnswerHolder.newAnswerHolder().getName());
       messages.add(getContext().getResources().getString(R.string.right_result) + " "  + AnswerHolder.newAnswerHolder().getName());

       return messages;
    };

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
