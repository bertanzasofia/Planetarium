package utilityClass;

import arnaldoLib.InputData;
import strutturaPlanetarium.*;
import java.util.ArrayList;

/**
 * Classe di gestione per i metodi che alterano il planetario. Creano ed eliminano corpi celesti.
 **/

public class Gestione {
    public static Stella inizializzaSistemaStellare(ArrayList<CorpoCeleste> sistemaStellare) {
        String nomeStella = Utility.inputStringColored("Nome stella: ", 34);
        double massaStella = Utility.inputDoubleColored("Massa stella: ", 34);

        Stella stella = new Stella(nomeStella, massaStella);
        sistemaStellare.add(stella);
        return stella;
    }

    public static void aggiungiPianeta(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        String nome = Utility.inputStringColored("Inserisci il nome del pianeta: ", 34);
        double massa = Utility.inputDoubleColoredWithMin("Inserisci Massa: ", 34, 0);
        double distanza = Utility.inputDoubleColoredWithMin("Inserisci Distanza dalla stella: ", 34, 0);
        double angolo = Utility.inputDoubleColored("Inserisci Angolo di riferimento: ", 34);

        Pianeta pianeta = new Pianeta(nome, massa, stella, distanza, angolo);
        stella.aggiungiPianeta(pianeta);
        sistemaStellare.add(pianeta);

        System.out.println("\u001B[32m");
        System.out.printf("%s è stato aggiunto, ID: %s \n", pianeta.getNome(), pianeta.getCodiceUnivoco());
        System.out.println("\u001B[0m");
    }

    public static void aggiungiLuna(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        if(!stella.getPianeti().isEmpty()) {
            String pianetaCercatoString = Utility.inputStringColored("Inserisci il nome del pianeta attorno a cui orbita la luna: ", 34);
            Pianeta pianeta = (Pianeta) Utility.ricercaCorpoCeleste(sistemaStellare, pianetaCercatoString);
            if(pianeta!=null) {
                String nome = Utility.inputStringColored("Inserisci il nome della luna: ", 34);
                double massa = Utility.inputDoubleColoredWithMin("Inserisci Massa: ", 34, 0);
                double distanza = Utility.inputDoubleColoredWithMin("Inserisci Distanza dal pianeta: ", 34, 0);
                double angolo = Utility.inputDoubleColored("Inserisci Angolo di riferimento dal pianeta: ", 34);

                Luna luna = new Luna(nome, massa, pianeta, distanza, angolo);
                pianeta.aggiungiLuna(luna);
                sistemaStellare.add(luna);

                System.out.println("\u001B[32m");
                System.out.printf("%s è stata aggiunta a %s, ID: %s \n", luna.getNome(), pianeta.getNome(), luna.getCodiceUnivoco());
                System.out.println("\u001B[0m");
            }
            else {
                Utility.printColored("***Impossibile trovare il pianeta specificato :((", 31);
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
                Utility.printColored("***Impossibile aggiungere nomi duplicati :(((", 33);
            }
        } while (duplicato);
        return nome;
    }

    public static void rimuoviPianeta(ArrayList<CorpoCeleste> sistemaStellare, Stella stella, String idPianeta){
        Pianeta pianeta = (Pianeta) Utility.ricercaCorpoCeleste(sistemaStellare, idPianeta);
        //todo: se la lista di pianeti e lune è vuota non stare a richiedere un input, restituire solo messaggio. Idem se 0 lune ma >1 pianeta per voce 2 dell'elenco
        if(pianeta != null){
            stella.rimuoviPianeta(pianeta);
            sistemaStellare.remove(pianeta);
            Utility.printColored("******* pianeta eliminato", 31);
        } else {
            Utility.printColored("******* pianeta non trovato !!", 33);
        }
    }

    public static void rimuoviLuna(ArrayList<CorpoCeleste> sistemaStellare, String idPianeta, String idLuna){
        Pianeta pianeta = (Pianeta) Utility.ricercaCorpoCeleste(sistemaStellare, idPianeta);
        Luna luna = (Luna) Utility.ricercaCorpoCeleste(sistemaStellare, idLuna);

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
