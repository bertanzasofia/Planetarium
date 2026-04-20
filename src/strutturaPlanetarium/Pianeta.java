package strutturaPlanetarium;

import static utilityClass.Costanti.*;
import java.util.ArrayList;

public class Pianeta extends CorpoCeleste {
    private final ArrayList<Luna> lune;
    private final double distanzaRif;
    private final double angoloRif;
    private final ArrayList<String> collisioni;

    public Pianeta(String nome, double massa, CorpoCeleste nodoRif, double distanzaRif, double angoloRif) {
        double posX = nodoRif.getPosizioneAssoluta().getX() + distanzaRif * Math.cos(Math.toRadians(angoloRif));
        double posY = nodoRif.getPosizioneAssoluta().getY() + distanzaRif * Math.sin(Math.toRadians(angoloRif));
        super(nome, massa, posX, posY, nodoRif);

        this.lune = new ArrayList<>();
        this.distanzaRif = distanzaRif;
        this.angoloRif = angoloRif;
        this.collisioni = new ArrayList<>();
    }

    public double getDistanzaRif() {
        return distanzaRif;
    }

    public ArrayList<Luna> getLune() {
        return lune;
    }

    public ArrayList<String> getCollisioni() {
        return collisioni;
    }

    public void aggiungiLuna(Luna luna) {
        this.lune.add(luna);
    }

    public void rimuoviLuna(Luna luna) {
        this.lune.remove(luna);
    }

    public void aggiungiCollisione(CorpoCeleste corpo){
        this.collisioni.add(corpo.getNome());
    }

    @Override
    public String toString() {
        String stampa = PIANETA_TOSTRING +super.toString()+ ANGOLO_TOSTRING +angoloRif+ LINE_BREAK;
        for(Luna luna : getLune()){
            stampa += FRECCIAMAGGIOREDI + luna.toString() + LINE_BREAK;
        }
        return stampa;
    }
}
