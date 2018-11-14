package org.jacared.housepin.models.relatorio;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ExportarHandlerJSONBytesArray<T> implements ExportarHandler<T, byte[]> {

    Map<String, Object> paylaod;
    private Gson gson;

    @Override
    public void start() {
        this.paylaod = new HashMap<>();
        this.gson = new Gson();
    }

    @Override
    public void inserir(EnumExportacao.Componente componente) {
        paylaod.put(componente.name(), componentes.get(componente));
    }

    @Override
    public byte[] gerar() {
        return gson.toJson(paylaod).getBytes();
    }

}
