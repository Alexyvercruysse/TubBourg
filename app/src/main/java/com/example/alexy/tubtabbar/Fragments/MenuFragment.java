package com.example.alexy.tubtabbar.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alexy.tubtabbar.Activities.Connection;
import com.example.alexy.tubtabbar.R;
import com.example.alexy.tubtabbar.Services.TubApi;
import com.example.alexy.tubtabbar.Services.WeatherApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

import static android.content.Context.MODE_PRIVATE;

public class MenuFragment extends Fragment {

    private View retVal = null;
    private Button connectionButton, connectionAdminButton;
    SharedPreferences sharedPreferences;
    String urlWeatherBourg;
    int apiKey;
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
        final ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();

        // TODO : Finish Weather. get is ok
        if (ni != null && ni.isConnectedOrConnecting()) {
            Log.i("MenuFragment", "Network " + ni.getTypeName() + " connected");
            WeatherApi api = WeatherApi.retrofit.create(WeatherApi.class);
            Call weather = api.getWeather(3031009,getResources().getString(R.string.weather_key));
            weather.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("MenuFragment", response.message()+" "+response.errorBody()+" "+response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("MenuFrgment",t.toString());
                }
            });
        }
        retVal = inflater.inflate(R.layout.fragment_menu, container, false);
        connectionButton = (Button) retVal.findViewById(R.id.connectionButton);
        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), Connection.class), 1);
            }
        });
        connectionAdminButton = (Button) retVal.findViewById(R.id.connectionAdminButton);
        if (isFacebookConnected()) {
            connectionAdminButton.setVisibility(View.INVISIBLE);
        }
        else {
            connectionAdminButton.setVisibility(View.VISIBLE);
        }
        connectionAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                alert.setTitle("Mot de passe Super Admin");

// Set an EditText view to get user input
                final EditText input = new EditText(getContext());
                alert.setView(input);

                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        if (value.equals("tub2017")){
                            connectionAdminButton.setClickable(false);
                            connectionAdminButton.setText("Connecté en super Admin");
                            sharedPreferences = getContext().getSharedPreferences("PREF", MODE_PRIVATE);
                            sharedPreferences
                                    .edit()
                                    .putBoolean("isConnectedAdmin", true)
                                    .apply();

                        }
                        else {
                            Toast.makeText(getContext(),"Mauvais mot de passe", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alert.setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
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
            return sharedPreferences.getBoolean("isConnected", false);
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
        if (isFacebookConnected()) {
            connectionAdminButton.setVisibility(View.INVISIBLE);
        }
        else {
            connectionAdminButton.setVisibility(View.VISIBLE);
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
