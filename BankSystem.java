import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class BankSystem extends JFrame implements ActionListener, ItemListener {

	
	private JDesktopPane desktop = new JDesktopPane ();

	//For Program's MenuBar.
	private JMenuBar bar;

	//All the Main Menu of the Program.
	private JMenu mnuFile, mnuEdit, mnuView, mnuOpt, mnuWin, mnuHelp;

	private JMenuItem addNew, printRec, end;				//File Menu Options.

	private	JMenuItem  deposit, withdraw, delRec, search, searchName;	//Edit Menu Options.

	private	JMenuItem oneByOne, allCustomer;				//View Menu Options.
	
	private JMenuItem close, closeAll;					//Window Menu Options.
	

	//PopupMenu of Program.
	private JPopupMenu popMenu = new JPopupMenu ();

	//MenuItems for PopupMenu of the Program.
	private JMenuItem open, report, dep, with, del, find, all;

	//For Program's ToolBar.
	private	JToolBar toolBar;

	//For ToolBar's Button.
	private	JButton btnNew, btnDep, btnWith, btnRec, btnDel, btnSrch;//, btnHelp, btnKey;

	//Main Form StatusBar where Program's Name & Welcome Message Display.
	private JPanel statusBar = new JPanel ();

	//Labels for Displaying Program's Name & saying Welcome to Current User on StatusBar.
	private JLabel welcome;
	private JLabel author;

	
	private java.util.Date currDate = new java.util.Date ();
	private SimpleDateFormat sdf = new SimpleDateFormat ("dd MMMM yyyy", Locale.getDefault());
	private String d = sdf.format (currDate);

	
	private int rows = 0;
	private	int total = 0;

	//String Type Array use to Load Records From File.
	private String records[][] = new String [500][6];

	//Variable for Reading the BankSystem Records File.
	private FileInputStream fis;
	private DataInputStream dis;

	//Constructor of The Bank Program to Iniatilize all Variables of Program.

	public BankSystem () {

		

		//Creating the MenuBar.
		bar = new JMenuBar ();

		//Setting the Main Window of Program.
		
		setSize (700, 650);
		setJMenuBar (bar);

		//Closing Code of Main Window.
		addWindowListener (new WindowAdapter () {
			public void windowClosing (WindowEvent we) {
				quitApp ();
			}
		}
		);

		//Setting the Location of Application on Screen.
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth()) / 2,
			(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);

		//Creating the MenuBar Items.
		mnuFile = new JMenu ("File");
		mnuEdit = new JMenu ("Edit");
		mnuView = new JMenu ("View");
		mnuOpt = new JMenu ("Options");
		mnuWin = new JMenu ("Window");
		mnuHelp = new JMenu ("Help");
		

		//Creating the MenuItems of Program.
		//MenuItems for FileMenu.
		addNew = new JMenuItem ("Open New Account");
		
		
		addNew.addActionListener (this);
		printRec = new JMenuItem ("Print Customer Balance");
		
		
		printRec.addActionListener (this);
		end = new JMenuItem ("Quit BankSystem ?");
		
		
		end.addActionListener (this);

		//MenuItems for EditMenu.
		deposit = new JMenuItem ("Deposit Money");
		
		
		deposit.addActionListener (this);
		withdraw = new JMenuItem ("Withdraw Money");
		
		
		withdraw.addActionListener (this);
		delRec = new JMenuItem ("Delete Customer");
		
		
		delRec.addActionListener (this);
		search = new JMenuItem ("Search By No.");
		
			
		search.addActionListener (this);
		searchName = new JMenuItem ("Search By Name");
		
		
		searchName.addActionListener (this);

		//MenuItems for View Menu.
		oneByOne = new JMenuItem ("View One By One");
		
			
		oneByOne.addActionListener (this);
		allCustomer = new JMenuItem ("View All Customer");
		
		
		allCustomer.addActionListener (this);

		//MenuItems for WindowMenu.
		close = new JMenuItem ("Close Active Window");
		
		close.addActionListener (this);
		closeAll = new JMenuItem ("Close All Windows...");
		
		closeAll.addActionListener (this);

		
	
		//File Menu Items.
		mnuFile.add (addNew);
		mnuFile.addSeparator ();
		mnuFile.add (printRec);
		mnuFile.addSeparator ();
		mnuFile.add (end);

		//Edit Menu Items.
		mnuEdit.add (deposit);
		mnuEdit.add (withdraw);
		mnuEdit.addSeparator ();
		mnuEdit.add (delRec);
		mnuEdit.addSeparator ();
		mnuEdit.add (search);
		mnuEdit.add (searchName);

		//View Menu Items.
		mnuView.add (oneByOne);
		mnuView.addSeparator ();
		mnuView.add (allCustomer);

		//Window Menu Items.
		mnuWin.add (close);
		mnuWin.add (closeAll);

		//Adding Menues to Bar.
		bar.add (mnuFile);
		bar.add (mnuEdit);
		bar.add (mnuView);
		bar.add (mnuOpt);
		bar.add (mnuWin);
		bar.add (mnuHelp);

		//MenuItems for PopupMenu.
		open = new JMenuItem ("Open New Account");
		open.addActionListener (this);
		report = new JMenuItem ("Print BankSystem Report");
		report.addActionListener (this);
		dep = new JMenuItem ("Deposit Money");
		dep.addActionListener (this);
		with = new JMenuItem ("Withdraw Money");
		with.addActionListener (this);
		del = new JMenuItem ("Delete Customer");
		del.addActionListener (this);
		find = new JMenuItem ("Search Customer");
		find.addActionListener (this);
		all = new JMenuItem ("View All Customer");
		all.addActionListener (this);

		//Adding Menues to PopupMenu.
		popMenu.add (open);
		popMenu.add (report);
		popMenu.add (dep);
		popMenu.add (with);
		popMenu.add (del);
		popMenu.add (find);
		popMenu.add (all);

		//Following Procedure display the PopupMenu of Program.
		addMouseListener (new MouseAdapter () {
			public void mousePressed (MouseEvent me) { checkMouseTrigger (me); }
			public void mouseReleased (MouseEvent me) { checkMouseTrigger (me); }
			private void checkMouseTrigger (MouseEvent me) {
				if (me.isPopupTrigger ())
					popMenu.show (me.getComponent (), me.getX (), me.getY ());
			}
		}
		);
		

		//Creating the ToolBar's Buttons of Program.
		btnNew = new JButton ("Create");
		
		btnNew.addActionListener (this);
		btnDep = new JButton ("Deposit");
		
		btnDep.addActionListener (this);
		btnWith = new JButton ("Withdraw");
		
		btnWith.addActionListener (this);
		btnRec = new JButton ("Print Bal.");
		
		btnRec.addActionListener (this);
		btnDel = new JButton ("Delete");
		
		btnDel.addActionListener (this);
		btnSrch = new JButton ("Search");
		
		btnSrch.addActionListener (this);


		//Creating the ToolBar of Program.
		toolBar = new JToolBar ();
		toolBar.add (btnNew);
		toolBar.addSeparator ();
		toolBar.add (btnDep);
		toolBar.add (btnWith);
		toolBar.addSeparator ();
		toolBar.add (btnRec);
		toolBar.addSeparator ();
		toolBar.add (btnDel);
		toolBar.addSeparator ();
		toolBar.add (btnSrch);
		toolBar.addSeparator ();
		
		//Creating the StatusBar of Program.
		author = new JLabel (" " + "BankSystem Core Java Project", Label.LEFT);
		author.setForeground (Color.black);
		author.setToolTipText ("Program's Title");
		welcome = new JLabel ("Welcome Today is " + d + " ", JLabel.RIGHT);
		welcome.setForeground (Color.black);
		welcome.setToolTipText ("Welcoming the User & System Current Date");
		statusBar.setLayout (new BorderLayout());
		statusBar.add (author, BorderLayout.WEST);
		statusBar.add (welcome, BorderLayout.EAST);

		//For Making the Dragging Speed of Internal Frames Faster.
		desktop.putClientProperty ("JDesktopPane.dragMode", "outline");

		//Setting the Contents of Programs.
		getContentPane().add (toolBar, BorderLayout.NORTH);
		getContentPane().add (desktop, BorderLayout.CENTER);
		getContentPane().add (statusBar, BorderLayout.SOUTH);

		//Showing The Main Form of Application.
		setVisible (true);

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	//Function For Performing different Actions By Menus of Program.

	public void actionPerformed (ActionEvent ae) {
	
		Object obj = ae.getSource();

		if (obj == addNew || obj == open || obj == btnNew) {

			boolean b = openChildWindow ("Create New Account");
			if (b == false) {
				NewAccount newAcc = new NewAccount ();
				desktop.add (newAcc);
				newAcc.show ();
			}

		}
		else if (obj == printRec || obj == btnRec || obj == report) {

			getAccountNo ();

		}
		else if (obj == end) {

			quitApp ();

		}
		else if (obj == deposit || obj == dep || obj == btnDep) {

			boolean b = openChildWindow ("Deposit Money");
			if (b == false) {
				DepositMoney depMon = new DepositMoney ();
				desktop.add (depMon);
				depMon.show ();
			}

		}
		else if (obj == withdraw || obj == with || obj == btnWith) {

			boolean b = openChildWindow ("Withdraw Money");
			if (b == false) {
				WithdrawMoney withMon = new WithdrawMoney ();
				desktop.add (withMon);
				withMon.show ();
			}

		}
		else if (obj == delRec || obj == del || obj == btnDel) {

			boolean b = openChildWindow ("Delete Account Holder");
			if (b == false) {
				DeleteCustomer delCus = new DeleteCustomer ();
				desktop.add (delCus);
				delCus.show ();
			}

		}
		else if (obj == search || obj == find || obj == btnSrch) {

			boolean b = openChildWindow ("Search Customer [By No.]");
			if (b == false) {
				FindAccount fndAcc = new FindAccount ();
				desktop.add (fndAcc);
				fndAcc.show ();
			}

		}
		else if (obj == searchName) {

			boolean b = openChildWindow ("Search Customer [By Name]");
			if (b == false) {
				FindName fndNm = new FindName ();
				desktop.add (fndNm);
				fndNm.show ();
			}

		}
		else if (obj == oneByOne) {

			boolean b = openChildWindow ("View Account Holders");
			if (b == false) {
				ViewOne vwOne = new ViewOne ();
				desktop.add (vwOne);
				vwOne.show ();
			}

		}
		else if (obj == allCustomer || obj == all) {

			boolean b = openChildWindow ("View All Account Holders");
			if (b == false) {
				ViewCustomer viewCus = new ViewCustomer ();
				desktop.add (viewCus);
				viewCus.show ();
			}

		}
		
		
		else if (obj == close) {

			try {
				desktop.getSelectedFrame().setClosed(true);
			}
			catch (Exception CloseExc) { }

		}
		else if (obj == closeAll) {

			JInternalFrame Frames[] = desktop.getAllFrames (); //Getting all Open Frames.
			for(int getFrameLoop = 0; getFrameLoop < Frames.length; getFrameLoop++) {
				try {
	 				Frames[getFrameLoop].setClosed (true); //Close the frame.
				} 
				catch (Exception CloseExc) { }	//if we can't close it then we have a problem.
			}

		}
		
	}
	



	//Function For Closing the Program.

	private void quitApp () {

		try {
			//Show a Confirmation Dialog.
		    	int reply = JOptionPane.showConfirmDialog (this,
					"Are you really want to exit\nFrom BankSystem?",
					"BankSystem - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			//Check the User Selection.
			if (reply == JOptionPane.YES_OPTION) {
				setVisible (false);	//Hide the Frame.
				dispose();            	//Free the System Resources.
				System.out.println ("Thanks for Using BankSystem\n");
				System.exit (0);        //Close the Application.
			}
			else if (reply == JOptionPane.NO_OPTION) {
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		} 

		catch (Exception e) {}

	}

	
	//Perform the Task.

	private boolean openChildWindow (String title) {

		JInternalFrame[] childs = desktop.getAllFrames ();
		for (int i = 0; i < childs.length; i++) {
			if (childs[i].getTitle().equalsIgnoreCase (title)) {
				childs[i].show ();
				return true;
			}
		}
		return false;

	}

	//Following Functions use for Printing Records & Report of BankSystem.

	void getAccountNo () {

		String printing;
		rows = 0;
		boolean b = populateArray ();
		if (b == false) { }
		else {
			try {
				printing = JOptionPane.showInputDialog (this, "Enter Account No. to Print Customer Balance.\n" +
				"(Tip: Account No. Contains only Digits)", "BankSystem - PrintRecord", JOptionPane.PLAIN_MESSAGE);
				if (printing == null) { }
				if (printing.equals ("")) {
					JOptionPane.showMessageDialog (this, "Provide Account No. to Print.",
						 "BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
					getAccountNo ();
				}
				else {
					findRec (printing);
				}
			}
			catch (Exception e) { }
		}

	}

	//Function use to load all Records from File when Application Execute.

	boolean populateArray () {

		boolean b = false;
		try {
			fis = new FileInputStream ("Bank.dat");
			dis = new DataInputStream (fis);
			//Loop to Populate the Array.
			while (true) {
				for (int i = 0; i < 6; i++) {
					records[rows][i] = dis.readUTF ();
				}
				rows++;
			}
		}
		catch (Exception ex) {
			total = rows;
			if (total == 0) {
				JOptionPane.showMessageDialog (null, "Records File is Empty.\nEnter Records First to Display.",
					 "BankSystem - EmptyFile", JOptionPane.PLAIN_MESSAGE);
				b = false;
			}
			else {
				b = true;
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
		}
		return b;

	}

	//Function use to Find Record by Matching the Contents of Records Array with InputBox.

	void findRec (String rec) {

		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals (rec)) {
				found = true;
				printRecord (makeRecordPrint (x));
				break;
			}
		}
		if (found == false) {
			JOptionPane.showMessageDialog (this, "Account No. " + rec + " doesn't Exist.",
					 "BankSystem - Wrong Number", JOptionPane.PLAIN_MESSAGE);
			getAccountNo ();
		}

	}

	//Function use to make Current Record ready for Print.

	String makeRecordPrint (int rec) {

		String data;
		String data0 = "               BankSystem Core Java.               \n";	//Page Title.
		String data1 = "               Customer Balance Report.              \n\n";	//Page Header.
		String data2 = "  Account No.:       " + records[rec][0] + "\n";
		String data3 = "  Customer Name:     " + records[rec][1] + "\n";
		//String data4 = "  Last Transaction:  " + records[rec][2] + ", " + records[rec][3] + ", " + records[rec][4] + "\n";
		
		String data4 = "  Current Balance:   " + records[rec][5] + "\n\n";
		String data5 = "  Last Transaction:  " + records[rec][2] + ", " + records[rec][3] + ", " + records[rec][4] + "\n";
		String data6 = "-----------------------------------------------------------\n";	
		String sep0 = " -----------------------------------------------------------\n";
		String sep1 = " -----------------------------------------------------------\n";
		String sep2 = " -----------------------------------------------------------\n";
		String sep3 = " -----------------------------------------------------------\n";
		String sep4 = " -----------------------------------------------------------\n\n";

		data = data0 + sep0 + data1 + data2 + sep1 + data3 + sep2 + data4 + sep3 + data5 + sep4 + data6;
		return data;

	}

	//Function use to Print the Current Record.

	void printRecord (String rec) {

		StringReader sr = new StringReader (rec);
		LineNumberReader lnr = new LineNumberReader (sr);
		Font typeface = new Font ("Times New Roman", Font.PLAIN, 12);
		Properties p = new Properties ();
		PrintJob prntJob = getToolkit().getPrintJob (this, "Print Customer Balance Report", p);

		if (prntJob != null) {
			Graphics gr = prntJob.getGraphics ();
			if (gr != null) {
				FontMetrics fm = gr.getFontMetrics (typeface);
				int margin = 20;
				int pageHeight = prntJob.getPageDimension().height - margin;
    				int fontHeight = fm.getHeight();
	    			int fontDescent = fm.getDescent();
    				int curHeight = margin;
				String nextLine;
				gr.setFont (typeface);

				try {
					do {
						nextLine = lnr.readLine ();
						if (nextLine != null) {         
							if ((curHeight + fontHeight) > pageHeight) {	//New Page.
								gr.dispose();
								gr = prntJob.getGraphics ();
								curHeight = margin;
							}							
							curHeight += fontHeight;
							if (gr != null) {
								gr.setFont (typeface);
								gr.drawString (nextLine, margin, curHeight - fontDescent);
							}
						}
					}
					while (nextLine != null);					
				}
				catch (EOFException eof) { }
				catch (Throwable t) { }
			}
			gr.dispose();
		}
		if (prntJob != null)
			prntJob.end ();
	}

}