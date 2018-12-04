/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupointegrado.servlets;

import br.com.grupointegrado.dao.MensagemDao;
import br.com.grupointegrado.model.Mensagem;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alunocentro
 */
public class ChatServlet extends HttpServlet {

        private void setUser(HttpServletRequest req) {
        HttpSession session = req.getSession();

        if (!(session.getAttribute("remetente") instanceof String)) {
            
            String remetente = "Usuario " + Math.round(Math.random() * 1000);
            session.setAttribute("remetente", remetente);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setUser(req);
        Connection conn = (Connection) req.getAttribute("conexao");
        MensagemDao dao = new MensagemDao(conn);
        
            try {
                List<Mensagem> Lista = dao.BuscaTodas();
                req.setAttribute("mensagens", Lista);
            } catch (SQLException ex) {
            } 
            catch (NullPointerException ex) {
                req.setAttribute("mensagem-erro","Erro ao Encontrar ");
            }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/paginas/chat.jsp");
        requestDispatcher.forward(req, resp);
    }
       @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setUser(req);

        Object textoMensagem = req.getParameter("mensagem");
        
        Connection conn = (Connection) req.getAttribute("conexao");
        if (validarMensagem(textoMensagem)) {
            
            MensagemDao dao = new MensagemDao(conn);
            Mensagem obj = new Mensagem();
            try {
            obj.setRemetente((String) req.getSession().getAttribute("remetente"));
            obj.setTexto((String)req.getAttribute("mensagem"));
            obj.setDataHora("2018"); //Prof a quando eu coloco a data da Erro ! não sei o que esta acontecendo
                dao.InsertMSG(obj);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            resp.sendRedirect("/ExameWeb/index");
            
        } else {
            req.setAttribute("mensagem-erro", "A mensagem não pode ser vazia.");

            doGet(req, resp);
        }
        
    }
    private boolean validarMensagem(Object textoMensagem) {
        if (textoMensagem instanceof String 
                && !textoMensagem.toString().isEmpty()) {
            return true;
        }
        return false;
    }

}
