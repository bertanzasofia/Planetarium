import arnaldoLib.InputData;

import java.util.ArrayList;
import java.util.List;

public class Pianeta extends CorpoCeleste {
    private CorpoCeleste nodoRif;
    private double distanzaRif;
    private double angoloRif;
    private List<Luna> lune;
    private String nomePianeta;

    public Pianeta(String nome, double massa, CorpoCeleste nodoRif, double distanzaRif, double angoloRif) {
        double posX = nodoRif.getPosizioneAssoluta().getX() + distanzaRif * Math.cos(Math.toRadians(angoloRif));
        double posY = nodoRif.getPosizioneAssoluta().getY() + distanzaRif * Math.sin(Math.toRadians(angoloRif));
        super(nome, massa, posX, posY);

        this.nodoRif = nodoRif;
        this.distanzaRif = distanzaRif;
        this.angoloRif = angoloRif;
        this.lune = new ArrayList<>();
        this.nomePianeta = nome;
    }

    //getters, setters, overrides, etc
    public String getNomePianeta() {
        return nomePianeta;
    }

    public CorpoCeleste getNodoRif() {
        return nodoRif;
    }

    public double getDistanzaRif() {
        return distanzaRif;
    }

    public double getAngoloRif() {
        return angoloRif;
    }

    public List<Luna> getLune() {
        return lune;
    }

    // funzioni specifiche
    public void aggiungiLuna(Stella stella) {
        boolean duplicato = true;
        boolean lunaDuplicata = true;
        String nome;
            do {
                nome = InputData.readNonEmptyString("Inserisci nome luna: ", false);
                lunaDuplicata = stella.esisteQuestoNome(nome);
                if(lunaDuplicata){
                    System.out.println("Impossibile aggiungere nomi duplicati. ");
                }
            }while(lunaDuplicata);
        double massa = InputData.readDoubleWithMinimum("Inserisci massa: ", 0);
        double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dal pianeta: ", 0);
        double angolo = InputData.readDoubleBetween("Inserisci l'angolo a cui si trova: ",0, 360);

        Luna luna = new Luna(nome, massa, nodoRif, distanza, angolo);
        lune.add(luna);
        System.out.printf("%s è stata aggiunta a %s, ID: %s", luna.getNome(), this.getNomePianeta(), luna.getCodiceUnivoco());
    }

    public Luna ricercaLuna(String lunaCercata){
        //Ricerca pianeta per ID univoco
        for(Luna luna: lune)
        {
            if(luna.getCodiceUnivoco().equalsIgnoreCase(lunaCercata) || luna.getNome().equalsIgnoreCase(lunaCercata))
            {
                return luna;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Pianeta: " + super.toString();

    }

}
