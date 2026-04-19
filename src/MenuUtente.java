import arnaldoLib.InputData;
import jdk.jshell.execution.Util;
import strutturaPlanetarium.CorpoCeleste;
import strutturaPlanetarium.Stella;
import utilityClass.Collisioni;
import utilityClass.Gestione;
import utilityClass.Utility;
import java.util.ArrayList;

public class MenuUtente {
    public static void mainMenu(Stella stella) {
        Utility.printColored("******* Benvenuto in Planetarium, inserisci la stella di riferimento del sistema per iniziare *******", 34);
        scegliVoceMenu(stella);
    }

    private static void scegliVoceMenu(Stella stella) {
        int scelta;
        do{
            Utility.printColored("**Azioni possibili sulla mappa galattica**", 35);
            System.out.println("1-Aggiungi corpo celeste");
            System.out.println("2-Rimuovi corpo celeste");
            System.out.println("3-Ricerca corpo celeste");
            System.out.println("4-Visualizza sistema");
            System.out.println("5-Calcola centro di massa");
            System.out.println("6-Calcola rotta");
            System.out.println("7-Verifica collisioni");
            System.out.println("0-Uscita \u001B[34m");
            scelta = InputData.readInteger("**Scegliere un'opzione** \t");
            System.out.print("\u001B[0m");

            switch (scelta){
                //stella hardcoded nel codice se avremo più stelle variabilizzeremo
                case 1: aggiungiCorpoCeleste(Planetarium.sistemaStellare, stella);
                    break;
                case 2: rimuoviCorpoCeleste(Planetarium.sistemaStellare, stella);
                    break;
                case 3: ricercaCorpoCeleste();
                    break;
                case 4: Utility.stampaSistemaStellare(stella);
                    break;
                case 5: calcoloCentroDiMassa(stella);
                    break;
                case 6: calcoloRotta();
                    break;
                case 7: Collisioni.detectCollisioni(stella);
                    break;
                case 0: Utility.printColored("**Grazie per aver usato il nostro sistema, ciao ciao**", 32);
                    break;
                default: Utility.printColored("**Scelta non disponibile**", 31);
                    break;
            }
        } while(scelta != 0);
    }

    public static void aggiungiCorpoCeleste(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        System.out.println("\u001B[34m");
        String scelta = InputData.readNonEmptyString("Cosa vuoi aggiungere? 1-Pianeta 2-Luna 3-Esci: ", false).toLowerCase();
        System.out.println("\u001B[0m");
        switch (scelta) { //Case senza break sopra a quelli numerici come condizione OR
            case "pianeta":
            case "1": Gestione.aggiungiPianeta(sistemaStellare, stella);
                break;
            case "luna":
            case "2": Gestione.aggiungiLuna(sistemaStellare, stella);
                break;
            case "esci":
            case "3": scegliVoceMenu(stella);
            default:
                Utility.printColored("Input non valido", 33);
        }
    }

    public static void rimuoviCorpoCeleste(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        System.out.println("\u001B[34m");
        String scelta = InputData.readNonEmptyString("cosa vuoi rimuovere? 1-Pianeta 2-Luna: ", false).toLowerCase();
        System.out.println("\u001B[0m");
        switch (scelta) { //Case senza break sopra a quelli numerici come condizione OR
            case "pianeta":
            case "1":
                Utility.printColored("L'eliminazione di un pianeta comporta l'eliminazione delle sue lune !", 33);

                System.out.println("\u001B[34m");
                String idPianeta = InputData.readNonEmptyString("Pianeta da rimuovere (id o nome): ", false);
                System.out.println("\u001B0");
                Gestione.rimuoviPianeta(sistemaStellare, stella, idPianeta);
                break;
            case "luna":
            case "2": //todo: aggiungere check, se non ci sono lune nel sistema non fare questa ricerca e stampa relativo messaggio
                Utility.printColored("Inserire  il pianeta attorno cui orbita la luna: ", 34);
                System.out.println("\u001B[34m");
                String idPadre = InputData.readNonEmptyString("Pianeta di riferimento (id o nome): ", false);
                String idLuna = InputData.readNonEmptyString("Luna da eliminare (id o nome): ", false);
                System.out.println("\u001B[0m");

                Gestione.rimuoviLuna(sistemaStellare, idPadre, idLuna);
                sistemaStellare.remove(Utility.ricercaCorpoCeleste(sistemaStellare, idLuna));
                break;
            case "esci":
            case "3": aggiungiCorpoCeleste(sistemaStellare, stella);
            default:
                Utility.printColored("Input non valido", 33);
        }
    }

    public static void ricercaCorpoCeleste() {
        System.out.println("\u001B[34m");
        String idCorpo = InputData.readNonEmptyString("Corpo celeste cercato (id o nome): ", false);
        System.out.println("\u001B[0m");
        CorpoCeleste corpoCeleste = Utility.ricercaCorpoCeleste(Planetarium.sistemaStellare, idCorpo);

        if(corpoCeleste != null){
            Utility.printColored("**Corpo trovato**", 32);
            System.out.println("\u001B[32m"+corpoCeleste.toString()+"\u001B[0m");
        } else {
            Utility.printColored("*Corpo celeste non trovato*", 33);
        }
    }

    public static void calcoloCentroDiMassa(Stella stella) {
        System.out.println("\u001B[32m");
        //todo: tagliare scritta a n decimali
        System.out.println("Massa totale del sistema stellare: "+Utility.calcolaMassa(stella));
        System.out.println("Centro di Massa del sistema stellare: " + Utility.centroMassa(stella).getX()+" "+Utility.centroMassa(stella).getY());
        System.out.println("\u001B[0m");
    }

    public static void calcoloRotta() {
        System.out.println("\u001B[32m");
        String idPartenza = InputData.readNonEmptyString("Corpo celeste di partenza (id o nome): ", false);
        CorpoCeleste partenza = Utility.ricercaCorpoCeleste(Planetarium.sistemaStellare, idPartenza);

        String idArrivo = InputData.readNonEmptyString("Corpo celeste di arrivo (id o nome): ", false);
        CorpoCeleste destinazione = Utility.ricercaCorpoCeleste(Planetarium.sistemaStellare, idArrivo);
        System.out.println("\u001B[0m");

        if(partenza != null && destinazione != null){
            String path = Utility.restituisciPath(Utility.calcolaRotta(partenza, destinazione));
            String distanza = Utility.restituisciDistanza(Utility.calcolaRotta(partenza, destinazione));

            System.out.println("\u001B[34m");
            System.out.println("Rotta: "+path);
            System.out.println("Distanza da percorrere: "+distanza);
            System.out.println("\u001B[30m");

        } else {
            Utility.printColored("*Corpi celesti non trovati*", 33);
        }
    }
}
