package model;
public class Carta {

 
    private String palo;
    private String numero;

    public Carta(String palo, String numero) {
        this.palo = palo;
        this.numero = numero;
    }

    public Carta() {
    }

    public String getPalo() {
        return palo;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Carta [ " + this.numero + " de " + this.palo + " ]";
    }



    
}
