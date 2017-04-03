package com.example.android.topolab;

/**
 * Created by Manuel on 25/1/2017.
 */

public class Cota {
    float cota, cotaInicial, distanciaEntreCotas, deltaDeCota, distancia;
    public Cota (float vCota, float gCotaInicial, float gDistanciaEntreCotas, float gDeltaDeCota){
        cota = vCota;
        cotaInicial = gCotaInicial;
        distanciaEntreCotas = gDistanciaEntreCotas;
        deltaDeCota = gDeltaDeCota;
        distancia = resultado();
    }

    public float resultado(){
        float resCota = cota-cotaInicial;
        float v = (resCota*distanciaEntreCotas)/deltaDeCota;
        return v;
    }

    public float getCota(){
        return cota;
    }
    public float getDistancia(){
        return distancia;
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

    @Override
    public String toString(){
        return redondeo(cota)+" | "+redondeo(distancia);
    }
}
