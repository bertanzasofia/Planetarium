import arnaldoLib.InputData;

public class Planetarium {
    public static void main(String[] args) {
        System.out.println("******* Benvenuto in Planetarium, inserisci la stella di riferimento del sistema per iniziare *******");
        Stella stella = GestionePlanetario.inizializzaSistemaStellare();

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
            scelta = InputData.readInteger("\n******* scegliere un'opzione: ");

            switch (scelta){
                // nuovo corpo celeste, stella hardcoded nel codice se avremo più stelle variabilizzeremo
                case 1: GestionePlanetario.nuovoCorpoCeleste(stella);
                    break;
                // todo: rimuovi corpo celeste
                case 2: GestionePlanetario.rimuoviCorpoCeleste(stella);
                    break;
                // todo: ricerca corpo celeste
                // (se cerco luna identificare il pianeta + stampa info corpo cercato)
                case 3:
                    break;
                // stampo intero sistema
                case 4: Utility.listaSistema(stella);
                    break;
                // todo: calcolo centro di massa
                case 5:
                    break;
                // todo: calcolo delle rotte
                case 6:
                    break;
                // verifica eventuali collisioni
                case 7: Collisioni.detectCollisioni(stella);
                    break;
                case 0: System.out.println("******* grazie per aver usato il nostro sistema, ciao ciao ******* ");
                    break;
                default: System.out.println("******* scelta non disponibile !!");
                    break;
            }
        } while(scelta != 0);
    }
}
