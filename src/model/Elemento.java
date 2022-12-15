package model;
public class Elemento extends ElementoAbstrato{

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