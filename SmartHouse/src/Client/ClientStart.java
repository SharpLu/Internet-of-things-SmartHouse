package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Devices.Devices;
import Devices.DevicesAdapter;
import Devices.Maker;
import Devices.ObjectMaker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/*
 * @author: Hamed Talebi
 * Date: 2013-11-03
 */
public class ClientStart {

	/*
	 * Creating socket and streams
	 */
	Socket clientSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	// ****************************

	// Our variables
	private static Scanner input = new Scanner(System.in);
	private String line;
	private String userName;
	private String passWord;

	/****************************************************************
	 * Maker class contain an array of objects' NAME and objects' TYPE
	 */
	Maker maker = new Maker();

	/********************************************************************************
	 * Map is a HashMap of our Objects ALL OBJECTS OF OUR DEVICES ARE KEPT IN
	 * THIS MAP
	 */
	Map<String, Devices> objectMap = new HashMap<>();

	/***********************************************
	 * START OF OUR GSON OBJECTS FOR USING IN SERVER
	 */
	GsonBuilder gsonBuilder = new GsonBuilder();
	GsonBuilder newGsonBuilder = gsonBuilder.registerTypeAdapter(Devices.class,
			new DevicesAdapter());
	// Complex gson for COMPLEX AND NORMAL OBJECTS
	Gson complexGson = gsonBuilder.registerTypeAdapter(Devices.class,
			new DevicesAdapter()).create();// newGsonBuilder.create();
	Type type = new TypeToken<HashMap<String, Devices>>() {
	}.getType();
	// our jsonString
	String jsonString;

	/*************************
	 * END OF OUR GSON OBJECTS
	 */

	public ClientStart() {

		/*
		 * Making socket and Streams
		 */

		try {
			// InetAddress serverAddr =
			// InetAddress.getByName("htcomputer.myvnc.com");
			clientSocket = new Socket(InetAddress.getLocalHost(), 1234);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Don't know about host.");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Couldn't get I/O");
			System.exit(1);
		}

	}

	public void execute() {

		/*
		 * This method will used to authenticate user Default user and password
		 * is user1 - user1
		 */
		try {
			authenticateUser();
		} catch (IOException e) {
			System.out.println("Authenticated has been failed.");
			e.printStackTrace();
		}

		/*
		 * This method will get map of available objects from server and make
		 * them locally
		 */
		makeAndUpdateObjects();
		showDeviceState();
		while(true){
			System.out.println("Please name which device you want to update OR type end to EXIT or refresh to REFRESH all devices: ");
			String deviceName = input.nextLine();
			if(deviceName.equalsIgnoreCase("end")){
				out.println(deviceName);
				try {
					System.out.println(in.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}else if(deviceName.equalsIgnoreCase("refresh")){
				out.println(deviceName);
				updateObjects();
				showDeviceState();
			}else{
				System.out.println("Please enter your desired state\nfor Temprature and ElectricityConsumption just write GET\nFor Microwave mention a second between 0 to 90\nFor fan please mention OFF/FAST/SLOW\nFor the rest please just write ON/OFF: ");
				String desiredState = input.nextLine();
				System.out.println(deviceName + "-" + desiredState);
				line = deviceName + "-" + desiredState;
				out.println(line);
				updateObjects();
				showDeviceState();
			}
		}
		
	}
	

	private void showDeviceState() {
		for (int i = 0; i < maker.getDevices().size(); i++) {
			System.out.println("Device: " + maker.getNames().get(i) + "\tis: "
					+ objectMap.get(maker.getNames().get(i)).getStates());
		}
	}

	private void makeAndUpdateObjects() {

		try {
			// UPDATE OUR MAKER CLASS FROM SERVER
			maker = complexGson.fromJson(in.readLine(), Maker.class);

			// Printing our Maker map (for observation)
//			for (int i = 0; i < maker.getDevices().size(); i++) {
//				System.out.println("Device type: " + maker.getDevices().get(i));
//				System.out.println("Device name: " + maker.getNames().get(i));
//			}

			/*
			 * ********************************************************************
			 * IMPORTANT PART WE WILL MAKE OUR OBJECTS WITH THE MAKER AND SAVE
			 * IT INTO OUR HASHMAP
			 * **********************************************
			 * **********************
			 */
			ObjectMaker objectMaker = new ObjectMaker(objectMap, maker);
			objectMaker.makingObject();
			/*
			 * END OF OBJECT MAKING NOW WE HAVE ALL OUR OBJECTS AND THEIR
			 * CURRNET STATE IN OUT HASHMAP
			 */

			/*
			 * UPDATING THE STATE OF OUR OBJECT FROM SERVER
			 */
			objectMap = complexGson.fromJson(in.readLine(), type);
			//Printing the states of object for observation
//			for (int i = 0; i < maker.getDevices().size(); i++) {
//				System.out.println(maker.getNames().get(i) + " is "
//						+ objectMap.get(maker.getNames().get(i)).getStates());
//			}
		} catch (JsonSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/*
	 * Updating all object from server
	 */
	private void updateObjects() {
		try {
			objectMap = complexGson.fromJson(in.readLine(), type);
		} catch (JsonSyntaxException | IOException e) {
			System.out.println("Failed to update user HashMap objects.");
			e.printStackTrace();
		}
	}

	private void authenticateUser() throws IOException {
		/*
		 * The default user and password for testing is user1 user1 START USER
		 * AUTHENTICATION
		 */
		System.out.print("Please enter username: ");
		userName = input.nextLine();
		System.out.print("Please enter password: ");
		passWord = input.nextLine();
		out.println(userName + "-" + passWord);
		line = in.readLine();
		while (line.contains("Try")) {
			System.out.println(line);
			System.out.print("Please enter username: ");
			userName = input.nextLine();
			System.out.print("Please enter password: ");
			passWord = input.nextLine();
			out.println(userName + "-" + passWord);
			line = in.readLine();
		}
		System.out.println("User authentication is: " + line);

		/*
		 * END OF USER AUTHENTICATION
		 */
	}

}
