package centromedico.entidadesdenegocios;

import java.time.LocalDate;
import java.util.ArrayList;


public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private LocalDate fechaRegistro;
     private int top_aux;
     private ArrayList<Historial> historial;

    public Paciente() {
    }

    public Paciente(int id, String nombre, String apellido, LocalDate fechaRegistro, int top_aux, ArrayList<Historial> historial) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaRegistro = fechaRegistro;
        this.top_aux = top_aux;
        this.historial = historial;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<Historial> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<Historial> historial) {
        this.historial = historial;
    }

    
}
