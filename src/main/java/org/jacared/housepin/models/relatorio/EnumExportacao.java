package org.jacared.housepin.models.relatorio;

public class EnumExportacao {
    public enum Componente {
        CABECALHO,
        CORPO,
        RODAPE
    }

    public enum Formato {
        CSV(true),
        JSON(true),
        TXT(false);

        public boolean isForDownload;

        Formato(boolean ehParaFazerDownload) {
            isForDownload = ehParaFazerDownload;
        }
    }
}
