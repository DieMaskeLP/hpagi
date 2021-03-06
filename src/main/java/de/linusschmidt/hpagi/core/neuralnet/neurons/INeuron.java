package de.linusschmidt.hpagi.core.neuralnet.neurons;

import de.linusschmidt.hpagi.core.neuralnet.utilities.Connection;

/**
 * @author Linus Schmidt
 * All rights reserved!
 */
public interface INeuron {
	
	void setValue(double value);
	double getValue();
	void reset();
	void addConnection(Connection connection);
}
