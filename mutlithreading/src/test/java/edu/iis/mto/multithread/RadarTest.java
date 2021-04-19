package edu.iis.mto.multithread;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RadarTest {

    @Mock
    private PatriotBattery batteryMock;

    @Mock
    private ExecutorService executorMock;

    @BeforeEach
    void startUp() {
        when(executorMock.submit(any(Runnable.class))).thenAnswer(invocation -> {
            ((Runnable) invocation.getArgument(0)).run();
            return null;
        });
    }
    @RepeatedTest(10)
    void launchZeroWhenNoticeZero() {
        BetterRadar radar = new BetterRadar(batteryMock, 0);
        radar.executor = executorMock;
        Scud enemyMissile = new Scud();
        radar.notice(enemyMissile);
        verify(batteryMock, times(0)).launchPatriot(enemyMissile);
    }
    @RepeatedTest(10)
    void launch1timeWhenNotice1() {
        BetterRadar radar = new BetterRadar(batteryMock, 1);
        radar.executor = executorMock;
        Scud enemyMissile = new Scud();
        radar.notice(enemyMissile);
        verify(batteryMock, times(1)).launchPatriot(enemyMissile);
    }
    @RepeatedTest(10)
    void launch2timeWhenNotice2() {
        BetterRadar radar = new BetterRadar(batteryMock, 2);
        radar.executor = executorMock;
        Scud enemyMissile = new Scud();
        radar.notice(enemyMissile);
        verify(batteryMock, times(2)).launchPatriot(enemyMissile);
    }

}
