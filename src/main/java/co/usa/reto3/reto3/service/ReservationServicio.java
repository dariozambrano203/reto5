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
public class ReservationServicio {
    @Autowired
    private ReservationRepositorio crud;

    public List<Reservation>getAll(){
        return crud.getAll();
    }

    public Optional<Reservation>getReservation(int reservationId){
        return crud.getReservation(reservationId);
    }

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
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> e= crud.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                crud.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            crud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public StatusReservas getReservationsStatusReport(){
        List<Reservation>completed=crud.getReservationByStatus("completed");
        List<Reservation>cancelled=crud.getReservationByStatus("cancelled");
    return new StatusReservas(completed.size(), cancelled.size());
    }
    
    public List<Reservation> getReservationPeriod(String dateA, String dateB){
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date aDate= new Date();
        Date bDate= new Date();
        
       try {
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
    
    public List<ContadorClientes> getTopClients(){
        return crud.getTopClients();
    }
    

    
}
