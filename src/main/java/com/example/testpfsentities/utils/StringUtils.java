package com.example.testpfsentities.utils;

import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.enums.PlayerPosition;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public interface StringUtils {
    String GK = "GK";
    String CB = "CB";
    String RB = "RB";
    String LB = "LB";
    String CDM = "CMD";
    String CM = "CM";
    String RCM = "LCM";
    String LCM = "LCM";
    String RM = "RM";
    String LM = "LM";
    String CAM = "CAM";
    String RW = "RW";
    String LW = "LW";
    String ST = "ST";

    String GARDIEN = "Gardien";
    String DEFENSEUR = "Défenseur";
    String MILIEU = "Milieu";
    String ATTAQUANT = "Attaquant";

    static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    static List<String> getListAvailablePositionsInATeam() {
//        return Arrays.asList(GK, CB, RB, LB, CDM, CM, LCM, RCM, CAM, RW, ST, LW, RM, LM);
        return Arrays.asList(PlayerPosition.GARDIEN.name(), PlayerPosition.DEFENSEUR.name(),
                PlayerPosition.MILIEU.name(), PlayerPosition.ATTAQUANT.name());
    }

    static String getFinalScore(int goalsFirstTeam, int goalSecondTeam) {
        return goalsFirstTeam + "-" + goalSecondTeam;
    }

    static String getNotificationPlayerMessageForEvaluation(Match match) {
        return "You have to evaluate your match which helds at "
                + match.getDate() + " and finished at "
                + DateUtils.returnDateAfterDuration(match.getDate(), match.getDuration()
        );
    }
}

