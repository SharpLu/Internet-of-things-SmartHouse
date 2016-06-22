package Devices;

/*
 * @author: Hamed Talebi
 * Date: 2013-10-16
 */

public class Lamp implements PhysicalDevices {

	private String state;

	public Lamp() {

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
