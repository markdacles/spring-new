import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Set;
import java.util.Objects;
import java.lang.*;


@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "roles")
public class Roles {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
	private Long roleId;
	
	@Column(name = "role")
	private String role;
	
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ManyToMany(targetEntity = Personnel.class, fetch = FetchType.LAZY, mappedBy = "roles")
    @Cascade(CascadeType.SAVE_UPDATE)
	private Set<Personnel> personnel;
	
	public Roles() { }

	public Long getRoleId() { return roleId; }
	public void setRoleId(Long r) { roleId = r; }

	public String getRole() { return role; }
	public void setRole(String r) { role = r; }

	public Set<Personnel> getPersonnel() { return personnel; }
	public void setPersonnel(Set<Personnel> p) { personnel = p; }

	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (!(obj instanceof Roles))
	        return false;
	    if (obj == this)
	        return true;
	    return this.getRoleId() == ((Roles) obj).getRoleId();
	}

	public int hashCode() {
	    return Objects.hash(roleId);
	}
	
}