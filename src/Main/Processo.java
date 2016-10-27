/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;

/**
 *
 * @author a1711610
 */
public class Processo {
    Integer id; //PID
    Integer prioridade; //Prioridade do processo
    Integer tamanho; //duração de fase na CPU;
    Integer tempoInicio; //tempo de entrada na CPU
    Integer tempoChegada; //tempo de chegada na lista de processos   
    Integer estado; //Estado do processo; 0-Bloqueado;1-Pronto;2-Executando
    ArrayList<Integer> fio = new ArrayList<>(); //Fila de Entradas/Saidas
    Integer tipo; //tipo do processo; 0-system; 1-usuario
}
