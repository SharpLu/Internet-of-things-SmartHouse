package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Devices.Devices;
import Devices.DevicesAdapter;
import Devices.Maker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/*
 * @author: FENG LU  QIUQI HU
 * Date: 2013-11-16
 */
public class Connector {

	static Socket clientSocket = null;
	static PrintWriter out = null;
	static BufferedReader in = null;
	// here we we user arraylist to get all devices names which provided to jsp
	// to dynamic to generate buttons
	static ArrayList<String> AllNameList = new ArrayList<String>();
	// here we need get all devices current status and compare different status
	// have different windo
	static ArrayList<String> getCurrentStatus = new ArrayList<String>();
	// here we need get all devices type because we need use devices type to
	// separate jsp file.
	// for example heater1 and heater2 should be separate to heater page
	static ArrayList<String> DevicesTypelist = new ArrayList<String>();

	String line;
	String userName;
	String passWord;

	static Maker maker = new Maker();

	Map<String, Devices> objectMap = new HashMap<>();

	GsonBuilder gsonBuilder = new GsonBuilder();
	GsonBuilder newGsonBuilder = gsonBuilder.registerTypeAdapter(Devices.class,
			new DevicesAdapter());
	Gson complexGson = gsonBuilder.registerTypeAdapter(Devices.class,
			new DevicesAdapter()).create();
	static Type type = new TypeToken<HashMap<String, Devices>>() {
	}.getType();
	String jsonString;

	public static ArrayList<String> getDevicesTypelist() {
		return DevicesTypelist;
	}

	public static void setDevicesTypelist(ArrayList<String> DevicesTypelist) {
		Connector.DevicesTypelist = DevicesTypelist;
	}

	public static ArrayList<String> getAllNameList() {
		return AllNameList;
	}

	public static void setAllNameList(ArrayList<String> AllNameList) {
		Connector.AllNameList = AllNameList;
	}

	public static ArrayList<String> getgetCurrentStatus() {
		return getCurrentStatus;
	}

	public static void setgetCurrentStatus(ArrayList<String> getCurrentStatus) {
		Connector.getCurrentStatus = getCurrentStatus;
	}

	// here we use authenticateuser method for receiver parameters from login
	// servlet
	// after get the values then though socket send to server
	public String authenticateUser(String username, String password)
	throws IOException {
		 InetAddress serverAddr =InetAddress.getByName("htcomputer.myvnc.com");
		clientSocket = new Socket("127.0.0.1", 1234);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		System.out.print("Please enter username: ");
		userName = username;
		System.out.print("Please enter password: ");
		passWord = password;
		out.println(userName + "-" + passWord);
		line = in.readLine();
		while (line.contains("Try")) {
			System.out.println(line);
			System.out.print("Please enter username: ");
			userName = username;
			System.out.print("Please enter password: ");
			passWord = password;
			out.println(userName + "-" + passWord);
			line = in.readLine();
			return line;
		}
		System.out.println("User authentication is: " + line);
		makeAndUpdateObjects();
		showDeviceState();
		return line;
	}

	// about the execute method,which is get the devicename and current status
	// send to server
	// this method call by action servlet
	public void execute(String devicename, String status)
			throws UnknownHostException, IOException {
		boolean comprare = true;
		while (comprare) {
			System.out
					.println("Please name which device you want to update OR type end to EXIT or refresh to REFRESH all devices: ");
			if (devicename.equalsIgnoreCase("end")) {
				out.println(devicename);
				try {
					System.out.println(in.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			} else if (devicename.equalsIgnoreCase("refresh")) {
				out.println(devicename);
				updateObjects();
				showDeviceState();
			} else {
				System.out.println("Please enter your desired state\nfor Temprature and ElectricityConsumption just write GET\nFor Microwave mention a second between 0 to 90\nFor fan please mention OFF/FAST/SLOW\nFor the rest please just write ON/OFF: ");

				System.out.println(devicename + "-" + status);
				line = devicename + "-" + status;
				System.out.println("here is the line  " + line);
				out.println(line);
				updateObjects();
				showDeviceState();
			}
			comprare = false;
		}

	}
	// this method is show all current devices state because if we dont know the current status we dont know the jsp how to display picture to  users
	public void showDeviceState() {
		String showAll = null;
		for (int i = 0; i < maker.getDevices().size(); i++) {
			System.out.println("Device: " + maker.getNames().get(i) + "\tis: "
					+ objectMap.get(maker.getNames().get(i)).getStates());
			showAll = maker.getNames().get(i) + "\t: "
					+ objectMap.get(maker.getNames().get(i)).getStates();
			getCurrentStatus.add(showAll);
			// this method us add the all current current status to ArrayList and jsp will call this ArrayList
		}
	}

	public void makeAndUpdateObjects() {
		try {
			String s = in.readLine();
			maker = complexGson.fromJson(s, Maker.class);
			objectMap = complexGson.fromJson(in.readLine(), type);

		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
	}

	public void updateObjects() {
		try {
			objectMap = complexGson.fromJson(in.readLine(), type);
		} catch (JsonSyntaxException | IOException e) {
			System.out.println("Failed to update user HashMap objects.");
			e.printStackTrace();
		}

	}
// this method we use to dynamic automatically genarate buttons after we get the devicename then we can know how many buttons we need to genarate 
	public void devicename() {
		String devicename = maker.getNames().get(0);
		for (int i = 0; i < maker.getNames().size(); i++) {
			devicename = maker.getNames().get(i);
			AllNameList.add(devicename);
		}
	}
// this method we use to separate our divices type like heater 1 and heater2 both of should be jump to heater page 
	public void getAllDevicesType() {
		for (int i = 0; i < maker.getDevices().size(); i++) {
			DevicesTypelist.add(maker.getDevices().get(i));
		}

	}

}
