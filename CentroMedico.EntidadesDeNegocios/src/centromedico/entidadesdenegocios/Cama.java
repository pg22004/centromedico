package centromedico.entidadesdenegocios;

public class Cama {
    
    private int id;
    private int idSala;

    public Cama() {
    }

    public Cama(int id, int IdSala) {
        this.id = id;
        this.idSala = IdSala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int IdSala) {
        this.idSala = IdSala;
    }
}
