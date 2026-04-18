import arnaldoLib.InputData;
import strutturaPlanetarium.*;
import utilityClass.*;
import java.util.ArrayList;

public class Planetarium {
    public static ArrayList<CorpoCeleste> sistemaStellare = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("******* Benvenuto in Planetarium, inserisci la stella di riferimento del sistema per iniziare *******");
        Stella stella = inizializzaSistemaStellare();

        int scelta;
        do{
            System.out.println("\n******* Azioni possibili sulla mappa galattica:");
            System.out.println("1 -Aggiungi corpo celeste");
            System.out.println("2 -Rimuovi corpo celeste");
            System.out.println("3 -Ricerca corpo celeste");
            System.out.println("4 -Visualizza sistema");
            System.out.println("5 -Calcola centro di massa");
            System.out.println("6 -Calcola rotta");
            System.out.println("7 -Verifica collisioni");
            System.out.println("0 -Uscita");
            scelta = InputData.readInteger("\n******* Scegliere un'opzione: ");

            switch (scelta){
                // nuovo corpo celeste, stella hardcoded nel codice se avremo più stelle variabilizzeremo
                case 1: Gestione.nuovoCorpoCeleste(sistemaStellare, stella);
                    break;
                // rimuovi corpo celeste
                case 2: rimuoviCorpoCeleste(stella);
                    break;
                // ricerca corpo celeste
                case 3: ricercaCorpoCeleste();
                    break;
                // stampo intero sistema
                case 4: Utility.stampaSistemaStellare(stella);
                    break;
                // calcolo centro di massa
                case 5: calcoloCetroDiMassa(stella);
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


    public static Stella inizializzaSistemaStellare() {
        String nomeStella;
        double massaStella;

        nomeStella = InputData.readNonEmptyString("Nome stella: ", false);
        massaStella = InputData.readDouble("Massa stella: " );

        Stella stella = new Stella(nomeStella, massaStella);
        sistemaStellare.add(stella);
        return stella;
    }

    public static void rimuoviCorpoCeleste(Stella stella) {
        int scelta = InputData.readIntegerBetween("Cosa vuoi eliminare? 1- strutturaSistemaStellare.Pianeta 2- strutturaSistemaStellare.Luna: ", 1, 2);

        switch (scelta) {
            case 1: // pianeta
                System.out.println("!! l'eliminazione di un pianeta comporta l'eliminazione delle sue lune !!");

                String idPianeta = InputData.readNonEmptyString("strutturaSistemaStellare.Pianeta da rimuovere (id o nome): ", false);
                Gestione.rimuoviPianeta(sistemaStellare, stella, idPianeta);
                break;
            case 2: // luna
                System.out.println("!! inserire prima il pianeta attorno cui orbita la luna !!");
                String idPadre = InputData.readNonEmptyString("strutturaSistemaStellare.Pianeta di riferimento (id o nome): ", false);
                String idLuna = InputData.readNonEmptyString("strutturaSistemaStellare.Luna da eliminare (id o nome): ", false);

                Gestione.rimuoviLuna(sistemaStellare, idPadre, idLuna);
                sistemaStellare.remove(Utility.cercaCorpoCeleste(sistemaStellare, idLuna));
                break;
        }
    }

    public static void ricercaCorpoCeleste() {
        String idCorpo = InputData.readNonEmptyString("Corpo celeste cercato (id o nome): ", false);
        CorpoCeleste corpoCeleste = Utility.cercaCorpoCeleste(sistemaStellare, idCorpo);

        if(corpoCeleste != null){
            System.out.println(corpoCeleste);
        } else {
            System.out.println("******* corpo celeste non trovato !!");
        }
    }

    private static void calcoloCetroDiMassa(Stella stella) {
        System.out.println("Massa totale del sistema stellare: " + Utility.calcolaMassa(stella));
        System.out.println("Centro di Massa del sistema stellare: " + Utility.centroMassa(stella).getX()+" "+Utility.centroMassa(stella).getY());
    }

    public static void calcoloRotta() {
        String idPartenza = InputData.readNonEmptyString("Corpo celeste di partenza (id o nome): ", false);
        CorpoCeleste partenza = Utility.cercaCorpoCeleste(sistemaStellare, idPartenza);

        String idArrivo = InputData.readNonEmptyString("Corpo celeste di arrivo (id o nome): ", false);
        CorpoCeleste destinazione = Utility.cercaCorpoCeleste(sistemaStellare, idArrivo);

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
