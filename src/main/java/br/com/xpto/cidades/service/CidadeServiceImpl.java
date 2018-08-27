package br.com.xpto.cidades.service;

import br.com.xpto.cidades.dao.CidadeDao;
import br.com.xpto.cidades.dao.domain.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CidadeServiceImpl implements CidadeService {

    @Autowired
    private CidadeDao cidadeDao;

    @Override
    public Optional<Map<String, Integer>> quantidadeCidadesPorEstado() {
        Optional<List<Cidade>> optCidades = cidadeDao.findAll();

        if(optCidades.isPresent()){

            Map<String, Integer> map = new HashMap<>();

            optCidades.get().stream().forEach(c -> {{
                if(map.containsKey(c.getUf())) {
                    map.get(c.getUf());
                    map.put(c.getUf(),map.get(c.getUf())+1);
                }else{
                    map.put(c.getUf(),map.get(c.getUf())+1);
                }
            }});

            return Optional.ofNullable(map);
        }

        return Optional.empty();
    }
}
