import arnaldoLib.InputData;

import java.util.ArrayList;
import java.util.List;

public class Pianeta extends CorpoCeleste {
    private CorpoCeleste nodoRif;
    private double distanzaRif;
    private double angoloRif;
    private List<Luna> lune;
    private String nomePianeta;
    private ArrayList<String> listaCollisioni = new ArrayList<>();

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

    public ArrayList<String> getListaCollisioni() {
        return listaCollisioni;
    }

    public void aggiungiCollisione(CorpoCeleste corpo){
        listaCollisioni.add(corpo.getNome());
    }

    @Override
    public String toString() {
        return "Pianeta:" + super.toString();

    }
}
