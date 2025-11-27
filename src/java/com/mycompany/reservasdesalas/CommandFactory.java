package com.mycompany.reservasdesalas;

import com.mycompany.reservasdesalas.CommandReserva.*;
import com.mycompany.reservasdesalas.CommandSala.*;
import com.mycompany.reservasdesalas.CommandUsuario.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CommandFactory {

    private static final Map<String, Supplier<InterfaceCommand>> commands = new HashMap<>();

    static {
        commands.put("Login", LoginAction::new);

        commands.put("BuscarTodasSalas", BuscarTodasSalas::new);
        commands.put("verificarDisponibilidadeSala", VerificarDisponibilidadeSala::new);

        commands.put("cadastrarReserva", CadastrarReservaAction::new);
        commands.put("atualizarReserva", AtualizaReservaAction::new);
        commands.put("deletarReserva", DeletarReservaAction::new);
        commands.put("consultarMinhasReservas", ConsultarReservaAction::new);

        commands.put("consultarSalas", ConsultarSalaAction::new);
    }

    public static InterfaceCommand create(String actionName) {
        Supplier<InterfaceCommand> supplier = commands.get(actionName);

        if (supplier == null) return null; 

        InterfaceCommand command = supplier.get();

        if ("cadastrarReserva".equals(actionName)) {
             command = new CadastrarReservaDecorator(command);
        }
        
        return command;
    }
}