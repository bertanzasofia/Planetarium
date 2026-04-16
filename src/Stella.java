import arnaldoLib.InputData;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Stella extends CorpoCeleste {
    private List<Pianeta> pianeti;

    public Stella(String nome, double massa) {
        super(nome, massa, 0, 0);
        this.pianeti = new ArrayList<>();
    }

    public List<Pianeta> getPianeti() {
        return pianeti;
    }

}
