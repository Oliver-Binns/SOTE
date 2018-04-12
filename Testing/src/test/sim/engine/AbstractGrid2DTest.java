package sim.engine;

import org.junit.Before;
import org.junit.Test;
import sim.field.grid.DoubleGrid2D;
import sim.field.grid.Grid2D;
import sim.util.IntBag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AbstractGrid2DTest {
	private DoubleGrid2D toTest;

	private int width = 200;
	private int height = 160;

	@Before
	public void before(){
		toTest = new DoubleGrid2D(width, height);
	}

	@Test
	public void testRadial(){
		IntBag x = new IntBag();
		IntBag y = new IntBag();

		int origin_x = 150;
		int origin_y = 150;
		int radius = 20;

		toTest.getRadialLocations(origin_x, origin_y, radius, Grid2D.TOROIDAL, true, x, y);

		assertTrue("x and y are matching", x.size() == y.size());
		assertEquals("x.size() == 1345", 1345, x.size());

		for(int i = -radius; i <= radius; i++){
			for(int j =-radius; j <= radius; j++) {
				if (i * i + j * j <= radius * radius) {
					assertTrue(x.contains((origin_x + i) % width));
					assertTrue(y.contains((origin_y + j) % height));
				}
			}
		}
	}



}
