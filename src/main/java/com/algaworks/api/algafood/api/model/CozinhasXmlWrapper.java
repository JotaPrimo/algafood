package com.algaworks.api.algafood.api.model;

import com.algaworks.api.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;

/**
 * Embrulha uma lista de cozinha
 * */

@Data
@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhasXmlWrapper {

    @NonNull
    @JacksonXmlProperty(localName = "cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Cozinha> cozinhas;
}