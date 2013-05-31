/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package net.sourceforge.cilib.functions.continuous.dynamic.moo.hef9;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * This function is the g function of the adapted F9 problem defined in the following paper:
 * H. Li and Q. Zhang. Multiobjective optimization problems with complicated Pareto sets,
 * MOEA/D and NSGA-II, IEEE Transactions on Evolutionary Computation, 13(2):284-302, 2009.
 *
 * The problem has been adapted by Helbig and Engelbrecht to make it a DMOOP.
 *
 */
public class HEF9_g extends ContinuousFunction {



    /**
     * Evaluates the function
     * f1(X_I) = x_1
     */
    @Override
    public Double f(Vector input) {
        double value = Math.abs(input.doubleValueOf(0));
        double sum = 0.0;

        int index = 0;
        for (int k=1; k < input.size(); k+=2) {
            sum += Math.pow(input.doubleValueOf(k)-Math.sin(6.0*Math.PI*value + (double)k*Math.PI/input.size()), 2);
            index++;
        }
        sum *= 2.0/index;
        sum += 2.0-Math.pow(value, 2);
        return sum;
    }


}
