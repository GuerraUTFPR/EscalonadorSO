package Main;

import java.util.LinkedList;

public class SJF {

    LinkedList<Processo> listaBloqueados = new LinkedList<>();
    LinkedList<Processo> listaProntos = new LinkedList<>();
    LinkedList<Processo> listaTerminados = new LinkedList<>();
    QuickSort quickSort = new QuickSort();

    public void EscSJF(LinkedList<Processo> listaProcesso) {
        Integer tempo = 0; //definindo tempo 0 para timeline
        Processo executando = null;
        
   
        do {
            if (listaProcesso.size() > 0 ) {
                if (listaProcesso.getFirst().getTempoChegada().equals(tempo)) {
                    listaProcesso.getFirst().setEstado(1);
                    listaProntos.add(listaProcesso.getFirst());
                    listaProcesso.removeFirst();
                    quickSort.getVetor(listaProntos);
                }
                
            }
            if (executando == null) {
                executando = listaProntos.getFirst();
                listaProntos.removeFirst();
                executando.setEstado(2);
            }

            executando.setTamanho(executando.getTamanho() - 1);

            if (executando.getTamanho() == 0) {
                listaTerminados.add(executando);
                executando = null;
            }
           // System.out.println("tempo: "+tempo);
           // System.out.println(listaProcesso.getFirst().getTempoChegada());
            
            tempo ++;
            
            System.out.println("Procesos: "+listaProcesso.size());
            System.out.println("Prontos: "+listaProntos.size());

        } while (listaProcesso.size() > 0  || listaProntos.size() > 0 || executando != null); 
        
        for (int i = 0; i < listaTerminados.size(); i++) {
            System.out.println(listaTerminados.get(i).toString());
        }
        
    }
}
