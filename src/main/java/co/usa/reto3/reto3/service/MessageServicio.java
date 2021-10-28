package co.usa.reto3.reto3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.reto3.reto3.model.Message;
import co.usa.reto3.reto3.repository.MessageRepositorio;

@Service
public class MessageServicio {
    @Autowired
    private MessageRepositorio messageRepositorio;

    public List<Message>getAll(){
        return messageRepositorio.getAll();
    }

    public Optional<Message>getMessage(int messageId){
        return messageRepositorio.getMessage(messageId);
    }

    public Message save(Message message){
        //Verificamos si el Departamento es nuevo y de ser así se guarda
        if (message.getIdMessage()==null) {
            return messageRepositorio.save(message);            
        }else{ //Si el objeto viene con un ID verificamos si existe o no
            Optional<Message> consulta=messageRepositorio.getMessage(message.getIdMessage());
            if (consulta.isEmpty()) {
                return messageRepositorio.save(message);                
            } else {
                return message;                
            }
        }
    }
    public Message update(Message message){
        if(message.getIdMessage()!=null){
            Optional<Message> e= messageRepositorio.getMessage(message.getIdMessage());
            if(!e.isEmpty()){
                if(message.getMessageText()!=null){
                    e.get().setMessageText(message.getMessageText());
                }
                messageRepositorio.save(e.get());
                return e.get();
            }else{
                return message;
            }
        }else{
            return message;
        }
    }

    public boolean deleteMessage(int messageId) {
        Boolean aBoolean = getMessage(messageId).map(message -> {
            messageRepositorio.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    
}