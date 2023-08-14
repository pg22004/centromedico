package centromedico.entidadesdenegocios;

import java.time.LocalDate;

public class Historial {
    
    private int id;
    private int idPaciente;
    private LocalDate fechaEntrada;
    private String detalleRegistro;
    private int top_aux;
    private Paciente paciente;

    public Historial() {
    }

    public Historial(int id, int idPaciente, LocalDate fechaEntrada, String detalleRegistro, int top_aux, Paciente paciente) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.fechaEntrada = fechaEntrada;
        this.detalleRegistro = detalleRegistro;
        this.top_aux = top_aux;
        this.paciente = paciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getDetalleRegistro() {
        return detalleRegistro;
    }

    public void setDetalleRegistro(String detalleRegistro) {
        this.detalleRegistro = detalleRegistro;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    
}
