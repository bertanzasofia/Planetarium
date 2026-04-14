import arnaldoLib.InputData;

public class Planetarium {
    static void main(String[] args) {
        System.out.println("******* Benvenuto in Planetarium, inserisci la stella di riferimento del sistema per iniziare *******");
        Stella stella = inizializzaSistemaStellare();

        int scelta;
        do{
            // todo: stampa menù delle azioni disponibili

            scelta = InputData.readIntegerBetween("\n******* scegliere un'opzione: ", 0,1);

            switch (scelta){
                case 1:
                    // aggiungi Corpo Celeste
                    break;
                case 2: // rimuovi Corpo Celeste
                    break;
                case 3: // toString Sistema (lista corpi celesti)
                    break;
                case 4: // calcola Centro di massa
                    break;
                case 5: // calcola distanza / percorso (rotta)
                    break;
                case 6: // verifica collisioni
                    break;
                case 7: // cerca Corpo Celeste (se cerco luna identificare il pianeta + stampa info corpo cercato)
                    break;

                case 0:
                    System.out.println("\n******* grazie per aver usato il nostro sistema, ciao ciao ******* ");
                    break;
                default:
                    System.out.println("scelta non disponibile !! \n");
                    break;
            }
        } while(scelta != 0);
    }

    private static Stella inizializzaSistemaStellare() {
        String nomeStella;
        double massaStella;

        nomeStella = InputData.readNonEmptyString("nome stella: ", false);
        massaStella = InputData.readDouble("massa stella: ");
        return new Stella(nomeStella, massaStella);
    }
}
