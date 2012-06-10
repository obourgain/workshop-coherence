package com.zenika.workshop.coherence.util;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.tangosol.net.CacheFactory;

/**
 * @author olivier
 *
 */
public class CoherenceHiLoGenerator {

	private static AtomicInteger count = new AtomicInteger();
	private static int MEMBER_ID = CacheFactory.getCluster().getLocalMember().getId();
	private static String left = StringUtils.leftPad(String.valueOf(MEMBER_ID), 16, '0');

	public static String generate() {
		return left + StringUtils.leftPad(String.valueOf(count.incrementAndGet()), 16, '0');
	}

}
