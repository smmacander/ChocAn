package control;

//import java.util.ArrayList;

import objects.Member;
import objects.Provider;
import control.ChocAnControl;
import interfaces.UserInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//BUILT BY: DEREK HASTINGS

public class UserManager {

	Scanner scan;
	File writeFile;

	public UserManager() {						//Constructor
		scan = new Scanner(System.in);			//Creates scanner to use in conjunction with prompt
	}

	/**
	   * This is the check function to make sure the user wants to delete a user.
	   * @param
	   * @return boolean
	   */
	public boolean areYouSure() {
		String check;																	//Return boolean to see if user is sure
		UserInterface.prompt("Are you sure that you want to delete? (yes/no)");			//Access prompt
		check = scan.next();
		if(check.equals("yes")) {
			return true;
		}												//Check to make sure
		else {
			return false;
		}
		//Prompt user to make sure they do want to delete a user
	}

	/**
	   * This is the function initiated by the user to add a new member to the system list.
	   * @param
	   * @return boolean
	   */
	public boolean addMember() {
		boolean suc = true;											//Check variable to make sure the member exists and is created
		long memID;													//Scan variable to take in member ID to make sure it doesnt exist
		int stat;													//Scan int variable to use as glorified scan boolean
		UserInterface.prompt("Enter member ID");					//Access prompt
		memID = Long.parseLong(scan.nextLine());
		String member;
		Member memToAdd;
		for(int i = 0; i < ChocAnControl.members.size(); i++) {				//Search member list (arrayList in control)
			if(ChocAnControl.members.get(i).id == memID)  {					//To see if member already exists
				suc = false;
			}
		}
		if(suc) {
			memToAdd = new Member();
			memToAdd.id = memID;
			
			UserInterface.prompt("Enter member name");		//Access prompt
			member = scan.nextLine();
			memToAdd.name = member;
			
			UserInterface.prompt("Enter member address");						//Access prompt
			String address = scan.nextLine();
			memToAdd.address = address;
			
			UserInterface.prompt("Enter member city");							//Access prompt
			String city = scan.nextLine();
			memToAdd.city = city;
			
			UserInterface.prompt("Enter member state");							//Access prompt
			String state = scan.nextLine();
			memToAdd.state = state;
		
			UserInterface.prompt("Enter member zip code");                       //Access prompt
            String zip = scan.nextLine();
            memToAdd.zipCode = zip;
			
			String status;
			
			UserInterface.prompt("Enter account status: 1-active, 0-suspended");	//Access prompt
			stat = Integer.parseInt(scan.nextLine());
			if(stat == 0) { memToAdd.accountStatus = false;
			                status = "false";}
			else { memToAdd.accountStatus = true;
			    status = "true";}

			ChocAnControl.members.add(memToAdd);						//Add member to the arrayList in control

		if (new File("release").exists()) {
	      writeFile = new File("release"+File.separator+"data"+File.separator+"members.txt");
	    } else {
	      writeFile = new File("data"+File.separator+"members.txt");
	    }

	    try {
	      FileWriter out = new FileWriter(writeFile, true); // writing to a separate text file
	      out.write(Long.toString(memID));
	      out.write(System.lineSeparator());
	      out.write(member);
	      out.write(System.lineSeparator());
	      out.write(address);
	      out.write(System.lineSeparator());
	      out.write(city);
	      out.write(System.lineSeparator());
	      out.write(state);
	      out.write(System.lineSeparator());
	      out.write(zip);
	      out.write(System.lineSeparator());
	      out.write(status);
	      out.write(System.lineSeparator());
	      out.close();
	      return true;
	    } catch (IOException e) {
	      UserInterface.prompt("Error writing file for members");
	      return false;
	    }
		}
		else {
			UserInterface.prompt("There is already a member with that ID");
			this.addMember();
			return false;
		}
	}
	
