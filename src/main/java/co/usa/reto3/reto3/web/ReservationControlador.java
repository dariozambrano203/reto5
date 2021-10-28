package co.usa.reto3.reto3.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.usa.reto3.reto3.model.Reservation;
import co.usa.reto3.reto3.reportes.ContadorClientes;
import co.usa.reto3.reto3.reportes.StatusReservas;
import co.usa.reto3.reto3.service.ReservationServicio;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins="*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

public class ReservationControlador {
    @Autowired
    private ReservationServicio ReservationServicio;

    @GetMapping("/all")
    public List<Reservation>getReservation(){
        return ReservationServicio.getAll();
    }

    @GetMapping("/{numId}")
    public Optional<Reservation>getReservation(@PathVariable("numId") int id){
        return ReservationServicio.getReservation(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation dpto){
        return ReservationServicio.save(dpto);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservacion) {
        return ReservationServicio.update(reservacion);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int reservationId) {
        return ReservationServicio.deleteReservation(reservationId);
    }
    
    @GetMapping("/report-status")
    public StatusReservas getReservationsStatusReport(){
        return ReservationServicio.getReservationsStatusReport();
    }
    
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationsReportDates(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo){
    return ReservationServicio.getReservationPeriod(dateOne, dateTwo);
    }
    
    @GetMapping("/report-clients")
    public List<ContadorClientes> getReservationsReportsClient(){
        return ReservationServicio.getTopClients();
    }



    
}
