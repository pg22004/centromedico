package centromedico.entidadesdenegocios;

import java.time.LocalDate;

public class PacienteSala {
     private int id;
     private int idPaciente;
     private int idSala;
     private LocalDate fechaEntrada;
     private Paciente paciente;
     private Sala sala;

    public PacienteSala() {
    }

    public PacienteSala(int id, int idPaciente, int idSala, LocalDate fechaEntrada, Paciente paciente, Sala sala) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idSala = idSala;
        this.fechaEntrada = fechaEntrada;
        this.paciente = paciente;
        this.sala = sala;
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

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
     
     
}
