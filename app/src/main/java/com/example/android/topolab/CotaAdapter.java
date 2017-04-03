package com.example.android.topolab;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Manuel on 25/1/2017.
 */

public class CotaAdapter extends ArrayAdapter<Cota> {
    public CotaAdapter(Activity context, ArrayList<Cota> cotas){
        super(context, 0, cotas);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Cota currentCota = getItem(position);
        TextView cotaTextView = (TextView) listItemView.findViewById(R.id.cota_iterada);
        cotaTextView.setText(position+1+")  "+redondeo(currentCota.getCota())+" m | Distancia = ");
        TextView resultadoTextView = (TextView) listItemView.findViewById(R.id.resultado_iteracion);
        resultadoTextView.setText(redondeo(currentCota.getDistancia())+" m");
        return listItemView;
    }
    //Esta función retorna el valor de un float redondeado, dos decimales a la derecha
    private String redondeo(float num){
        String sret = "";
        float red = redondeo2(num, 2);
        int len = getLongitud(num);
        sret = String.valueOf(red);
        int len2 = sret.length();
        if (len2<len){
            sret+='0';
        }
        return sret;
    }
    //Esta funcion retorna la longitud de un numero float, dos decimales a la derecha
    private int getLongitud(float num){
        int acum = 0;
        int inc = 1;
        while(inc<num){
            acum++;
            inc=inc*10;
        }
        if(num<1){
            acum++;
        }
        return acum+3;
    }
    //Esta funcion redondea un numero a dos cifras decimales en float
    public float redondeo2(float valorInicial, int numeroDeDecimales){
        double parteEntera;
        double resultado;
        resultado=valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDeDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDeDecimales))+parteEntera;
        return (float) resultado;
    }

}
