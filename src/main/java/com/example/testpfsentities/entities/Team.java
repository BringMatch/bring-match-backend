package com.example.testpfsentities.entities;

import com.example.testpfsentities.entities.enums.MatchResult;
import com.example.testpfsentities.entities.enums.MatchStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"playersTeams"},allowGetters = true)
public class Team extends AbstractEntity {
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "players_teams",
//            joinColumns = @JoinColumn(name = "team_id"),
//            inverseJoinColumns = @JoinColumn(name = "player_id")
//    )
//    private List<Player> players;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    List<TeamPlayer> playersTeams;

//    @ManyToOne
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    @JoinTable(name = "match_teams", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "match_id"))
//    private Match match;
    private int length;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private MatchResult matchResult;

    private int numberGoals;

}
