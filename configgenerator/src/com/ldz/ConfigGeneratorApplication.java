package com.ldz;

import com.badlogic.gdx.utils.Json;
import com.ldz.common.ArgsVerificatorUtil;
import com.ldz.config.upgrade.referential.domain.Currency;
import com.ldz.config.upgrade.referential.domain.PriceCurrencies;
import com.ldz.config.upgrade.referential.domain.Upgrade;
import com.ldz.config.upgrade.referential.domain.Upgrades;
import com.ldz.domain.UpgradeFormulas;
import org.apache.commons.lang3.SerializationUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Loic on 06/09/2017.
 */
public class ConfigGeneratorApplication {

    private static String OUTPUT_JSON_FILE_ARG = "outputJson";

    public static void main(String[] argc) {

        Map<String, String> args = ArgsVerificatorUtil.extractScriptArgs(argc);

        String outputJsonFilePath = args.get(OUTPUT_JSON_FILE_ARG);

        if (outputJsonFilePath != null) {

            try {
                Json json = new Json();
                UpgradeFormulas upgradeFormulas =
                        json.fromJson(UpgradeFormulas.class, new FileInputStream(new File(Paths.get("configgenerator\\resources\\upgradeFormulas.json").toAbsolutePath().toString())));

                //load initial upgrade referential
                Upgrades upgrades =
                        json.fromJson(Upgrades.class, new FileInputStream(new File(Paths.get("configgenerator\\resources\\UpgradeReferential.json").toAbsolutePath().toString())));

                ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
                ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");

                engine.put("oldValue", 10);
                engine.eval(upgradeFormulas.getUpgradeBonusFormula());

                Upgrades finalUpgrades = new Upgrades();
                finalUpgrades.setUpgrades(new ArrayList<>());

                for (Upgrade upgrade :
                        upgrades.getUpgrades()) {
                    //upgrade copy
                    Upgrade finalUpgrade = processUpgrade(upgradeFormulas, engine, upgrade);
                    finalUpgrades.getUpgrades().add(finalUpgrade);
                }


                //write conf
                String jsonString = json.toJson(finalUpgrades);
                Files.write(Paths.get(outputJsonFilePath), jsonString.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    private static Upgrade processUpgrade(UpgradeFormulas upgradeFormulas, ScriptEngine engine, Upgrade currentUpgrade) throws ScriptException {
        Upgrade finalUpgrade = SerializationUtils.clone(currentUpgrade);

        PriceCurrencies actualPriceCurrencies = currentUpgrade.getUpgradesCosts().get(0);
        PriceCurrencies actualBonusCurrencies = currentUpgrade.getUpgradesCosts().get(0);

        writeAllPricesCurrencies(upgradeFormulas, engine, finalUpgrade.getUpgradesCosts(), actualPriceCurrencies);
        writeAllPricesCurrencies(upgradeFormulas, engine, finalUpgrade.getUpgradeBonuses(), actualBonusCurrencies);
        return finalUpgrade;
    }

    private static void writeAllPricesCurrencies(UpgradeFormulas upgradeFormulas, ScriptEngine engine, List<PriceCurrencies> priceCurrenciesAccumulator, PriceCurrencies actualPriceCurrencies) throws ScriptException {
        for (int i = 0; i < upgradeFormulas.getNbLevel(); i++) {

            PriceCurrencies priceCurrenciesCopy = SerializationUtils.clone(actualPriceCurrencies);
            priceCurrenciesCopy.setLevelNumber(priceCurrenciesCopy.getLevelNumber() + 1);
            for (Currency currency :
                    priceCurrenciesCopy.getCurrencies()) {
                engine.put("oldValue", currency.getValue());
                double resultValue = (double) engine.eval(upgradeFormulas.getUpgradeBonusFormula());
                currency.setValue(Float.valueOf(String.valueOf(resultValue)));
            }
            priceCurrenciesAccumulator.add(priceCurrenciesCopy);

            actualPriceCurrencies = priceCurrenciesCopy;
        }
    }

}
