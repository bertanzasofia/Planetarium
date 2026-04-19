package strutturaPlanetarium;

import java.util.ArrayList;

public class Luna extends CorpoCeleste {
    private final double distanzaRif;
    private final double angoloRif;
    private final ArrayList<String> collisioni;

    public Luna(String nome, double massa, CorpoCeleste nodoRif, double distanzaRif, double angoloRif) {
        double posX = nodoRif.getPosizioneAssoluta().getX() + distanzaRif * Math.cos(Math.toRadians(angoloRif));
        double posY = nodoRif.getPosizioneAssoluta().getY() + distanzaRif * Math.sin(Math.toRadians(angoloRif));
        super(nome, massa, posX, posY, nodoRif);

        this.distanzaRif = distanzaRif;
        this.angoloRif = angoloRif;
        this.collisioni = new ArrayList<>();
    }

    public double getDistanzaRif() {
        return distanzaRif;
    }

    public ArrayList<String> getCollisioni() {
        return collisioni;
    }

    public void aggiungiCollisione(CorpoCeleste corpo){
        collisioni.add(corpo.getNome());
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String toString() {
        // todo: stampa posizione relativa
        return "Luna: " + super.toString();
    }
}