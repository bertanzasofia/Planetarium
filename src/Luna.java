public class Luna extends CorpoCeleste {
    private CorpoCeleste nodoRif;
    private double distanzaRif;
    private double angoloRif;

    public Luna(String nome, double massa, CorpoCeleste nodoRif, double distanzaRif, double angoloRif) {
        double posX = nodoRif.getPosizioneAssoluta().getX() + distanzaRif * Math.cos(Math.toRadians(angoloRif));
        double posY = nodoRif.getPosizioneAssoluta().getY() + distanzaRif * Math.sin(Math.toRadians(angoloRif));
        super(nome, massa, posX, posY);

        this.nodoRif = nodoRif;
        this.distanzaRif = distanzaRif;
        this.angoloRif = angoloRif;
    }

    @Override
    public String toString() {
        return "";
    }
}