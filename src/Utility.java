import arnaldoLib.InputData;
/**
 * Classe di utilità per i soli metodi che non alterano il planetario. Ricercano e restituiscono valori.
 * */
public class Utility {
    public static void listaSistema(Stella stella){
        System.out.println("Stella: " + stella.getNome());
        for(Pianeta pianeta : stella.getPianeti()){
            System.out.println(pianeta.toString());
            for(Luna luna : pianeta.getLune()){
                System.out.println(pianeta.getLune().toString());
            }
        }
    }

    public static Pianeta ricercaPianeta(Stella stella, String pianetaCercato){
        //Ricerca pianeta per ID univoco
        for(Pianeta pianeta: stella.getPianeti())
        {
            if(pianeta.getCodiceUnivoco().equalsIgnoreCase(pianetaCercato) || pianeta.getNome().equalsIgnoreCase(pianetaCercato))
            {
                return pianeta;
            }
        }
        return null;
    }

    public static Luna ricercaLuna(Stella stella, String lunaCercata){
        //Ricerca luna per ID univoco
        for(Pianeta pianeta: stella.getPianeti()) {
            for (Luna luna : pianeta.getLune()) {
                if (luna.getCodiceUnivoco().equalsIgnoreCase(lunaCercata) || luna.getNome().equalsIgnoreCase(lunaCercata)) {
                    return luna;
                }
            }
        }
        return null;
    }

    public static boolean esisteQuestoNome(Stella stella, String nome) {
        boolean duplicato = true;
        if(stella.getPianeti().isEmpty()) {
            return false;
        }
        else{
            for (Pianeta pianeta : stella.getPianeti()) {
                if (ricercaPianeta(stella, nome) == null && ricercaLuna(stella, nome) == null) {
                    duplicato = false;
                }
            }
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

}
