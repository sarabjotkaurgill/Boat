package models;

public class Boat {
	
	// Static constants
	
	private final double SPEED_CHANGE;
	private final double CAPSIZE_SINK_SPEED;
	private final double DANGER_SPEED;
	private final double NORMAL_SPEED = 0;
	
	
	// Instance variables
	
	private double speed = 0;
	public BoatState state;
	public VelocityState velocityState;
	
	
	// Constructor
	
	public Boat(double capsizeSinkSpeed) {
		this.state = BoatState.Off;
		this.velocityState = VelocityState.Maximum;
		this.speed = 0;
		
		this.SPEED_CHANGE = 10;
		this.CAPSIZE_SINK_SPEED = capsizeSinkSpeed;
		this.DANGER_SPEED = CAPSIZE_SINK_SPEED - 20;
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
			
			System.out.println("Boat speed = " + speed);
		}
		
		
		// Private action method
		private void capsize()
		{
			System.out.println("Boat capsize"); // Message
			state = BoatState.Capsized; // Apply the action
		}

		private void danger()
		{
			System.out.println("Boat is in danger zone");
		}
		
		// Private action method
		private void off()
		{
			boolean validAction = (state == BoatState.Off);
			if (validAction) {
				System.out.println("Boat stop"); // Message
				state = BoatState.Running; // Apply the action
			}	
		}
	
	
	// Public action methods
	
	public boolean start()
	{
		boolean validAction = (state == BoatState.Off);
		if (validAction) // Valid action
		{
			System.out.println("Boat Running"); // Message
			state = BoatState.Running; // Apply the action
		}
		else // Error: Invalid action
		{
			System.out.println("Error: Boat cannot run"); // Error message
		}
		return validAction; // Return Boolean error code
	}
	
	public boolean stop()
	{
		boolean validAction = (state == BoatState.Running);
		if (validAction) // Valid action
		{
			System.out.println("Boat stopping"); // Message
			state = BoatState.Off; // Apply the action
		}
		else // Error: Invalid action
		{
			System.out.println("Error: Boat cannot stop"); // Error message
		}
		return validAction; // Return Boolean error code
	}
	
	public boolean increaseSpeed()
	{
		boolean validAction = (state == BoatState.Running);
		if (validAction) // Valid action
		{
			System.out.println("Boat increasing speed"); // Message
			setSpeed(speed + SPEED_CHANGE); // Apply the action
		}
		else // Error: Invalid action
		{
			System.out.println("Error: Boat cannot increase speed"); // Error message
		}
		return validAction; // Return Boolean error code
	}
	
	public boolean decreaseSpeed()
	{	
		boolean validAction = (state == BoatState.Running/* || state == BoatState.Off */);
		if (validAction) {
			System.out.println("Airplane decreasing altitude");
			setSpeed(speed - SPEED_CHANGE);
		}
		else {
			System.out.println("Invalid Action");
		}
		return validAction;
	}
}
