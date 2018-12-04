<%@page import="java.util.Objects"%>
<%@page import="br.com.grupointegrado.model.Mensagem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<%
    List<Mensagem> mensagens = (List<Mensagem>) request.getAttribute("mensagens");
    Object mensagemErro = request.getAttribute("mensagem-erro");
    String autor = (String) session.getAttribute("remetente");
    
%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title><%= autor%> - Bem Vindo ao Super Chat</title>

        <link rel="stylesheet" type="text/css" href="CSS/fontawesome.all.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/style-chat.css">

        <script>
            function validarMensagem() {
                var textarea = document.getElementById('write_msg');

                var formValido = true;

                if (!textarea.value) {
                    formValido = false;
                    textarea.classList.add('is-invalid');
                    textarea.classList.remove('is-valid');
                } else {
                    textarea.classList.add('is-valid');
                    textarea.classList.remove('is-invalid');
                }

                return formValido;
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h3 class=" text-center">Bem Vindo ao Super Chat! -> (<%= autor%>)</h3>
            <div class="mesgs">
                <div id="msg_history">
                    <%  
                        if(mensagens != null){
                        for (Mensagem mensagem : mensagens) {
                            boolean MinhaMensagem = autor.equals(mensagem.getRemetente());
                            if (MinhaMensagem) {
                    %>
                    <div class="outgoing_msg">
                        <div class="sent_msg">
                            <p><%= mensagem.getTexto()%></p>
                            <span class="time_date"><%= mensagem.getDataHora()%></span> 
                        </div>
                    </div>
                    <%
                    } else {
                    %>
                    <div class="incoming_msg">
                        <div class="incoming_msg_img"> 
                            <img src="IMG/user-profile.png"> 
                        </div>
                        <div class="received_msg">
                            <div class="received_withd_msg">
                                <strong><%= mensagem.getRemetente()%></strong>
                                <p><%= mensagem.getTexto()%></p>
                                <span class="time_date"><%= mensagem.getDataHora()%></span> 
                            </div>
                        </div>
                    </div>
                    <%
                            }
                        }
                        }
                    %>
                </div>
                <div class="type_msg">
                    <%
                        if (mensagemErro != null) {
                    %>
                    <div class="alert alert-danger" role="alert">
                        <%= mensagemErro%>
                    </div>
                    <%
                        }
                    %>
                    <form class="input_msg_write" method="POST" 
                          onsubmit="return validarMensagem();">
                        <div class="form-group">
                            <textarea id="write_msg"
                                      name="mensagem" 
                                      class="form-control"
                                      placeholder="Escreva sua mensagem..." ></textarea>
                            <div class="invalid-feedback">
                                Sua mensagem não pode ser vazia!                  
                            </div>
                        </div>
                        <button class="msg_send_btn" type="submit">
                            <i class="fas fa-paper-plane" ></i>
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/componente-util.js"></script>

        <script>
            textAreaAutoAjustar('write_msg');

            rolarAoFinal('msg_history');
        </script>
    </body>
</html>
