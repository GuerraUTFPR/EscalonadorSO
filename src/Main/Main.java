package Main;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        String caminho = "/home/todos/alunos/cm/a1711610/Área de trabalho/ArquivoProcesso.txt";
        manipulaArquivo.abrirArquivo(caminho);
        List<String> lista_processos_string = manipulaArquivo.abrirArquivo(caminho);

        for (int i = 1; i < lista_processos_string.size(); i++) {
            String aux[] = lista_processos_string.get(i).split("");
        }
            

        }

    }
