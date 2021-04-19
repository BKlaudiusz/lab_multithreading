package edu.iis.mto.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BetterRadar {

    private final int rocketCount;
    private final PatriotBattery battery;
    ExecutorService executor;


    public BetterRadar(PatriotBattery battery, int rocketCount) {
        this.rocketCount = rocketCount;
        this.battery = battery;
    }

    public void notice(Scud enemyMissle) {
        launchPatriot(enemyMissle, rocketCount);
    }

    private void launchPatriot(Scud enemyMissle, int rocketCount) {
        Runnable launchPatriotTask = () -> {
            for (int i = 0; i < rocketCount; i++) {
                battery.launchPatriot(enemyMissle);
            }
        };
        executor.submit(launchPatriotTask);
    }
}