	/**
	   * This is the function initiated by the user to edit an existing member in the system list.
	   * @param
	   * @return boolean
	   */
	public boolean editMember() {
		boolean out = false;						//Check variable to make sure the member exists and is edited
		int memNum = 0;								//Starting the member in the array index
		long memID;									//Scan variable to take in member ID to look for/edit
		int test;									//Glorified boolean to use with scan
		String query = "none";						//String check for editing while loop
		UserInterface.prompt("Enter member ID");					//Access prompt
		memID = Long.parseLong(scan.nextLine());
		for(int i = 0; i < ChocAnControl.members.size(); i++) {
			if(ChocAnControl.members.get(i).id == memID)  {					//Check arrayList in control to find the referenced member
				out = true;
				memNum = i;
			}
		}
		if(out) {
			while(!(query.equals("stop"))) {
				if(out) {
					UserInterface.prompt("What do you want to edit? (ID, name, add, city, zip, st, status) (stop) to end edit");					//Access prompt
					query = scan.nextLine();
					if(query.equals("ID")) {
						UserInterface.prompt("Enter new member ID");					//Access prompt
						ChocAnControl.members.get(memNum).id = Long.parseLong(scan.nextLine());			//Allow user to edit member ID
						memID = ChocAnControl.members.get(memNum).id;
					}
					else if(query.equals("name")) {
						UserInterface.prompt("Enter new member name");					//Access prompt
						ChocAnControl.members.get(memNum).name = scan.nextLine();		//Allow user to edit member name
					}
					else if(query.equals("add")) {
						UserInterface.prompt("Enter new member address");				//Access prompt
						ChocAnControl.members.get(memNum).address = scan.nextLine();	//Allow user to edit member state
					}
					else if(query.equals("city")) {
						UserInterface.prompt("Enter new member city");					//Access prompt
						ChocAnControl.members.get(memNum).city = scan.nextLine();		//Allow user to edit member city
					}
					else if(query.equals("zip")) {
						UserInterface.prompt("Enter new member zipCode");				//Access prompt
						ChocAnControl.members.get(memNum).zipCode = scan.nextLine();	//Allow user to edit member  zip code
					}
					else if(query.equals("st")) {
						UserInterface.prompt("Enter new member state");					//Access prompt
						ChocAnControl.members.get(memNum).state = scan.nextLine();		//Allow user to edit member state
					}
					else if(query.equals("status")) {
						UserInterface.prompt("Enter new member status (1 for active, 0 for suspended");		//Access prompt
						test = Integer.parseInt(scan.nextLine());															//Allow user to edit member account status
						if(test == 0) { ChocAnControl.members.get(memNum).accountStatus = true; }
						if(test == 1) { ChocAnControl.members.get(memNum).accountStatus = false; }

					}
				}
			}
			return true;
		}
		else {
			UserInterface.prompt("There is no member with that ID");
			this.editMember();
			return false;
		}
	}
	
