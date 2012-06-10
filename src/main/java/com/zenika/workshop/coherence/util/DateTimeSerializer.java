package com.zenika.workshop.coherence.util;

import java.io.IOException;

import org.joda.time.DateTime;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofSerializer;
import com.tangosol.io.pof.PofWriter;

/**
 * @author olivier
 *
 */
public class DateTimeSerializer implements PofSerializer {

	public Object deserialize(PofReader in) throws IOException {
		DateTime dateTime = new DateTime(in.readLong(0));
		in.readRemainder();
		return dateTime;
	}

	public void serialize(PofWriter out, Object o) throws IOException {
		DateTime dateTime = (DateTime) o;
		out.writeLong(0, dateTime.getMillis());
		out.writeRemainder(null);
	}

}
