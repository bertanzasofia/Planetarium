import strutturaPlanetarium.*;
import utilityClass.Gestione;

import java.util.ArrayList;

public class Planetarium {
    public static ArrayList<CorpoCeleste> sistemaStellare = new ArrayList<>();

    static void main(String[] args) {
        Stella stella = Gestione.inizializzaSistemaStellare(sistemaStellare);
        MenuUtente.mainMenu(stella);
    }
}
