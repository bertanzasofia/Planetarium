package strutturaPlanetarium;

import utilityClass.Costanti;
import static utilityClass.Costanti.*;
import java.util.ArrayList;

public class Stella extends CorpoCeleste {

    private final ArrayList<Pianeta> pianeti;

    public Stella(String nome, double massa) {
        super(nome, massa, X_STELLA, Y_STELLA, null);
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
        String stampa = STELLA_TOSTRING + getNome() + MASSA_TOSTRING + getMassa() + KG_TOSTRING;
        for(Pianeta pianeta : getPianeti()){
            stampa += MAGGIOREDI + pianeta.toString() + LINE_BREAK;
        }
        return stampa;
    }
}
