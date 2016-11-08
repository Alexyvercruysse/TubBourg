package com.example.alexy.tubtabbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LigneFragment extends Fragment {

    private View retVal = null;
    private Button ligne1,ligne2,ligne3,ligne4,ligne5,ligne6,ligne7,ligne21;
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
        ligne1 = (Button) retVal.findViewById(R.id.ligne1Button);
        ligne2 = (Button) retVal.findViewById(R.id.ligne2Button);
        ligne3 = (Button) retVal.findViewById(R.id.ligne3Button);
        ligne4 = (Button) retVal.findViewById(R.id.ligne4Button);
        ligne5 = (Button) retVal.findViewById(R.id.ligne5Button);
        ligne6 = (Button) retVal.findViewById(R.id.ligne6Button);
        ligne7 = (Button) retVal.findViewById(R.id.ligne7Button);
        ligne21 = (Button) retVal.findViewById(R.id.ligne21Button);
        showLigneHorraire(ligne1,1);
        showLigneHorraire(ligne2,2);
        showLigneHorraire(ligne3,3);
        showLigneHorraire(ligne4,4);
        showLigneHorraire(ligne5,5);
        showLigneHorraire(ligne6,6);
        showLigneHorraire(ligne7,7);
        showLigneHorraire(ligne21,0);
        return retVal;
    }

    private void showLigneHorraire(Button button, final int numLigne){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LigneMap.class);
                intent.putExtra("numLigne",numLigne);
                startActivity(intent);
            }
        });
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
