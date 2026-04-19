package utilityClass;

import arnaldoLib.InputData;
import strutturaPlanetarium.*;
import java.util.ArrayList;

/**
 * Classe di gestione per i metodi che alterano il planetario. Creano ed eliminano corpi celesti.
 **/

public class Gestione {
    public static Stella inizializzaSistemaStellare(ArrayList<CorpoCeleste> sistemaStellare) {
        String nomeStella;
        double massaStella;

        System.out.print("\u001B[34m");
        nomeStella = InputData.readNonEmptyString("Nome stella: ", false);
        massaStella = InputData.readDouble("Massa stella: " );
        System.out.print("\u001B[0m");

        Stella stella = new Stella(nomeStella, massaStella);
        sistemaStellare.add(stella);
        return stella;
    }

    public static void aggiungiPianeta(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        System.out.println("\u001B[34m");
        String nome = chiediNomeUnivoco(sistemaStellare, "Inserisci il nome del Pianeta: ");

        double massa = InputData.readDoubleWithMinimum("Inserisci Massa: ", 0);
        double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dalla Stella: ", 0);
        double angolo = InputData.readDouble("Inserisci l'angolo di riferimento: ");
        System.out.println("\u001B[0m");

        Pianeta pianeta = new Pianeta(nome, massa, stella, distanza, angolo);
        stella.aggiungiPianeta(pianeta);
        sistemaStellare.add(pianeta);

        System.out.println("\u001B[32m");
        System.out.printf("%s è stato aggiunto, ID: %s \n", pianeta.getNome(), pianeta.getCodiceUnivoco());
        System.out.println("\u001B[0m");
    }

    public static void aggiungiLuna(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        if(!stella.getPianeti().isEmpty()) {
            System.out.println("\u001B[34m");
            String pianetaCercatoString = InputData.readNonEmptyString("Inserisci il Pianeta intorno a cui orbita la Luna (ID o nome): ", false);
            System.out.println("\u001B[0m");
            Pianeta pianeta = (Pianeta) Utility.cercaCorpoCeleste(stella.getPianeti(), pianetaCercatoString);
            if(pianeta!=null) {
                System.out.println("\u001B[34m");
                String nome = chiediNomeUnivoco(sistemaStellare, "Inserisci il nome della Luna: ");
                double massa = InputData.readDoubleWithMinimum("Inserisci la Massa della Luna: ", 0);
                double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dal Pianeta: ", 0);
                double angolo = InputData.readDouble("Inserisci l'angolo di riferimento: ");
                System.out.println("\u001B[0m");

                Luna luna = new Luna(nome, massa, pianeta, distanza, angolo);
                pianeta.aggiungiLuna(luna);
                sistemaStellare.add(luna);
                System.out.println("\u001B[32m");
                System.out.printf("%s è stata aggiunta a %s, ID: %s \n", luna.getNome(), pianeta.getNome(), luna.getCodiceUnivoco());
                System.out.println("\u001B[0m");
            }
            else {
                Utility.printColored("Impossibile trovare il pianeta specificato", 31);
            }
        }
        else{
            Utility.printColored("Si prega di inserire un pianeta prima di continuare", 33);
        }
    }

    private static String chiediNomeUnivoco(ArrayList<CorpoCeleste> sistemaStellare, String message) {
        boolean duplicato;
        String nome;
        do {
            nome = InputData.readNonEmptyString(message, false);
            duplicato = Utility.esisteQuestoNome(sistemaStellare, nome);
            if (duplicato) {
                Utility.printColored("Impossibile aggiungere nomi duplicati", 33);
            }
        } while (duplicato);
        return nome;
    }

    public static void rimuoviPianeta(ArrayList<CorpoCeleste> sistemaStellare, Stella stella, String idPianeta){
        Pianeta pianeta = (Pianeta) Utility.cercaCorpoCeleste(sistemaStellare, idPianeta);

        if(pianeta != null){
            stella.rimuoviPianeta(pianeta);
            sistemaStellare.remove(pianeta);
            Utility.printColored("******* pianeta eliminato", 31);
        } else {
            Utility.printColored("******* pianeta non trovato !!", 33);
        }
    }

    public static void rimuoviLuna(ArrayList<CorpoCeleste> sistemaStellare, String idPianeta, String idLuna){
        Pianeta pianeta = (Pianeta) Utility.cercaCorpoCeleste(sistemaStellare, idPianeta);
        Luna luna = (Luna) Utility.cercaCorpoCeleste(sistemaStellare, idLuna);

        if(pianeta != null){
            if(luna != null){
                pianeta.rimuoviLuna(luna);
                sistemaStellare.remove(luna);
                Utility.printColored("******* luna eliminata", 31);
            } else {
                Utility.printColored("******* luna non trovata !!", 33);
            }
        } else {
            Utility.printColored("******* pianeta non trovato !!", 33);
        }
    }


}
