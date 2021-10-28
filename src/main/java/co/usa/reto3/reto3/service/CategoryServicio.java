package co.usa.reto3.reto3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.reto3.reto3.model.Category;
import co.usa.reto3.reto3.repository.CategoryRepositorio;

@Service
public class CategoryServicio {
    @Autowired
    private CategoryRepositorio categoryRepositorio;

    public List<Category>getAll(){
        return categoryRepositorio.getAll();
    }

    public Optional<Category>getCategory(int idCategory){
        return categoryRepositorio.getCategory(idCategory);
    }

    public Category save(Category category){
        //Verificamos si el Departamento es nuevo y de ser así se guarda
        if (category.getId()==null) {
            return categoryRepositorio.save(category);            
        }else{ //Si el objeto viene con un numId verificamos si existe o no
            Optional<Category> consulta=categoryRepositorio.getCategory(category.getId());
            if (consulta.isEmpty()) {
                return categoryRepositorio.save(category);                
            } else {
                return category;                
            }
        }
    }

    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category>g=categoryRepositorio.getCategory(category.getId());
            if(!g.isEmpty()){
                if(category.getDescription()!=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    g.get().setName(category.getName());
                }
                return categoryRepositorio.save(g.get());
            }
        }
        return category;
    }
    public boolean deleteCategory(int categoryId){
        Boolean d=getCategory(categoryId).map(category -> {
            categoryRepositorio.delete(category);
            return true;
        }).orElse(false);
        return d;
    }


    
}