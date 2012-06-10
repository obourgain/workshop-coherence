package com.zenika.workshop.coherence.model;

import java.io.IOException;
import java.io.Serializable;

import org.joda.time.DateTime;

import com.tangosol.io.AbstractEvolvable;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

/**
 * @author olivier
 *
 */
public class Person extends AbstractEvolvable implements PortableObject, Serializable {

	private String id;

	private int age;

	private String lastName;
	private String firstName;

	private DateTime modificationDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public DateTime getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(DateTime birthDate) {
		this.modificationDate = birthDate;
	}

	@Override
	public int getImplVersion() {
		return 0;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=").append(id).append(", lastName=").append(lastName).append(", firstName=").append(firstName).append(", age=").append(age)
				.append(", modificationDate=").append(modificationDate).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void readExternal(PofReader in) throws IOException {
		lastName = in.readString(0);
		firstName = in.readString(1);
		modificationDate = (DateTime) in.readObject(2);
		age = in.readInt(3);
		id = in.readString(4);
	}

	public void writeExternal(PofWriter out) throws IOException {
		out.writeString(0, lastName);
		out.writeString(1, firstName);
		out.writeObject(2, modificationDate);
		out.writeInt(3, age);
		out.writeString(4, id);
	}

}
