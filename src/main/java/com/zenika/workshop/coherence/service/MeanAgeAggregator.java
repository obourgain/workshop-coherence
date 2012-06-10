package com.zenika.workshop.coherence.service;

import java.io.Serializable;

import com.tangosol.util.ValueExtractor;
import com.tangosol.util.aggregator.AbstractAggregator;

/**
 * @author olivier
 *
 */
public class MeanAgeAggregator extends AbstractAggregator {

	int totalSum;
	int individualCount;
	int processedCount;

	/**
	 * POF-deserializing a PortableObject requires an empty constructor
	 */
	public MeanAgeAggregator() {
	}

	public MeanAgeAggregator(ValueExtractor extractor) {
		super(extractor);
	}

	@Override
	protected Object finalizeResult(boolean isFinal) {
		if (isFinal) {
			return (float) totalSum / (float) processedCount;
		} else {
			return new PartialResult(individualCount, processedCount);
		}
	}

	@Override
	protected void init(boolean isFinal) {
		if (isFinal) {
			totalSum = 0;
			processedCount = 0;
		} else {
			individualCount = 0;
			processedCount = 0;
		}
	}

	@Override
	protected void process(Object object, boolean isFinal) {
		if (isFinal) {
			// partial result returned from an individual aggregator
			PartialResult partialResult = (PartialResult) object;
			totalSum += partialResult.getAge();
			processedCount += partialResult.getProcessedEntriesCount();
		} else {
			// processing the object returned by the extractor
			individualCount += (Integer) object;
			processedCount++;
		}
	}

	public static class PartialResult implements Serializable {

		public PartialResult(int age, int processedEntriesCount) {
			this.sum = age;
			this.processedEntriesCount = processedEntriesCount;
		}

		private int sum;
		private int processedEntriesCount;

		public int getAge() {
			return sum;
		}

		public int getProcessedEntriesCount() {
			return processedEntriesCount;
		}
	}
}
