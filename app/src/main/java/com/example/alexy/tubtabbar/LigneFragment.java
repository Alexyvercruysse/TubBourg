package com.example.alexy.tubtabbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LigneFragment extends Fragment {

    private View retVal = null;
    private Button ligne1,ligne2,ligne3,ligne4,ligne5,ligne6,ligne7,ligne21;
    private ListView listLigne;
    public LigneFragment() {
        // Required empty public constructor
    }

    public static LigneFragment newInstance() {
        LigneFragment fragment = new LigneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        retVal = inflater.inflate(R.layout.fragment_ligne, container, false);
        listLigne = (ListView) retVal.findViewById(R.id.listLigne);
        List<String> ListDeString = new ArrayList<String>(){{add("Ligne 1");add("Ligne 2");add("Ligne 3");add("Ligne 4");add("Ligne 5");add("Ligne 6");add("Ligne 7");add("Ligne 21");}};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, ListDeString );
        listLigne.setAdapter(arrayAdapter);
        listLigne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 7){
                    showLigneHorraire(0);
                }
                else {
                    showLigneHorraire(i+1);
                }
            }
        });
        return retVal;
    }

    private void showLigneHorraire(final int numLigne){
                Intent intent = new Intent(getActivity(),LigneMap.class);
                intent.putExtra("numLigne",numLigne);
                startActivity(intent);
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
