/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reservasdesalas.CommandSala;
     
import DAO.ReservaDAO;
import DAO.SalaDAO;
import Entity.Reserva;
import Entity.Sala;
import com.mycompany.reservasdesalas.InterfaceCommand;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerificarDisponibilidadeSala implements InterfaceCommand {
  
SalaDAO salaDAO = new SalaDAO();
ReservaDAO reservaDAO = new ReservaDAO();
LocalDate hoje = LocalDate.now();


public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {

    try {

        List<Sala> salas = salaDAO.consultaTudo();

        Map<Sala, Boolean> mapaDisponibilidade = new HashMap<>();

        for (Sala sala : salas) {
            List<Reserva> reservasHoje =
                reservaDAO.reservasPorSalaData(sala.getId(), hoje);

            boolean disponivel = reservasHoje.isEmpty();

            mapaDisponibilidade.put(sala, disponivel);
        }

        request.setAttribute("salas", salas);
        request.setAttribute("disponibilidade", mapaDisponibilidade);

        return "view/Salas.jsp";

    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        request.setAttribute("msg", "Erro: " + e.getMessage());
        return "view/erro.jsp";
        }
    }
}