package com.example.android.topolab;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Float.parseFloat;

public class MainActivity extends AppCompatActivity {

    float cotaInicial;
    float cotaFinal;
    float deltaDeCota;
    int xDistanciaDeCurva;
    float distanciaEntreCotas;
    ArrayList<Cota> cotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cotas = new ArrayList<>();
        //This code is used to Underline the word "Cota Inicial" that in the view
        TextView tCotaInicial = (TextView) findViewById(R.id.cot_init);
        tCotaInicial.setPaintFlags(tCotaInicial.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        //This code is used to Underline the word "Cota Final" that in the view
        TextView tCotaFinal = (TextView) findViewById(R.id.cota_final_text);
        tCotaFinal.setPaintFlags(tCotaFinal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        //This code is used to Underline the word "Cota Final" that in the view
        TextView tXDistanciaDeCurva = (TextView) findViewById(R.id.x_distancia_de_curva_text);
        tXDistanciaDeCurva.setPaintFlags(tXDistanciaDeCurva.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        //This code is used to Underline the word "Cota Final" that in the view
        TextView tDistanciaEntreCotas = (TextView) findViewById(R.id.distancia_entre_cotas_text);
        tDistanciaEntreCotas.setPaintFlags(tDistanciaEntreCotas.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
    public void matar(View view){
        try{

            //Tomar el valor de la cota inicial
            EditText viewCotaInicial = (EditText) findViewById(R.id.cota_inicial);
            cotaInicial = parseFloat(viewCotaInicial.getText().toString());
            //Tomar el valor de la cota final
            TextView viewCotaFinal = (TextView) findViewById(R.id.cota_final);
            cotaFinal = parseFloat(viewCotaFinal.getText().toString());
            //Tomar el valor de la xDistancia de Curva
            TextView viewXDistanciaDeCurva = (TextView) findViewById(R.id.x_distancia_de_curva);
            xDistanciaDeCurva = Integer.parseInt(viewXDistanciaDeCurva.getText().toString());
            //Tomar el valor de la Dstancia entre cotas
            TextView viewDiferenciaEntreCotas = (TextView) findViewById(R.id.distancia_entre_cotas);
            distanciaEntreCotas = parseFloat(viewDiferenciaEntreCotas.getText().toString());

            //Obtener el valor de deltaDeCota
            //deltaDeCota = cotaFinal - cotaInicial;
            //getValues();


            Intent i = new Intent(MainActivity.this, Resultados.class);
            i.putExtra("cotaInicial", cotaInicial);
            i.putExtra("cotaFinal", cotaFinal);
            i.putExtra("xDistanciaDeCurva",xDistanciaDeCurva);
            i.putExtra("distanciaEntreCotas",distanciaEntreCotas);
            i.putExtra("cotas", cotas);
            //i.putExtra("valores", valores);
            startActivity(i);

        }catch(Exception e){

        }


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

    //Esta función retorna el valor de un float redondeado, dos decimales a la derecha
    /*private float redondeo(float num){
        String sret = "";
        float red = redondeo2(num, 2);
        int len = getLongitud(num);
        sret = String.valueOf(red);
        int len2 = sret.length();
        if (len2<len){
            sret+='0';
        }
        return Float.parseFloat(sret);
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
    }*/

}