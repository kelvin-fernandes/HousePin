package org.jacared.housepin.models.relatorio;

import org.jacared.housepin.models.Anuncio;

public final class ExportarAnunciosStrategy extends ExportarStrategy<Anuncio, byte[]> {

    ExportarAnunciosStrategy() {
        super.sethandler(EnumExportacao.Formato.CSV, new ExportarHandlerCSVBytesArray<>())
                .sethandler(EnumExportacao.Formato.JSON, new ExportarHandlerJSONBytesArray<>())
                .sethandler(EnumExportacao.Formato.TXT, new ExportarHandlerCSVBytesArray<>());
    }
}
