package utilityClass;

import strutturaPlanetarium.*;
import java.util.ArrayList;

/**
 * Classe di utilità per i soli metodi che non alterano il planetario. Ricercano e restituiscono valori.
 **/

public class Utility {
    public static CorpoCeleste cercaCorpoCeleste(ArrayList<? extends CorpoCeleste> sistemaStellare, String idCorpo) {
        for (CorpoCeleste corpo : sistemaStellare) {
            if (corpo.getNome().equalsIgnoreCase(idCorpo) || corpo.getCodiceUnivoco().equalsIgnoreCase(idCorpo)) {
                return corpo;
            }
        }
        return null;
    }

    // todo: magari possiamo definire il toString in stella per questa funzione
    // todo: la stella stampa solo il nome, manca la massa
    public static void stampaSistemaStellare(Stella stella){
        System.out.println("Stella: " + stella.getNome());
        for(Pianeta pianeta : stella.getPianeti()){
            System.out.println(pianeta.toString());
            // todo: perchè scorri lune ??
            for(Luna luna : pianeta.getLune()){
                System.out.println(pianeta.getLune().toString());
            }
        }
    }

    public static boolean esisteQuestoNome(ArrayList<CorpoCeleste> sistemaStellare, String nome) {
        boolean duplicato = true;
            CorpoCeleste trovato = cercaCorpoCeleste(sistemaStellare, nome);
                if (trovato==null) {
                    duplicato = false;
                }
        return duplicato;
    }

    public static double calcolaMassa(Stella stella){ //calcola la massa totale del sistema planetario
        double sommaMassa = stella.getMassa();
        for(Pianeta pianeta: stella.getPianeti()){
            sommaMassa += pianeta.getMassa();
            for (Luna luna : pianeta.getLune()) {
                sommaMassa += luna.getMassa();
            }
        }
        return sommaMassa;
    }

    public static Punto centroMassa(Stella stella){//calcola la somma pesata del sistema cioè la massa che moltplica la posizione di ogni corpo celeste
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
                path.append(" > ");
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

    public static void printColored(String testo, int colore){
        System.out.println("\u001B["+colore+"m "+testo+" \u001B[0m");
    }

    public static void printColored(String testo, int colore, String args){
        System.out.println("\u001B["+colore+"m "+testo+" \u001B[0m"+args);
    }
}
