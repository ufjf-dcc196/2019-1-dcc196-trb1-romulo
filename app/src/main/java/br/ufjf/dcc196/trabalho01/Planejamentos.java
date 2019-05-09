package br.ufjf.dcc196.trabalho01;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Planejamentos implements Serializable {

    private int ano;
    private int semestre;
    private float horas;
    private float horasLinguas;
    private float horasHumanas;
    private float horasExatas;
    private float horasSaude;
    private ArrayList<Disciplinas> disciplinas;


    public Planejamentos(){
        this.disciplinas = new ArrayList<>();
    };

    public Planejamentos(int  ano, int semestre, float horasLinguas, float horasHumanas, float horasExatas, float horasSaude){
        this.ano = ano;
        this.semestre = semestre;
        this.horasLinguas = horasLinguas;
        this.horasHumanas = horasHumanas;
        this.horasExatas = horasExatas;
        this.horasSaude = horasSaude;
        this.disciplinas = new ArrayList<>();
        calculaHoras();
    }

    protected Planejamentos(Parcel in) {
        ano = in.readInt();
        semestre = in.readInt();
        horas = in.readFloat();
        horasLinguas = in.readFloat();
        horasHumanas = in.readFloat();
        horasExatas = in.readFloat();
        horasSaude = in.readFloat();
    }

    public int getAno() {
        return ano;
    }

    public int getSemestre() {
        return semestre;
    }

    public float getHoras() {
        return horas;
    }

    public float getHorasLinguas() {
        return horasLinguas;
    }

    public float getHorasHumanas() {
        return horasHumanas;
    }

    public float getHorasExatas() {
        return horasExatas;
    }

    public float getHorasSaude() {
        return horasSaude;
    }

    public ArrayList<Disciplinas> getDisciplinas() {
        return disciplinas;
    }

    public void setAno(int ano) { this.ano = ano; }

    public void setSemestre(int semestre) { this.semestre = semestre; }

    public void setHorasLinguas(float horasLinguas) { this.horasLinguas = horasLinguas; }

    public void setHorasHumanas(float horasHumanas) { this.horasHumanas = horasHumanas; }

    public void setHorasExatas(float horasExatas) { this.horasExatas = horasExatas; }

    public void setHorasSaude(float horasSaude) { this.horasSaude = horasSaude; }

    public void setHoras(float horas) {
        this.horas = horas;
    }

    public void setDisciplinas (String nome, float hora, String area) {
        Disciplinas temp = new Disciplinas();
        temp.setNome(nome);
        temp.setThoras(hora);
        temp.setArea(area);
        this.disciplinas.add(temp);
    }

    public void setDisciplinas (Disciplinas temp){
        this.disciplinas.add(temp);
    }

    public void calculaHoras(){
        this.horas = this.horasLinguas+this.horasHumanas+this.horasExatas+this.horasSaude;
    }

    private float porcent(float cont, float total){
        return (cont*100)/total;
    }

    public float porcentagemLinguas(){
        float k = 0;
        if(disciplinas.size() != 0){
            for (int i=0; i<disciplinas.size(); i++){
                Disciplinas a = disciplinas.get(i);
                if (a.getArea() == "Linguas"){
                    k += a.getThoras();
                }
            }
            k = porcent(k, this.horasLinguas);
        }
        else{k=0;}
        return k;
    }

    public float porcentagemHumanas(){
        float k = 0;
        for (int i=0; i<disciplinas.size(); i++){
            Disciplinas a = disciplinas.get(i);
            if (a.getArea() == "Humanas"){
                k += a.getThoras();
            }
        }
        k = porcent(k, this.horasHumanas);
        return k;
    }

    public float porcentagemExatas(){
        float k = 0;
        for (int i=0; i<disciplinas.size(); i++){
            Disciplinas a = disciplinas.get(i);
            if (a.getArea() == "Exatas"){
                k += a.getThoras();
            }
        }
        k = porcent(k, this.horasExatas);
        return k;
    }

    public float porcentagemSaude(){
        float k = 0;
        for (int i=0; i<disciplinas.size(); i++){
            Disciplinas a = disciplinas.get(i);
            if (a.getArea() == "Saúde"){
                k += a.getThoras();
            }
        }
        k = porcent(k, this.horasSaude);
        return k;
    }
}
