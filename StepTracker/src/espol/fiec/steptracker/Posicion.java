package espol.fiec.steptracker;

public class Posicion {
 
    long id;
    String latitud;
    String longitud;
 
    // constructors
    public Posicion() {
    }
 
    public Posicion(String latitud, String longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
 
    public Posicion(long id, String latitud, String longitud) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
    }
 
    // setters
    public void setId(long id) {
        this.id = id;
    }
 
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
 
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
     
 
    // getters
    public long getId() {
        return this.id;
    }
 
    public String getLatitud() {
        return this.latitud;
    }

    public String getLongitud() {
        return this.longitud;
    }

}
