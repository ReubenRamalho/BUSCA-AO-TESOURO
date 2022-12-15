package view;
import javax.swing.JPanel;

import minegame.Jogo;
import model.Casa;
import model.Elemento;
import model.Tabuleiro;

public class TabuleiroGUI extends JPanel {

    private JanelaPrincipal janela;
    private CasaGUI[][] casas;
    private int tamanho;

    /**
     * Creates new form Tabuleiro
     */
    public TabuleiroGUI() {
        // Construtor sem par�metros requerido pela especifica�?o JavaBeans.
    }

    public TabuleiroGUI(JanelaPrincipal janela, int tamanho) {
        this.janela = janela;
        this.tamanho = tamanho;
        initComponents();
        criarCasas();
    }

    /**
     * Preenche o tabuleiro com casas
     */
    private void criarCasas() {
        casas = new CasaGUI[tamanho][tamanho];
        // De cima para baixo
        for (int y = (tamanho-1); y >= 0; y--) {
            // Da esquerda para a direita
            for (int x = 0; x < tamanho; x++) {
                CasaGUI casa = new CasaGUI(x, y, this);
                casas[x][y] = casa;
                add(casa);
            }
        }
    }

    public void atualizar(Jogo jogo) {
        for (int x = 0; x < tamanho; x++) {
            for (int y = 0; y < tamanho; y++) {
                CasaGUI casaGUI = casas[x][y];
                
                Tabuleiro tabuleiro = jogo.getTabuleiro();
                Casa casa = tabuleiro.getCasa(x, y);
                if (casa.possuiElemento()) {
                    Elemento elemento = casa.getElemento();
                    if(elemento.getVisibilidade())
                    {
                        switch (elemento.getID()) {
                            case Elemento.VAZIO:
                                casaGUI.desenharVazio();
                                break;
                            case Elemento.RECOMPENSA:
                                casaGUI.desenharRecompensa();
                                break;
                            case Elemento.BOMBA:
                                casaGUI.desenharBomba();
                                break;
                        }
                    }
                    else {
                        casaGUI.desenharEscondido();
                    }
                }
                else {
                    casaGUI.apagarPeca();
                }
            }
        }
    }

    public JanelaPrincipal getJanela() {
        return janela;
    }

    
    private void initComponents() {
        //editar posteriormente para os valores dinamicos de fase
        setLayout(new java.awt.GridLayout(tamanho, tamanho));
    }
}
