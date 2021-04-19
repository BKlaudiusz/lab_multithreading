package edu.iis.mto.multithread;

import java.util.concurrent.ExecutorService;

public class BetterRadar {

    private final int rocketCount;
    private final PatriotBattery battery;
    ExecutorService executor;


    public BetterRadar(PatriotBattery battery, int rocketCount) {
        this.rocketCount = rocketCount;
        this.battery = battery;
    }

    public void notice(Scud enemyMissile) {
        launchPatriot(enemyMissile, rocketCount);
    }

    private void launchPatriot(Scud enemyMissile, int rocketCount) {
        Runnable launchPatriotTask = () -> {
            for (int i = 0; i < rocketCount; i++) {
                battery.launchPatriot(enemyMissile);
            }
        };
        executor.submit(launchPatriotTask);
    }
}