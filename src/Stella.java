import arnaldoLib.InputData;

import java.util.ArrayList;
import java.util.List;

public class Stella extends CorpoCeleste {
    private List<Pianeta> pianeti;

    public Stella(String nome, double massa) {
        super(nome, massa, 0, 0);
        this.pianeti = new ArrayList<>();
    }

    public List<Pianeta> getPianeti() {
        return pianeti;
    }

    //funzioni specifiche
    public Pianeta ricercaPianeta(String pianetaCercato){
        //Ricerca pianeta per ID univoco
        for(Pianeta pianeta: pianeti)
        {
            if(pianeta.getCodiceUnivoco().equalsIgnoreCase(pianetaCercato) || pianeta.getNome().equalsIgnoreCase(pianetaCercato))
            {
                return pianeta;
            }
        }
        return null;
    }

    public void aggiungiPianeta(Stella nodoRif) {
        boolean duplicato = true;
        String nome;
        do {
            nome = InputData.readNonEmptyString("Inserisci nome pianeta: ", false);
            duplicato = esisteQuestoNome(nome);
            if(duplicato){
                System.out.println("Impossibile aggiungere nomi duplicati. ");
            }
        }while(duplicato);

        double massa = InputData.readDoubleWithMinimum("Inserisci massa: ", 0);
        double distanza = InputData.readDoubleWithMinimum("Inserisci la distanza dalla stella: ", 0);
        double angolo = InputData.readDoubleBetween("Inserisci l'angolo a cui si trova: ",0,360);

        Pianeta pianeta = new Pianeta(nome, massa, nodoRif, distanza, angolo);
        pianeti.add(pianeta);
        System.out.printf("%s è stato aggiunto, ID: %s", pianeta.getNome(), pianeta.getCodiceUnivoco());
        System.out.println(" ");
    }

    public boolean esisteQuestoNome(String nome) {
        boolean duplicato = true;
        if(pianeti.isEmpty()) {
            return false;
        }
        else{
            for (Pianeta pianeta : getPianeti()) {
                if (pianeta.ricercaLuna(nome) == null && ricercaPianeta(nome) == null) {
                    duplicato = false;
                }
            }
        }

        return duplicato;
    }

    public void aggiungiLuna(Stella stella){ //Questa funzione verifica che il pianeta esista e chiama la funzione aggiungiLuna specifica in Pianeta
        if(!pianeti.isEmpty()) {
            String pianetaCercatoString = InputData.readNonEmptyString("Inserisci il pianeta intorno a cui orbita la luna (ID o nome): ", false);
            Pianeta pianetaCercatoObj = stella.ricercaPianeta(pianetaCercatoString);
            if(pianetaCercatoObj!=null) {
                pianetaCercatoObj.aggiungiLuna(stella);
            }
            else {
                System.out.println("Impossibile trovare il pianeta specificato.");
            }
        }
        else{
            System.out.println("Si prega di inserire un pianeta prima di continuare.");
        }
    }

    public void rimuoviPianeta(String idPianeta){
        pianeti.remove(ricercaPianeta(idPianeta));
    }

    public void listaSistema(){ //Questo metodo stampa la lista del sistema planetario.
        System.out.println("Stella : " + super.toString());
        for(Pianeta pianeta : pianeti){
            System.out.println(pianeta.toString());
            for(Luna luna : pianeta.getLune()){
                System.out.println(pianeta.getLune().toString());
            }
        }

    }
    public double calcolaMassa(){ //calcola la massa totale del sistema planetario
        double sommaMassa = getMassa();
        for(Pianeta pianeta: pianeti){
            sommaMassa += pianeta.getMassa();
            for (Luna luna : pianeta.getLune()) {
                sommaMassa += luna.getMassa();
            }
        }
        return sommaMassa;
    }
    public Punto calcolaSommaPesata(){//calcola la somma pesata del sistema cioè la massa che moltplica la posizione di ogni corpo celeste
        double sommaMassaX = getMassa()*getPosizioneAssoluta().getX();
        double sommaMassaY = getMassa()*getPosizioneAssoluta().getY();
        for(Pianeta pianeta: pianeti){
            sommaMassaX += pianeta.getMassa()*pianeta.getPosizioneAssoluta().getX();
            sommaMassaY += pianeta.getMassa()*pianeta.getPosizioneAssoluta().getY();
            for (Luna luna : pianeta.getLune()) {
                sommaMassaX += luna.getMassa()*luna.getPosizioneAssoluta().getX();
                sommaMassaY += luna.getMassa()*luna.getPosizioneAssoluta().getY();
            }
        }
        return new Punto(sommaMassaX,sommaMassaY);
    }
    public Punto centroMassa(){//calcolo del centro di massa
        double sommaMassa = calcolaMassa();
        double centroX = calcolaSommaPesata().getX()/ calcolaMassa();
        double centroY = calcolaSommaPesata().getY()/ calcolaMassa();
        return new Punto(centroX,centroY);
    }
}
