package model;
public class Elemento {

    // Possivel ID

    /**
     *  ID    Tipo
     *   0   Vazio
     *   1   Recompensa
     *   2   Bomba
     */

    public static final int VAZIO = 0;
    public static final int RECOMPENSA = 1;
    public static final int BOMBA = 2;

    // Atributos 
    protected Casa casa;
    protected int ID;
    //protected int valor;
    protected boolean visibilidade = false;

    public void setCasa(Casa casa) {
        this.casa = casa;
        casa.colocarElemento(this);
    }

    // return o tipo da peca.
    public int getID() {
        return ID;
    }

    public boolean getVisibilidade() {
        return visibilidade;
    }  

    public void revelar() {
        visibilidade = true;
    }

    public void esconder() {
        visibilidade = false;
    }
}
