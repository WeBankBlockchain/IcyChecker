/*
 * Copyright (C) 2016 University of Freiburg
 *
 * This file is part of the Ultimate Delta Debugger plug-in.
 *
 * The Ultimate Delta Debugger plug-in is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Ultimate Delta Debugger plug-in is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the Ultimate Delta Debugger plug-in. If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the Ultimate Delta Debugger plug-in, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the Ultimate Delta Debugger plug-in grant you additional permission
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.deltadebugger.core.search.minimizers.decorators;

import java.util.List;

import de.uni_freiburg.informatik.ultimate.deltadebugger.core.search.minimizers.IMinimizer;
import de.uni_freiburg.informatik.ultimate.deltadebugger.core.search.minimizers.IMinimizerStep;

/**
 * Ensure local minimality by restarting on the result until the size stays the same.
 */
public class RepeatUntilMinimalDecorator implements IMinimizer {
	private final IMinimizer mDelegateMinimizer;
	
	/**
	 * @param delegate
	 *            Delegate minimizer.
	 */
	public RepeatUntilMinimalDecorator(final IMinimizer delegate) {
		mDelegateMinimizer = delegate;
	}
	
	@Override
	public <E> IMinimizerStep<E> create(final List<E> input) {
		return new StepDecorator<>(mDelegateMinimizer.create(input), input.size());
	}
	
	@Override
	public boolean isEachVariantUnique() {
		return false;
	}
	
	@Override
	public boolean isResultMinimal() {
		return true;
	}
	
	/**
	 * @param minimizer
	 *            Minimizer.
	 * @return the minimizer wrapped in a {@link RepeatUntilMinimalDecorator} if not already minimal
	 */
	public static IMinimizer decorate(final IMinimizer minimizer) {
		return minimizer.isResultMinimal() ? minimizer : new RepeatUntilMinimalDecorator(minimizer);
	}
	
	/**
	 * A step decorator.
	 *
	 * @param <E>
	 *            element type
	 */
	final class StepDecorator<E> implements IMinimizerStep<E> {
		private final IMinimizerStep<E> mDelegate;
		private final int mInitialSize;
		
		private StepDecorator(final IMinimizerStep<E> delegate, final int initialSize) {
			mDelegate = delegate;
			mInitialSize = initialSize;
		}
		
		@Override
		public List<E> getResult() {
			return mDelegate.getResult();
		}
		
		@Override
		public List<E> getVariant() {
			return mDelegate.getVariant();
		}
		
		@Override
		public boolean isDone() {
			return mDelegate.isDone();
		}
		
		@Override
		public IMinimizerStep<E> next(final boolean keepVariant) {
			final IMinimizerStep<E> nextStep = mDelegate.next(keepVariant);
			if (nextStep.isDone()) {
				// Restart if something was removed
				final List<E> result = nextStep.getResult();
				if (result.size() != mInitialSize) {
					return create(result);
				}
			}
			
			return new StepDecorator<>(nextStep, mInitialSize);
		}
	}
}
