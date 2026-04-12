import java.util.ArrayList;
import java.util.List;

public class Pianeta extends CorpoCeleste {
    private CorpoCeleste nodoRif;
    private double distanzaRif;
    private double angoloRif;
    private List<Luna> lune;

    public Pianeta(String nome, double massa, CorpoCeleste nodoRif, double distanzaRif, double angoloRif) {
        double posX = nodoRif.getPosizioneAssoluta().getX() + distanzaRif * Math.cos(Math.toRadians(angoloRif));
        double posY = nodoRif.getPosizioneAssoluta().getY() + distanzaRif * Math.sin(Math.toRadians(angoloRif));
        super(nome, massa, posX, posY);

        this.nodoRif = nodoRif;
        this.distanzaRif = distanzaRif;
        this.angoloRif = angoloRif;
        this.lune = new ArrayList<>();
    }

    public void aggiornaLune(Luna luna) {
        lune.add(luna);
    }

    @Override
    public String toString() {
        return "";
    }
}
