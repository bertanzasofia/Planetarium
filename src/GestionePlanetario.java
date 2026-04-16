import arnaldoLib.InputData;
/**
 * Classe di tutte le funzioni che modificano il planetario
 * */

public class GestionePlanetario {
    public static Stella inizializzaSistemaStellare() {
        String nomeStella;
        double massaStella;

        nomeStella = InputData.readNonEmptyString("nome stella: ", false);
        massaStella = InputData.readDouble("massa stella: ");
        return new Stella(nomeStella, massaStella);
    }

    public static void aggiungiPianeta(Stella stella) {
        boolean duplicato = true;
        String nome;
        do {
            nome = InputData.readNonEmptyString("Inserisci nome pianeta: ", false);
            duplicato = Utility.esisteQuestoNome(stella, nome);
            if(duplicato){
                System.out.println("Impossibile aggiungere nomi duplicati. ");
            }
        }while(duplicato);

        double massa = InputData.readDoubleWithMinimum("Inserisci massa: ", 0);
        double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dalla stella: ", 0);
        double angolo = InputData.readDouble("Inserisci l'angolo a cui si trova: ");

        Pianeta pianeta = new Pianeta(nome, massa, stella, distanza, angolo);
        stella.getPianeti().add(pianeta);
        System.out.printf("%s è stato aggiunto, ID: %s", pianeta.getNome(), pianeta.getCodiceUnivoco());
    }

    public static void rimuoviPianeta(Stella stella, String idPianeta){
        stella.getPianeti().remove(Utility.ricercaPianeta(stella, idPianeta));
    }

    public static void aggiungiLuna(Stella stella) {
        if(!stella.getPianeti().isEmpty()) {
            String pianetaCercatoString = InputData.readNonEmptyString("Inserisci il pianeta intorno a cui orbita la luna (ID o nome): ", false);
            Pianeta pianeta = Utility.ricercaPianeta(stella, pianetaCercatoString);
            if(pianeta!=null) {
                boolean duplicato = true;
                boolean lunaDuplicata = true;
                String nome;
                do {
                    nome = InputData.readNonEmptyString("Inserisci nome luna: ", false);
                    lunaDuplicata = Utility.esisteQuestoNome(stella, nome);
                    if (lunaDuplicata) {
                        System.out.println("Impossibile aggiungere nomi duplicati. ");
                    }
                } while (lunaDuplicata);
                double massa = InputData.readDoubleWithMinimum("Inserisci massa: ", 0);
                double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dal pianeta: ", 0);
                double angolo = InputData.readDouble("Inserisci l'angolo a cui si trova: ");

                Luna luna = new Luna(nome, massa, stella, distanza, angolo);
                pianeta.getLune().add(luna);
                System.out.printf("%s è stata aggiunta a %s, ID: %s", luna.getNome(), pianeta.getNome(), luna.getCodiceUnivoco());
            }
            else {
                System.out.println("Impossibile trovare il pianeta specificato.");
            }
        }
        else{
            System.out.println("Si prega di inserire un pianeta prima di continuare.");
        }
    }
}
