package br.com.xpto.cidades.dao.repository;

import br.com.xpto.cidades.dao.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade,Long> {
}
