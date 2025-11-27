package com.mycompany.reservasdesalas;

import com.mycompany.reservasdesalas.CommandReserva.*;
import com.mycompany.reservasdesalas.CommandSala.*;
import com.mycompany.reservasdesalas.CommandUsuario.*;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final Map<String, InterfaceCommand> commands = new HashMap<>();

    
    static {
        
        commands.put("Login", new LoginAction());
        
        commands.put("BuscarTodasSalas", new BuscarTodasSalas());
        commands.put("verificarDisponibilidadeSala", new VerificarDisponibilidadeSala());

        commands.put("cadastrarReserva", new CadastrarReservaAction());
        commands.put("atualizarReserva", new AtualizaReservaAction());
        commands.put("deletarReserva", new DeletarReservaAction());
        commands.put("consultarMinhasReservas", new ConsultarReservaAction());

        
        commands.put("consultarSalas", new ConsultarSalaAction());
        
        
    }

    
    public static InterfaceCommand create(String actionName) {
        if (actionName == null || !commands.containsKey(actionName)) {
            return null; 
        }
        return commands.get(actionName);
    }
}