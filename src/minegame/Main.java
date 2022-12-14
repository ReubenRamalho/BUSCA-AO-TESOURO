package minegame;
import java.util.Scanner;

import view.JanelaPrincipal;

public class Main {

    /*
    [*]Tabuleiro
        Casas [matriz, inicialmente 5x5]
            Elemento - 3 subtipos [Bomba, Recompensa, Vazio] - ok
    [*]Armazenar a pontuação do jogador - todo
    [*]Gerar niveis aleatorios
    [**]Armazenar na pasta do jogo um arquivo em qual nivel o jogador está
    [**]Armazenar pontuação máxima (high score)
    */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Escreva a dificuldade do jogo:");
        System.out.println("facil");
        System.out.println("medio");
        System.out.println("dificil");

        System.out.print("->");
        String dificuldade = s.nextLine();
        
        while(!dificuldade.equals("facil") && !dificuldade.equals("medio") && !dificuldade.equals("dificil")){
            System.out.println("Dificuldade nao reconhecida. Digite novamente");
            dificuldade = s.nextLine();
        }
        s.close();

        JanelaPrincipal mainGame = new JanelaPrincipal(dificuldade);
        System.out.println("Jogo aberto, instancia " + mainGame);
    }
}