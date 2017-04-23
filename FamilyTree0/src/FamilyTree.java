import java.util.*;
import java.io.*;
import java.awt.event.*;

import javax.swing.*;
 

public class FamilyTree implements ActionListener {
	// Fields
	private ArrayList<Person> db = new ArrayList<Person>(); // list of Person
															// objects
	private Person currentPerson; // The person currently specified by the user.
	private JFrame frame;
	private JTextArea display;
	private JTextField search;

	JScrollPane scroller;

	public FamilyTree() {

		makeFrame();
		frame.setVisible(true);
	}

	// GUI Methods
	/**
	 * Make this interface visible again. (Has no effect if it is already
	 * visible.)
	 */

	
	private void makeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 623, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton parents = new JButton("Parents");
		parents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printParents();
			}
		});
		parents.setBounds(10, 413, 146, 40);
		frame.getContentPane().add(parents);

		JButton children = new JButton("Children");
		children.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printChildren();
			}
		});
		children.setBounds(10, 362, 146, 40);
		frame.getContentPane().add(children);

	    display = new JTextArea();
		display.setVisible(true);
 		display.setToolTipText("this is the display");
		display.setBounds(10, 32, 587, 202);
		frame.getContentPane().add(display);

		/**
		 * scroller = new JScrollPane(display,
		 * JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		 * JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 * frame.getContentPane().add(scroller); frame.setVisible (true);
		 **/

		search = new JTextField();
		search.setBounds(36, 245, 526, 50);
		search.setToolTipText("enter name to be found here");
		frame.getContentPane().add(search);
		search.setColumns(10);

		JButton grandchildren = new JButton("Grandchildren");
		grandchildren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grandChildren();
				
			}
		});
		grandchildren.setBounds(10, 464, 146, 40);
		frame.getContentPane().add(grandchildren);
