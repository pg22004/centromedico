package centromedico.entidadesdenegocios;

import java.time.LocalDate;


public class Historial {
    
    private int id;
    private LocalDate fechaEntrada;
    private String detalleRegistro;
    private int idPaciente;

    public Historial() {
    }

    public Historial(int id, LocalDate fechaEntrada, String detalleRegistro, int idPaciente) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.detalleRegistro = detalleRegistro;
        this.idPaciente = idPaciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

}
