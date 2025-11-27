/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @author uriel
 */
public class Reserva {
    private int idReserva;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private boolean status; 
    private Sala idSala;
    private Usuario idUsuario;
    private String observacao;

    
    public Reserva() {
    }
    
    
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    
     public static class ReservaBuilder{
     private Reserva reserv = new Reserva();
     
     public ReservaBuilder comIdReserva (int idReserva){
         this.reserv.idReserva = idReserva;
         return this;
     }
     
     public ReservaBuilder comData (LocalDate data){
         this.reserv.data = data;
         return this;
     }
     
    public ReservaBuilder comHoraInicio (LocalTime horaInicio){
        this.reserv.horaInicio = horaInicio;
        return this;
    }
    
    public ReservaBuilder comHoraFim (LocalTime horaFim){
        this.reserv.horaFim = horaFim;
        return this;
        
    }
    public ReservaBuilder comStatus (boolean status){
        this.reserv.status = status;
        return this;
    }
    
    public ReservaBuilder comIdSala (Sala idSala){
        this.reserv.idSala = idSala;
        return this;
    }
    
    public ReservaBuilder comIdUsuario (Usuario idUsuario){
        this.reserv.idUsuario = idUsuario;
        return this;
    }
    
    public ReservaBuilder comObservacao (String observacao){
        this.reserv.observacao = observacao;
        return this;
    }
    
    public Reserva constroi(){
        return reserv;
    }
    
  } 
  
}


 