	/**
	   * This is the function initiated by the user to edit an existing member in the system list.
	   * 
	   * @param id, string converted to long in order to search for appropriate user
	   * @param edit, string used to specify which member field to edit
	   * @param newString, the new value placed into the choosen slot to edit
	   * @return boolean
	   */
	public boolean editMember(String id, String edit, String newString) {
		boolean out = false;						//Check variable to make sure the member exists and is edited
		int memNum = 0;								//Starting the member in the array index
		long memID = Long.parseLong(id);								//Scan variable to take in member ID to look for/edit
		String test;									//Glorified boolean to use with scan
		for(int i = 0; i < ChocAnControl.members.size(); i++) {
			if(ChocAnControl.members.get(i).id == memID)  {					//Check arrayList in control to find the referenced member
				out = true;
				memNum = i;
			}
		}
		if(out) {
				if(edit.equals("ID")) {
					ChocAnControl.members.get(memNum).id = Long.parseLong(newString);			//Allow user to edit member ID
					memID = ChocAnControl.members.get(memNum).id;
				}
				else if(edit.equals("name")) {
					ChocAnControl.members.get(memNum).name = newString;		//Allow user to edit member name
				}
				else if(edit.equals("add")) {
					ChocAnControl.members.get(memNum).address = newString;	//Allow user to edit member state
				}
				else if(edit.equals("city")) {
					ChocAnControl.members.get(memNum).city = newString;		//Allow user to edit member city
				}
				else if(edit.equals("zip")) {
					ChocAnControl.members.get(memNum).zipCode = newString;	//Allow user to edit member  zip code
				}
				else if(edit.equals("st")) {
					ChocAnControl.members.get(memNum).state = newString;		//Allow user to edit member state
				}
				else if(edit.equals("status")) {
					test = newString;															//Allow user to edit member account status
					if(test.equals("true")) { ChocAnControl.members.get(memNum).accountStatus = true; }
					if(test.equals("false")) { ChocAnControl.members.get(memNum).accountStatus = false; }
				}

			return true;
		}
		else {
			UserInterface.prompt("There is no member with that ID");
			return false;
		}
	}

