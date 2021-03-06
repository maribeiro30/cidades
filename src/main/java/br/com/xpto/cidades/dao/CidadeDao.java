package br.com.xpto.cidades.dao;

import br.com.xpto.cidades.dao.domain.Cidade;

import java.util.List;
import java.util.Optional;

public interface CidadeDao {

    Optional<List<Cidade>> saveAll(List<Cidade> ts);

    Optional<Cidade> save(Cidade cidade);

    void deleteById(Long id);

    Optional<List<Cidade>> findAll();

    Optional<Long> count();


}
