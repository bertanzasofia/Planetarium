import arnaldoLib.InputData;
/**
 modifica di Planetarium
 **/

public class GestionePlanetario {
    public static Stella inizializzaSistemaStellare() {
        String nomeStella;
        double massaStella;

        nomeStella = InputData.readNonEmptyString("Inserisci nome Stella: ", false);
        massaStella = InputData.readDouble("Inserisci la Massa della Stella:" );
        return new Stella(nomeStella, massaStella);
    }

    public static void nuovoCorpoCeleste(Stella stella) {
        int scelta = InputData.readIntegerBetween("cosa vuoi aggiungere? 1- Pianeta 2- Luna: ", 1, 2);
        switch (scelta) {
            case 1: aggiungiPianeta(stella);
                break;
            case 2: aggiungiLuna(stella);
                break;
        }
    }

    public static void aggiungiPianeta(Stella stella) {
        boolean duplicato = true;
        String nome;
        do {
            nome = InputData.readNonEmptyString("Inserisci il nome del Pianeta: ", false);
            duplicato = Utility.esisteQuestoNome(stella, nome);
            if(duplicato){
                System.out.println("Impossibile aggiungere nomi duplicati. ");
            }
        }while(duplicato);

        double massa = InputData.readDoubleWithMinimum("Inserisci Massa: ", 0);
        double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dalla Stella: ", 0);
        double angolo = InputData.readDouble("Inserisci l'angolo di riferimento: ");

        Pianeta pianeta = new Pianeta(nome, massa, stella, distanza, angolo);
        stella.getPianeti().add(pianeta);
        System.out.printf("%s è stato aggiunto, ID: %s", pianeta.getNome(), pianeta.getCodiceUnivoco());
        System.out.print("\n");
    }

    public static void aggiungiLuna(Stella stella) {
        if(!stella.getPianeti().isEmpty()) {
            String pianetaCercatoString = InputData.readNonEmptyString("Inserisci il Pianeta intorno a cui orbita la Luna (ID o nome): ", false);
            Pianeta pianeta = Utility.ricercaPianeta(stella, pianetaCercatoString);
            if(pianeta!=null) {
                boolean duplicato = true;
                boolean lunaDuplicata = true;
                String nome;
                do {
                    nome = InputData.readNonEmptyString("Inserisci il nome della Luna: ", false);
                    lunaDuplicata = Utility.esisteQuestoNome(stella, nome);
                    if (lunaDuplicata) {
                        System.out.println("Impossibile aggiungere nomi duplicati. ");
                    }
                } while (lunaDuplicata);
                double massa = InputData.readDoubleWithMinimum("Inserisci la Massa della Luna: ", 0);
                double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dal Pianeta: ", 0);
                double angolo = InputData.readDouble("Inserisci l'angolo di riferimento: ");

                Luna luna = new Luna(nome, massa, stella, distanza, angolo);
                pianeta.getLune().add(luna);
                System.out.printf("%s è stata aggiunta a %s, ID: %s", luna.getNome(), pianeta.getNome(), luna.getCodiceUnivoco());
                System.out.print("\n");
            }
            else {
                System.out.println("Impossibile trovare il Pianeta specificato.");
            }
        }
        else{
            System.out.println("Si prega di inserire un Pianeta prima di continuare.");
        }
    }

    public static void rimuoviCorpoCeleste(Stella stella) {
        int scelta = InputData.readIntegerBetween("cosa vuoi eliminare? 1- Pianeta 2- Luna: ", 1, 2);
        switch (scelta) {
            case 1: rimuoviPianeta(stella);
                break;
            case 2: rimuoviLuna(stella);
                break;
        }
    }

    public static void rimuoviPianeta(Stella stella){
        System.out.println("!! l'eliminazione di un pianeta comporta l'eliminazione delle sue lune !!");
        String idPianeta = InputData.readNonEmptyString("id o nome pianeta: ", false);

        if(Utility.ricercaPianeta(stella, idPianeta) != null){
            stella.getPianeti().remove(Utility.ricercaPianeta(stella, idPianeta));
            System.out.println("******* pianeta eliminato");
        } else {
            System.out.println("******* pianeta non trovato !!");
        }
    }

    public static void rimuoviLuna(Stella stella){
        System.out.println("!! inserire prima il pianeta attorno cui orbita la luna !!");
        String idPianeta = InputData.readNonEmptyString("id o nome pianeta: ", false);
        String idLuna = InputData.readNonEmptyString("id o nome luna: ", false);

        /*if(Utility.ricercaPianeta(stella, idPianeta) != null){
            stella.getPianeti().remove(Utility.ricercaPianeta(stella, idPianeta));
            System.out.println("******* pianeta eliminato");
        } else {
            System.out.println("******* pianeta non trovato !!");
        }*/
    }
}
