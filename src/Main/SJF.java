package Main;

import java.util.LinkedList;

public class SJF {
    LinkedList<Processo> listaBloqueados = new LinkedList<>();
    LinkedList<Processo> listaProntos = new LinkedList<>();
    
    public void EscSJF(LinkedList<Processo> listaProcesso){
    Integer tempo = 0; //definindo tempo 0 para timeline
        while (listaProcesso.size() > 0){ //laço que só para quando lista de processos for menor que 0
            if(listaProcesso.getFirst().getTempoChegada().equals(tempo)){//se o tempo de chegada do processo for igual ao tempo da timeline,
                                                                         //adicionar a lista de processos prontos
                listaProntos.add(listaProcesso.getFirst());//adcionando o primeiro elemento da fila de processos na fila de prontos
                listaProntos.getFirst().setEstado(1); //definindo estado do processo como 1(pronto)
                listaProcesso.removeFirst(); //removendo processo da lista de processos
            }
            
            
        }
        
    
    }
}
