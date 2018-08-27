package br.com.xpto.cidades.service;

import br.com.xpto.cidades.web.dto.TransferDto;

import java.nio.file.Path;

public interface TransfomerService {

    TransferDto importar(String nameFile, Path path);



}
