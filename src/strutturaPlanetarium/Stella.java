package strutturaPlanetarium;

import java.util.ArrayList;

public class Stella extends CorpoCeleste {
    private final ArrayList<Pianeta> pianeti;

    public Stella(String nome, double massa) {
        super(nome, massa, 0, 0, null);
        this.pianeti = new ArrayList<>();
    }

    public double getDistanzaRif(){
        return 0;
    }

    public ArrayList<Pianeta> getPianeti() {
        return pianeti;
    }

    public void aggiungiPianeta(Pianeta pianeta) {
        this.pianeti.add(pianeta);
    }

    public void rimuoviPianeta(Pianeta pianeta){
        this.pianeti.remove(pianeta);
    }

    @Override
    public String toString() {
        String stampa = "Stella: " + getNome() + "\n\tMassa: " + getMassa() + "kg\n";
        for(Pianeta pianeta : getPianeti()){
            stampa += ">" + pianeta.toString() + "\n";
        }
        return stampa;
    }
}
