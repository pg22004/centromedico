package centromedico.entidadesdenegocios;

public class Sala {
    
    private int id;
    private String nombre;
    private int numeroCamas;

    public Sala() {
    }

    public Sala(int id, String nombre, int numeroCamas) {
        this.id = id;
        this.nombre = nombre;
        this.numeroCamas = numeroCamas;
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
}
