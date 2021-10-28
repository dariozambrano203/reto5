package co.usa.reto3.reto3.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.usa.reto3.reto3.model.Client;
import co.usa.reto3.reto3.model.Reservation;
import co.usa.reto3.reto3.reportes.ContadorClientes;
import co.usa.reto3.reto3.repository.crud.ReservationCrudRepositorio;

@Repository
public class ReservationRepositorio {

    @Autowired
    private ReservationCrudRepositorio reservationCrudRepositorio;

    public List<Reservation>getAll(){
        return (List<Reservation>)reservationCrudRepositorio.findAll(); 

    }

    public Optional<Reservation>getReservation(int id){
        return reservationCrudRepositorio.findById(id);
    }

    public Reservation save(Reservation reservation){
        return reservationCrudRepositorio.save(reservation);
    }

    public void delete(Reservation reservation){
        reservationCrudRepositorio.delete(reservation);
    }
    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepositorio.findAllByStatus(status);
    }
    
   public List<Reservation> getReservationPeriod(Date a, Date b){
       return reservationCrudRepositorio.findAllByStartDateAfterAndStartDateBefore(a, b);
   } 
   
   public List<ContadorClientes> getTopClients(){
       List<ContadorClientes> res= new ArrayList<>();
       List<Object[]> report = reservationCrudRepositorio.countTotalReservationByClient();
       for(int i=0;i<report.size();i++){
           res.add(new ContadorClientes((Long)report.get(i)[1],(Client) report.get(i)[0]));
       }
       return res;
   }
  
    
    
}
