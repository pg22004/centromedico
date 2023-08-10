package centromedico.entidadesdenegocios;

public class Cama {
    
    private int id;
    private int IdSala;

    public Cama() {
    }

    public Cama(int id, int IdSala) {
        this.id = id;
        this.IdSala = IdSala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSala() {
        return IdSala;
    }

    public void setIdSala(int IdSala) {
        this.IdSala = IdSala;
    }
}
