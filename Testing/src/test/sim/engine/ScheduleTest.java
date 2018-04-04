package sim.engine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ScheduleTest{
    private Schedule schedule;
    private Increment increment;
    private SimState state;

    @Before
    public void before(){
        schedule = new Schedule();
        increment = new Increment();
        state = new SimState(2, schedule);
    }

    @Test
    public void stepTest(){
        schedule.scheduleOnce(0, increment);

        for(int i = 0; i < 10; i++) {
            schedule.step(state);
            assertEquals("Increment has been stepped.", increment.i, 1);
        }
    }

    @Test
    public void intervalTest(){
        schedule.scheduleRepeating(0, increment, 3.0);

        for(int i = 0; i < 10; i++){
            assertEquals("Adds a repeating event. This event should be called every 3 iterations.", increment.i, i);
            schedule.step(state);
        }
    }

    class Increment implements Steppable{
        private int i = 0;

        @Override
        public void step(SimState state) {
            i++;
        }
    }
}
