package sim.integration;

import sim.engine.SimState;
import sim.field.grid.ObjectGrid2D;

public class Collision extends SimState {
	ObjectGrid2D grid = new ObjectGrid2D(20, 20);
	Car car1 = new Car(0, 0, false, false);
	Car car2 = new Car(19, 19, true, true);

	public Collision(long seed){
		super(seed);

		grid.set(car1.getX(), car1.getY(), car1);
		grid.set(car2.getX(), car2.getY(), car2);
	}

	@Override
	public void start() {
		super.start();

		schedule.scheduleRepeating(car1, 2.0);
		schedule.scheduleRepeating(car2, 3.0);
	}
}