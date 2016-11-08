package Main;

import java.util.LinkedList;

public class SJF {

    LinkedList<Processo> listaBloqueados = new LinkedList<>(); //Lista de processos bloqueados para I/O
    LinkedList<Processo> listaProntos = new LinkedList<>(); // Lista de processos prontos para ser executados
    LinkedList<Processo> listaTerminados = new LinkedList<>(); //Lista de processos concluidos
    QuickSort quickSort = new QuickSort();//Instancia da classe quick sort

    public void EscSJF(LinkedList<Processo> listaProcesso) {
        Integer tempo = 0; //definindo tempo 0 para timeline
        Processo executando = null; //criando variavel executando
        Integer soma_tempo_inicio = 0; //somatoria dos tempo de espera
        double tme; //tempo medio de espera

        do {
            if (listaProcesso.size() > 0) {//se existir processo na file de prontos
                if (listaProcesso.getFirst().getTempoChegada().equals(tempo)) {//se o tempo de chegada do 1º processo for == tempo
                    listaProcesso.getFirst().setEstado(1); //muda o estado para pronto
                    listaProntos.add(listaProcesso.getFirst()); //adciona na fila de pronto
                    listaProcesso.removeFirst(); //remove da lista de processos
                    quickSort.getVetor(listaProntos); //ordena fila de prontos
                }
            }
            
            if (executando == null) { //se niguem estiver executando
                executando = listaProntos.getFirst(); //o primeiro processo da fila vai pra execução
                executando.setTempoInicio(tempo); //salvando o tempo de inicio da execução 
                listaProntos.removeFirst();//é removido da fila de prontos
                executando.setEstado(2);//o estado vai pra executando
                executando.settempoEspera(executando.getTempoInicio() - executando.getTempoChegada()); //calculando o tempo de espera de cada processo

                //System.out.println("Processo:"+ executando.getId()+"Tempo de Espera = "+executando.gettEspera());
            }

            executando.setTamanho(executando.getTamanho() - 1);//decrementa 1 do tamanho do processo

            if (executando.getTamanho() == 0) { //se o tamanho do processo for 0
                soma_tempo_inicio = soma_tempo_inicio + executando.gettempoEspera(); //somando os tempos de espera
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
        System.out.println("TME = " + tme+"ms"); //tempo medio de espera

    }
}
