package utilityClass;

import arnaldoLib.InputData;
import strutturaPlanetarium.*;
import java.util.ArrayList;

/**
 * Classe di gestione per i  metodi che  alterano il planetario. Creano ed eliminano corpi celesti.
 **/

public class Gestione {
    public static void nuovoCorpoCeleste(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        int scelta = InputData.readIntegerBetween("cosa vuoi aggiungere? 1- strutturaSistemaStellare.Pianeta 2- strutturaSistemaStellare.Luna: ", 1, 2);
        switch (scelta) {
            case 1: aggiungiPianeta(sistemaStellare, stella);
                break;
            case 2: aggiungiLuna(sistemaStellare, stella);
                break;
        }
    }

    public static void aggiungiPianeta(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        boolean duplicato = true;
        String nome;
        do {
            nome = InputData.readNonEmptyString("Inserisci il nome del strutturaSistemaStellare.Pianeta: ", false);
            duplicato = Utility.esisteQuestoNome(stella, nome);
            if(duplicato){
                System.out.println("Impossibile aggiungere nomi duplicati. ");
            }
        }while(duplicato);

        double massa = InputData.readDoubleWithMinimum("Inserisci Massa: ", 0);
        double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dalla strutturaSistemaStellare.Stella: ", 0);
        double angolo = InputData.readDouble("Inserisci l'angolo di riferimento: ");

        Pianeta pianeta = new Pianeta(nome, massa, stella, distanza, angolo);
        stella.aggiungiPianeta(pianeta);
        sistemaStellare.add(pianeta);
        System.out.printf("%s è stato aggiunto, ID: %s", pianeta.getNome(), pianeta.getCodiceUnivoco());
        System.out.print("\n");
    }

    public static void aggiungiLuna(ArrayList<CorpoCeleste> sistemaStellare, Stella stella) {
        if(!stella.getPianeti().isEmpty()) {
            String pianetaCercatoString = InputData.readNonEmptyString("Inserisci il strutturaSistemaStellare.Pianeta intorno a cui orbita la strutturaSistemaStellare.Luna (ID o nome): ", false);
            Pianeta pianeta = Utility.ricercaPianeta(stella, pianetaCercatoString);
            if(pianeta!=null) {
                boolean lunaDuplicata = true;
                String nome;
                do {
                    nome = InputData.readNonEmptyString("Inserisci il nome della strutturaSistemaStellare.Luna: ", false);
                    lunaDuplicata = Utility.esisteQuestoNome(stella, nome);
                    if (lunaDuplicata) {
                        System.out.println("Impossibile aggiungere nomi duplicati. ");
                    }
                } while (lunaDuplicata);
                double massa = InputData.readDoubleWithMinimum("Inserisci la Massa della strutturaSistemaStellare.Luna: ", 0);
                double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dal strutturaSistemaStellare.Pianeta: ", 0);
                double angolo = InputData.readDouble("Inserisci l'angolo di riferimento: ");

                Luna luna = new Luna(nome, massa, pianeta, distanza, angolo);
                pianeta.aggiungiLuna(luna);
                sistemaStellare.add(luna);
                System.out.printf("%s è stata aggiunta a %s, ID: %s", luna.getNome(), pianeta.getNome(), luna.getCodiceUnivoco());
                System.out.print("\n");
            }
            else {
                System.out.println("Impossibile trovare il strutturaSistemaStellare.Pianeta specificato.");
            }
        }
        else{
            System.out.println("Si prega di inserire un strutturaSistemaStellare.Pianeta prima di continuare.");
        }
    }

    public static void rimuoviPianeta(ArrayList<CorpoCeleste> sistemaStellare, Stella stella, String idPianeta){
        Pianeta pianeta = (Pianeta) Utility.cercaCorpoCeleste(sistemaStellare, idPianeta);

        if(pianeta != null){
            stella.rimuoviPianeta(pianeta);
            sistemaStellare.remove(pianeta);
            System.out.println("******* pianeta eliminato");
        } else {
            System.out.println("******* pianeta non trovato !!");
        }
    }

    public static void rimuoviLuna(ArrayList<CorpoCeleste> sistemaStellare, String idPianeta, String idLuna){
        Pianeta pianeta = (Pianeta) Utility.cercaCorpoCeleste(sistemaStellare, idPianeta);
        Luna luna = (Luna) Utility.cercaCorpoCeleste(sistemaStellare, idLuna);

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
