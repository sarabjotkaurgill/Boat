import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import views.BoatManagerWindow;

public class Application {
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() ->
		{
			setSystemLookAndFeel();
			BoatManagerWindow setupWindow = new BoatManagerWindow(60);
			setupWindow.setVisible(true);
		});
	}
	
	private static void setSystemLookAndFeel()
	{
		try 
		{
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}
		catch (UnsupportedLookAndFeelException | ClassNotFoundException |
				InstantiationException | IllegalAccessException e) {}
	}
}
