import strutturaPlanetarium.*;
import static utilityClass.Costanti.*;
import utilityClass.Gestione;
import utilityClass.Utility;
import java.util.ArrayList;

public class Planetarium {
    public static ArrayList<CorpoCeleste> sistemaStellare = new ArrayList<>();

    static void main(String[] args) {
        Utility.printColored(MESSAGGIO_BENVENUTO, COLORE_BELLEZZA);
        Stella stella = Gestione.inizializzaSistemaStellare(sistemaStellare);

        MenuUtente.mainMenu(stella);
    }
}
