import java.util.ArrayList;
import java.util.List;

public class Stella extends CorpoCeleste {
    private List<Pianeta> pianeti;

    public Stella(String nome, double massa) {
        super(nome, massa, 0,0);
        this.pianeti = new ArrayList<>();
    }

    public void aggiornaPianeti(Pianeta pianeta) {
        pianeti.add(pianeta);
    }
}
