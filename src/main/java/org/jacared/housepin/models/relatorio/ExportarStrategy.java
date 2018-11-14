package org.jacared.housepin.models.relatorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ExportarStrategy<T, R> {
    Map<EnumExportacao.Componente, Object> componentes = new HashMap<>();
    private Map<EnumExportacao.Formato, ExportarHandler<T, R>> handlers = new HashMap<>();

    ExportarStrategy<T, R> sethandler(EnumExportacao.Formato formato, ExportarHandler<T, R> handler) {
        this.handlers.put(formato, handler);
        return this;
    }

    ExportarStrategy adicionar(EnumExportacao.Componente c, Object d) {
        componentes.put(c, d);
        return this;
    }

    public R para(EnumExportacao.Formato formato, List<EnumExportacao.Componente> componentes) {
        if (this.handlers.containsKey(formato)) {
            return this.handlers.get(formato)
                    .setComponentes(this.componentes)
                    .exportar(componentes);
        } else {
            throw new RuntimeException("Formato não suportado.");
        }
    }
}
