package fr.sii.formation.gwt.server.repository.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity()
public class Genre {
	@Id
	private String label;

	/**
	 * Get accessor for label
	 * 
	 * @return value of label
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Set accessor for label
	 * 
	 * @param value
	 *            the value to set in label
	 */
	public void setLabel(String value) {
		this.label = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
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
		Genre other = (Genre) obj;
		if (getLabel() == null) {
			if (other.getLabel() != null)
				return false;
		} else if (!getLabel().equals(other.getLabel()))
			return false;
		return true;
	}
}
