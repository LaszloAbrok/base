package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorTest {

    TrainController controller;
    TrainUser user;
    TrainSensor sensor;

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    
    @Test
    public void AlertSpeedLimitIsAbove500() {
        when(controller.getReferenceSpeed()).thenReturn(100);    
        sensor.overrideSpeedLimit(501);
        verify(user,times(1)).setAlarmState(true);

    }

    
    @Test
    public void AlertSpeedLimitIsBelow0() {
        when(controller.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit(200);
        verify(user,times(1)).setAlarmState(false);
    }

    @Test
    public void DifferencetIsLargerThan50Percent() {
        when(controller.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit((int)0.2*controller.getReferenceSpeed());
        verify(user,times(1)).setAlarmState(true);
    }

    @Test
    public void DifferenceLimitIsSmallerThan50Percent() {
        when(controller.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit((int)0.8*controller.getReferenceSpeed());
        verify(user,times(1)).setAlarmState(false);
    }

    /* 
    @Test
    public void ThisIsAnExampleTestStub() {
        // TODO Delete this and add test cases based on the issues
    }*/
}
