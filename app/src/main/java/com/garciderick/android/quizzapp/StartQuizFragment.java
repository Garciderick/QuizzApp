package com.garciderick.android.quizzapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StartQuizFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class StartQuizFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View contextView;

    public StartQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Button button;

        contextView = inflater.inflate(R.layout.fragment_start_quiz, container, false);
        button = (Button)contextView.findViewById(R.id.sq_nb);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText;
                AnswerHolder answerHolder;
                InputMethodManager inputMethodManager;

                answerHolder = AnswerHolder.newAnswerHolder();
                editText = (EditText)contextView.findViewById(R.id.sq_et);

                answerHolder.setName(editText.getText().toString());

                if(answerHolder.getName() != null && !answerHolder.getName().isEmpty())
                {
                    inputMethodManager = (InputMethodManager)((MainActivity)getActivity()).getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(((MainActivity)getActivity()).getCurrentFocus().getWindowToken(),0);

                    ((MainActivity)getActivity()).manageFragments();
                }
                else
                {
                    Toast.makeText(getContext(),R.string.name_validation,Toast.LENGTH_LONG).show();
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
