package model;
/**
 * O Tabuleiro do jogo. 
 * Respons�vel por armazenar as 64 casas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Tabuleiro {

    private Casa[][] board;

    public Tabuleiro(int tamanho) {
        
        board = new Casa[tamanho][tamanho];
        
        for (int x = 0; x < tamanho; x++) {
            for (int y = 0; y < tamanho; y++) {

                Casa casa = new Casa();
                board[x][y] = casa;

            }
        }
    }
    
    /**
     * @param x linha
     * @param y coluna
     * @return Casa na posicao (x,y)
     */
    public Casa getCasa(int x, int y) {
        return board[x][y];
    }
}
