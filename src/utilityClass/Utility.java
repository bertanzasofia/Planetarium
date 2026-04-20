package utilityClass;

import arnaldoLib.InputData;
import strutturaPlanetarium.*;
import java.util.ArrayList;
import static utilityClass.Costanti.*;

/**
 * Classe di utilità per i soli metodi che non alterano il planetario. Ricercano e restituiscono valori.
 **/

public class Utility {

    public static CorpoCeleste ricercaCorpoCeleste(ArrayList<CorpoCeleste> sistemaStellare, String idCorpo) {
        for (CorpoCeleste corpo : sistemaStellare) {
            // OR necessario se qualcuno prova a usare un ID già esistente come nome
            if (corpo.getNome().equalsIgnoreCase(idCorpo) || corpo.getCodiceUnivoco().equalsIgnoreCase(idCorpo)) {
                return corpo;
            }
        }
        return null;
    }

    public static String chiediNomeUnivoco(ArrayList<CorpoCeleste> sistemaStellare, String message) {
        boolean duplicato;
        String nome;
        do {
            nome = inputStringColored(message, COLORE_INPUT);
            duplicato = Utility.esisteQuestoNome(sistemaStellare, nome);
            if (duplicato) {
                Utility.printColored(NO_NOMI_DUPLICATI, COLORE_AVVISO);
            }
        } while (duplicato);
        return nome;
    }

    public static boolean esisteQuestoNome(ArrayList<CorpoCeleste> sistemaStellare, String nome) {
        boolean duplicato = true;
            CorpoCeleste trovato = ricercaCorpoCeleste(sistemaStellare, nome);
                if (trovato==null) {
                    duplicato = false;
                }
        return duplicato;
    }

    public static boolean almenoUnaLuna(Stella stella){
        for(Pianeta pianeta: stella.getPianeti()){
            if (!pianeta.getLune().isEmpty())
                return true;
        }
        return false;
    }

    // calcola la massa totale del sistema planetario
    public static double calcolaMassa(Stella stella){
        double sommaMassa = stella.getMassa();
        for(Pianeta pianeta: stella.getPianeti()){
            sommaMassa += pianeta.getMassa();
            for (Luna luna : pianeta.getLune()) {
                sommaMassa += luna.getMassa();
            }
        }
        return sommaMassa;
    }

    // calcola la somma pesata del sistema cioè la massa che moltIplica la posizione di ogni corpo celeste
    public static Punto centroMassa(Stella stella){
        double sommaMassaX = stella.getMassa()*stella.getPosizioneAssoluta().getX();
        double sommaMassaY = stella.getMassa()*stella.getPosizioneAssoluta().getY();
        for(Pianeta pianeta: stella.getPianeti()){
            sommaMassaX += pianeta.getMassa()*pianeta.getPosizioneAssoluta().getX();
            sommaMassaY += pianeta.getMassa()*pianeta.getPosizioneAssoluta().getY();
            for (Luna luna : pianeta.getLune()) {
                sommaMassaX += luna.getMassa()*luna.getPosizioneAssoluta().getX();
                sommaMassaY += luna.getMassa()*luna.getPosizioneAssoluta().getY();
            }
        }
        double centroX = sommaMassaX /calcolaMassa(stella);
        double centroY = sommaMassaY/ calcolaMassa(stella);
        return new Punto(centroX,centroY);
    }

    public static ArrayList<CorpoCeleste> calcolaRotta(CorpoCeleste partenza, CorpoCeleste destinazione) {
        ArrayList<CorpoCeleste> path1 = partenza.getPath();
        ArrayList<CorpoCeleste> path2 = destinazione.getPath();
        ArrayList<CorpoCeleste> rotta = new ArrayList<>();

        int i = path1.size() - 1;
        int j = path2.size() - 1;
        CorpoCeleste antenatoComune = null;

        while (i >= 0 && j >= 0 && path1.get(i).equals(path2.get(j))) {
            antenatoComune = path1.get(i);
            i--;
            j--;
        }

        for (int k=0; k<= i; k++) {
            rotta.add(path1.get(k));
        }
        rotta.add(antenatoComune);
        for (int k=j; k>=0; k--) {
            rotta.add(path2.get(k));
        }

        return rotta;
    }

    public static String restituisciPath(ArrayList<CorpoCeleste> rotta){
        StringBuilder path = new StringBuilder();

        for (CorpoCeleste corpo : rotta) {
            if (!path.isEmpty()) {
                path.append(MAGGIOREDI_SPAZIATO);
            }
            path.append(corpo.getNome());
        }

        return path.toString();
    }

    public static String restituisciDistanza(ArrayList<CorpoCeleste> rotta){
        double distanza = 0;

        for (CorpoCeleste corpo : rotta) {
            distanza += corpo.getDistanzaRif();
        }

        return String.valueOf(distanza);
    }

    /**
     * @param colore numero intero associato al colore (30->37)
     *               31 errori e eliminazioni
     *               32 output richieste
     *               33 avvisi
     *               34 input utente
     *               35 per bellezza
     * */
    public static void printColored(String testo, int colore){
        System.out.println(TAG_COLORE_APRI+colore+M+testo+TAG_COLORE_CHIUDI);
    }

    public static void printColored(String testo, int colore, String args){
        System.out.println(TAG_COLORE_APRI+colore+M+testo+args+TAG_COLORE_CHIUDI);
    }

    public static void printColored(String testo, int colore, CorpoCeleste args){
        System.out.println(TAG_COLORE_APRI+colore+M+testo+args+TAG_COLORE_CHIUDI);
    }

    public static void printColored(String testo, int colore, double args){
        System.out.println(TAG_COLORE_APRI+colore+M+testo+args+TAG_COLORE_CHIUDI);
    }

    public static String inputStringColored(String messaggio, int colore) {
        return InputData.readNonEmptyString(TAG_COLORE_APRI+colore+M+messaggio+TAG_COLORE_CHIUDI, false);
    }

    public static int inputIntColored(String messaggio, int colore) {
        return InputData.readIntegerBetween(TAG_COLORE_APRI+colore+M+messaggio+TAG_COLORE_CHIUDI, 0, 7);
    }

    public static double inputDoubleColored(String messaggio, int colore) {
        return InputData.readDouble(TAG_COLORE_APRI+colore+M+messaggio+TAG_COLORE_CHIUDI);
    }

    public static double inputDoubleColoredWithMin(String messaggio, int colore, double min) {
        return InputData.readDoubleWithMinimum(TAG_COLORE_APRI+colore+M+messaggio+TAG_COLORE_CHIUDI, min);
    }
}
