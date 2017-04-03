package com.example.android.topolab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Resultados extends AppCompatActivity {
    ArrayList<Cota> cotas = new ArrayList<>();
    float cotaInicial, cotaFinal, distanciaEntreCotas, deltaDeCota;
    int xDistanciaDeCurva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        cotaInicial = getIntent().getExtras().getFloat("cotaInicial");
        cotaFinal = getIntent().getExtras().getFloat("cotaFinal");
        xDistanciaDeCurva = getIntent().getExtras().getInt("xDistanciaDeCurva");
        distanciaEntreCotas = getIntent().getExtras().getFloat("distanciaEntreCotas");
        TextView viewDatos = (TextView) findViewById(R.id.datos);
        viewDatos.setText("C. Inicial: "+cotaInicial+" m, C. Final: "+cotaFinal+" m\nxDist. de Curva: "+xDistanciaDeCurva+" cm, Dif. de Cotas: "+distanciaEntreCotas+" m");
        deltaDeCota = cotaFinal - cotaInicial;
        getValues();
        CotaAdapter adapter = new CotaAdapter(this, cotas);
        ListView listView = (ListView) findViewById(R.id.present_resultados);
        listView.setAdapter(adapter);
    }
    public void regresar(View view){
        Intent i = new Intent(Resultados.this, MainActivity.class);
        startActivity(i);
    }
    private void getValues(){
        //String ret = "";
        float valor = getInicio();

        float incremento = (float)xDistanciaDeCurva/100;
        while(valor<cotaFinal){
            /*float restCota = valor-cotaInicial;
            float xget = (restCota*distanciaEntreCotas)/deltaDeCota;*/
            cotas.add(new Cota(valor, cotaInicial, distanciaEntreCotas, deltaDeCota));
            //ret = ret + redondeo(valor)+" | "+ redondeo(xget) + "\n";

            valor+=incremento;
        }
    }

    //Esta funcion sirve para conseguir el valor con el que se va a iniciar
    private float getInicio(){
        //return 0.0f;
        int numMultiplo = getDecimales(cotaInicial);
        while(numMultiplo%xDistanciaDeCurva!=0){
            //Se sacan los decimales para obtener el numero en int como decimal
            numMultiplo+=1;
        }
        float ret = ((int)cotaInicial)+((float)numMultiplo/100);
        return ret;
    }

    //Conseguir los valores decimales como un entero
    private int getDecimales(float num){
        int inum = (int) num;
        float ret = num - inum;
        return (int) (ret*100);
    }
}
