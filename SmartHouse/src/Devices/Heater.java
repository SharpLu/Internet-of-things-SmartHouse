package Devices;

/*
 * @author: Hamed Talebi
 * Date: 2013-11-03
 */

public class Heater implements PhysicalDevices {

	private String state;

	public Heater() {

	}

	@Override
	public String getStates() {
		return state;
	}

	@Override
	public void setStates(String state) {
		this.state = state;
	}

}
