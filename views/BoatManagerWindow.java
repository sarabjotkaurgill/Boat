package views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import models.Boat;
import models.BoatState;

public class BoatManagerWindow extends JFrame {
	
private static final long serialVersionUID = 1L;
	
	private JLabel runningStateLabel;
	private JLabel fillingStateLabel;
	private JLabel fuelLevelLabel;
	private JLabel speedStateLabel;
	private JLabel messageLabel;
	private JLabel velocityLabel;
	private JLabel positionLabel;
	private JProgressBar fuelLevelProgressBar;

	private JButton runningBoatButton;
	private JButton stopBoatButton;
	private JButton startFillingButton;
	private JButton stopFillingButton;
	private JButton increaseSpeedButton;
	private JButton decreaseSpeedButton;
	private JButton northButton;
	private JButton westButton;
	private JButton eastButton;
	private JButton southButton;
	private JButton exitButton;
	
	private Boat boat;
	
	private final double SPEED_CHANGE;
	private final double CAPSIZE_SINK_SPEED;
	private final double DANGER_SPEED;
	private final double NORMAL_SPEED = 0;
	private final double FUEL_LEVEL;
	
	private double speed = 0;
	private double fuel = 0;
	private BoatState state;

	private static final DecimalFormat levelFormat = new DecimalFormat("0.0");

