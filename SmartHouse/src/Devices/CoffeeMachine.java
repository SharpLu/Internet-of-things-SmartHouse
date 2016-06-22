package Devices;

/*
 * @author: Hamed Talebi
 * Date: 2013-10-16
 */

public class CoffeeMachine implements SimulatedDevices {

	private String state;

	public CoffeeMachine() {
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
