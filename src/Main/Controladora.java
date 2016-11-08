package Main;

import java.util.LinkedList;

public class Controladora {

    LinkedList<Processo> listaBloqueados = new LinkedList<>(); //Lista de processos bloqueados para I/O
    LinkedList<Processo> listaProntos = new LinkedList<>(); // Lista de processos prontos para ser executados
    LinkedList<Processo> listaTerminados = new LinkedList<>(); //Lista de processos concluidos
    Processo executando;
    
    public void AdcPronto(LinkedList<Processo> listaProcesso, LinkedList<Processo> listaProntos) {
    }

    public void Escalona(Processo executando, LinkedList<Processo> listaProntos) {

    }
}
