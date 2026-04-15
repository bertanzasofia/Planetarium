import arnaldoLib.InputData;

public class Planetarium {
    public static void main(String[] args) {
        System.out.println("******* Benvenuto in Planetarium, inserisci la stella di riferimento del sistema per iniziare *******");
        Stella stella = inizializzaSistemaStellare();

        int scelta;
        do{
            // todo: stampa menù delle azioni disponibili
            System.out.println("Azioni possibili sulla mappa galattica:");
            System.out.println("1-Aggiungi corpo celeste | 2-Rimuovi corpo celeste | 3-Lista sistema planetario | 4-Calcola centro di massa | 5-Calcola rotta | 6-Verifica collisioni | 7-Ricerca corpo celeste | 0-Esci");
            scelta = InputData.readIntegerBetween("\n******* scegliere un'opzione: ", 0,7);

            switch (scelta){
                case 1: // scelta per aggiungere un corpo celeste
                    int sceltaCorpo = InputData.readIntegerBetween("Cosa vuoi aggiungere? 1-Pianeta | 2-Luna: ", 1, 2);
                    switch (sceltaCorpo) {
                        case 1: // aggiungi pianeta
                            stella.aggiungiPianeta(stella); //stella hardcoded nel codice; se avremo più stelle variabilizzeremo
                            break;
                        case 2: // aggiungi luna
                            stella.aggiungiLuna(stella);
                            break;
                    }
                    break;
                case 2: // rimuovi Corpo Celeste

                    break;
                case 3: // toString Sistema (lista corpi celesti)
                    stella.listaSistema();
                    break;
                case 4: // calcola Centro di massa
                    System.out.println("La massa totale del sistema planetario: "+ stella.calcolaMassa());
                    System.out.println("La somma pesata totale del sistema planetario: "+ stella.calcolaSommaPesata().getX() +" "+ stella.calcolaSommaPesata().getY());
                    System.out.println("Il centro di massa del sistema planetario: "+ stella.centroMassa().getX()+" "+ stella.centroMassa().getY());
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

        nomeStella = InputData.readNonEmptyString("Nome stella: ", false);
        massaStella = InputData.readDouble("Massa stella: ");

        return new Stella(nomeStella, massaStella);
    }
}
