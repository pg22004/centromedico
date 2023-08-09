package centromedico.entidadesdenegocios;

import java.time.LocalDate;


public class Historial {
    
    private int id;
    private LocalDate fechaEntrada;
    private String detalleRegistro;

    public Historial() {
    }

    public Historial(int id, LocalDate fechaEntrada, String detalleRegistro) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.detalleRegistro = detalleRegistro;
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
    
    
}
