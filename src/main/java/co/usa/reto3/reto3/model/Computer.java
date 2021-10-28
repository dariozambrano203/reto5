package co.usa.reto3.reto3.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/*Create
*
*se crea tabla computer como entidad
*@Dario Zambrano
*/
@Entity
@Table(name="computer")

 

public class Computer implements Serializable{
  /*
*publicMethodCommentRequirement
*Se crea la llave primaria para computer
*Se relaciona con las clases Category, Client, Computer
*
*/  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//se crea como entidad

    private Integer id; //id general
    private String name; //Nombre del computador
    private String brand; //marca del fabricante
    private Integer year; //año de fabricación
    private String description; //que procesador es*/

    @ManyToOne//muchos a uno 
    @JoinColumn(name = "categoryid")//unir tablas por el join
    @JsonIgnoreProperties("computers")//para no crear el loop infinito
    
    private Category category;//variable categoria
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "computer")//uno a muchos 
    @JsonIgnoreProperties({"computer", "client"})//para no crear el loop infinito*/
    private List<Message> messages;//lista de mensajes
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "computer")
    @JsonIgnoreProperties({"computer", "client"})//*para no crear el loop infinito*/
    private List<Reservation> reservations;//Lista de reservas

 /**
   * generar setters and getters para ID
   * @exception sin excepcion
   * @return id.
   */ 
    
    public Integer getId() {
        //getter del nID
        return id;
    }

     /**
   * generar setters para ID
   * @exception sin excepcion
   * @return id.
   */ 
    public void setId(Integer id) {
        this.id = id;
    }
 /**
   * generar  getters para id
   * @exception sin excepcion
   * @return name.
   */ 
    public String getName() {
        /*getter del nombre*/
        return name;
    }
 /**
   * generar setters para name
   * @exception sin excepcion
   * @return name.
   */ 
    public void setName(String name) {
        /*setter del nombre*/
        this.name = name;
    }
 /**
   * generar setters and getters para Brand
   * @exception sin excepcion
   * @return brand.
   */ 
    public String getBrand() {
        /*getter de la marca*/
        return brand;
    }
 /**
   * generar setters para brand
   * @exception sin excepcion
   * @return brand.
   */ 
    public void setBrand(String brand) {
        /*setter de la marca*/
        this.brand = brand;
    }

     /**
   * generar getters para año
   * @exception sin excepcion
   * @return year.
   */ 
    public Integer getYear() {
        /*getter del año*/
        return year;
    }
 /**
   * generar setters para año
   * @exception sin excepcion
   * @return year.
   */ 
    public void setYear(Integer year) {
        /*setter del año*/
        this.year = year;
    }
 /**
   * generar setters para descripcion
   * @exception sin excepcion
   * @return descripcion.
   */ 
    public String getDescription() {
        /*getter de la descripcion*/
        return description;
    }
 /**
   * generar getters para descripcion
   * @exception sin excepcion
   * @return descripcion.
   */ 
    public void setDescription(String description) {
        /*setter de la descripcion*/
        this.description = description;
    }
 /**
   * generar getters para category
   * @exception sin excepcion
   * @return category.
   */ 
    public Category getCategory() {
        /*getter de la categoria*/
        return category;
    }
 /**
   * generar setters para año
   * @exception sin excepcion
   * @return category.
   */ 
    public void setCategory(Category category) {
        /*setter de la categoria*/
        this.category = category;
    }
 /**
   * generar setters para año
   * @exception sin excepcion
   * @return messages.
   */ 
    public List<Message> getMessages() {
        /*/lista de mensajes*/
        return messages;
    }
 /**
   * generar setters para año
   * @exception sin excepcion
   * @return message.
   */ 
    public void setMessages(List<Message> messages) {
        /*setter de la  lista de mensajes*/
        this.messages = messages;

    }
 /**
   * generar getters para reserva
   * @exception sin excepcion
   * @return reserva.
   */ 
    public List<Reservation> getReservations() {
        /*getter de la reserva*/
        return reservations;
    }
 /**
   * generar setters para reserva
   * @exception sin excepcion
   * @return reserva.
   */ 
    public void setReservations(List<Reservation> reservations) {
        /*setter de la reserva*/
        this.reservations = reservations;
    }



    
}
