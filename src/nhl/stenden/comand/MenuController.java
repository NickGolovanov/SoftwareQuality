package nhl.stenden.comand;

import nhl.stenden.Presentation;
import nhl.stenden.comand.buttons.*;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** <p>The controller for the menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
	public class MenuController extends MenuBar {
	
	private Frame parent; // the frame, only used as parent for the Dialogs
	private Presentation presentation; // Commands are given to the presentation
	private Receiver receiver;
	
	private static final long serialVersionUID = 227L;

	
	protected static final String TESTFILE = "test.xml";
	protected static final String SAVEFILE = "dump.xml";
	
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	public MenuController(Frame frame, Presentation pres) {
		parent = frame;
		presentation = pres;
		MenuItem menuItem;

		this.receiver = new Receiver(presentation);

		Menu fileMenu = new Menu(GlobalVariable.FILE.getButtonName());
		fileMenu.add(menuItem = mkMenuItem(GlobalVariable.OPEN.getButtonName()));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Command openCommand = new Open(receiver, TESTFILE);
				openCommand.execute();
			}
		} );
		fileMenu.add(menuItem = mkMenuItem(GlobalVariable.NEW.getButtonName()));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Save save = new Save(receiver);
				save.execute();
			}
		});
		fileMenu.add(menuItem = mkMenuItem(GlobalVariable.SAVE.getButtonName()));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Save save = new Save(receiver);
				save.execute();
			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(GlobalVariable.EXIT.getButtonName()));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Exit exit = new Exit(receiver);
				exit.execute();
			}
		});
		add(fileMenu);
		Menu viewMenu = new Menu(GlobalVariable.VIEW.getButtonName());
		viewMenu.add(menuItem = mkMenuItem(GlobalVariable.NEXT.getButtonName()));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Next nextCommand = new Next(receiver);
				nextCommand.execute();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(GlobalVariable.PREV.getButtonName()));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Previous previous = new Previous(receiver);
				previous.execute();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(GlobalVariable.GOTO.getButtonName()));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				GoTo goTo = new GoTo(receiver);
				goTo.execute();
			}
		});
		add(viewMenu);
		Menu helpMenu = new Menu(GlobalVariable.HELP.getButtonName());
		helpMenu.add(menuItem = mkMenuItem(GlobalVariable.ABOUT.getButtonName()));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Command aboutBox = new About(receiver);
				aboutBox.execute();
			}
		});
		setHelpMenu(helpMenu);		// needed for portability (Motif, etc.).
	}

// create a menu item
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
