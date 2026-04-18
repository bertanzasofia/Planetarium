import arnaldoLib.InputData;
import strutturaPlanetarium.CorpoCeleste;
import strutturaPlanetarium.Stella;
import utilityClass.Collisioni;
import utilityClass.Gestione;
import utilityClass.Utility;

import java.util.ArrayList;

public class MenuUtente {
    public static void mainMenu(Stella stella) {
        System.out.println("******* Benvenuto in Planetarium, inserisci la stella di riferimento del sistema per iniziare *******");
        int scelta;
        do{
            System.out.println("\n******* Azioni possibili sulla mappa galattica:");
            System.out.println("1-Aggiungi corpo celeste");
            System.out.println("2-Rimuovi corpo celeste");
            System.out.println("3-Ricerca corpo celeste");
            System.out.println("4-Visualizza sistema");
            System.out.println("5-Calcola centro di massa");
            System.out.println("6-Calcola rotta");
            System.out.println("7-Verifica collisioni");
            System.out.println("0-Uscita");
            scelta = InputData.readInteger("\n******* scegliere un'opzione: ");

            switch (scelta){
                // nuovo corpo celeste, stella hardcoded nel codice se avremo più stelle variabilizzeremo
                case 1: aggiungiCorpoCeleste(Planetarium.sistemaStellare, stella);
                    break;
                // rimuovi corpo celeste
                case 2: rimuoviCorpoCeleste(Planetarium.sistemaStellare, stella);
                    break;
                // ricerca corpo celeste
                case 3: ricercaCorpoCeleste();
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
                case 0: System.out.println("******* Grazie per aver usato il nostro sistema, ciao ciao ******* ");
                    break;
                default: System.out.println("******* Scelta non disponibile !!");
                    break;
            }
        } while(scelta != 0);
    }

    public static void aggiungiCorpoCeleste(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        //int scelta = InputData.readIntegerBetween("", 1, 2);
        String scelta = InputData.readNonEmptyString("Cosa vuoi aggiungere? 1-Pianeta 2-Luna: ", false).toLowerCase();
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
        String scelta = InputData.readNonEmptyString("cosa vuoi rimuovere? 1-Pianeta 2-Luna: ", false).toLowerCase();
        switch (scelta) { //Case senza break sopra a quelli numerici come condizione OR
            case "pianeta":
            case "1":
                System.out.println("!! l'eliminazione di un pianeta comporta l'eliminazione delle sue lune !!");

                String idPianeta = InputData.readNonEmptyString("Pianeta da rimuovere (id o nome): ", false);
                Gestione.rimuoviPianeta(sistemaStellare, stella, idPianeta);
                break;
            case "luna":
            case "2":
                System.out.println("!! inserire prima il pianeta attorno cui orbita la luna !!");
                String idPadre = InputData.readNonEmptyString("Pianeta di riferimento (id o nome): ", false);
                String idLuna = InputData.readNonEmptyString("Luna da eliminare (id o nome): ", false);

                Gestione.rimuoviLuna(sistemaStellare, idPadre, idLuna);
                sistemaStellare.remove(Utility.ricercaCorpoCeleste(sistemaStellare, idLuna));
                break;
        }
    }

    public static void ricercaCorpoCeleste() {
        String idCorpo = InputData.readNonEmptyString("Corpo celeste cercato (id o nome): ", false);
        CorpoCeleste corpoCeleste = Utility.ricercaCorpoCeleste(Planetarium.sistemaStellare, idCorpo);

        if(corpoCeleste != null){
            System.out.println(corpoCeleste);
        } else {
            System.out.println("******* corpo celeste non trovato !!");
        }
    }

    public static void calcoloCentroDiMassa(Stella stella) {
        System.out.println("Massa totale del sistema stellare: " + Utility.calcolaMassa(stella));
        System.out.println("Centro di Massa del sistema stellare: " + Utility.centroMassa(stella).getX()+" "+Utility.centroMassa(stella).getY());
    }

    public static void calcoloRotta() {
        String idPartenza = InputData.readNonEmptyString("Corpo celeste di partenza (id o nome): ", false);
        CorpoCeleste partenza = Utility.ricercaCorpoCeleste(Planetarium.sistemaStellare, idPartenza);

        String idArrivo = InputData.readNonEmptyString("Corpo celeste di arrivo (id o nome): ", false);
        CorpoCeleste destinazione = Utility.ricercaCorpoCeleste(Planetarium.sistemaStellare, idArrivo);

        if(partenza != null && destinazione != null){
            String path = Utility.restituisciPath(Utility.calcolaRotta(partenza, destinazione));
            String distanza = Utility.restituisciDistanza(Utility.calcolaRotta(partenza, destinazione));

            System.out.println("\nutilityClass.Rotta: " + path);
            System.out.println("Distanza da percorrere: " + distanza);

        } else {
            System.out.println("******* corpi celesti non trovati !!");
        }
    }
}
