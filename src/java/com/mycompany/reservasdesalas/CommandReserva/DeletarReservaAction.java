/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reservasdesalas.CommandReserva;
import com.mycompany.reservasdesalas.InterfaceCommand;
import DAO.ReservaDAO;
import Entity.Reserva;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author uriel
 */
public class DeletarReservaAction implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = "";
        ReservaDAO rdao = new ReservaDAO();
        Reserva r = new Reserva();

        try {
            int idReserva = Integer.parseInt(request.getParameter("txtid"));
            r.setIdReserva(idReserva);

            rdao.deleta(r);
            msg = "Reserva excluída com sucesso!";

        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            e.printStackTrace();
            msg = "Erro ao excluir reserva: " + e.getMessage();
        }

        request.setAttribute("msg", msg);
        return "redirect:frontcontrollerServlet?action=consultarMinhasReservas";
    }

}

