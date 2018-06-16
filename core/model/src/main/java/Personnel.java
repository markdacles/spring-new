import java.time.*;
import java.util.Set;
import java.util.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "personnel")
public class Personnel {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gwa")
    private Double gwa;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_hired")
    private Date dateHired;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(fetch = FetchType.EAGER)
    @Cascade({CascadeType.ALL})
    @JoinColumn(name = "personnel_id")
    private Set<Contact> contact  = new HashSet<Contact>();

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(targetEntity = Roles.class, fetch = FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(name = "personnel_roles", 
        joinColumns = {@JoinColumn(name="id")}, 
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Roles> roles  = new HashSet<Roles>();

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(targetEntity = Project.class, fetch = FetchType.LAZY, mappedBy = "personnel")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<Project> project  = new HashSet<Project>();

    public Personnel() { }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Name getName() { return name; }
    public void setName(Name name) { this.name = name; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }    

    public Double getGwa() { return gwa; }
    public void setGwa(Double gwa) { this.gwa = gwa; }

    public Date getDateHired() { return dateHired; }
    public void setDateHired(Date dateHired) { this.dateHired = dateHired; }  

    public Set<Contact> getContact() { return contact; }
    public void setContact(Set<Contact> contact) { this.contact = contact; }

    public Set<Roles> getRoles() { return roles; }
    public void setRoles(Set<Roles> rroles) { roles = rroles; }

    public Set<Project> getProject() { return project; }
    public void setProject(Set<Project> project) { this.project = project; }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Personnel))
            return false;
        if (obj == this)
            return true;
        return this.getId() == ((Personnel) obj).getId();
    }

    public int hashCode() {
        return Objects.hash(id);
    }
}