package view;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CasaGUI extends JButton {
    
    // Icones das pecas
    private static final ImageIcon VAZIO = new ImageIcon("imagens/Empty-Image.png");
    private static final ImageIcon RECOMPENSA = new ImageIcon("imagens/Treasure-3.png");
    private static final ImageIcon BOMBA = new ImageIcon("imagens/Bomb.png");
    private static final ImageIcon ESCONDIDO = new ImageIcon("imagens/Hidden.png");
    

    private int x;
    private int y;

    public CasaGUI(int x, int y, TabuleiroGUI tabuleiro) {
        this.x = x;
        this.y = y;

        // Layout e cor
        setOpaque(false);
        setFocusPainted(true);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setMargin(new Insets(0, 0, 0, 0));

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabuleiro.getJanela().reagir((CasaGUI) e.getSource());
            }
        });
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }

    public int getPosicaoX() {
        return x;
    }

    public int getPosicaoY() {
        return y;
    }

    public void desenharVazio() {
        setIcon(resizeIcon(VAZIO, getWidth(), getHeight()));
    }

    public void desenharRecompensa() {
        setIcon(resizeIcon(RECOMPENSA, getWidth(), getHeight()));
    }

    public void desenharBomba() {
        setIcon(resizeIcon(BOMBA, getWidth(), getHeight()));
    }

    public void desenharEscondido() {
        setIcon(resizeIcon(ESCONDIDO, getWidth(), getHeight()));
    }

    public void apagarPeca() {
        setIcon(null);
    }

    public boolean possuiElemento() {
        return getIcon() != null;
    }
}
