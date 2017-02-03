package com.example.alexy.tubtabbar.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.alexy.tubtabbar.Activities.Connection;
import com.example.alexy.tubtabbar.R;
import static android.content.Context.MODE_PRIVATE;

public class MenuFragment extends Fragment {

    private View retVal = null;
    private Button connectionButton;
    SharedPreferences sharedPreferences;
    public MenuFragment() {
        // Required empty public constructor
    }
    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        retVal = inflater.inflate(R.layout.fragment_menu, container, false);
        connectionButton = (Button) retVal.findViewById(R.id.connectionButton);
        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), Connection.class), 1);
            }
        });
        updateView();
        return retVal;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }

    private boolean isFacebookConnected() {
        sharedPreferences = getActivity().getSharedPreferences("PREF", MODE_PRIVATE);
        if (sharedPreferences.contains("isConnected")) {
            Boolean isConnected = sharedPreferences.getBoolean("isConnected", false);
            return isConnected;
        }
        return false;
    }

    private void updateView(){
        if (isFacebookConnected()){
            if (sharedPreferences.getString("facebookName","") == null ||
                    sharedPreferences.getString("facebookName","").isEmpty() ||
                    sharedPreferences.getString("facebookName","").equals("NULL")) {
                connectionButton.setText("Connecté avec facebook");
                Log.d("TAG",""+sharedPreferences.getString("facebookName",""));
            }
            else {
                connectionButton.setText("Connecté en tant que : " + sharedPreferences.getString("facebookName", ""));
                Log.d("TAG",""+sharedPreferences.getString("facebookName",""));
            }
        }
        else {
            connectionButton.setText("Je veux me connecter");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
