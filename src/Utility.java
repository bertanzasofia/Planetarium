import arnaldoLib.InputData;
/**
 * Classe di utilità per i soli metodi che non alterano il planetario. Ricercano e restituiscono valori.
 * */
public class Utility {
    public static void listaSistema(Stella stella){
        System.out.println("Stella : " + stella.getNome());
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
}
