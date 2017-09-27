package com.ldz.domain;

/**
 * Created by Loic on 27/09/2017.
 */
public class UpgradeFormulas {

    private int nbLevel;

    private String upgradeCostFomula;
    private String upgradeBonusFormula;


    public int getNbLevel() {
        return nbLevel;
    }

    public void setNbLevel(int nbLevel) {
        this.nbLevel = nbLevel;
    }

    public String getUpgradeCostFomula() {
        return upgradeCostFomula;
    }

    public void setUpgradeCostFomula(String upgradeCostFomula) {
        this.upgradeCostFomula = upgradeCostFomula;
    }

    public String getUpgradeBonusFormula() {
        return upgradeBonusFormula;
    }

    public void setUpgradeBonusFormula(String upgradeBonusFormula) {
        this.upgradeBonusFormula = upgradeBonusFormula;
    }
}
