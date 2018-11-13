package org.jacared.housepin.models.relatorio;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.utils.EnumFinalidade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.jacared.housepin.models.relatorio.EnumExportacao.Componente.CABECALHO;
import static org.jacared.housepin.models.relatorio.EnumExportacao.Componente.CORPO;
import static org.jacared.housepin.models.relatorio.EnumExportacao.Componente.RODAPE;

public class Relatorio {

    private List<Anuncio> anuncios;
    private Double total;
    private Anunciante anunciante;
    private EnumFinalidade tipo;

    public Relatorio(List<Anuncio> anuncios, EnumFinalidade tipo, Anunciante anunciante) {
        this.anuncios = anuncios;
        this.anunciante = anunciante;
        this.tipo = tipo;
        this.total = anuncios.stream().mapToDouble(Anuncio::getValor).sum();
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Anunciante getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }

    public EnumFinalidade getTipo() {
        return tipo;
    }

    public void setTipo(EnumFinalidade tipo) {
        this.tipo = tipo;
    }

    public ExportarStrategy exportar() {

        Map<String, String> cabecalho = new HashMap<>();
        cabecalho.put("Tipo dos anuncios", tipo.toString());
        cabecalho.put("anunciante", this.anunciante.getNome());
        Map<String, String> rodape = new HashMap<>();
        rodape.put("Total", String.valueOf(this.total));

        return new ExportarAnunciosStrategy()
                .adicionar(CORPO, this.anuncios)
                .adicionar(CABECALHO, cabecalho)
                .adicionar(RODAPE, rodape);
    }
}
