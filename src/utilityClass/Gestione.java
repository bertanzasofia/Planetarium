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

        nomeStella = InputData.readNonEmptyString("Nome stella: ", false);
        massaStella = InputData.readDouble("Massa stella: " );

        Stella stella = new Stella(nomeStella, massaStella);
        sistemaStellare.add(stella);
        return stella;
    }

    public static void aggiungiPianeta(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        String nome = chiediNomeUnivoco("Inserisci il nome del Pianeta: ", stella);

        double massa = InputData.readDoubleWithMinimum("Inserisci Massa: ", 0);
        double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dalla Stella: ", 0);
        double angolo = InputData.readDouble("Inserisci l'angolo di riferimento: ");

        Pianeta pianeta = new Pianeta(nome, massa, stella, distanza, angolo);
        stella.aggiungiPianeta(pianeta);
        sistemaStellare.add(pianeta);
        System.out.printf("%s è stato aggiunto, ID: %s", pianeta.getNome(), pianeta.getCodiceUnivoco());
        System.out.print("\n");
    }

    public static void aggiungiLuna(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        if(!stella.getPianeti().isEmpty()) {
            String pianetaCercatoString = InputData.readNonEmptyString("Inserisci il Pianeta intorno a cui orbita la Luna (ID o nome): ", false);
            Pianeta pianeta = Utility.ricercaPianeta(stella, pianetaCercatoString);
            if(pianeta!=null) {
                String nome = chiediNomeUnivoco("Inserisci il nome della Luna: ", stella);
                double massa = InputData.readDoubleWithMinimum("Inserisci la Massa della Luna: ", 0);
                double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dal Pianeta: ", 0);
                double angolo = InputData.readDouble("Inserisci l'angolo di riferimento: ");

                Luna luna = new Luna(nome, massa, pianeta, distanza, angolo);
                pianeta.aggiungiLuna(luna);
                sistemaStellare.add(luna);
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

    private static String chiediNomeUnivoco(String message, Stella stella) {
        boolean duplicato;
        String nome;
        do {
            nome = InputData.readNonEmptyString(message, false);
            duplicato = Utility.esisteQuestoNome(stella, nome);
            if (duplicato) {
                System.out.println("Impossibile aggiungere nomi duplicati. ");
            }
        } while (duplicato);
        return nome;
    }

    public static void rimuoviPianeta(ArrayList<CorpoCeleste> sistemaStellare, Stella stella, String idPianeta){
        Pianeta pianeta = (Pianeta) Utility.ricercaCorpoCeleste(sistemaStellare, idPianeta);

        if(pianeta != null){
            stella.rimuoviPianeta(pianeta);
            sistemaStellare.remove(pianeta);
            System.out.println("******* pianeta eliminato");
        } else {
            System.out.println("******* pianeta non trovato !!");
        }
    }

    public static void rimuoviLuna(ArrayList<CorpoCeleste> sistemaStellare, String idPianeta, String idLuna){
        Pianeta pianeta = (Pianeta) Utility.ricercaCorpoCeleste(sistemaStellare, idPianeta);
        Luna luna = (Luna) Utility.ricercaCorpoCeleste(sistemaStellare, idLuna);

        if(pianeta != null){
            if(luna != null){
                pianeta.rimuoviLuna(luna);
                sistemaStellare.remove(luna);
                System.out.println("******* luna eliminata");
            } else {
                System.out.println("******* luna non trovata !!");
            }
        } else {
            System.out.println("******* pianeta non trovato !!");
        }
    }


}
