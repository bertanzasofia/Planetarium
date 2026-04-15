import java.util.ArrayList;

public class Luna extends CorpoCeleste {
    private CorpoCeleste nodoRif;
    private double distanzaRif;
    private double angoloRif;
    private String nome;
    private ArrayList<String> listaCollisioni = new ArrayList<>();

    public Luna(String nome, double massa, CorpoCeleste nodoRif, double distanzaRif, double angoloRif) {
        double posX = nodoRif.getPosizioneAssoluta().getX() + distanzaRif * Math.cos(Math.toRadians(angoloRif));
        double posY = nodoRif.getPosizioneAssoluta().getY() + distanzaRif * Math.sin(Math.toRadians(angoloRif));
        super(nome, massa, posX, posY);

        this.nodoRif = nodoRif;
        this.distanzaRif = distanzaRif;
        this.angoloRif = angoloRif;
        this.nome = nome;
    }

    public ArrayList<String> getListaCollisioni() {
        return listaCollisioni;
    }

    public double getDistanzaRif() {
        return distanzaRif;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Luna: " + super.toString();
    }

    public void aggiungiCollisione(CorpoCeleste corpo){
        listaCollisioni.add(corpo.getNome());
    }


}