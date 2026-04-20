import strutturaPlanetarium.*;
import utilityClass.*;
import java.util.ArrayList;

import static utilityClass.Costanti.*;

public class MenuUtente {
    public static void mainMenu(Stella stella) {
        int scelta;

        do{
            Utility.printColored(AZIONI_POSSIBILI, COLORE_BELLEZZA);
            System.out.println(AGGIUNGI_CORPO);
            System.out.println(RIMUOVI_CORPO);
            System.out.println(RICERCA_CORPO);
            System.out.println(VISUALIZZA_SISTEMA);
            System.out.println(CALCOLA_CENTRO_DI_MASSA);
            System.out.println(CALCOLA_ROTTA);
            System.out.println(VERIFICA_COLLISIONI);
            System.out.println(USCITA);
            scelta = Utility.inputIntColored(SCEGLI_OPZIONE, COLORE_INPUT);

            switch (scelta){
                // stella hardcoded nel codice se avremo più stelle variabilizzeremo
                case 1: aggiungiCorpoCeleste(Planetarium.sistemaStellare, stella);
                    break;
                case 2: rimuoviCorpoCeleste(Planetarium.sistemaStellare, stella);
                    break;
                case 3: ricercaCorpoCeleste(Planetarium.sistemaStellare);
                    break;
                case 4: Utility.printColored(TESTO_VUOTO, COLORE_INPUT, stella);
                    break;
                case 5: calcoloCentroDiMassa(stella);
                    break;
                case 6: calcoloRotta();
                    break;
                case 7: Collisioni.detectCollisioni(stella);
                    break;
                case 0: Utility.printColored(GRAZIE_CIAO, COLORE_BELLEZZA);
                    break;
                default: Utility.printColored(SCELTA_NON_DISPONIBILE, COLORE_ERRORE);
            }
        } while(scelta != 0);
    }

    public static void aggiungiCorpoCeleste(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        String scelta = Utility.inputStringColored(COSA_AGGIUNGERE, COLORE_INPUT).toLowerCase();

        // case senza break sopra a quelli numerici come condizione OR
        switch (scelta) {
            case "pianeta":
            case "1": Gestione.aggiungiPianeta(sistemaStellare, stella);
                break;
            case "luna":
            case "2": Gestione.aggiungiLuna(sistemaStellare, stella);
                break;
        }
    }

    public static void rimuoviCorpoCeleste(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        if(sistemaStellare.size() == 1){
            Utility.printColored(AVVISO_NO_CORPI, COLORE_AVVISO);
            return;
        }

        String scelta = Utility.inputStringColored(COSA_RIMUOVERE, COLORE_INPUT).toLowerCase();
        switch (scelta) {
            case "pianeta":
            case "1":
                Utility.printColored(AVVISO_ELIMINAZIONE, COLORE_AVVISO);
                String idPianeta = Utility.inputStringColored(PIANETA_DA_RIMUOVERE, COLORE_INPUT);

                Gestione.rimuoviPianeta(sistemaStellare, stella, idPianeta);
                break;
            case "luna":
            case "2":
                if(!Utility.almenoUnaLuna(stella)){
                    Utility.printColored(AVVISO_NO_LUNE, COLORE_AVVISO);
                    return;
                }

                Utility.printColored(AVVISO_RICHIESTA_PIANETA, COLORE_AVVISO);
                String idPadre = Utility.inputStringColored(PIANETA_RIFERIMENTO, COLORE_INPUT);
                String idLuna = Utility.inputStringColored(LUNA_DA_ELIMINARE, COLORE_INPUT);

                Gestione.rimuoviLuna(sistemaStellare, idPadre, idLuna);
                break;
        }
    }

    public static void ricercaCorpoCeleste(ArrayList<CorpoCeleste> sistemaStellare) {
        String idCorpo = Utility.inputStringColored(CORPO_DA_CERCARE, COLORE_INPUT);
        CorpoCeleste corpoCeleste = Utility.ricercaCorpoCeleste(sistemaStellare, idCorpo);

        if(corpoCeleste != null){
            Utility.printColored(TESTO_VUOTO, COLORE_OUTPUT_RICHIESTE, corpoCeleste);
        } else {
            Utility.printColored(CORPO_NON_TROVATO, COLORE_ERRORE);
        }
    }

    public static void calcoloCentroDiMassa(Stella stella) {
        Utility.printColored(MASSA_TOTALE, COLORE_OUTPUT_RICHIESTE, Utility.calcolaMassa(stella));
        Utility.printColored(CENTRO_DI_MASSA, COLORE_OUTPUT_RICHIESTE, String.format("%.3f %.3f", Utility.centroMassa(stella).getX(), Utility.centroMassa(stella).getY()));
    }

    public static void calcoloRotta() {
        String idPartenza = Utility.inputStringColored(CORPO_DI_PARTENZA, COLORE_INPUT);
        CorpoCeleste partenza = Utility.ricercaCorpoCeleste(Planetarium.sistemaStellare, idPartenza);
        String idArrivo = Utility.inputStringColored(CORPO_DI_ARRIVO, COLORE_INPUT);
        CorpoCeleste destinazione = Utility.ricercaCorpoCeleste(Planetarium.sistemaStellare, idArrivo);

        if(partenza != null && destinazione != null){
            String path = Utility.restituisciPath(Utility.calcolaRotta(partenza, destinazione));
            String distanza = Utility.restituisciDistanza(Utility.calcolaRotta(partenza, destinazione));

            Utility.printColored(ROTTA, COLORE_OUTPUT_RICHIESTE, path);
            Utility.printColored(DISTANZA_DA_PERCORRERE, COLORE_OUTPUT_RICHIESTE, distanza);
        } else {
            Utility.printColored(CORPO_NON_TROVATO, COLORE_ERRORE);
        }
    }
}
