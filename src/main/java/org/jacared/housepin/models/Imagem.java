package org.jacared.housepin.models;

import javax.persistence.*;

@Entity
@Table(name = "imagem")
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "imagem_id")
    private int id;

    @Column(name = "uri")
    private String uri;

    @ManyToOne
    @JoinColumn(name="anuncio_id")
    private Anuncio anuncio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
}
