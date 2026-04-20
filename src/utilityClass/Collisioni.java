package utilityClass;

import static utilityClass.Costanti.*;
import strutturaPlanetarium.*;

/**
 * Ricerca collisioni nel sistema solare della stella fornita
 **/

public class Collisioni {

    public static void detectCollisioni(Stella stella){
        boolean checkCollisioni = false; //Per stampare un feedback all'utente se non ci sono collisioni
        for(Pianeta pianeta : stella.getPianeti()) {
            for(Pianeta pianetaSecondo : stella.getPianeti()) {
                verificaCollisionePianeti(pianeta, pianetaSecondo);
                for(Luna luna : pianeta.getLune()) {
                    for(Luna lunaSeconda : pianetaSecondo.getLune()){
                        verificaCollisioneLune(pianeta, pianetaSecondo, luna, lunaSeconda);
                        verificaCollisionePianetaLuna(pianeta, pianetaSecondo, lunaSeconda);
                        if(!luna.getCollisioni().isEmpty()){
                            checkCollisioni = true;
                        }
                    }
                }
                if(!pianeta.getCollisioni().isEmpty()){
                    checkCollisioni = true;
                }
            }
        }
        for(Pianeta pianeta : stella.getPianeti()) {
            Utility.printColored(COLLISIONI_PRESENTI, COLORE_ERRORE);
            if(checkCollisioni){
                stampaCollisioniPianeta(pianeta);
                stampaCollisioniLune(pianeta);
            }else{
                Utility.printColored("Non ci sono corpi in rotta di collisione :)", 32);
            }
        }
    }

    private static void stampaCollisioniPianeta(Pianeta pianeta) {
        if(!pianeta.getCollisioni().isEmpty()) {
            System.out.println(pianeta.getNome() + COLLIDE_CON + pianeta.getCollisioni());
        }
    }

    private static void stampaCollisioniLune(Pianeta pianeta) {
        for(Luna luna : pianeta.getLune()){
            if(!luna.getCollisioni().isEmpty()) {
                System.out.println(luna.getNome() + COLLIDE_CON + luna.getCollisioni());
            }
        }
    }

    private static void verificaCollisionePianetaLuna(Pianeta pianeta, Pianeta pianetaSecondo, Luna lunaSeconda) {
        if((pianetaSecondo.getDistanzaRif()+ lunaSeconda.getDistanzaRif()> pianeta.getDistanzaRif()) && !pianeta.getLune().contains(lunaSeconda) && !pianeta.getCollisioni().contains(lunaSeconda.getNome())){
            pianeta.aggiungiCollisione(lunaSeconda);
            lunaSeconda.aggiungiCollisione(pianeta);
        }
    }

    private static void verificaCollisioneLune(Pianeta pianeta, Pianeta pianetaSecondo, Luna luna, Luna lunaSeconda) {
        if((pianeta.getDistanzaRif() - luna.getDistanzaRif() < pianetaSecondo.getDistanzaRif() + lunaSeconda.getDistanzaRif()) && !luna.getCollisioni().contains(lunaSeconda.getNome()) && !luna.equals(lunaSeconda) && !pianeta.equals(pianetaSecondo)){
            luna.aggiungiCollisione(lunaSeconda);
        }
    }

    private static void verificaCollisionePianeti(Pianeta pianeta, Pianeta pianetaSecondo) {
        if(Math.abs(pianeta.getDistanzaRif()- pianetaSecondo.getDistanzaRif())< FI && !pianeta.equals(pianetaSecondo)) {
            if(!pianeta.getCollisioni().contains(pianetaSecondo.getNome())){
                pianeta.aggiungiCollisione(pianetaSecondo);
            }
        }
    }
}
