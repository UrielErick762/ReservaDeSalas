package com.mycompany.reservasdesalas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InterfaceCommand {
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
//vnsnv