/**
		ButtonGroup members = new ButtonGroup();

		// Add radio buttons to the group

		members.add(parents);
		members.add(children);
		members.add(grandParents);

		Border operBorder = BorderFactory.createTitledBorder("Family");
		
// Set the border for the panel
**/

		JButton btnDisplayAll = new JButton("Display All");
		btnDisplayAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				printAllNames();			}
		});
		btnDisplayAll.setBounds(451, 362, 146, 40);
		frame.getContentPane().add(btnDisplayAll);

		JButton btnTraverse = new JButton("Traverse");
		btnTraverse.setBounds(451, 413, 146, 40);
		frame.getContentPane().add(btnTraverse);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearText();
			}
		});
		btnClear.setBounds(451, 464, 146, 40);
		frame.getContentPane().add(btnClear);

		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setDialogTitle("File Chooser");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		JButton searchButton = new JButton("Search");
		searchButton.setBounds(226, 306, 146, 50);
		frame.getContentPane().add(searchButton);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fc.showOpenDialog(btnLoad) == JFileChooser.APPROVE_OPTION) {

				}
			}
		});
		btnLoad.setBounds(246, 371, 89, 23);
		frame.getContentPane().add(btnLoad);
		
		JButton btnLoad_2 = new JButton("Load2");
		btnLoad_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDatabase();
			}
		});
		btnLoad_2.setBounds(246, 407, 89, 23);
		frame.getContentPane().add(btnLoad_2);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmLoadFile = new JMenuItem("Load File");
		mnOptions.add(mntmLoadFile);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmFaq = new JMenuItem("FAQ");
		mntmFaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Runtime.getRuntime().exec("./faq.txt");
				}
				catch(Exception h){
					JOptionPane.showMessageDialog(null,"Error");
					
				}
			}
		});
		mnHelp.add(mntmFaq);

	}

	public void loadDatabase() {
		System.out.println("Testing1");
		display.append("Reading Database \n");
		try {
			BufferedReader scan = new BufferedReader(new FileReader(
					"./src/large-database.txt"));
			String empty = "";
			String delims = " ";
			while ((scan.readLine() != empty) && scan.readLine() != null) {
				String[] data = scan.readLine().split(delims);
				Person person = new Person(data[0], data[1],Integer.parseInt(data[2]), data[3], data[4], null, null);
				db.add(person);

			}
			scan.close();

		} catch (FileNotFoundException fne) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Print out names of all the people in the database
	 */
	public void printAllNames() {
		clearText();
		display.append("All names: \n");
		for (int i = 0; i < db.size(); i++) {
			display.append(db.get(i).getName() + " \n");
		}
	}

	/**
	 * Looks for, and returns, a Person with the given name in the database.
	 * Returns null if not found
	 */
	public Person getPerson(String name) {
		Person person = null;
		for (int i = 0; i < db.size(); i++) {
			if (db.get(i).getName().equals(name)) {
				person = db.get(i);
			}
		}
		return person;
	}

	/**
	 * Prints the name, gender, and year of birth of the currently selected
	 * person. If no current person, prints a message. [Note, the toString()
	 * method of the Person class returns a string containing the name, gender,
	 * and year of birth of the person.]
	 */
	public void printPerson() {
		clearText();
		currentPerson = getPerson(search.getText());
		if (currentPerson != null) {
			display.append(currentPerson.toString() + "\n");
		} else {
			display.append("No person selected" + "\n");
		}

	}

	/**
	 * Prints the name, gender, and year of birth of the mother and the father
	 * of the currently selected person, if they are known. Prints appropriate
	 * messages if they are unknown. (Must find the Person objects in the
	 * database)
	 */
	public void printParents() {
		clearText();
		display.append("Printing parents \n");
		Person person = currentPerson;
		Person mother = null;
		Person father = null;
		for (int i = 0; i < db.size(); i++) {
			currentPerson = db.get(i);
			if (getChildren().contains(person)) {
				if (currentPerson.getGender() == "F") {
					mother = currentPerson;
				} else {
					father = currentPerson;
				}
			}
		}
		if (mother != null) {
			display.append(mother.toString() + "\n");
		} else {
			display.append("No mother found" + "\n");
		}
		if (father != null) {
			display.append(father.toString() + "\n");
		} else {
			display.append("No father found" + "\n");
		}
		currentPerson = person;

	}

	/**
	 * Prints the number of children of the given person, followed by the names,
	 * genders, and years of birth of all the children. Searches the database
	 * for Persons who have the currently specified person as one of their
	 * parents. Any such person is added to a list It then prints out the
	 * information from the list of children.
	 */
	public void printChildren() {
		clearText();
		display.append("Printing children \n");
		ArrayList<Person> children = getChildren();
		display.append("Number of children = " + children.size() + "\n");
		for (int i = 0; i < children.size(); i++) {
			display.append(children.get(i).toString() + "\n");
		}
	}

	/**
	 * Returns a list of the children of a person.
	 */
	public ArrayList<Person> getChildren() {
		ArrayList<Person> children = new ArrayList<Person>();
		for (int i = 0; i < db.size(); i++) {
			if ((db.get(i).getFatherName().equals(currentPerson.getName()) )
					|| (db.get(i).getMotherName().equals(currentPerson.getName()))) {
				children.add(db.get(i));
			}
		}
		return children;
	}

	/**
	 * Completion: Prints (to textArea) names of all grandchildren (if any) of
	 * the currently specified person
	 */
	public void grandChildren() {
		clearText();
		Person person = currentPerson;
		ArrayList<Person> children = getChildren();
		ArrayList<Person> grandchildren = new ArrayList<Person>();
		for (Person child : children) {
			currentPerson = child;
			ArrayList<Person> grandKid = getChildren();
			for (Person grandkid : grandKid) {
				grandchildren.add(grandkid);
			}
		}
		if (grandchildren.size() != 0) {
			for (Person grandchild : grandchildren) {
				display.append(grandchild.getName() + "\n");
			}
		} else {
			display.append("No grandchildren found" + "\n");
		}
		currentPerson = person;
	}

	public void clearText() {
		display.setText(null);
	}

	// Main
	public static void main(String[] arguments) {
		FamilyTree f = new FamilyTree();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}