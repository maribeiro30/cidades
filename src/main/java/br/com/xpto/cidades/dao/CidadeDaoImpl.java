package br.com.xpto.cidades.dao;

import br.com.xpto.cidades.dao.domain.Cidade;
import br.com.xpto.cidades.dao.repository.CidadeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CidadeDaoImpl extends CoreDao<Cidade,CidadeRepository> implements CidadeDao {


    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
