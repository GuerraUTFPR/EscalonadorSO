package Main;

import java.util.ArrayList;

public class Processo {

    private Integer id; //PID
    private Integer prioridade; //Prioridade do processo
    private Integer tamanho; //duração de fase na CPU;
    private Integer tempoInicio; //tempo de entrada na CPU
    private Integer tempoChegada; //tempo de chegada na lista de processos   
    private Integer estado; //Estado do processo; 0-Bloqueado;1-Pronto;2-Executando;3-Finalizado
    private ArrayList<Integer> fio = new ArrayList<>(); //Fila de Entradas/Saidas
    private Integer tipo; //tipo do processo; 0-system; 1-usuario
    private Integer tempoEspera = 0;
    private Integer usado;//variavel para decisão em caso de empates
    private Integer aux; //variavel usada para armazenar soma de tempo de espera

    public Integer getAux() {
        return aux;
    }

    public void setAux(Integer aux) {
        this.aux = aux;
    }

    public void settempoEspera(Integer tEspera) {
        this.tempoEspera = tEspera;
    }

    public Integer gettempoEspera() {
        return tempoEspera;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public void setTempoChegada(Integer tempoChegada) {
        this.tempoChegada = tempoChegada;
        this.aux = tempoChegada;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    public void setTempoInicio(Integer tempoInicio) {
        this.tempoInicio = tempoInicio;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;

    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void setFio(ArrayList<Integer> fio) {
        this.fio = fio;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public Integer getTempoInicio() {
        return tempoInicio;
    }

    public Integer getTempoChegada() {
        return tempoChegada;
    }

    public Integer getEstado() {
        return estado;
    }

    public ArrayList<Integer> getFio() {
        return fio;
    }

    public Integer getTipo() {
        return tipo;
    }

    public Processo(Integer id, Integer prioridade, Integer tamanho, Integer tempoInicio, Integer tempoChegada, Integer estado, Integer tipo) {
        this.id = id;
        this.prioridade = prioridade;
        this.tamanho = tamanho;
        this.tempoInicio = tempoInicio;
        this.tempoChegada = tempoChegada;
        this.estado = estado;
        this.tipo = tipo;
        this.usado = 0;
        this.aux = tempoChegada;
    
    }

    @Override
    public String toString() {
        StringTools st = new StringTools();
        return "|PID= " + st.ajustaLargura(String.valueOf(id), 5) + "|Prioridade= " + st.ajustaLargura(String.valueOf(prioridade), 2) + "|Tamanho= " + st.ajustaLargura(String.valueOf(tamanho), 3) + "|Tempo de Chegada= " + st.ajustaLargura(String.valueOf(tempoChegada), 3) + "|Estado= " + st.ajustaLargura(String.valueOf(estado), 1) + "|Tipo= " + st.ajustaLargura(String.valueOf(tipo), 2) + "|Lista de I/O= " + fio.toString();
    }

    public void setUsado(Integer usado) {
        this.usado = usado;
    }

    public Integer getTempoEspera() {
        return tempoEspera;
    }

    public Integer getUsado() {
        return usado;
    }

}
