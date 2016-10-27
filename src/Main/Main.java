package Main;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo(); //Instanciando a classe manipula arquivo
        String caminho = "/home/guerra/Área de Trabalho/Escalonador/Arquivo.txt";  //Especeficar caminho do arquivo a ser lido     
        List<String> lista_processos_string = manipulaArquivo.abrirArquivo(caminho); //lista recebendo leitura do arquivo

        ArrayList<Processo> listaProcesso = new ArrayList<>(); //criação da lista de processos
        Processo processo; //criando variavel processo do tipo Processo

        Integer id; //PID
        Integer prioridade; //Prioridade do processo
        Integer tamanho; //duração de fase na CPU;
        Integer tempoInicio; //tempo de entrada na CPU
        Integer tempoChegada; //tempo de chegada na lista de processos 
        Integer estado; //Estado do processo; 0-Bloqueado;1-Pronto;2-Executando
        ArrayList<Integer> fio = new ArrayList<>(); //Fila de Entradas/Saidas
        Integer tipo; //tipo do processo; 0-system; 1-usuario        

        for (int i = 0; i < lista_processos_string.size(); i++) {
            String aux[] = lista_processos_string.get(i).split(" "); //tirando os espaços das string obtida do arquivo

            //System.out.println(aux.length);
            //para adicionar F I/O basta pegar da pos aux[4] até aux[aux.length]
            id = Integer.parseInt(aux[0]); //convertendo string em integer
            tamanho = Integer.parseInt(aux[1]);//convertendo string em integer
            prioridade = Integer.parseInt(aux[2]);//convertendo string em integer
            tempoChegada = Integer.parseInt(aux[3]);//convertendo string em integer
            tempoInicio = 0;
            estado = 1;
            tipo = 1;

            processo = new Processo(id, prioridade, tamanho, tempoInicio, tempoChegada, estado, tipo); //criando um novo processo
            listaProcesso.add(processo); //adcionando o processo criado a uma lista de processos

        }
        for (int i = 0; i < listaProcesso.size(); i++) {
            System.out.println(listaProcesso.get(i).toString());//imprimindo a lista de processos
        }

    }

}
