package centromedico.entidadesdenegocios;

public class Cama {
    
    private int id;
    private int idSala;
    private int  top_aux;
    private Sala sala;

    public Cama() {
    }

    public Cama(int id, int idSala, int top_aux, Sala sala) {
        this.id = id;
        this.idSala = idSala;
        this.top_aux = top_aux;
        this.sala = sala;
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

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}

    
