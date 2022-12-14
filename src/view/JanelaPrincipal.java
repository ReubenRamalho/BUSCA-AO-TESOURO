package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import minegame.Jogo;

public class JanelaPrincipal extends JFrame {

    private Jogo jogo;
    private int delay;
    private static String dificuldade;
    private static int tamanho;
    Timer timer;
    
    /**
     * Responde aos cliques realizados no tabuleiro.
     * 
     * @param casaClicada Casa que o jogador clicou.
     */
    public void reagir(CasaGUI casaClicada) {
        // testa de a casa possui um elemento
        if (casaClicada.possuiElemento()) {
            // True se o elemento não estiver visível
            if(!jogo.getTabuleiro().getCasa(casaClicada.getPosicaoX(), casaClicada.getPosicaoY()).getElemento().getVisibilidade()){
                // Revela o Elemento 
                jogo.mostrarPeca(casaClicada.getPosicaoX(), casaClicada.getPosicaoY());
                // Atualiza a janela
                atualizar();
                // Atualiza a quantidade de pontos e recompensas disponíveis 
                lbl_a.setText(jogo.pontos + " pontos");
                lbl_b.setText(jogo.quantidadeDeRecompensas + " recompensas");
                
                // Checa o estado atual do jogo após a ação do Jogador
                switch(jogo.getEstado())
                {
                    // Jogo continua
                    case 0:
                        break;

                    // Jogo termina pois o Jogador venceu    
                    case 1:
                        JOptionPane.showMessageDialog(this, "Parabéns, você venceu com " + jogo.pontos + " pontos");
                        criarNovoJogo();
                        break;

                    // Jogo termina pois o jogador atingiu uma bomba 
                    case 2:
                        JOptionPane.showMessageDialog(this, "Você clicou em uma bomba e perdeu! Conseguiu " + jogo.pontos + " pontos");
                        criarNovoJogo();
                        break;
                    
                    default:
                    break; 
                }
            }
        }
    }

    /**
     * Construtor da classe.
     */
    public JanelaPrincipal(String dificuldade) {
        JanelaPrincipal.dificuldade = dificuldade;

        // Os valores dos atributos dessa Classe são setados baseados na dificuldade do jogo
        switch(dificuldade){

            case "facil": 
                JanelaPrincipal.tamanho = 8;
                this.delay = 1000;
                break;
            case "medio": 
                JanelaPrincipal.tamanho = 12;
                this.delay = 1500;
                break;
            case "dificil": 
                JanelaPrincipal.tamanho = 16;
                this.delay = 2000;
                break;

        }

        timer = new Timer(delay, al);
        initComponents();
        criarNovoJogo();

        lbl_c.setText(jogo.quantidadeDeBombas + " bombas");

        // configura action listener para o menu novo
        menuNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarNovoJogo();
            }
        });

        // configura action listener para o menu sair
        menuSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        super.setLocationRelativeTo(null);
        super.setVisible(true);
        super.pack();
    }

    

    /**
     * Cria um novo jogo e atualiza o tabuleiro gr�fico.
     */
    private void criarNovoJogo() {   
        jogo = new Jogo(tamanho, dificuldade);
        lbl_a.setText(jogo.pontos + " pontos");
        lbl_b.setText(jogo.quantidadeDeRecompensas + " recompensas");
        atualizar();
        timer.start();
    }


    ActionListener al=new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            jogo.esconderElementos();
            atualizar();
            timer.stop();
        }
    };

    private void atualizar() {
        tabuleiroGUI.atualizar(jogo);
    }


    private void initComponents() {

        pnlLinhas = new javax.swing.JPanel();
        pnlColunas = new javax.swing.JPanel();
        lbl_a = new javax.swing.JLabel();
        lbl_b = new javax.swing.JLabel();
        lbl_c = new javax.swing.JLabel();
        tabuleiroGUI = new TabuleiroGUI(this, tamanho);
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        menuNovo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlLinhas.setLayout(new java.awt.GridLayout(tamanho, 1));


        pnlColunas.setLayout(new java.awt.GridLayout(1, tamanho));

        lbl_a.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
        lbl_a.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_a.setText(0 + " pontos");
        pnlColunas.add(lbl_a);

        lbl_b.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
        lbl_b.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_b.setText(0 + " recompensas");
        pnlColunas.add(lbl_b);

        lbl_c.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
        lbl_c.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_c.setText(0 + " bombas");
        pnlColunas.add(lbl_c);

        menuArquivo.setText("Jogo");

        menuNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuNovo.setText("Novo");
        menuArquivo.add(menuNovo);
        menuArquivo.add(jSeparator1);

        menuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuSair.setText("Sair");
        menuArquivo.add(menuSair);

        jMenuBar1.add(menuArquivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLinhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlColunas, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addComponent(tabuleiroGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLinhas, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabuleiroGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlColunas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal(dificuldade).setVisible(true);
            }
        });
    }

    
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lbl_a;
    private javax.swing.JLabel lbl_b;
    private javax.swing.JLabel lbl_c;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenuItem menuNovo;
    private javax.swing.JMenuItem menuSair;
    private javax.swing.JPanel pnlColunas;
    private javax.swing.JPanel pnlLinhas;
    private TabuleiroGUI tabuleiroGUI;

}
