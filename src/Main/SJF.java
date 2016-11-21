package Main;

import java.util.LinkedList;

public class SJF {

    LinkedList<Processo> listaBloqueados = new LinkedList<>(); //Lista de processos bloqueados para I/O
    LinkedList<Processo> listaProntos = new LinkedList<>(); // Lista de processos prontos para ser executados
    LinkedList<Processo> listaTerminados = new LinkedList<>(); //Lista de processos concluidos
    QuickSort quickSort = new QuickSort();//Instancia da classe quick sort
    StringTools st = new StringTools();

    public void EscSJF(LinkedList<Processo> listaProcesso) {

        Integer tempo = 0; //definindo tempo 0 para timeline
        Processo executando = null; //criando variavel executando
        Integer soma_tempo_inicio = 0; //somatoria dos tempo de espera
        double tme; //tempo medio de espera
        Processo psis = new Processo(-1, -1, 1, 0, 0, 1, 0); //processo do sistema

        do {
            //verificação se o processo terá I/O
            if (executando != null && executando.getFio().isEmpty() != true && executando.getFio().get(0).equals(executando.getTamanho())) {//se alguém tiver executando e tiver uma lista de I/o e o primeiro elemento dessa lista for igual ao tamanho restante do processo
                executando.setEstado(0);// o processo tem o estado alterado para bloqueado
                executando.getFio().remove(0); //é removida da lista de I/O o primeiro elemento
                listaBloqueados.add(executando);//e o processo vai pra lista de bloqueados
                executando = null;//o processo sai da cpu

            }
            //-----------------------------------------------------------   

            //verificando se um processo chegou
            if (listaProcesso.size() > 0) {//se existir processo na file de prontos
                if (listaProcesso.getFirst().getTempoChegada().equals(tempo)) {//se o tempo de chegada do 1º processo for == tempo
                    listaProcesso.getFirst().setEstado(1); //muda o estado para pronto
                    listaProntos.add(listaProcesso.getFirst()); //adciona na fila de pronto
                    listaProcesso.removeFirst(); //remove da lista de processos
                    quickSort.getVetor(listaProntos); //ordena fila de prontos
                }
            }
            //--------------------------------------------------------

            // verificando se ninguém esta processando e se tem alguém pronto
            if (executando == null && listaProntos.size() > 0) { //se niguem estiver executando e tiver um processo pronto
                executando = listaProntos.getFirst(); //o primeiro processo da fila vai pra execução
                if (executando.getTipo().equals(0) && listaBloqueados.size() > 0) {//se o processo for de sistema e tiver algum processo bloqueado
                    executando.setEstado(2);//processo em execução
                    listaProntos.removeFirst();//remove o primeiro da lista de prontos                    
                    listaProntos.add(listaBloqueados.getFirst()); //adiciona o bloqueado na lista de prontos novamente
                    listaBloqueados.removeFirst(); //remove o processo da lista de bloqueados

                } else { //se for processo de usuario
                    executando.setTempoInicio(tempo); //salvando o tempo de inicio da execução 
                    listaProntos.removeFirst();//é removido da fila de prontos
                    executando.setEstado(2);//o estado vai pra executando
                    executando.settempoEspera(executando.getTempoInicio() - executando.getTempoChegada()); //calculando o tempo de espera de cada processo
                }
                //-------------------------------------------------------------
            }

            if (executando != null) {//se alguém estiver executando
                if (executando.getTipo().equals(0)) {//e for de sistema
                    System.out.println("Tempo= "+ st.ajustaLargura(String.valueOf(tempo), 3) +" "+executando.toString());
                    executando.setEstado(1);//estado do processo de sistema é alterado para pronto
                    executando = null;//e libera o uso da cpu
                } else {//se o processo for de usuario
                    executando.setTamanho(executando.getTamanho() - 1);//decrementa 1 do tamanho do processo
                    System.out.println("Tempo= "+ st.ajustaLargura(String.valueOf(tempo), 3)+" "+executando.toString());
                    if (executando.getTamanho().equals(0)) {//se o tamanho do processo chegar a 0
                        soma_tempo_inicio = soma_tempo_inicio + executando.gettempoEspera(); //somando os tempos de espera
                        executando.setEstado(3);//muda o estado para finalizado
                        listaTerminados.add(executando); //adciona a uma lista de processos concluidos
                        executando = null; //libera a execução pra outro processo
                    }
                }
            }
            

            if (listaBloqueados.size() > 0 && tempo % 5 == 0) {//se existir alguém na lista de bloqueados e o tempo for multiplo de 5
                listaProntos.add(psis);//adiciona o processo do sistema para ser escalonado
            }

            tempo++;//incrementa o tempo                    
        } while (listaProcesso.size() > 0 || listaProntos.size() > 0 || executando != null || listaBloqueados.size() > 0); //repetir enquanto existir processo na lista ou pronto ou em execução ou bloqueado para I/O
        
        System.out.println("\n\n\nLista de processos finalizados:");
        
        for (int i = 0; i < listaTerminados.size(); i++) {//imprimindo lista de processos concluidos
            System.out.println(listaTerminados.get(i).toString());
        }

        tme = (soma_tempo_inicio / (float) listaTerminados.size()); //soma os tempo de inicio dividindo pela qtd de processos
        System.out.println("TME = " + tme + "ms"); //tempo medio de espera

    }

}
