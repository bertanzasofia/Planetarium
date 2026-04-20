package strutturaPlanetarium;

import java.util.ArrayList;
import java.util.UUID;

public abstract class CorpoCeleste {
    private final String codiceUnivoco;
    private final String nome;
    private final double massa;
    private final Punto posizioneAssoluta;
    private final CorpoCeleste nodoRif;

    public CorpoCeleste(String nome, double massa, double x, double y, CorpoCeleste nodoRif) {
        this.codiceUnivoco = UUID.randomUUID().toString().substring(0, 8);
        this.nome = nome;
        this.massa = massa;
        this.posizioneAssoluta = new Punto(x,y);
        this.nodoRif = nodoRif;
    }

    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public String getNome() {
        return nome;
    }

    public double getMassa() {
        return massa;
    }

    public Punto getPosizioneAssoluta() {
        return posizioneAssoluta;
    }

    public abstract double getDistanzaRif();

    public ArrayList<CorpoCeleste> getPath() {
        ArrayList<CorpoCeleste> path = new ArrayList<>();
        CorpoCeleste corrente = this;

        while (corrente != null) {
            path.add(corrente);
            corrente = corrente.nodoRif;
        }
        return path;
    }

    @Override
    public String toString() {
        return String.format("%s\n\tMassa: %skg\n\tPosizione X: %.3f\tPosizione Y: %.3f \n\tNodo di riferimento: %s\n\tDistanza rif: %s\n\tCodice ID: %s",
                nome, massa, posizioneAssoluta.getX(), posizioneAssoluta.getY(),nodoRif.nome,getDistanzaRif(), codiceUnivoco);
    }
}
