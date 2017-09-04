package com.ldz.config.game.entities;

/**
 * Created by Loic on 23/08/2017.
 */
public enum EntityId {
    zit_coin_entity("Entity managing input/output zit coins.", EntityType.NONE),
    ithereum_coin_entity("Entity managing input/output ithereum coins.", EntityType.NONE),
    loud_coin_entity("Entity managing input/output loud coins.", EntityType.NONE),
    computer_entity("The computer accessing to upgrade menu.", EntityType.NONE),
    upgrade_menu_popup("The upgrade menu. Listing all upgrades and allow player to buy some.", EntityType.NONE),
    exit_upgrade_menu_popup("The exit button to the upgrade menu.", EntityType.NONE),
    persistant_upgrade_1("The first upgrade available.", EntityType.NONE),
    persistant_upgrade_2("The second upgrade available.", EntityType.NONE),
    upgrade_1("The first upgrade displayed in upgrade menu.", EntityType.NONE),
    upgrade_1_critical_state_display("The animation of critical state feedback", EntityType.NONE),
    upgrade_1_buy_popup("The upgrade popup for buying.", EntityType.NONE),
    upgrade_1_cost_display("The cost of upgrade.", EntityType.COST_DISPLAY),
    upgrade_1_decade_display("The visualiszation of efficiency decay.", EntityType.DECADE_DISPLAY),
    upgrade_1_buy_button("The buy button allowing to buy upgrade.", EntityType.NONE),
    upgrade_1_resp_button("The button to resplendish the upgrade performances.", EntityType.NONE),
    upgrade_1_exit_button("The exit button to exit buy popup.", EntityType.NONE),
    upgrade_2("The second upgrade displayed in upgrade menu.", EntityType.NONE),
    upgrade_2_buy_popup("The upgrade popup for buying.", EntityType.NONE),
    upgrade_2_buy_button("The buy button allowing to buy upgrade.", EntityType.NONE);

    private String description;
    private EntityType entityType;

    EntityId(String description, EntityType entityType) {
        this.description = description;
        this.entityType = entityType;
    }

    public String getDescription() {
        return description;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
