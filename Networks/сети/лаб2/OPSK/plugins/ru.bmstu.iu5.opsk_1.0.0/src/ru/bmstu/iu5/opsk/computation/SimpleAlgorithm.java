/*
 * Created on 04.06.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.computation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ru.bmstu.iu5.opsk.gef.model.ConnectionElement;
import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;
import ru.bmstu.iu5.opsk.model.Connection;
import ru.bmstu.iu5.opsk.path.Path;

/**
 * @author Egorova Olga
 */
public class SimpleAlgorithm {
	
	/**
	 * Compute Ci over all network
	 * @param network
	 */
	public static void compute(NetworkElement network) {
		// We are using formula from kleinrock paper:
		// Solution for problem A from "On topological design of networks" by GERLA and KLEINROCK
		// Every C is computed as f(i) + ( SUM( sqrt(d(i) * f(i) ) / ( gamma * Tmax ) ) * sqrt( f(i) * d(i) )

		// For following computation we need a list of all connections in the model
		List connections = new ArrayList();
		// Iterating through all nodes
		for (Iterator iter = network.getChildren().iterator(); iter.hasNext();) {
			NodeElement element = (NodeElement) iter.next();
			// We are iterating only source connections
			// because we don't have a detached connections
			// hence every source connection in a destination connection
			// in another node
			for (Iterator iterator = element.getSourceConnections().iterator(); iterator.hasNext();) {
				ConnectionElement conn = (ConnectionElement) iterator.next();
				connections.add(conn);
			}
		}
		
		// First of all we need to calculate mu for calculation of the f
		float mu = 1 / (float)network.getModel().getPacketLength();
		
		float gamma = calculateGamma(network);
		
		// Maximum delivery time is set in the network model properties
		float Tmax = network.getModel().getMaximumDeliveryTime();
		
		// First of all we compute gamma * Tmax
		float gammaOnTmax = gamma * Tmax;
		
		// Then we get a d, d for our network is independent from the link, therefore it's simply d (not d(i))
		float d = network.getModel().getChannelSpecificCosts();
		
		// Now we will calculate f(i) as lambda(i) / mu
		float f[] = new float[connections.size()];
		int num = 0;
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			Connection model = ((ConnectionElement) iter.next()).getModel();
			// lambda(i) is assumed to be intensivity because
			// we have simple routing policy
			float lambda_k = model.getIntensivity();
			f[num] = lambda_k / mu;
			num++;
		}
		//System.out.println("F[1,2]=" + f[0] + "," + f[1]);
		
		// Calculating summ( sqrt(d * f(i) ), for all i = 1..<number of connections>)
		float sum = 0;
		for (int i = 0; i < f.length; i++) {
			sum += Math.sqrt(f[i] * d);
		}
		
		// Now we are ready to calculate end result for every connection
		// Iterating through all connections
		num = 0;
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			ConnectionElement element = (ConnectionElement) iter.next();
			Connection model = element.getModel();
			// Calculate result
			float result = (float) (f[num] + (sum / gammaOnTmax) * Math.sqrt(f[num] / d));
			num++;
			// Set result back to the model
			element.setThroughput(result);
		}
	}
	
	protected static float calculateGamma(NetworkElement net) {
		// For following computation we need a list of all connections in the model
		List connections = new ArrayList();
		// Iterating through all nodes
		for (Iterator iter = net.getChildren().iterator(); iter.hasNext();) {
			NodeElement element = (NodeElement) iter.next();
			// We are iterating only source connections
			// because we don't have a detached connections
			// hence every source connection in a destination connection
			// in another node
			for (Iterator iterator = element.getSourceConnections().iterator(); iterator.hasNext();) {
				ConnectionElement conn = (ConnectionElement) iterator.next();
				connections.add(conn);
			}
		}
		// Then we need to calculate average packet rate gamma for whole network
		// it is calculated as sum of all average packet rates that needed to be
		// transvered through all connections
		// where gamma(i) is a intensivity
		float gamma = 0f;
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			Connection model = ((ConnectionElement) iter.next()).getModel();
			gamma += model.getIntensivity();
		}
		return gamma;
	}
	
	/**
	 * This function computes average delay for given path
	 * 
	 * @param path
	 * @return
	 */
	public static float computePathAvgTime(Path path) {
		// Calculate gamma
		float gamma = calculateGamma(path.getNetwork());
		// We need mu for calculation of the f
		float mu = 1 / (float)path.getNetwork().getModel().getPacketLength();
		// Retrieve lambda
		// We need to initialize result with node processing delay
		// because every path contains N+1 node where N is a number of connections
		float result = path.getNetwork().getModel().getNodeSpecificDelay();
		for (Iterator iter = path.getConnections().iterator(); iter.hasNext();) {
			Connection model = ((ConnectionElement) iter.next()).getModel();
			float f = model.getIntensivity() / mu;
			float c = model.getThrougthput();
			result += (f / (c - f));
			// Here we will calculate additional delay in the channel
			// and node 
			// Channel delay is linearry dependent on channel lentgth
			float channelDelay = path.getNetwork().getModel().getChannelSpecificDelay();
			float channelLength = model.getLength();
			result += channelLength * channelDelay;
			// Node processing delay should also be added
			result += path.getNetwork().getModel().getNodeSpecificDelay();
		}
		return (1 / gamma) * result;
	}


}
