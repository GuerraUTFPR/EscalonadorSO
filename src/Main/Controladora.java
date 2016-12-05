/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.LinkedList;

/**
 *
 * @author guerra
 */
public class Controladora {
    QuickSort quickSort = new QuickSort();

    public void verificaIO(Processo executando, LinkedList<Processo> listaBloqueados) {
        if (executando != null && executando.getFio().isEmpty() != true && executando.getFio().get(0).equals(executando.getTamanho())) {
        executando.setEstado(0);// o processo tem o estado alterado para bloqueado
        executando.getFio().remove(0); //é removida da lista de I/O o primeiro elemento
        listaBloqueados.add(executando);//e o processo vai pra lista de bloqueados
        executando = null;//o processo sai da cpu
        }

    }
    
    public void verificaSeProcessoChegou(LinkedList<Processo> listaProcesso, LinkedList<Processo> listaProntos, Integer tempo){
           if (listaProcesso.size() > 0) {//se existir processo na file de prontos
                if (listaProcesso.getFirst().getTempoChegada().equals(tempo)) {//se o tempo de chegada do 1º processo for == tempo
                    listaProcesso.getFirst().setEstado(1); //muda o estado para pronto
                    listaProntos.add(listaProcesso.getFirst()); //adciona na fila de pronto
                    listaProcesso.removeFirst(); //remove da lista de processos
                    quickSort.getVetor(listaProntos); //ordena fila de prontos
                }
            }
    }
}
