package com.example.empresa_personalsoft;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IngresoFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    EditText jetcorreo,jetclave,jetnombre,jetusuario;
    Button jbtingresar;
    CheckBox jcbactivo;
    TextView jtvregistrar;
    RequestQueue rq;
    JsonRequest jrq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_ingreso, container, false);
        //Asociar objetos
        View vista = inflater.inflate(R.layout.fragment_ingreso,container,false);
        jetcorreo = vista.findViewById(R.id.etcorreo);
        jetclave = vista.findViewById(R.id.etclave);
        jetnombre = vista.findViewById(R.id.etnombre);
        jbtingresar = vista.findViewById(R.id.btingresar);
        jtvregistrar=vista.findViewById(R.id.tvregistrar);
        jbtingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iniciar_sesion();
            }
        });
        jtvregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar();
            }
        });
        return vista;
    }

    private void Iniciar_sesion(){
    }

    private void Registrar(){
        Intent intregistrar=new Intent(getContext(),RegistrarseActivity.class);
        startActivity(intregistrar);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Usuario no hallado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Usuario hallado", Toast.LENGTH_SHORT).show();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);//posicion 0 del arreglo....
            jetnombre.setText(jsonObject.optString("nombre"));
            jetcorreo.setText(jsonObject.optString("correo"));
            jetclave.setText(jsonObject.optString("clave"));
            if (jsonObject.optString("activo").equals("si"))
                jcbactivo.setChecked(true);
            else
                jcbactivo.setChecked(false);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }




}
