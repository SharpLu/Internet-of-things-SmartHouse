package Devices;

import java.util.ArrayList;

/*
 * @author: Hamed Talebi
 * Date: 2013-10-16
 * THIS CLASS KEEP TRACK OF OUR OBJECTS' TYPE, NAME ADN CODE
 * TO BE ABLE TO CREATE THEM 
 */
public class Maker {

	private ArrayList<String> devices = new ArrayList<String>();
	private ArrayList<String> names = new ArrayList<String>();

	public Maker() {

	}

	public ArrayList<String> getDevices() {
		return devices;
	}

	public void setDevices(String devices) {
		this.devices.add(devices);
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names.add(names);
	}

}
