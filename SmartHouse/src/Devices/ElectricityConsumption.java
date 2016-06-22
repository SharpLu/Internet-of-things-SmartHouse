package Devices;

/*
 * @author: Hamed Talebi
 * Date: 2013-11-03
 */

public class ElectricityConsumption implements PhysicalDevices {

	private String state;

	public ElectricityConsumption() {

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
