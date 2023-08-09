package centromedico.entidadesdenegocios;

import java.time.LocalDate;


public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private LocalDate fechaRegistro;

    public Paciente() {
    }

    public Paciente(int Id, String nombre, String apellido, LocalDate FechaRegistro) {
        this.id = Id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaRegistro = FechaRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
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

    public void setFechaRegistro(LocalDate FechaRegistro) {
        this.fechaRegistro = FechaRegistro;
    }
    
}
