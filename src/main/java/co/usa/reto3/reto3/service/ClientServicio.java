package co.usa.reto3.reto3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.reto3.reto3.model.Client;
import co.usa.reto3.reto3.repository.ClientRepositorio;

@Service
public class ClientServicio {
    @Autowired
    private ClientRepositorio ClientRepositorio;

    public List<Client>getAll(){
        return ClientRepositorio.getAll();
    }

    public Optional<Client>getClient(int clientId){
        return ClientRepositorio.getClient(clientId);
    }

    public Client save(Client client){
        //Verificamos si el Departamento es nuevo y de ser así se guarda
        if (client.getIdClient()==null) {
            return ClientRepositorio.save(client);            
        }else{ //Si el objeto viene con un numId verificamos si existe o no
            Optional<Client> consulta=ClientRepositorio.getClient(client.getIdClient());
            if (consulta.isEmpty()) {
                return ClientRepositorio.save(client);                
            } else {
                return client;                
            }
        }
    }
    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> e= ClientRepositorio.getClient(client.getIdClient());
            if(!e.isEmpty()){
                if(client.getName()!=null){
                    e.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    e.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    e.get().setPassword(client.getPassword());
                }
                ClientRepositorio.save(e.get());
                return e.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }

    public boolean deleteClient(int clientId) {
        Boolean aBoolean = getClient(clientId).map(client -> {
            ClientRepositorio.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    
}