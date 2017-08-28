package com.ldz.config.game.entities;

/**
 * Created by Loic on 23/08/2017.
 */
public enum EntityId {
    zit_coin_entity("Entity managing input/output zit coins."),
    ithereum_coin_entity("Entity managing input/output ithereum coins."),
    loud_coin_entity("Entity managing input/output loud coins."),
    computer_entity("The computer accessing to upgrade menu."),
    upgrade_menu_popup("The upgrade menu. Listing all upgrades and allow player to buy some."),
    exit_upgrade_menu_popup("The exit button to the upgrade menu."),
    persistant_upgrade_1("The first upgrade available."),
    persistant_upgrade_2("The second upgrade available."),
    upgrade_1("The first upgrade displayed in upgrade menu."),
    upgrade_1_buy_popup("The upgrade popup for buying."),
    upgrade_1_cost_display("The cost of upgrade."),
    upgrade_1_decade_display("The visualiszation of efficiency decay."),
    upgrade_1_buy_button("The buy button allowing to buy upgrade."),
    upgrade_1_exit_button("The exit button to exit buy popup."),
    upgrade_2("The second upgrade displayed in upgrade menu."),
    upgrade_2_buy_popup("The upgrade popup for buying."),
    upgrade_2_buy_button("The buy button allowing to buy upgrade.");

    private String description;

    EntityId(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
