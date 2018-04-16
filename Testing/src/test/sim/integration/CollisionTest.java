package sim.integration;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CollisionTest{
	@Test
	public void testMovementCollision()
	{
		Collision obj = new Collision(3);
		obj.start();

		while(!obj.schedule.scheduleComplete()){
			obj.schedule.step(obj);
		}

		assertEquals(23.0, obj.schedule.getTime());
		assertTrue(obj.schedule.isSealed());

		assertEquals(12, obj.car1.getX());
		assertEquals(12, obj.car1.getY());

		assertEquals(11, obj.car2.getX());
		assertEquals(11, obj.car2.getY());
	}

	@Test
	public void testObjectDetection()
	{
		Collision obj = new Collision(3);
		obj.start();

		while(!obj.schedule.scheduleComplete()){
			obj.schedule.step(obj);
		}

		assertEquals(23.0, obj.schedule.getTime());
		assertTrue(obj.schedule.isSealed());

		assertEquals(12, obj.car1.getX());
		assertEquals(12, obj.car1.getY());

		assertEquals(11, obj.car2.getX());
		assertEquals(11, obj.car2.getY());
	}
}