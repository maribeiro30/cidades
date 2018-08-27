package br.com.xpto.cidades.service;

import java.util.Map;
import java.util.Optional;

public interface CidadeService {


    Optional<Map<String,Integer>> quantidadeCidadesPorEstado();
}
