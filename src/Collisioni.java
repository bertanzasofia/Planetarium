/**
 * Ricerca collisioni nel sistema solare della stella fornita
 * */
public class Collisioni {
    public static void detectCollisioni(Stella stella){

        for(Pianeta pianeta : stella.getPianeti()) {
            for(Pianeta pianetaSecondo : stella.getPianeti()) {
                verificaCollisionePianeti(pianeta, pianetaSecondo);
                for(Luna luna : pianeta.getLune()) {
                    for(Luna lunaSeconda : pianetaSecondo.getLune()){
                        verificaCollisioneLune(pianeta, pianetaSecondo, luna, lunaSeconda);
                        verificaCollisionePianetaLuna(pianeta, pianetaSecondo, lunaSeconda);
                    }
                }
            }
        }
        for(Pianeta pianeta : stella.getPianeti()) {
            stampaCollisioniPianeta(pianeta);
            stampaCollisioniLune(pianeta);
        }
    }

    private static void stampaCollisioniPianeta(Pianeta pianeta) {
        if(!pianeta.getListaCollisioni().isEmpty()) {
            System.out.println(pianeta.getNome() + " collide con: " + pianeta.getListaCollisioni());
        }
    }

    private static void stampaCollisioniLune(Pianeta pianeta) {
        for(Luna luna : pianeta.getLune()){
            if(!luna.getListaCollisioni().isEmpty()) {
                System.out.println(luna.getNome() + " collide con: " + luna.getListaCollisioni());
            }
        }
    }

    private static void verificaCollisionePianetaLuna(Pianeta pianeta, Pianeta pianetaSecondo, Luna lunaSeconda) {
        if((pianetaSecondo.getDistanzaRif()+ lunaSeconda.getDistanzaRif()> pianeta.getDistanzaRif()) && !pianeta.getLune().contains(lunaSeconda) && !pianeta.getListaCollisioni().contains(lunaSeconda.getNome())){
            pianeta.aggiungiCollisione(lunaSeconda);
            lunaSeconda.aggiungiCollisione(pianeta);
        }
    }

    private static void verificaCollisioneLune(Pianeta pianeta, Pianeta pianetaSecondo, Luna luna, Luna lunaSeconda) {
        if((pianeta.getDistanzaRif() - luna.getDistanzaRif() < pianetaSecondo.getDistanzaRif() + lunaSeconda.getDistanzaRif()) && !luna.getListaCollisioni().contains(lunaSeconda.getNome()) && !luna.equals(lunaSeconda) && !pianeta.equals(pianetaSecondo)){
            luna.aggiungiCollisione(lunaSeconda);
        }
    }

    private static void verificaCollisionePianeti(Pianeta pianeta, Pianeta pianetaSecondo) {
        if(Math.abs(pianeta.getDistanzaRif()- pianetaSecondo.getDistanzaRif())<0.000001 && !pianeta.equals(pianetaSecondo)) {
            if(!pianeta.getListaCollisioni().contains(pianetaSecondo.getNome())){
                pianeta.aggiungiCollisione(pianetaSecondo);
            }
        }
    }
}
