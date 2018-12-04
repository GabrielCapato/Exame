/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupointegrado.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alunocentro
 */
public class Mensagem {
    
    private  String remetente;
    private  String texto;
    private  String dataHora;

    public Mensagem() {
    }

    public Mensagem(String remetente, String texto, String dataHora) {
        this.remetente = remetente;
        this.texto = texto;
        this.dataHora = dataHora;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String HoraString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        return sdf.format(dataHora);
    }
}
