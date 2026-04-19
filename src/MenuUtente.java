import arnaldoLib.InputData;
import jdk.jshell.execution.Util;
import strutturaPlanetarium.*;
import utilityClass.*;
import java.util.ArrayList;

public class MenuUtente {
    public static void mainMenu(Stella stella) {
        int scelta;

        do{
            Utility.printColored("\n******* Azioni possibili sulla mappa galattica:", 35);
            System.out.println("1-Aggiungi corpo celeste");
            System.out.println("2-Rimuovi corpo celeste");
            System.out.println("3-Ricerca corpo celeste");
            System.out.println("4-Visualizza sistema");
            System.out.println("5-Calcola centro di massa");
            System.out.println("6-Calcola rotta");
            System.out.println("7-Verifica collisioni");
            System.out.println("0-Uscita");
            scelta = Utility.inputIntColored("\n******* scegliere un'opzione: ", 35);

            switch (scelta){
                // nuovo corpo celeste, stella hardcoded nel codice se avremo più stelle variabilizzeremo
                case 1: aggiungiCorpoCeleste(Planetarium.sistemaStellare, stella);
                    break;
                // rimuovi corpo celeste
                case 2: rimuoviCorpoCeleste(Planetarium.sistemaStellare, stella);
                    break;
                // ricerca corpo celeste
                case 3: ricercaCorpoCeleste(Planetarium.sistemaStellare);
                    break;
                // stampo intero sistema
                case 4: Utility.stampaSistemaStellare(stella);
                    break;
                // calcolo centro di massa
                case 5: calcoloCentroDiMassa(stella);
                    break;
                // calcolo di una rotta
                case 6: calcoloRotta();
                    break;
                // verifica eventuali collisioni
                case 7: Collisioni.detectCollisioni(stella);
                    break;
                case 0: Utility.printColored("******* Grazie per aver usato il nostro sistema, ciao ciao ******* ", 35);
                    break;
                default: Utility.printColored("**** Scelta non disponibile!!", 31);
                    break;
            }
        } while(scelta != 0);
    }

    public static void aggiungiCorpoCeleste(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        String scelta = Utility.inputStringColored("Cosa vuoi aggiungere? 1-Pianeta 2-Luna: ", 34).toLowerCase();

        switch (scelta) { //Case senza break sopra a quelli numerici come condizione OR
            case "pianeta":
            case "1": Gestione.aggiungiPianeta(sistemaStellare, stella);
                break;
            case "luna":
            case "2": Gestione.aggiungiLuna(sistemaStellare, stella);
                break;
        }
    }

    public static void rimuoviCorpoCeleste(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        String scelta = Utility.inputStringColored("Cosa vuoi rimuovere? 1 -Pianeta 2 -Luna: ", 34).toLowerCase();

        switch (scelta) {
            case "pianeta":
            case "1":
                Utility.printColored("Avviso: L'eliminazione di un pianeta comporta l'eliminazione delle sue lune !", 33);
                String idPianeta = Utility.inputStringColored("Pianeta da rimuovere (id o nome): ", 34);
                Gestione.rimuoviPianeta(sistemaStellare, stella, idPianeta);
                break;
            case "luna":
            case "2":
                Utility.printColored("Inserire il pianeta attorno cui orbita la luna: ", 34);
                String idPadre = Utility.inputStringColored("Pianeta di riferimento (id o nome): ", 34);
                String idLuna = Utility.inputStringColored("Luna da elimiare (id o nome): ", 34);
                Gestione.rimuoviLuna(sistemaStellare, idPadre, idLuna);
                sistemaStellare.remove(Utility.ricercaCorpoCeleste(sistemaStellare, idLuna));
                break;
        }
    }

    public static void ricercaCorpoCeleste(ArrayList<CorpoCeleste> sistemaStellare) {
        String idCorpo = Utility.inputStringColored("Corpo celeste cercato (id o nome): ", 34);
        CorpoCeleste corpoCeleste = Utility.ricercaCorpoCeleste(sistemaStellare, idCorpo);

        if(corpoCeleste != null){
            Utility.printColored("", 32, corpoCeleste);
            //System.out.println(corpoCeleste); //todo: posizione appare come link(?). da fixare?
        } else {
            Utility.printColored("Corpo celeste non trovato!!! :(", 31);
        }
    }

    public static void calcoloCentroDiMassa(Stella stella) {
        Utility.printColored("Massa totale del sistema stellare: ", 32, Utility.calcolaMassa(stella));
        Utility.printColored("Centro di Massa del sistema stellare: ", 32, Utility.centroMassa(stella).getX()+" "+Utility.centroMassa(stella).getY());
    }

    public static void calcoloRotta() {
        String idPartenza = Utility.inputStringColored("Corpo celeste di partenza (id o nome):", 34);
        CorpoCeleste partenza = Utility.ricercaCorpoCeleste(Planetarium.sistemaStellare, idPartenza);
        String idArrivo = Utility.inputStringColored("Corpo celeste di arrivo (id o nome): ", 34);
        CorpoCeleste destinazione = Utility.ricercaCorpoCeleste(Planetarium.sistemaStellare, idArrivo);

        if(partenza != null && destinazione != null){
            String path = Utility.restituisciPath(Utility.calcolaRotta(partenza, destinazione));
            String distanza = Utility.restituisciDistanza(Utility.calcolaRotta(partenza, destinazione));

            Utility.printColored("\n Rotta: ", 32, path);
            Utility.printColored("\n Distanza da percorrere: ", 32, distanza);
        } else {
            Utility.printColored("Corpi celesti non trovati!!! :(", 31);
        }
    }
}
