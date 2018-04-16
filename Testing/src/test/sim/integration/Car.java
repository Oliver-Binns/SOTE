package sim.integration;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.grid.Grid3D;

public class Car implements Steppable {
	private int x;
	private int y;
	private boolean x_reverse;
	private boolean y_reverse;

	public Car(int x, int y, boolean x_reverse, boolean y_reverse){
		this.x = x;
		this.y = y;

		this.x_reverse = x_reverse;
		this.y_reverse = y_reverse;
	}

	public void willCollide(Collision world){
		world.grid.getRadialNeighbors(x, y, 3, Grid3D.TOROIDAL, false);
	}

	@Override
	public void step(SimState state) {
		Collision world = (Collision)state;
		world.grid.set(x, y, null);

		x += x_reverse ? -1: 1;
		y += y_reverse ? -1: 1;

		if(world.grid.get(x, y) != null){
			System.out.println("Collision!");
			world.kill();
		}

		world.grid.set(x, y, this);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
