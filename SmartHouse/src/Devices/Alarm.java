package Devices;

/*
 * @author: Hamed Talebi
 * Date: 2013-11-03
 */

public class Alarm implements PhysicalSimulated {

	private String state;

	public Alarm() {

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
