package utilityClass;
import static utilityClass.Costanti.*;
import strutturaPlanetarium.*;
import java.util.ArrayList;

/**
 * Classe di gestione per i metodi che alterano il planetario. Creano ed eliminano corpi celesti.
 **/

public class Gestione {

    public static Stella inizializzaSistemaStellare(ArrayList<CorpoCeleste> sistemaStellare) {
        String nomeStella = Utility.inputStringColored(NOME_STELLA, COLORE_INPUT);
        double massaStella = Utility.inputDoubleColored(MASSA_STELLA, COLORE_INPUT);

        Stella stella = new Stella(nomeStella, massaStella);
        sistemaStellare.add(stella);
        return stella;
    }

    public static void aggiungiPianeta(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        String nome = Utility.chiediNomeUnivoco(sistemaStellare, "Inserisci nome del pianeta: ");
        double massa = Utility.inputDoubleColoredWithMin("Inserisci Massa: ", 34, 0);
        double distanza = Utility.inputDoubleColoredWithMin("Inserisci Distanza dalla stella: ", 34, 0);
        double angolo = Utility.inputDoubleColored("Inserisci Angolo di riferimento: ", 34);

        Pianeta pianeta = new Pianeta(nome, massa, stella, distanza, angolo);
        stella.aggiungiPianeta(pianeta);
        sistemaStellare.add(pianeta);

        System.out.println("\u001B[32m");
        System.out.printf("%s è stato aggiunto, ID: %s \n", pianeta.getNome(), pianeta.getCodiceUnivoco());
        System.out.println("\u001B[0m");
    }

    public static void aggiungiLuna(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        if(!stella.getPianeti().isEmpty()) {
            String pianetaCercatoString = Utility.inputStringColored("Inserisci il nome del pianeta attorno a cui orbita la luna: ", 34);
            Pianeta pianeta = (Pianeta) Utility.ricercaCorpoCeleste(sistemaStellare, pianetaCercatoString);
            if(pianeta!=null) {
                String nome = Utility.chiediNomeUnivoco(sistemaStellare, "Inserisci il nome della luna: ");
                double massa = Utility.inputDoubleColoredWithMin("Inserisci Massa: ", 34, 0);
                double distanza = Utility.inputDoubleColoredWithMin("Inserisci Distanza dal pianeta: ", 34, 0);
                double angolo = Utility.inputDoubleColored("Inserisci Angolo di riferimento dal pianeta: ", 34);

                Luna luna = new Luna(nome, massa, pianeta, distanza, angolo);
                pianeta.aggiungiLuna(luna);
                sistemaStellare.add(luna);

                System.out.println("\u001B[32m");
                System.out.printf("%s è stata aggiunta a %s, ID: %s \n", luna.getNome(), pianeta.getNome(), luna.getCodiceUnivoco());
                System.out.println("\u001B[0m");
            }
            else {
                Utility.printColored("***Impossibile trovare il pianeta specificato :((", 31);
            }
        }
        else{
            Utility.printColored("Si prega di inserire un pianeta prima di continuare", 33);
        }
    }

    public static void rimuoviPianeta(ArrayList<CorpoCeleste> sistemaStellare, Stella stella, String idPianeta){
        Pianeta pianeta = (Pianeta) Utility.ricercaCorpoCeleste(sistemaStellare, idPianeta);

        if(pianeta != null){
            stella.rimuoviPianeta(pianeta);
            sistemaStellare.remove(pianeta);
            Utility.printColored(PIANETA_ELIMINATO, COLORE_ERRORE);
        } else {
            Utility.printColored(PIANETA_NON_TROVATO, COLORE_AVVISO);
        }
    }

    public static void rimuoviLuna(ArrayList<CorpoCeleste> sistemaStellare, String idPianeta, String idLuna){
        Pianeta pianeta = (Pianeta) Utility.ricercaCorpoCeleste(sistemaStellare, idPianeta);
        Luna luna = (Luna) Utility.ricercaCorpoCeleste(sistemaStellare, idLuna);

        if(pianeta != null){
            if(luna != null){
                pianeta.rimuoviLuna(luna);
                sistemaStellare.remove(luna);
                Utility.printColored(LUNA_ELIMINATA, COLORE_ERRORE);
            } else {
                Utility.printColored(LUNA_NON_TROVATA, COLORE_AVVISO);
            }
        } else {
            Utility.printColored(PIANETA_NON_TROVATO, COLORE_AVVISO);
        }
    }
}