	public BoatManagerWindow(double capsizeSinkSpeed)
	{
		super("Boat");
		
		boat = new Boat(60);
		
		this.state = BoatState.Off;
		this.speed = 0;
		this.fuel = 0;
		
		this.SPEED_CHANGE = 10;
		this.CAPSIZE_SINK_SPEED = capsizeSinkSpeed;
		this.DANGER_SPEED = CAPSIZE_SINK_SPEED - 20;
		this.FUEL_LEVEL = 5;
		
		// Create/configure basic components

		this.runningBoatButton = new JButton("Start boat");
		this.stopBoatButton = new JButton("Stop boat");

		JLabel runningStateTextLabel = new JLabel("State:");
		this.runningStateLabel = new JLabel("Stop");

		this.startFillingButton = new JButton("Start filling fuel");
		this.stopFillingButton = new JButton("Stop filling fuel");

		JLabel fillingStateTextLabel = new JLabel("State:");
		this.fillingStateLabel = new JLabel("Not Filling");

		JLabel fuelLevelTextLabel = new JLabel("Fuel level:");
		this.fuelLevelLabel = new JLabel(levelFormat.format(0));
		JLabel fuelCapacityTextLabel = new JLabel("Fuel capacity:");
		JLabel fuelCapacityLabel = new JLabel("95.0");

		this.fuelLevelProgressBar = new JProgressBar(0, 60);
		
		this.increaseSpeedButton = new JButton("Increase Speed");
		this.decreaseSpeedButton = new JButton("Decrease Speed");
		
		JLabel speedStateTextLabel = new JLabel("Speed:");
		this.speedStateLabel = new JLabel("0.0");
		
		this.messageLabel = new JLabel("Out of Danger Zone");
		
		this.velocityLabel = new JLabel("Velocity: 0.0");
		
		this.positionLabel =  new JLabel("Position");
		
		this.northButton = new JButton("North");
		this.westButton = new JButton("West");
		this.eastButton = new JButton("East");
		this.southButton = new JButton("South");

		this.exitButton = new JButton("Exit");
		
		// Add action listeners to buttons

		addListenersToButtons();
		
		// Add components to panels

		JPanel driveStopBoatPanel = new JPanel();
		driveStopBoatPanel.add(runningBoatButton);
		driveStopBoatPanel.add(stopBoatButton);

		JPanel drivingStatePanel = new JPanel();
		drivingStatePanel.add(runningStateTextLabel);
		drivingStatePanel.add(Box.createRigidArea(new Dimension(5, 0)));
		drivingStatePanel.add(runningStateLabel);

		JPanel startStopFillingPanel = new JPanel();
		startStopFillingPanel.add(startFillingButton);
		startStopFillingPanel.add(stopFillingButton);

		JPanel fillingStatePanel = new JPanel();
		fillingStatePanel.add(fillingStateTextLabel);
		fillingStatePanel.add(Box.createRigidArea(new Dimension(5, 0)));
		fillingStatePanel.add(fillingStateLabel);

		JPanel fuelLevelPanel = new JPanel();
		fuelLevelPanel.add(fuelLevelTextLabel);
		fuelLevelPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		fuelLevelPanel.add(fuelLevelLabel);

		JPanel fuelCapacityPanel = new JPanel();
		fuelCapacityPanel.add(fuelCapacityTextLabel);
		fuelCapacityPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		fuelCapacityPanel.add(fuelCapacityLabel);

		JPanel levelCapacityPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		levelCapacityPanel.add(fuelLevelPanel);
		levelCapacityPanel.add(fuelCapacityPanel);

		JPanel fuelProgressPanel = new JPanel();
		fuelProgressPanel.add(fuelLevelProgressBar);
		
		JPanel increaseDecreaseSpeedButtonPanel = new JPanel();
		increaseDecreaseSpeedButtonPanel.add(increaseSpeedButton);
		increaseDecreaseSpeedButtonPanel.add(decreaseSpeedButton);
		
		JPanel speedStatePanel = new JPanel();
		speedStatePanel.add(speedStateTextLabel);
		speedStatePanel.add(Box.createRigidArea(new Dimension(5, 0)));
		speedStatePanel.add(speedStateLabel);
		
		JPanel messagePanel = new JPanel();
		messagePanel.add(messageLabel);
		
		JPanel velocityPanel = new JPanel();
		velocityPanel.add(velocityLabel);
		
		JPanel positionPanel = new JPanel();
		positionPanel.add(positionLabel);
		
		JPanel directionPanel = new JPanel();
		directionPanel.add(northButton);
		directionPanel.add(westButton);
		directionPanel.add(eastButton);
		directionPanel.add(southButton);

		JPanel controlButtonsPanel = new JPanel();
		controlButtonsPanel.add(exitButton);

		// Add components/panels to content pane

		JPanel contentPane = (JPanel)getContentPane();
		contentPane.setLayout(new GridLayout(13, 1, 0, 10));
		contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		contentPane.add(driveStopBoatPanel);
		contentPane.add(drivingStatePanel);
		contentPane.add(startStopFillingPanel);
		contentPane.add(fillingStatePanel);
		contentPane.add(levelCapacityPanel);
		contentPane.add(velocityPanel);
		contentPane.add(fuelProgressPanel);
		contentPane.add(increaseDecreaseSpeedButtonPanel);
		contentPane.add(speedStatePanel);
		contentPane.add(messagePanel);
		contentPane.add(positionPanel);
		contentPane.add(directionPanel);
		contentPane.add(controlButtonsPanel);
		
		// Configure window

		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	// Method for adding action listeners to buttons
	
	private void addListenersToButtons()
	{
		runningBoatButton.addActionListener((ActionEvent e) -> {
			if (boat.start()) {
				runningStateLabel.setText("Boat is Running");
			} 
			else {
				runningStateLabel.setText("Boat cannot run");
			}
		});
		
		stopBoatButton.addActionListener((ActionEvent e) -> {
			if (boat.stop()) {
				runningStateLabel.setText("Boat stopping");
			}
			else {
				runningStateLabel.setText("Boat cannot stop");
			}
		});
		
		startFillingButton.addActionListener((ActionEvent e) -> {
			double fuelLevel = fuel + FUEL_LEVEL;
			if (fuelLevel >= 95) {
                 fillingStateLabel.setText("Cannot fill more fuel");
			}
			else {
				fillingStateLabel.setText("Filling");
				setFillingFuel(fuelLevel);
			}
			fuelLevelLabel.setText(String.valueOf(fuelLevel));
		});
		
		stopFillingButton.addActionListener((ActionEvent e) -> {
			fillingStateLabel.setText("Not filling");
		});
		
		increaseSpeedButton.addActionListener((ActionEvent e) -> {
			if (boat.increaseSpeed()) {
				if (Double.valueOf(fuelLevelLabel.getText()) <= 0) {
					speedStateLabel.setText("Please fill fuel");
					//state = BoatState.RefillingFuel;
				}
				else {
				fuel -= FUEL_LEVEL;
				fuelLevelLabel.setText(String.valueOf(fuel));
				speedStateLabel.setText("Boat increasing speed");
				setSpeed(speed + SPEED_CHANGE);
				fuelLevelProgressBar.setValue((int) Math.round(speed + SPEED_CHANGE));
				}
			}
			else {
				speedStateLabel.setText("Boat cannot increase speed");
			}
		});
		
		decreaseSpeedButton.addActionListener((ActionEvent e) -> {
			if (boat.decreaseSpeed()) {
				if (Double.valueOf(fuelLevelLabel.getText()) <= 0) {
					speedStateLabel.setText("Please fill fuel");
					//state = BoatState.RefillingFuel;
				}
				else {
				fuel -= FUEL_LEVEL;
				speedStateLabel.setText("Boat decreasing speed");
				setSpeed(speed - SPEED_CHANGE);
				fuelLevelProgressBar.setValue((int) Math.round(speed - SPEED_CHANGE));
				}
			}
			else {
				speedStateLabel.setText("Invalid Action");
			}
		});
		
		northButton.addActionListener((ActionEvent e) -> {
			positionLabel.setText("Position: North");
		});
		
		westButton.addActionListener((ActionEvent e) -> {
			positionLabel.setText("Position: West");
		});
		
		eastButton.addActionListener((ActionEvent e) -> {
			positionLabel.setText("Position: East");
		});
		
		southButton.addActionListener((ActionEvent e) -> {
			positionLabel.setText("Position: South");
		});
		
		exitButton.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});
	}
	
	// Private setter method
			private void setSpeed(double speed)
			{
				if (speed < NORMAL_SPEED)
					speed = NORMAL_SPEED;
				
				this.speed = speed;
				
				if (speed >= CAPSIZE_SINK_SPEED)
					capsize();
				else if (speed >= DANGER_SPEED)
					danger();
				else if (speed <= NORMAL_SPEED) {
					if(state == BoatState.Off)
						off();
					else if (state == BoatState.Running) {
						capsize();
					}
				}
				
				speedStateLabel.setText("Boat speed = " + speed);
				velocityLabel.setText("Velocity: " + speed);
				System.out.println("Boat speed = " + speed);
			}
			
			// Private action method
			private void capsize()
			{
				System.out.println("Boat capsize"); // Message
				messageLabel.setText("Boat capsize");
				state = BoatState.Capsized; // Apply the action
			}

			private void danger()
			{
				System.out.println("Boat is in danger zone");
				messageLabel.setText("Boat is in danger zone");
			}
			
			// Private action method
			private void off()
			{
				boolean validAction = (state == BoatState.Off);
				if (validAction) {
					System.out.println("Boat stop"); // Message
					messageLabel.setText("Boat stop");
					runningStateLabel.setText("Boat Stop");
					state = BoatState.Running; // Apply the action
				}	
			}
			
			private void setFillingFuel(double fuel) {
				this.fuel = fuel;
			}
}
