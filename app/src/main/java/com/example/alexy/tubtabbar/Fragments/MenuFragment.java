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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexy.tubtabbar.Activities.Connection;
import com.example.alexy.tubtabbar.Entities.WeatherObject;
import com.example.alexy.tubtabbar.R;
import com.example.alexy.tubtabbar.Services.WeatherApi;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MenuFragment extends Fragment {

    private View retVal = null;
    private Button connectionButton, connectionAdminButton;
    private TextView resultWeather;
    private ImageView imageViewWeather;

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
        final ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();

        retVal = inflater.inflate(R.layout.fragment_menu, container, false);

        resultWeather = (TextView) retVal.findViewById(R.id.weatherTextView);
        imageViewWeather = (ImageView) retVal.findViewById(R.id.imageViewWeather);
        if (ni != null && ni.isConnectedOrConnecting()) {
            Log.i("MenuFragment", "Network " + ni.getTypeName() + " connected");
            WeatherApi api = WeatherApi.retrofit.create(WeatherApi.class);
            Call weather = api.getWeatherObject(getResources().getString(R.string.city_id),getResources().getString(R.string.weather_key));
            weather.enqueue(new Callback<WeatherObject>() {
                @Override
                public void onResponse(Call<WeatherObject> call, Response<WeatherObject> response) {
                        resultWeather.setText(response.body().getName() + " actuellement : " + response.body().getMain().getTemp() + "°C");
                        imageViewWeather.setVisibility(View.VISIBLE);
                        Picasso.with(getActivity())
                                .load("http://openweathermap.org/img/w/" + response.body().getWeather().get(0).getIcon() + ".png")
                                .into(imageViewWeather);
                }

                @Override
                public void onFailure(Call<WeatherObject> call, Throwable t) {
                    resultWeather.setText("Erreur lors de la récupération de la météo");
                }
            });
        }
        else {
            resultWeather.setText("Connectez-vous à internet pour la météo.");

        }

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
            connectionButton.setText("Connexion avec Facebook");
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
