package telran.employees.net;

import telran.employees.CompanyApplItems;
import telran.net.*;
import telran.view.*;

import java.util.*;

public class CompanyClientAppl {
	private static final String DEFAULT_HOST = "localhost";
	private static final int DEFAULT_PORT = 5000;

	public static void main(String[] args) {
		
		String hostIP = args.length > 0 ? args[0] : DEFAULT_HOST;
		int port = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_PORT;
		TcpClient tcpClient = new TcpClient(hostIP, port); 
		CompanyProxy companyProxy = new CompanyProxy(tcpClient);
		List<Item> items = CompanyApplItems.getCompanyItems(companyProxy, 
				new HashSet<>(List.of(new String[] {"QA", "Audit", "Development"})));
		items.add(Item.of("Exit & connection close", io -> tcpClient.close(), true));
		Menu menu = new Menu("TCP Client Application", items.toArray(Item[]::new));
		menu.perform(new SystemInputOutput());
	}

}
