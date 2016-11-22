package Main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo(); //Instanciando a classe manipula arquivo
        String caminho = "/home/guerra/Área de Trabalho/Escalonador/Arquivo.txt";  //Especeficar caminho do arquivo a ser lido     
        List<String> lista_processos_string = manipulaArquivo.abrirArquivo(caminho); //lista recebendo leitura do arquivo
        LinkedList<Processo> listaProcesso = new LinkedList<>(); //criação da lista de processos
        Scanner scanner = new Scanner(System.in);

        Processo processo; //criando variavel processo do tipo Processo

        Integer id; //PID
        Integer prioridade; //Prioridade do processo
        Integer tamanho; //duração de fase na CPU;        
        Integer tempoChegada; //tempo de chegada na lista de processos        

        for (int i = 0; i < lista_processos_string.size(); i++) {
            String aux[] = lista_processos_string.get(i).split(" "); //tirando os espaços das string obtida do arquivo

            id = Integer.parseInt(aux[0]); //convertendo string em integer
            tamanho = Integer.parseInt(aux[1]);//convertendo string em integer
            prioridade = Integer.parseInt(aux[2]);//convertendo string em integer
            tempoChegada = Integer.parseInt(aux[3]);//convertendo string em integer            
            ArrayList<Integer> fio = new ArrayList<>(); //Fila de Entradas/Saidas

            if (aux.length > 3) {
                for (int j = 4; j < aux.length; j++) {
                    fio.add(tamanho - Integer.parseInt(aux[j]));
                }
            }

            processo = new Processo(id, prioridade, tamanho, 0, tempoChegada, 1, 1); //criando um novo processo
            processo.setFio(fio);
            listaProcesso.add(processo); //adcionando o processo criado a uma lista de processos

        }

        System.out.println("Escolha um tipo de escalonamento\n1-SJF\n2-Prioridade\n3-Round robin");
        int var = scanner.nextInt();
        System.out.println("");

        switch (var) {
            case 1:
                SJF sjf = new SJF(); //instanciando a classe Shortest Job First
                sjf.EscSJF(listaProcesso);//chamando a função de escalonamento                
                break;
            case 2:
                Prioridade prio = new Prioridade();
                prio.EscPrio(listaProcesso);
                break;
            case 3:
                RoundRobin rr = new RoundRobin();
                rr.EscRRobin(listaProcesso);
                break;
            default:
                throw new AssertionError();
        }

    }

}
