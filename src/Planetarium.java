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
            scelta = InputData.readInteger("\n******* Scegliere un'opzione: ");

            switch (scelta){
                // nuovo corpo celeste, stella hardcoded nel codice se avremo più stelle variabilizzeremo
                case 1: GestionePlanetario.nuovoCorpoCeleste(stella);
                    break;
                // rimuovi corpo celeste
                case 2: GestionePlanetario.rimuoviCorpoCeleste(stella);
                    break;
                // ricerca corpo celeste
                case 3: Utility.ricercaCorpoCeleste(stella);
                    break;
                // stampo intero sistema
                case 4: Utility.listaSistema(stella);
                    break;
                // calcolo centro di massa
                case 5:
                    System.out.println("Massa totale del sistema planetario: "+Utility.calcolaMassa(stella));
                    System.out.println("Valore del Centro di Massa: "+Utility.centroMassa(stella).getX()+" "+Utility.centroMassa(stella).getY());
                    break;
                // todo: calcolo delle rotte
                case 6:
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
}
