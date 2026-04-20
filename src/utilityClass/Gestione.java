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
        String nome = Utility.chiediNomeUnivoco(sistemaStellare, INSERISCI_NOME_DEL_PIANETA);
        double massa = Utility.inputDoubleColoredWithMin(INSERISCI_MASSA, COLORE_INPUT, MIN);
        double distanza = Utility.inputDoubleColoredWithMin(INSERISCI_DISTANZA, COLORE_INPUT, MIN);
        double angolo = Utility.inputDoubleColored(INSERISCI_ANGOLO, COLORE_INPUT);

        Pianeta pianeta = new Pianeta(nome, massa, stella, distanza, angolo);
        stella.aggiungiPianeta(pianeta);
        sistemaStellare.add(pianeta);

        System.out.println(TAG_COLORE_APRI+COLORE_OUTPUT_RICHIESTE+M);
        System.out.printf(PIANETA_STATO_AGGIUNTO, pianeta.getNome(), pianeta.getCodiceUnivoco());
        System.out.println(TAG_COLORE_CHIUDI);
    }

    public static void aggiungiLuna(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        if(!stella.getPianeti().isEmpty()) {
            String pianetaCercatoString = Utility.inputStringColored(INSERISCI_NOME_PIANETA_DI_LUNA, COLORE_INPUT);
            Pianeta pianeta = (Pianeta) Utility.ricercaCorpoCeleste(sistemaStellare, pianetaCercatoString);
            if(pianeta!=null) {
                String nome = Utility.chiediNomeUnivoco(sistemaStellare, INSERISCI_NOME_LUNA);
                double massa = Utility.inputDoubleColoredWithMin(INSERISCI_MASSA, COLORE_INPUT, MIN);
                double distanza = Utility.inputDoubleColoredWithMin(INSERISCI_DISTANZA_DAL_PIANETA, COLORE_INPUT, MIN);
                double angolo = Utility.inputDoubleColored(INSERISCI_ANGOLO_DI_RIFERIMENTO_DAL_PIANETA, COLORE_INPUT);

                Luna luna = new Luna(nome, massa, pianeta, distanza, angolo);
                pianeta.aggiungiLuna(luna);
                sistemaStellare.add(luna);

                System.out.println(TAG_COLORE_APRI+COLORE_OUTPUT_RICHIESTE+M);
                System.out.printf(LUNA_STATA_AGGIUNTA, luna.getNome(), pianeta.getNome(), luna.getCodiceUnivoco());
                System.out.println(TAG_COLORE_CHIUDI);
            }
            else {
                Utility.printColored(IMPOSSIBILE_TROVARE_PIANETA, COLORE_ERRORE);
            }
        }
        else{
            Utility.printColored(INSERISCI_PIANETA_PRIMA_GRAZIE, COLORE_AVVISO);
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
