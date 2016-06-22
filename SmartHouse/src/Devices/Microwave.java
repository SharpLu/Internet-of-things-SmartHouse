package Devices;

/*
 * @author: Hamed Talebi
 * Date: 2013-11-03
 */

public class Microwave implements SimulatedDevices {

	private String state;

	public Microwave() {

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
