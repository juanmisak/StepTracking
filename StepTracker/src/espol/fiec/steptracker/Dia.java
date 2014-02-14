package espol.fiec.steptracker;

public class Dia {
 
    long id;
    String fecha;
    double tiempo;
    double distancia;
 
    // constructors
    public Dia() {
    }
 
    public Dia(String fecha) {
        this.fecha = fecha;
        this.tiempo=0;
        this.distancia=0;
    }
 
    public Dia(long id, String fecha) {
        this.id = id;
        this.fecha = fecha;
        this.tiempo=0;
        this.distancia=0;
    }
 
    // setters
    public void setId(long id) {
        this.id = id;
    }
 
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
 
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }
     
    public void setDistancia(double distancia){
        this.distancia = distancia;
    }
 
    // getters
    public long getId() {
        return this.id;
    }
 
    public String getFecha() {
        return this.fecha;
    }
 
    public double getDistancia() {
        return this.distancia;
    }

    public double getTiempo() {
        return this.tiempo;
    }


}
