package Main;

import java.util.LinkedList;

public class Prioridade {

    LinkedList<Processo> listaBloqueados = new LinkedList<>(); //Lista de processos bloqueados para I/O
    LinkedList<Processo> listaProntos = new LinkedList<>(); // Lista de processos prontos para ser executados
    LinkedList<Processo> listaTerminados = new LinkedList<>(); //Lista de processos concluidos
    QuickSortPrio quickSortPrio = new QuickSortPrio();//Instancia da classe quick sort

    public void EscPrio(LinkedList<Processo> listaProcesso) {
        Integer tempo = 0; //definindo tempo 0 para timeline
        Processo executando = null;
        Integer soma_tempo_inicio = 0;
        double tme;

        do {
            if (listaProcesso.size() > 0) {//se existir processo na lista de processos
                if (listaProcesso.getFirst().getTempoChegada().equals(tempo)) {//se o tempo de chegada do 1º processo for == tempo
                    listaProcesso.getFirst().setEstado(1); //muda o estado para pronto
                    listaProntos.add(listaProcesso.getFirst()); //adciona na fila de pronto
                    listaProcesso.removeFirst(); //remove da lista de processos
                    quickSortPrio.getVetor(listaProntos); //ordena fila de prontos
                }

            }
            if (executando == null) { //se niguem estiver executando
                executando = listaProntos.getFirst(); //o primeiro processo da fila vai pra execução
                executando.setTempoInicio(tempo); //salvando o tempo de inicio da execução 
                listaProntos.removeFirst();//é removido da fila de prontos
                executando.setEstado(2);//o estado vai pra executando
                executando.settempoEspera(executando.getTempoInicio() - executando.getTempoChegada()); //calculando o tempo de espera de cada processo
                
                
            }
            
            else if(listaProntos.size() > 0) {
                if(executando.getPrioridade().compareTo(listaProntos.getFirst().getPrioridade()) > 0 ){
                    executando.setEstado(1);
                    listaProntos.add(executando);                   
                    executando = listaProntos.getFirst();
                    executando.setEstado(2);
                    listaProntos.removeFirst();
                    
                }
            }

            executando.setTamanho(executando.getTamanho() - 1);//decrementa 1 do tamanho do processo
            
            //System.out.println(executando.toString());
            
            if (executando.getTamanho() == 0) { //se o tamanho do processo for 0                
                executando.setEstado(3);//muda o estado para finalizado
                listaTerminados.add(executando); //adciona a uma lista de processos concluidos
                executando = null; //libera a execução pra outro processo
                
                
                
            }
            
            
            tempo++;//incrementa o tempo                    

        } while (listaProcesso.size() > 0 || listaProntos.size() > 0 || executando != null); //repetir enquanto existir processo na lista ou pronto ou em execução 

        for (int i = 0; i < listaTerminados.size(); i++) {//imprimindo lista de processos concluidos
            System.out.println(listaTerminados.get(i).toString());
        }

        tme = (soma_tempo_inicio / (float) listaTerminados.size()); //soma os tempo de inicio dividindo pela qtd de processos
        System.out.println("TME = " + tme + "ms"); //tempo medio de espera

    }
}
