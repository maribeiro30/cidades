package br.com.xpto.cidades.web.rest;

import br.com.xpto.cidades.dao.CidadeDao;
import br.com.xpto.cidades.dao.domain.Cidade;
import br.com.xpto.cidades.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping
public class CidadeController {

    @Autowired
    private CidadeDao cidadeDao;

    @Autowired
    private CidadeService cidadeService;

    @PostMapping
    public Mono<ResponseEntity<?>> create( @RequestBody Cidade cidade) {
        Optional<Cidade> opt = cidadeDao.save(cidade);

        if(opt.isPresent())
            return Mono.just(ResponseEntity.ok().body(opt.get()));
        else return Mono.just(new ResponseEntity<String>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping
    public Mono<ResponseEntity<?>> delete(@PathVariable Long id) {
        cidadeDao.deleteById(id);

        return Mono.just(ResponseEntity.ok().body("Sucesso"));

    }

    @GetMapping("/cidadesPorCapital")
    public Mono<ResponseEntity<?>> showCidadesPorCapital(){
        Optional<Map<String,Integer>> map = cidadeService.quantidadeCidadesPorEstado();

        StringBuilder sb = new StringBuilder("{");

        String virgula = "";
        for(String uf : map.get().keySet()) {
            sb.append(virgula + uf + " : " + map.get().get(uf));
            virgula = ", ";
        }

        sb.append("}");

        return Mono.just(ResponseEntity.ok(sb.toString()));
    }

    @GetMapping("/totalRegistros")
    public Mono<ResponseEntity<?>> showTotalRegistro(){
        Optional<Long> total = cidadeDao.count();

        if(total.isPresent())
            return Mono.just(ResponseEntity.ok("{ total : "+ total.get() +"}"));
        else return Mono.just(ResponseEntity.ok("{ total : 0 }"));



    }

}
