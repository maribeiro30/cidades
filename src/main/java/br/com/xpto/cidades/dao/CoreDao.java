package br.com.xpto.cidades.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CoreDao<T, R extends CrudRepository<T,Long>> {

    @Autowired
    protected R repository;

    @Transactional(readOnly = true)
    public Optional<T> findById(Long id){
        return repository.findById(id);
    }


    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Optional<List<T>> saveAll(List<T> ts){

       Optional<Iterable<T>>  iterable = Optional.of(repository.saveAll(ts));

       List<T> t = new ArrayList<>();
       if(iterable.isPresent())
           iterable.get().forEach(t::add);

       return Optional.of(t);
    }


    public Optional<T> save(T entity) {
        return Optional.ofNullable(repository.save(entity));
    }

    public Optional<List<T>> findAll(){
        List<T> ts = new ArrayList<>();
        repository.findAll().forEach(ts::add);
        return Optional.of(ts);
    }


    public Optional<Long> count(){
        return Optional.of(repository.count());
    }

}
