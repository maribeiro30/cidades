package br.com.xpto.cidades.dao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="CIDADE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cidade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IBGE_ID")
    @JsonProperty(value = "ibge_id")
    private Long ibgeId;

    @Column(name = "UF")
    @JsonProperty(value = "uf")
    private String uf;

    @Column(name = "NAME")
    @JsonProperty(value = "name")
    private String name;

    @Column(name = "CAPITAL")
    @JsonProperty(value = "capital")
    private Boolean capital;

    @Column(name = "LONGITUDE")
    @JsonProperty(value = "lon")
    private BigDecimal longitude;

    @Column(name = "LATITUDE")
    @JsonProperty(value = "lat")
    private BigDecimal latitude;

    @Column(name = "NO_ACCENTS")
    @JsonProperty(value = "no_accents")
    private String noAccents;

    @Column(name = "ALTERNATIVE_NAMES")
    @JsonProperty(value = "alternative_names")
    private String alternativeNames;

    @Column(name = "MICRO_REGION")
    @JsonProperty(value = "microregion")
    private String microRegion;

    @Column(name = "MESO_REGION")
    @JsonProperty(value = "mesoregion")
    private String mesoregion;

}
