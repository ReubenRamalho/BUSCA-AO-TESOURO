package model;
public class Casa {
    private Elemento elemento;

    public Casa() {
        this.elemento = null;
    }
    
    /**
     * @param elemento a ser posicionada nesta Casa.
     */
    public void colocarElemento(Elemento elemento) {
        this.elemento = elemento;
    }
    
    /**
     * @return elemento posicionada nesta Casa, ou Null se a casa estiver livre.
     */
    public Elemento getElemento() {
        return elemento;
    }
    
    /**
     * @return true se existe uma elemento nesta casa, caso contrario false.
     */
    public boolean possuiElemento() {
        return (elemento != null);
    }
}
