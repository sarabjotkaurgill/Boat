package interfaces;

import models.VelocityState;

public interface IVelocityListener {
	void updateVelocityValue(int velocityValue, VelocityState state);
	void updateVelocity(int velocity);
}
