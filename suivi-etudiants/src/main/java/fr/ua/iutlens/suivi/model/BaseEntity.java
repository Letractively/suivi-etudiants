package fr.ua.iutlens.suivi.model;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.Transient;


/**
 * Entity implementation class for Entity: BaseEntity
 * 
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@Basic
	@Temporal(TIMESTAMP)
	private Date createdOn;
	@Temporal(TIMESTAMP)
	private Date modifiedOn;
	private static final long serialVersionUID = 1L;

	public BaseEntity() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date CreateOn) {
		this.createdOn = CreateOn;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date ModifiedOn) {
		this.modifiedOn = ModifiedOn;
	}

	@Transient
	public abstract String getDisplayText();

	@PrePersist
	public void initTimeStamps() {
		// we do this for the purpose of the demo, this lets us create our own
		// creation dates. Typically we would just set the createdOn field.
		if (createdOn == null) {
			createdOn = new Date();
		}
		modifiedOn = createdOn;
	}

	@PreUpdate
	public void updateTimeStamp() {
		modifiedOn = new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((getDisplayText() == null) ? 0 : getDisplayText().hashCode());
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
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
