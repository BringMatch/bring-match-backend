package com.example.testpfsentities.entities.composite;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public
class TeamPlayerKey implements Serializable {

    @Column(name = "team_id")
    String team_id;

    @Column(name = "player_id")
    String player_id;

}
