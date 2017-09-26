package com.ldz.config.game.entities;

/**
 * Created by Loic on 23/08/2017.
 */
public enum EntityId {
    currency_entity("Entity managing input/output zit coins."),
    computer_entity("The computer accessing to upgrade menu."),
    upgrade_menu_popup("The upgrade menu. Listing all upgrades and allow player to buy some."),
    exit_upgrade_menu_popup("The exit button to the upgrade menu."),
    persistant_upgrade("The first upgrade available."),
    upgrade("The first upgrade displayed in upgrade menu."),
    upgrade_critical_state_display("The animation of critical state feedback"),
    upgrade_buy_popup("The upgrade popup for buying."),
    upgrade_decade_display("The visualiszation of efficiency decay."),
    upgrade_buy_button("The buy button allowing to buy upgrade."),
    upgrade_resp_button("The button to resplendish the upgrade performances."),
    upgrade_exit_button("The exit button to exit buy popup."),
    upgrade_currency_entity("Container displaying upgrade price."),
    upgrade_currency_displayer("Display one currency of the upgrade."),
    mini_game_entity("Entity managing mini-games."),
    simple_mini_game_target("simple mini games target");

    private String description;

    EntityId(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
