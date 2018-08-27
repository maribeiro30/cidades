package br.com.xpto.cidades.web.rest;

import br.com.xpto.cidades.service.TransfomerService;
import br.com.xpto.cidades.web.dto.TransferDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping(value = "/importar")
@Api(value = "Cidades API", description = "Api importacao de cidades")
public class CSVController {

    @Autowired
    private TransfomerService transformerService;

    @PostMapping
    @ApiOperation(value = "Importar arquivo empresa")
    public Mono<ResponseEntity<?>> create(@ApiParam(value = "Arquivo para download",
            required = true) @RequestParam("file") MultipartFile file) {

        if(!file.isEmpty()){
            String nameFile = file.getOriginalFilename();

            try {
                Path pathXls = Files.write(new File(nameFile).toPath(),file.getBytes());
                TransferDto<File> xlsRetorno = new TransferDto<>(null, null, null);
                Flux.just(transformerService.importar(nameFile,pathXls)).subscribe(s -> {{
                    xlsRetorno.setHttpStatus(s.getHttpStatus());
                    xlsRetorno.setDescription(s.getDescription());
                }});

                switch(xlsRetorno.getHttpStatus()){
                    case OK:  return Mono.just(ResponseEntity.ok()
                            .body("Sucesso!"));
                    default : return Mono.just(new ResponseEntity<String>("falha : " + xlsRetorno.getDescription(),xlsRetorno.getHttpStatus()));
                }

            } catch (IOException e) {
                return Mono.just(new ResponseEntity<String>(nameFile + ", falha : " + e.getMessage(), HttpStatus.BAD_GATEWAY));
            }

        }else {
            return Mono.just(new ResponseEntity<String>("Arquivo vazio.", HttpStatus.BAD_GATEWAY));
        }

    }


}
