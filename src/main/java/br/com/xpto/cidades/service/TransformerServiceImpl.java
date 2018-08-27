package br.com.xpto.cidades.service;

import br.com.xpto.cidades.dao.CidadeDao;
import br.com.xpto.cidades.dao.domain.Cidade;
import br.com.xpto.cidades.web.dto.TransferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class TransformerServiceImpl implements TransfomerService {

    @Autowired
    private CidadeDao cidadeDao;

    @Override
    public TransferDto importar(String nameFileCompany, Path path) {
        try {

            TransferDto<String> transferFileSF = processarArquivo(path);

            return transferFileSF;
        } catch (Exception e) {
            return new TransferDto<>(HttpStatus.BAD_GATEWAY,e.getMessage());
        }
    }


    private TransferDto processarArquivo(Path path) {
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            List<Cidade> cidades = new ArrayList<>();


            reader.lines().forEach( l -> {{
                String[] campos = l.split(";");
                cidades.add(new Cidade(){{
                    if(!campos[0].equals("ibge_id")) {
                        setIbgeId(Long.valueOf(campos[0]));
                        setUf(campos[1]);
                        setName(campos[2]);
                        setCapital(Boolean.valueOf(campos[3]));
                        setLongitude(new BigDecimal(campos[4]));
                        setLatitude(new BigDecimal(campos[5]));
                        setNoAccents(campos[6]);
                        setAlternativeNames(campos[7]);
                        setMicroRegion(campos[8]);
                        setMesoregion(campos[9]);
                    }
                }});

            }});

            cidadeDao.saveAll(cidades);

            return new TransferDto<>(HttpStatus.OK, null);
        } catch(Exception ie) {
            return new TransferDto<>(HttpStatus.BAD_REQUEST, ie.getMessage());
        }
    }




}
