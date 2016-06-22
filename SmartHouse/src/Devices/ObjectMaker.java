package Devices;

import java.util.HashMap;
import java.util.Map;

/*
 * @author: Hamed Talebi
 * Date: 2013-10-16
 * THE FIRST TIME WE MAKE OUR OBJECT IN THIS CLASS
 */
public class ObjectMaker {
	/*
	 * Making object of Maker and HashMap to set our objects from
	 */
	Map<String, Devices> objectMap = new HashMap<>();
	Maker maker = new Maker();

	public ObjectMaker(Map<String, Devices> objectMap, Maker maker) {
		this.objectMap = objectMap;
		this.maker = maker;
	}

	public void makingObject() {
		for (int i = 0; i < maker.getDevices().size(); i++) {
			try {
				objectMap.put(
						maker.getNames().get(i),
						(Devices) Class.forName(
								"Devices." + maker.getDevices().get(i))
								.newInstance());
				System.out.println(objectMap.get(maker.getNames().get(i))
						.getStates());
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Failed to make the objects map");
			}
		}
	}

}
