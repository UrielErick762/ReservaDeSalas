/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reservasdesalas.CommandReserva;
import com.mycompany.reservasdesalas.InterfaceCommand;
import DAO.ReservaDAO;
import DAO.UsuarioDAO;
import DAO.SalaDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entity.Reserva;
import Entity.Sala;
import Entity.Usuario;


public class CadastrarReservaAction implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = "";
        
        try {
            
            LocalDate data = LocalDate.parse(request.getParameter("txtdata"));
            LocalTime horaInicio = LocalTime.parse(request.getParameter("txthoraInicio"));
            LocalTime horaFim = LocalTime.parse(request.getParameter("txthoraFim"));
            boolean status = Boolean.parseBoolean(request.getParameter("txtstatus"));
            int idSala = Integer.parseInt(request.getParameter("txtidSala"));
            int idUsuario = Integer.parseInt(request.getParameter("txtidUsuario"));
            String observacao = request.getParameter("txtobservacao");
            
            Sala sala = new SalaDAO().consultaPorId(idSala);
            Usuario usuario = new UsuarioDAO().consultar(idUsuario);
            ReservaDAO rdao = new ReservaDAO();
            
            Reserva r = new Reserva.ReservaBuilder()
                    .comData(data)
                    .comHoraInicio(horaInicio)
                    .comHoraFim(horaFim)
                    .comStatus(status)
                    .comIdSala(sala)
                    .comIdUsuario(usuario)
                    .comObservacao(observacao)
                    .constroi();

            rdao.cadastro(r);
            msg = "Reserva cadastrada com sucesso!";

        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            e.printStackTrace();
            msg = "Erro ao cadastrar reserva: " + e.getMessage();
        }

        request.setAttribute("msg", msg);
        return "redirect:frontcontrollerServlet?action=consultarMinhasReservas";

    }

}
