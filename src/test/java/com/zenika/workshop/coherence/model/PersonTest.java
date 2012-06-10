package com.zenika.workshop.coherence.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import junit.framework.Assert;

import org.fluttercode.datafactory.impl.DataFactory;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.tangosol.io.ReadBuffer.BufferInput;
import com.tangosol.io.WrapperBufferInput;
import com.tangosol.io.WrapperBufferOutput;
import com.tangosol.io.WriteBuffer.BufferOutput;
import com.tangosol.io.pof.ConfigurablePofContext;
import com.tangosol.io.pof.PofContext;

/**
 * @author olivier
 * 
 */
public class PersonTest {

	private PofContext context;
	private DataFactory df;

	@Before
	public void setUp() {
		context = new ConfigurablePofContext("pof-config.xml");
		df = new DataFactory();
	}

	@Test
	public void test() throws Exception {
		DateTime expectedDateTime = new DateTime();
		Person expectedPerson = new Person();
		expectedPerson.setFirstName(df.getFirstName());
		expectedPerson.setLastName(df.getLastName());
		expectedPerson.setModificationDate(expectedDateTime);
		expectedPerson.setAge(df.getNumberUpTo(99));

		Person actualPerson = (Person) serializeDeserialize(expectedPerson);
		Assert.assertEquals(expectedPerson, actualPerson);
		Assert.assertEquals(expectedPerson.getLastName(), actualPerson.getLastName());
		Assert.assertEquals(expectedPerson.getFirstName(), actualPerson.getFirstName());
		Assert.assertEquals(expectedPerson.getModificationDate(), actualPerson.getModificationDate());
		Assert.assertEquals(expectedPerson.getAge(), actualPerson.getAge());
	}

	public byte[] serialize(Object object) throws IOException {
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			BufferOutput out = new WrapperBufferOutput(new DataOutputStream(baos));
			context.serialize(out, object);
			return baos.toByteArray();
		} finally {
			if (baos != null) {
				baos.close();
			}
		}
	}

	public Object deserialize(byte[] byteArray) throws Exception {
		ByteArrayInputStream bais = null;
		BufferInput in = null;
		try {
			bais = new ByteArrayInputStream(byteArray);
			in = new WrapperBufferInput(new DataInputStream(bais));
			return context.deserialize(in);
		} finally {
			if (bais != null) {
				bais.close();
			}
			if (in != null) {
				in.close();
			}
		}
	}

	public Object serializeDeserialize(Object object) throws Exception {
		return deserialize(serialize(object));
	}

}
