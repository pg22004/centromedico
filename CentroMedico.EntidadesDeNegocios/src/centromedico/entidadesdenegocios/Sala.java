package centromedico.entidadesdenegocios;

import java.util.ArrayList;
public class Sala {
    
    private int id;
    private String nombre;
    private int numeroCamas;
    private int top_aux;
    private ArrayList<Cama> camas;
    private ArrayList<PacienteSala> pacienteSalas;

    public Sala() {
    }

    public Sala(int id, String nombre, int numeroCamas, int top_aux, ArrayList<Cama> camas, ArrayList<PacienteSala> pacienteSalas) {
        this.id = id;
        this.nombre = nombre;
        this.numeroCamas = numeroCamas;
        this.top_aux = top_aux;
        this.camas = camas;
        this.pacienteSalas = pacienteSalas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroCamas() {
        return numeroCamas;
    }

    public void setNumeroCamas(int numeroCamas) {
        this.numeroCamas = numeroCamas;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<Cama> getCamas() {
        return camas;
    }

    public void setCamas(ArrayList<Cama> camas) {
        this.camas = camas;
    }

    public ArrayList<PacienteSala> getPacienteSalas() {
        return pacienteSalas;
    }

    public void setPacienteSalas(ArrayList<PacienteSala> pacienteSalas) {
        this.pacienteSalas = pacienteSalas;
    }

   
}


