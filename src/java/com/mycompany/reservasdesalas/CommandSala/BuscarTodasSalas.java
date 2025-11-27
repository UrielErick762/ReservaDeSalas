/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reservasdesalas.CommandSala;

import DAO.SalaDAO;
import Entity.Sala;
import com.mycompany.reservasdesalas.InterfaceCommand;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuscarTodasSalas implements InterfaceCommand{


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String msg = "";

        try {
            SalaDAO salaDAO = new SalaDAO();

            // consulta todas as salas
            List<Sala> salas = salaDAO.consultaTudo();

            // envia a lista para a JSP
            request.setAttribute("salas", salas);

            msg = "Consulta realizada com sucesso!";

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            msg = "Erro ao consultar salas: " + e.getMessage();
        }

        request.setAttribute("msg", msg);
        return "Salas.jsp"; 
    }
} 

