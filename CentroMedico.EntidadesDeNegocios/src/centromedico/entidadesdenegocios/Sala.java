package centromedico.entidadesdenegocios;

import java.util.ArrayList;
public class Sala {
    
    private int id;
    private String nombre;
    private int numeroCamas;
    private int top_aux;
    private ArrayList<Cama> cama;

    public Sala() {
    }

    public Sala(int id, String nombre, int numeroCamas, int top_aux, ArrayList<Cama> cama) {
        this.id = id;
        this.nombre = nombre;
        this.numeroCamas = numeroCamas;
        this.top_aux = top_aux;
        this.cama = cama;
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

    public ArrayList<Cama> getCama() {
        return cama;
    }

    public void setCama(ArrayList<Cama> cama) {
        this.cama = cama;
    }
}


