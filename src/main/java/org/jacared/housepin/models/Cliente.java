package org.jacared.housepin.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cliente")
public class Cliente extends Usuario{

}
