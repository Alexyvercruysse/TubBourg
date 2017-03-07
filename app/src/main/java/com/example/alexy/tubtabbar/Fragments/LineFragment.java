package com.example.alexy.tubtabbar.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.alexy.tubtabbar.Activities.LineMap;
import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.R;
import com.example.alexy.tubtabbar.Repositories.LineRepository;
import com.example.alexy.tubtabbar.Repositories.LineRepositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineFragment extends Fragment {

    private View retVal = null;
    private ListView listLine;
    public LineFragment() {
    }

    public static LineFragment newInstance() {
        LineFragment fragment = new LineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LineRepository lineRepository = new LineRepositoryImpl();

        retVal = inflater.inflate(R.layout.fragment_line, container, false);
        listLine = (ListView) retVal.findViewById(R.id.listLigne);

        List <String> listNameLine = new ArrayList<>();

        for(Line line : lineRepository.listLines()){
            listNameLine.add(line.getName());
        }

        //Add all the names of the lines in a listView
        listLine.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listNameLine));

        //return the name of the selected line
        listLine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showLigneHorraire( (String) listLine.getItemAtPosition(i));
            }
        });
        return retVal;
    }

    private void showLigneHorraire(String nameLine){
                Intent intent = new Intent(getActivity(),LineMap.class);
                intent.putExtra("nameLine", nameLine);
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
