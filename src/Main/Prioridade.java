package Main;

import java.util.LinkedList;

public class Prioridade {

    LinkedList<Processo> listaBloqueados = new LinkedList<>(); //Lista de processos bloqueados para I/O
    LinkedList<Processo> listaProntos = new LinkedList<>(); // Lista de processos prontos para ser executados
    LinkedList<Processo> listaTerminados = new LinkedList<>(); //Lista de processos concluidos

    StringTools st = new StringTools();//instanciando classe string tools

    QuickSortPrio quickSortPrio = new QuickSortPrio();//Instancia da classe quick sort

    Processo psis = new Processo(-1, -1, 1, 0, 0, 1, 0); //processo do sistema

    public void EscPrio(LinkedList<Processo> listaProcesso) {
        Integer tempo = 0; //definindo tempo 0 para timeline

        Processo executando = null; //criação da "cpu"
        Integer soma_tempo_inicio = 0; //variavel de soma do tempo de inicio de cada processo
        double tme = 0; //tempo medio de expera

        do {
            //verificação se o processo terá I/O
            if (executando != null && executando.getFio().isEmpty() != true && executando.getFio().get(0).equals(executando.getTamanho())) {//se alguém tiver executando e tiver uma lista de I/o e o primeiro elemento dessa lista for igual ao tamanho restante do processo
                executando.setEstado(0);// o processo tem o estado alterado para bloqueado
                executando.getFio().remove(0); //é removida da lista de I/O o primeiro elemento
                listaBloqueados.add(executando);//e o processo vai pra lista de bloqueados
                executando.setAux(tempo);
                executando = null;//o processo sai da cpu

            }
            //-----------------------------------------------------------   

            //verificando se um processo chegou
            if (listaProcesso.size() > 0) {//se existir processo na file de prontos
                if (listaProcesso.getFirst().getTempoChegada().equals(tempo)) {//se o tempo de chegada do 1º processo for == tempo
                    listaProcesso.getFirst().setEstado(1); //muda o estado para pronto
                    listaProntos.add(listaProcesso.getFirst()); //adciona na fila de pronto
                    listaProcesso.removeFirst(); //remove da lista de processos
                    quickSortPrio.getVetor(listaProntos); //ordena fila de prontos
                }
            }
            //--------------------------------------------------------

            // verificando se ninguém esta processando e se tem alguém pronto
            if (executando == null && listaProntos.size() > 0) { //se niguem estiver executando e tiver um processo pronto
                executando = listaProntos.getFirst(); //o primeiro processo da fila vai pra execução
                executando.setUsado(1);//altera-se a flag para processo já executado.
                executando.setEstado(2);//processo em execução 
                executando.setTempoInicio(tempo);//tempo de inicio é o tempo que processo chegou 
                if (executando.getTipo().equals(0) && listaBloqueados.size() > 0) {//se o processo for de sistema e tiver algum processo bloqueado
                    listaProntos.removeFirst();//remove o primeiro da lista de prontos                    
                    listaProntos.add(listaBloqueados.getFirst()); //adiciona o bloqueado na lista de prontos novamente
                    listaBloqueados.removeFirst(); //remove o processo da lista de bloqueados
                } else { //se for processo de usuario 
                    executando.settempoEspera(executando.gettempoEspera() + (executando.getTempoInicio() - executando.getAux()));
                    listaProntos.removeFirst();//é removido da fila de prontos 
                }
            } else if (executando != null && listaProntos.size() > 0 && listaProntos.getFirst().getPrioridade() < executando.getPrioridade()) { //se algum processo estiver executando, e estiver algum processo na lista de prontos e o processo da lista de prontos tiver prioridade maior que o processo executando
                executando.setEstado(1);
                executando.setAux(tempo);
                listaProntos.add(executando);//o processo executando vai para lista de prontos
                executando = listaProntos.getFirst(); //o primeiro processo da fila vai pra execução
                if (executando.getTipo().equals(0) && listaBloqueados.size() > 0) {//se o processo for de sistema e tiver algum processo bloqueado
                    executando.setEstado(2);//processo em execução
                    listaProntos.removeFirst();//remove o primeiro da lista de prontos                    
                    listaProntos.add(listaBloqueados.getFirst()); //adiciona o bloqueado na lista de prontos novamente
                    listaBloqueados.removeFirst(); //remove o processo da lista de bloqueados
                } else { //se for processo de usuario 
                    executando.setTempoInicio(tempo);//tempo de inicio é o tempo que processo chegou               
                    executando.settempoEspera(executando.gettempoEspera() + (executando.getTempoInicio() - executando.getTempoChegada()));//armazenando diferença do inicio - chegada;
                    listaProntos.removeFirst();//é removido da fila de prontos
                    executando.setEstado(2);//o estado vai pra executando
                }
            }
            if (executando != null) {//se alguém estiver executando
                if (executando.getTipo().equals(0)) {//e for de sistema
                    System.out.println("Tempo= " + st.ajustaLargura(String.valueOf(tempo), 3) + " " + executando.toString());
                    executando.setEstado(1);//estado do processo de sistema é alterado para pronto
                    executando = null;//e libera o uso da cpu
                } else {//se o processo for de usuario
                    executando.setAux(tempo);
                    executando.setTamanho(executando.getTamanho() - 1);//decrementa 1 do tamanho do processo
                    System.out.println("Tempo= " + st.ajustaLargura(String.valueOf(tempo), 3) + " " + executando.toString());
                    if (executando.getTamanho().equals(0)) {//se o tamanho do processo chegar a 0
                        executando.setEstado(3);//muda o estado para finalizado 
                        listaTerminados.add(executando); //adciona a uma lista de processos concluidos
                        executando = null; //libera a execução pra outro processo
                    }
                }
            }

            if (listaBloqueados.size() > 0 && tempo % 3 == 0) { //se tiver algum processo bloqueado e o tempo for multiplo de 3
                listaProntos.add(psis); //processo do sistema e adicionado a lista de prontos
            }

            tempo++;//incrementa o tempo   
        } while (listaProcesso.size() > 0 || listaProntos.size() > 0 || executando != null || listaBloqueados.size() > 0); //repetir enquanto existir processo na lista ou pronto ou em execução 

        System.out.println("\n\n\n\nLista de processos escalonados");

        for (int i = 0; i < listaTerminados.size(); i++) {//imprimindo lista de processos concluidos
            soma_tempo_inicio = soma_tempo_inicio + listaTerminados.get(i).getTempoEspera();
            System.out.println(listaTerminados.get(i).toString());

        }
        System.out.println("TME = " + soma_tempo_inicio / (float) listaTerminados.size());
    }
}
