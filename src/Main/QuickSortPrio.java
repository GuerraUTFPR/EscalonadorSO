package Main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class QuickSortPrio {

    private LinkedList<Processo> listaProntos = new LinkedList<>();

    public void setLista2(LinkedList<Processo> listaProntos) {
        this.listaProntos = listaProntos;
    }

    public void getVetor(LinkedList listaProntos) {
        int tam = listaProntos.size();
        quickSort(listaProntos, 0, tam - 1);
        //empate (listaProntos);

    }

    private int partition(List<Processo> listaProntos, int start, int end) {
        Processo aux;
        Integer pivot = listaProntos.get(end).getPrioridade();
        int pIndex = start;
        for (int i = start; i < end; i++) {
            if (listaProntos.get(i).getPrioridade() <= pivot) {
                aux = listaProntos.get(i);
                listaProntos.set(i, listaProntos.get(pIndex));
                listaProntos.set(pIndex, aux);
                pIndex++;
            }
        }
        aux = listaProntos.get(pIndex);
        listaProntos.set(pIndex, listaProntos.get(end));
        listaProntos.set(end, aux);
        return pIndex;
    }

    private void quickSort(LinkedList<Processo> listaProntos, int start, int end) {
        if (start < end) {
            int particionIndex = partition(listaProntos, start, end);
            quickSort(listaProntos, start, particionIndex - 1);
            quickSort(listaProntos, particionIndex + 1, end);
        }
    }

    public void empate(LinkedList<Processo> listaProntos) {
        Processo p0 = listaProntos.getFirst(), p1 = listaProntos.get(1);

        if (listaProntos.size() > 1 && p0.getPrioridade().equals(p1.getPrioridade())) {
            if (p0.getUsado().equals(1) && p1.getUsado().equals(0)) {
                listaProntos.removeFirst();
                listaProntos.add(1, p0);
            } else if ((p0.getUsado().equals(1) && p1.getUsado().equals(1)) ||(p0.getUsado().equals(0) && p1.getUsado().equals(0))) {
                   if(p0.getTempoChegada() > p1.getTempoChegada()){
                       listaProntos.removeFirst();
                       listaProntos.add(1, p0);
                   }
            }

        }
    }
}
