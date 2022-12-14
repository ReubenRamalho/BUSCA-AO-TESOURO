package minegame;

import java.util.Random;

import model.Bomba;
import model.Casa;
import model.Elemento;
import model.Recompensa;
import model.Tabuleiro;
import model.Vazio;

public class Jogo {

    private Tabuleiro tabuleiro;
    private int tamanho;

    public float relacaoRecompensaBomba;
    public int quantidadeDeRecompensas;
    public int quantidadeDeBombas;
    public int quantidadeDeVazios;

    public Recompensa[] recompensas;
    public Bomba[] bombas;
    public Vazio[] vazios;


    public int pontos = 0;
    private int estado = 0;

    public Jogo(int tamanho, String dificuldade) {
        this.tamanho = tamanho;

        calcularElementos(dificuldade);
        criarPecas();
    }
    
    void calcularElementos(String dificuldade){

        switch (dificuldade){

            case "facil":

                quantidadeDeRecompensas = 16;
                relacaoRecompensaBomba = 0.5f;
                quantidadeDeBombas = Math.round(quantidadeDeRecompensas * relacaoRecompensaBomba);
                break;

            case "medio":
                quantidadeDeRecompensas = 26;
                relacaoRecompensaBomba = 0.5f;
                quantidadeDeBombas = Math.round(quantidadeDeRecompensas * relacaoRecompensaBomba);
                break;
            case "dificil":
                quantidadeDeRecompensas = 34;
                relacaoRecompensaBomba = 0.5f;
                quantidadeDeBombas = Math.round(quantidadeDeRecompensas * relacaoRecompensaBomba);
                break;
        }

        quantidadeDeVazios = (tamanho * tamanho) - (quantidadeDeRecompensas + quantidadeDeBombas);
        recompensas = new Recompensa[quantidadeDeRecompensas];
        //bombas = new Bomba[Math.round(quantidadeDeBombas)];
        bombas = new Bomba[quantidadeDeBombas];
        vazios = new Vazio[quantidadeDeVazios];
        tabuleiro = new Tabuleiro(tamanho);
    }

    private void criarPecas() {

        for(int i = 0; i < quantidadeDeVazios; i++)
        {
            vazios[i] = new Vazio();
        }

        for(int i = 0; i < quantidadeDeRecompensas; i++)
        {
            recompensas[i] = new Recompensa();
        }

        for(int j = 0; j < quantidadeDeBombas; j++)
        {
            bombas[j] = new Bomba();
        }

        for(Recompensa r : recompensas)
        {
            
            Random random = new Random();
            boolean found = false;

            while(!found)
            {
                found = false;
                Casa c = tabuleiro.getCasa(random.nextInt(tamanho), random.nextInt(tamanho));
                if(!c.possuiElemento())
                {
                    r.setCasa(c);
                    r.revelar();
                    found = true;   
                }
            }
        }

        for(Bomba b : bombas)
        {
            
            Random random = new Random();
            boolean found = false;

            while(!found)
            {
                found = false;
                Casa c = tabuleiro.getCasa(random.nextInt(tamanho), random.nextInt(tamanho));
                if(!c.possuiElemento())
                {
                    b.setCasa(c);
                    b.revelar();
                    found = true;   
                }
            }
        }

        for(Vazio v : vazios)
        {
            
            Random random = new Random();
            boolean found = false;

            while(!found)
            {
                found = false;
                Casa c = tabuleiro.getCasa(random.nextInt(tamanho), random.nextInt(tamanho));
                if(!c.possuiElemento())
                {
                    v.setCasa(c);
                    found = true;   
                }
            }
        }
    }

    public void esconderElementos()
    {
        for(int i = 0; i < quantidadeDeRecompensas; i++)
        {
            recompensas[i].esconder();
        }

        for(int j = 0; j < quantidadeDeBombas; j++)
        {
            bombas[j].esconder();
        }
    }

    public void mostrarPeca(int x, int y)
    {
        Casa c = tabuleiro.getCasa(x, y);
        Elemento e = c.getElemento();
        e.revelar();
        switch(e.getID())
        {
            case 0:
                //VAZIO
                break;
            case 1:
                //RECOMPENSA 1
                //se ainda houver bombas, somar valor, caso contrario, finalizar com vitoria
                
                Recompensa r = (Recompensa) e;

                somarPontos( r.getValor());
                quantidadeDeRecompensas--; //podemos usar o mesmo numero, pois já foi utilizado na inicialização
                if(quantidadeDeRecompensas <= 0){
                    estado = 1;
                } else {
                    estado = 0;
                }
                break;
            case 2:
                //BOMBA
                estado = 2; //vai dar o trigger na janela principal pra finalizar o jogo e reiniciar
                break;
            case 3:
                //novo tipo de recompensa
                break;
            case 4:
                //novo tipo de recompensa
                break;
            case 5:
                //novo tipo de recompensa
                break;
        }
    }

    public void somarPontos(int soma)
    {
        pontos += soma;
    }

    public int getEstado()
    {
        return estado;
    }
    
    /**
     * @return o Tabuleiro em jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
