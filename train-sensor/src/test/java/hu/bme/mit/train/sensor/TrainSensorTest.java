package hu.bme.mit.train.sensor;

import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.interfaces.*;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

	TrainSensor sensor;
    TrainController controller;
    TrainUser user;
	
	@Before
	public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
		sensor = new TrainSensorImpl(controller, user);
        // TODO Add initializations
    }

/*    @Test
    public void ThisIsAnExampleTestStub() {
        // TODO Delete this and add test cases based on the issues
    }*/

    @Test
    public void overrideSpeedLimit_lessThanZero() {
        when(controller.getReferenceSpeed()).thenReturn(-1);
        sensor.overrideSpeedLimit(-1);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_MoreThan500() {
        when(controller.getReferenceSpeed()).thenReturn(501);
        sensor.overrideSpeedLimit(501);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_moreThan50PercentLessThanReferenceSpeed() {
        when(controller.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit(49);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_moreThan50PercentLargerThanReferenceSpeed() {
        when(controller.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit(151);
        verify(user, times(1)).setAlarmState(true);
    }

}
