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

/**
 *1. command - buscarTodasSalas
            SalaDAO salaDAO = new SalaDAO(); isso vai para o command 
            List<Sala> salas = salaDAO.consultaTudo();
            return salas **exemplo**
 * @author uriel
 */
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
        return "Salas.jsp"; // ou a JSP que você quiser
    }
} 

