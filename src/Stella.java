import arnaldoLib.InputData;

import java.util.ArrayList;
import java.util.List;

public class Stella extends CorpoCeleste {
    private List<Pianeta> pianeti;

    public Stella(String nome, double massa) {
        super(nome, massa, 0, 0);
        this.pianeti = new ArrayList<>();
    }

    public void aggiungiPianeta(Stella nodoRif) {
        String nome = InputData.readNonEmptyString("Inserisci nome pianeta: ", false);
        double massa = InputData.readDoubleWithMinimum("Inserisci massa: ", 0);
        double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dalla stella: ", 0);
        double angolo = InputData.readDouble("Inserisci l'angolo a cui si trova: ");

        Pianeta pianeta = new Pianeta(nome, massa, nodoRif, distanza, angolo);
        pianeti.add(pianeta);
        System.out.printf("%s è stato aggiunto, ID: %s", pianeta.getNome(), pianeta.getCodiceUnivoco());
    }

    public void rimuoviPianeta(String idPianeta){
        for(Pianeta pianeta: pianeti)
        {
            if(pianeta.getCodiceUnivoco().equals(idPianeta)){
                System.out.printf("%s rimosso", pianeta.getNome());
                pianeti.remove(pianeta);
            }
        }
    }
}
