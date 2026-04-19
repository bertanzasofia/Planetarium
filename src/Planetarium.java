import strutturaPlanetarium.*;
import utilityClass.Gestione;
import utilityClass.Utility;

import java.util.ArrayList;

public class Planetarium {
    public static ArrayList<CorpoCeleste> sistemaStellare = new ArrayList<>();

    static void main(String[] args) {
        Utility.printColored("******* Benvenuto in Planetarium, inserisci la stella di riferimento del sistema per iniziare *******", 35);
        Stella stella = Gestione.inizializzaSistemaStellare(sistemaStellare);

        MenuUtente.mainMenu(stella);
    }
}
