/*
 * IterationStrategy.java
 *
 * Copyright (C) 2003 - 2008
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sourceforge.cilib.algorithm.population;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.cilib.entity.operators.Operator;
import net.sourceforge.cilib.pso.iterationstrategies.BoundaryConstraint;
import net.sourceforge.cilib.pso.iterationstrategies.UnconstrainedBoundary;

/**
 * Generic IterationStrategy class for all Population based algorithms. Each and every IterationStrategy
 * will have a pipeline available to it in order to specify the needed operators that will be
 * required to be executed during the iteration. For the implementing classes, a default
 * pipeline is constructed and made available, this can (as with everything within CILib) be overridden.  
 * 
 * @author Gary Pampara
 * 
 * @param <E> The {@linkplain PopulationBasedAlgorithm} type.
 */
public abstract class IterationStrategy<E extends PopulationBasedAlgorithm> implements Serializable {
	
	protected BoundaryConstraint boundaryConstraint;
	protected List<Operator> operatorPipeline;
	
	public IterationStrategy() {
		this.boundaryConstraint = new UnconstrainedBoundary();
		this.operatorPipeline = new ArrayList<Operator>();
	}
	
	public abstract IterationStrategy<E> getClone();
	

	/**
	 * Perform the iteration of the PopulationBasedAlgorithm.
	 * <p>
	 * Due to the nature of the PopulationBasedAlgorithms, the actual manner in which the algorithm's
	 * iteration is performed is deferred to the specific iteration strategy being used.
	 * <p>
	 * This implies that the general structure of the iteration for a specific flavour of
	 * algorithm is constant with modifications on that algorithm being made. For example,
	 * within a Genetic Algorithm you would expect:
	 * <ol>
	 *   <li>Parent individuals to be <b>selected</b> in some manner</li>
	 *   <li>A <b>crossover</b> process to be done on the selected parent individuals to create
	 *       the offspring</li>
	 *   <li>A <b>mutation</b> process to alter the generated offspring</li>
	 *   <li><b>Recombine</b> the existing parent individuals and the generated offspring to create
	 *       the next generation</li>
	 * </ol>
	 *       
	 * @param algorithm The algorithm to perform the iteration process on.
	 */
	public abstract void performIteration(E algorithm);

	public BoundaryConstraint getBoundaryConstraint() {
		return boundaryConstraint;
	}

	public void setBoundaryConstraint(BoundaryConstraint boundaryConstraint) {
		this.boundaryConstraint = boundaryConstraint;
	}

	/**
	 * Get the <code>List&lt;Operator&gt;</code> that represents the sequence
	 * of operators to be performed within the current <tt>IterationStrategy</tt>.
	 * @return The operator pipeline <code>List&lt;Operator&gt;</code>
	 */
	public List<Operator> getOperatorPipeline() {
		return operatorPipeline;
	}

	/**
	 * Set the pipeline to be used within the current <tt>IterationStrategy</tt>.
	 * @param operatorPipeline The pipeline to be used.
	 */
	public void setOperatorPipeline(List<Operator> operatorPipeline) {
		this.operatorPipeline = operatorPipeline;
	}	
	
}