	/**
	   * This is the function initiated by the user to remove a member in the system list.
	   * @param
	   * @return boolean
	   */
	public boolean deleteMember() {
		boolean out = false;										//Boolean to see if member exists and if deleted
		int memNum = 0;												//Int variable to represent the index in the member arrayList
		long memID;													//Scan variable to take in member ID to look for/edit
		UserInterface.prompt("Enter member ID");					//Access prompt
		memID = Long.parseLong(scan.nextLine());
		for(int i = 0; i < ChocAnControl.members.size(); i++) {
			if(ChocAnControl.members.get(i).id == memID)  {				//Find the input member ID to set index for later use
				out = true;
				memNum = i;
			}
		}
		
		File inputFile;
		File tempFile;

		if (new File("release").exists()) {
		      inputFile = new File("release"+File.separator+"data"+File.separator+"members.txt");
				tempFile = new File("release"+File.separator+"data"+File.separator+"membersTemp.txt");
		    } else {
		      inputFile = new File("data"+File.separator+"members.txt");
				tempFile = new File("data"+File.separator+"membersTemp.txt");
		    }
		
		try {

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String lineToRemove = Long.toString(memID);
		String currentLine;
		int count = 0;
		boolean delete = false;
		
		while((currentLine = reader.readLine()) != null) {
			if(delete){
				if(count < 7) continue;
				else delete = false;
			}
			else {
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToRemove)) {
		    		delete = true;
		    		continue;
		    	}
			}
		    writer.write(currentLine + System.lineSeparator());
		}
		writer.close(); 
		reader.close(); 
		inputFile.delete();
		tempFile.renameTo(inputFile);
		} catch(IOException i) {}
		
		if(out) {													//If found then remove that indexed member from the arrayList
			if(areYouSure()) {										//Prompt user to see if they are sure
				ChocAnControl.members.remove(memNum);
				return true;
			}
			else {																			//otherwise report error
				UserInterface.prompt("The member with ID" + memID + "was not deleted");
				return false;
			}
		}
		else {																				//otherwise report error
			UserInterface.prompt("The member with ID" + memID + "does not exist");
			this.deleteMember();
			return false;
		}
	}

	/**
	   * This is the function initiated by the user to add a new provider to the system list.
	   * @param
	   * @return boolean
	   */
	public boolean addProvider() {
		boolean suc = true;											//Check variable to make sure the provider exists and is created
		long provID;												//Scan variable to take in provider ID to create and check existence
		UserInterface.prompt("Enter provider ID");					//Access prompt
		provID = Long.parseLong(scan.nextLine());
		Provider provToAdd;
		for(int i = 0; i < ChocAnControl.providers.size(); i++) {		//Check to see if provider by that ID already exists
			if(ChocAnControl.providers.get(i).id == provID)  {
				suc = false;
			}
		}
		if(suc) {													//If its not found then create it
			provToAdd = new Provider();
			provToAdd.id = provID;
			UserInterface.prompt("Enter provider name");			//Access prompt
			String provider = scan.nextLine();
			provToAdd.name = provider;
			
			UserInterface.prompt("Enter provider address");			//Access prompt
			String address = scan.nextLine();
			provToAdd.address = address;
			
			UserInterface.prompt("Enter provider city");			//Access prompt
			String city = scan.nextLine();
			provToAdd.city = city;
			
			UserInterface.prompt("Enter provider state");			//Access prompt
			String state = scan.nextLine();
			provToAdd.state = state;
			
			UserInterface.prompt("Enter provider zip code");     //Access prompt
            String zip = scan.nextLine();
            provToAdd.zipCode = zip;
            
			UserInterface.prompt("Enter the number of consultations");		//Access prompt
			int consul = Integer.parseInt(scan.nextLine());
			provToAdd.totalConsultations = consul;
			
			UserInterface.prompt("Enter the total cost of fees");	//Access prompt
			int fees = Integer.parseInt(scan.nextLine());
			provToAdd.totalFees = fees;

			ChocAnControl.providers.add(provToAdd);
			if (new File("release").exists()) {
	      writeFile = new File("release"+File.separator+ "data"+File.separator+"providers.txt");
	    } else {
	      writeFile = new File("data"+File.separator+"providers.txt");
	    }

	    try {
	      FileWriter out = new FileWriter(writeFile, true); // writing to a separate text file
	      out.write(Long.toString(provID));
	      out.write(System.lineSeparator());
	      out.write(provider);
	      out.write(System.lineSeparator());
	      out.write(address);
	      out.write(System.lineSeparator());
	      out.write(city);
	      out.write(System.lineSeparator());
	      out.write(state);
	      out.write(System.lineSeparator());
	      out.write(zip);
	      out.write(System.lineSeparator());
	      out.write(Integer.toString(consul));
	      out.write(System.lineSeparator());
	      out.write(Integer.toString(fees));
	      out.write(System.lineSeparator());
	      out.close();
	      return true;
	    } catch (IOException e) {
	      UserInterface.prompt("Error writing file for providers");
	      return false;
	    }
		}
		else {
			UserInterface.prompt("There is already a provider with that ID");
			this.addProvider();
			return false;
		}
	}

	/**
	   * This is the function initiated by the user to edit an existing provider in the system list.
	   * @param
	   * @return boolean
	   */
	public boolean editProvider() {
		boolean out = false;										//Check variable to make sure the provider exists and is edited
		int provNum = 0;											//Int to use as index to keep track of provider to edit
		long provID;												//Scan variable to take in provider ID to edit and check existence
		String query = "none";										//String check for editing while loop
		UserInterface.prompt("Enter provider ID to edit");					//Access prompt
		provID = Long.parseLong(scan.nextLine());
		for(int i = 0; i < ChocAnControl.providers.size(); i++) {	//Find the input provider ID to set index for later use
			if(ChocAnControl.providers.get(i).id == provID)  {
				out = true;
				provNum = i;
			}
		}
		if(out) {
			while(!(query.equals("stop"))) {											//Allow user to keep editing until they enter "stop"
				UserInterface.prompt("What do you want to edit? "
						+ "(ID, name, add, city, zip, st, fees, consul) (stop) to end edit");		//Access prompt
				query = scan.nextLine();
				if(query.equals("ID")) {											//Allow user to edit provider ID
					UserInterface.prompt("Enter new provider ID");					//Access prompt
					ChocAnControl.providers.get(provNum).id = Long.parseLong(scan.nextLine());
					provID = ChocAnControl.providers.get(provNum).id;
				}
				else if(query.equals("name")) {
					UserInterface.prompt("Enter new provider name");					//Access prompt
					ChocAnControl.providers.get(provNum).name = scan.nextLine();	//Allow user to edit provider name
				}
				else if(query.equals("add")) {
					UserInterface.prompt("Enter new provider address");					//Access prompt
					ChocAnControl.providers.get(provNum).address = scan.nextLine();		//Allow user to edit provider address
				}
				else if(query.equals("city")) {
					UserInterface.prompt("Enter new provider city");					//Access prompt
					ChocAnControl.providers.get(provNum).city = scan.nextLine();		//Allow user to edit provider city
				}
				else if(query.equals("zip")) {
					UserInterface.prompt("Enter new provider zipCode");					//Access prompt
					ChocAnControl.providers.get(provNum).zipCode = scan.nextLine();		//Allow user to edit provider zip code
				}
				else if(query.equals("st")) {
					UserInterface.prompt("Enter new provider state");						//Access prompt
					ChocAnControl.providers.get(provNum).state = scan.nextLine();		//Allow user to edit provider state
				}
					else if(query.equals("consul")) {
					UserInterface.prompt("Enter new number of consultationss");					//Access prompt
					ChocAnControl.providers.get(provNum).totalConsultations = Integer.parseInt(scan.nextLine());	//Allow user to edit provider number of visits
				}
				else if(query.equals("fees")) {
					UserInterface.prompt("Enter new provider fee total");				//Access prompt
					ChocAnControl.providers.get(provNum).totalFees = Integer.parseInt(scan.nextLine());	//Allow user to edit provider total fees
				}
			}
			return true;
		}
		else {
			UserInterface.prompt("There is no provider with that ID");
			this.editProvider();
			return false;
		}
	}

	/**
	   * This is the function initiated by the user to remove a provider in the system list.
	   * @param
	   * @return boolean
	   */
	public boolean deleteProvider() {
		boolean out = false;										//Check variable to make sure the provider exists and is edited
		int provNum = 0;											//Int to use as index to keep track of provider to edit
		long provID;												//Scan variable to take in provider ID to edit and check existence
		UserInterface.prompt("Enter provider ID");					//Access prompt
		provID = Long.parseLong(scan.nextLine());
		for(int i = 0; i < ChocAnControl.providers.size(); i++) {
			if(ChocAnControl.providers.get(i).id == provID)  {		//Find the input provider ID to set index for later use
				out = true;
				provNum = i;
			}
		}
		
		
		File inputFile;
		File tempFile;

		if (new File("release").exists()) {
		      inputFile = new File("release"+File.separator+"data"+File.separator+"providers.txt");
				tempFile = new File("release"+File.separator+"data"+File.separator+"providersTemp.txt");
		    } else {
		      inputFile = new File("data"+File.separator+"providers.txt");
				tempFile = new File("data"+File.separator+"providersTemp.txt");
		    }
		
		try {

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String lineToRemove = Long.toString(provID);
		String currentLine;
		int count = 0;
		boolean delete = false;
		
		while((currentLine = reader.readLine()) != null) {
			if(delete){
				if(count < 8) continue;
				else delete = false;
			}
			else {
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToRemove)) {
		    		delete = true;
		    		continue;
		    	}
			}
		    writer.write(currentLine + System.lineSeparator());
		}
		writer.close(); 
		reader.close(); 
		inputFile.delete();
		tempFile.renameTo(inputFile);
		} catch(IOException i) {}
		
		if(out) {												//If a provider exists
			if(areYouSure()) {									//Then ask if the user is sure
				ChocAnControl.providers.remove(provNum);		//Then remove
				return true;
			}
			else {
				UserInterface.prompt("The provider with ID: " + provID + " :was not deleted");		//otherwise report error
				return false;
			}
		}
		else {
			UserInterface.prompt("The provider with ID" + provID + "does not exist");				//otherwise report error
			this.deleteProvider();
			return false;
		}
	}

}
