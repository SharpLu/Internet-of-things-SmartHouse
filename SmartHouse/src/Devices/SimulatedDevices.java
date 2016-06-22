package Devices;

/*
 * @author: Hamed Talebi
 * Date: 2013-10-16
 */

public abstract interface SimulatedDevices extends Devices {

	public abstract String getStates();

	public abstract void setStates(String state);

}
