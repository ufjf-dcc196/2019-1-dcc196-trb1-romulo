package br.ufjf.dcc196.trabalho01;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Disciplinas implements Serializable {
    private String nome;
    private String area;
    private float thoras;

    public  Disciplinas(){}

    public Disciplinas(String nome, String area, float thoras){
        this.nome = nome;
        this.area = area;
        this.thoras = thoras;
    }

    public String getNome() {
        return nome;
    }

    public String getArea() {
        return area;
    }

    public float getThoras() {
        return thoras;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setThoras(float thoras) {
        this.thoras = thoras;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
