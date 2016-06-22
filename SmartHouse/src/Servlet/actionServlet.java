package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author: FENG LU  QIUQI HU
 * Date: 2013-11-16
 */
/**
 * Servlet implementation class actionServlet
 */
@WebServlet("/actionServlet")
public class actionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Connector connect;
	Connector con = new Connector();

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public actionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// connector c = new connector();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String getDeviceName = (String) request.getSession().getAttribute("name");
		// get the devices name from jsp attribute 
		String turnback = request.getParameter("back");
		// which is use to back menu page 
		String GET = request.getParameter("GET");
		// the get use to compare the temprature 
		String microware_degree = request.getParameter("Microwave");
		// the micrware _degree which is use to get value  0-90 from jsp file
		String fanState = request.getParameter("Fan_Item");
		// the fanstate use to get fan current status like fast slow off
		String ON = request.getParameter("ON");
		// get the devices status if is on send on to server
		String OFF = request.getParameter("OFF");
		// get the devices status if is off send off to server
		String end = request.getParameter("end");
		// get the end page value
		String refresh = request.getParameter("refresh");
		String whichDevices = request.getParameter(getDeviceName);
		//get the devices name
		String getDevicesType = (String) request.getSession().getAttribute("DevicesType");
		// get the devices type
		con.devicename();
		ArrayList<String> AllNameList = Connector.getAllNameList();
		ArrayList<String> currentStatus=con.getgetCurrentStatus();
		boolean flag = true;
		while (flag) {
			if (fanState != null && turnback == null) {
				con.execute(getDeviceName, fanState);
				while (flag) {
					if (turnback != null && fanState == null) {
						AllNameList.clear();
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("/MainControlbox.jsp");
						dispatcher.forward(request, response);
						con.execute("refresh", "null");
						break;
					}
					flag = false;
				}
			}

			else if (end != null) {
				currentStatus.clear();
				con.execute("end", "");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
				AllNameList.clear();
				break;
			} else if (refresh != null) {
				con.execute("refresh", "null");
			} else if (microware_degree != null && turnback == null) {
				System.out.println("2222222222222222222222");
				con.execute(getDeviceName, microware_degree);
				while (flag) {
					if (turnback != null && microware_degree == null) {
						AllNameList.clear();
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("/MainControlbox.jsp");
						dispatcher.forward(request, response);
						con.execute("refresh", "null");
						break;
					}
					flag = false;
				}

			} else if (GET != null && turnback == null) {
				System.out.println("3333333333333333333333");
				con.execute(getDeviceName, GET);
				while (flag) {
					if (turnback != null && GET == null) {
						AllNameList.clear();
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("/MainControlbox.jsp");
						dispatcher.forward(request, response);
						con.execute("refresh", "null");
						break;
					}
					flag = false;
				}

			} else if (getDeviceName.equalsIgnoreCase(whichDevices)) {
				if (ON != null) {
					con.execute(getDeviceName, ON);
				} else if (OFF != null) {
					con.execute(getDeviceName, OFF);
				} else if (turnback != null) {
					AllNameList.clear();
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/MainControlbox.jsp");
					con.execute("refresh", "null");
					dispatcher.forward(request, response);
					break;
				}
			} else if (turnback != null) {
				AllNameList.clear();
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/MainControlbox.jsp");
				con.execute("refresh", "null");
				dispatcher.forward(request, response);
				break;
			}
			flag = false;
			for (int i = 0; i < AllNameList.size(); i++) {
				if (getDeviceName.equalsIgnoreCase(AllNameList.get(i))) {
					try {
						request.getRequestDispatcher(
								getDevicesType + "?buttonName="
										+ AllNameList.get(i)).forward(request,
								response);
					} catch (Exception e) {
					}
					AllNameList.clear();
				}
			}

		}

	}

}
