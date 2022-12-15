package model;

public abstract class ElementoAbstrato {

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
    protected boolean visibilidade = false;

    
    // return o tipo da peca.
    public abstract int getID(); 

    public abstract boolean getVisibilidade() ;

    public abstract void revelar() ;

    public abstract void esconder() ;
}