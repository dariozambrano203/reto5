package co.usa.reto3.reto3.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.reto3.reto3.model.Reservation;
import co.usa.reto3.reto3.reportes.ContadorClientes;
import co.usa.reto3.reto3.reportes.StatusReservas;
import co.usa.reto3.reto3.repository.ReservationRepositorio;

@Service
/**
 * Reservacionservicio implementa metodos para hacer reservas 
 * @author Dario Zambrano
 */
public class ReservationServicio {
    @Autowired
    /**
    * An crea una variable nombre crud tipo repositorio.
    */
    private ReservationRepositorio crud;

    /**
    * consigue toda la lista de reservas
    */

    public List<Reservation>getAll(){

        return crud.getAll();
    }
/**
    * busca el id de la reserva
    */
    public Optional<Reservation>getReservation(int reservationId){
        return crud.getReservation(reservationId);
    }

/**
    * Apara guardar la reserva @return reserva
    */
    public Reservation save(Reservation reservation){
        //Verificamos si el Departamento es nuevo y de ser así se guarda
        if (reservation.getIdReservation()==null) {
            return crud.save(reservation);            
        }else{ //Si el objeto viene con un numId verificamos si existe o no
            Optional<Reservation> consulta=crud.getReservation(reservation.getIdReservation());
            if (consulta.isEmpty()) {
                return crud.save(reservation);                
            } else {
                return reservation;                
            }
        }
    }
    /**
    * para actualizar la reserva si hay alguna modificación
    */
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> e= crud.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    /**
    * pone el inicio de la reserva
    */
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                                       /**
    * pone el final de la reserva
    */
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                                       /**
    * verifica el estatus, completed o cancelles
    */
                }
                crud.save(e.get());
                return e.get();
                       /**
    * para guardarla
    */
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }
                   /**
    * para borrar la reserva
    */
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            crud.delete(reservation);
                                     /**
    * responde si la reserva ha sido borrada
    */
            return true;
        }).orElse(false);
        return aBoolean;
    }
                         /**
    * reporte del estado de la reserva completed /cancelles
    */
    public StatusReservas getReservationsStatusReport(){
  
        List<Reservation>completed=crud.getReservationByStatus("completed");
        List<Reservation>cancelled=crud.getReservationByStatus("cancelled");
    return new StatusReservas(completed.size(), cancelled.size());
    }
    
    public List<Reservation> getReservationPeriod(String dateA, String dateB){

                           /**
    * duracion d ela reserva
    */
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date aDate= new Date();
        Date bDate= new Date();
        
       try {
                           /**
    * pconvierte la fecha
    */
           aDate = parser.parse(dateA);
           bDate = parser.parse(dateB);
       }catch(ParseException evt){
           evt.printStackTrace();
       }
       if(aDate.before(bDate)){
           return crud.getReservationPeriod(aDate, bDate);
       }else{
           return new ArrayList<>();
       } 
    
    }
                       /**
    * cuenta clientes en un periodo de tiempo
    */
    public List<ContadorClientes> getTopClients(){
        return crud.getTopClients();
    }
    

    
}
