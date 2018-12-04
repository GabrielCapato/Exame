/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupointegrado.dao;

import br.com.grupointegrado.model.Mensagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alunocentro
 */
public class MensagemDao {

    private final Connection conexao;

    public MensagemDao(Connection conexao) {
        this.conexao = conexao;
    }
    

    public  void InsertMSG(Mensagem obj) throws SQLException {

        String sql = "INSERT INTO chat (IdMsg, Msg ,Data) VALUES (?,?,?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, obj.getRemetente());
        ps.setString(2, obj.getTexto());
        ps.setString(3,"2018");

        ps.execute();

    }

    public List<Mensagem> BuscaTodas() throws SQLException {
        try {
            
        String sql = "SELECT * FROM chat";
                PreparedStatement statement = conexao.prepareStatement(sql);

        List<Mensagem> lista = new ArrayList<>();

        ResultSet rs = statement.executeQuery();
        if (rs.first()) {
            do {
                Mensagem obj = getCategoriaByResultSet(rs);
                lista.add(obj);
            } while (rs.next());
        }
        return lista;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Mensagem getCategoriaByResultSet(ResultSet rs) throws SQLException {
        try {
        Mensagem obj = new Mensagem();
            
        obj.setRemetente(rs.getString("IdMsg"));
        obj.setTexto(rs.getString("Msg"));
        obj.setDataHora(rs.getString("Data"));
        
        return obj;    
        } catch (NullPointerException e) {
            e.printStackTrace();
                   
        }
        
        return null;
    }

}
