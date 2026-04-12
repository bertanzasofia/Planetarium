import java.util.UUID;

public abstract class CorpoCeleste {
    private final String codiceUnivoco;
    private String nome;
    private double massa;
    private Punto posizioneAssoluta;

    public CorpoCeleste(String nome, double massa, double x, double y) {
        this.codiceUnivoco = UUID.randomUUID().toString().substring(0, 8);
        this.nome = nome;
        this.massa = massa;
        this.posizioneAssoluta = new Punto(x,y);
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
}
