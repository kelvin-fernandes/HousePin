package org.jacared.housepin.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "caracteristica")
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "caracteristica_id")
    private int id;

    @Column(name = "descricao", length = 50)
    private String descricao;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable( name = "caracterisica_anuncio", joinColumns = { @JoinColumn(name = "caracteristica_id") },
            inverseJoinColumns = { @JoinColumn(name = "anuncio_id") })
    Set<Anuncio> anuncios = new HashSet<>();
}
