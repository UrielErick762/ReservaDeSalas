
package com.mycompany.reservasdesalas.CommandReserva;

import com.mycompany.reservasdesalas.InterfaceCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarReservaDecorator implements InterfaceCommand {

    private final InterfaceCommand wrappee;

    public CadastrarReservaDecorator(InterfaceCommand wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // Comportamento adicional antes da execução
        System.out.println("Iniciando o cadastro da reserva...");

        // Chama o comando original
        String resultado = wrappee.execute(req, res);

        // Comportamento adicional depois da execução
        System.out.println("Reserva cadastrada com sucesso!");

        return resultado;
    }
}

