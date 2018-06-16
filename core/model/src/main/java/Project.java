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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.*;
import java.util.*;


@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "project")
public class Project {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
	private Long projectId;
	
	@Column(name = "project")
	private String projectName;

	@Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

	@Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(targetEntity = Personnel.class, fetch = FetchType.EAGER)
    @Cascade({CascadeType.MERGE})
    @JoinTable(name = "project_personnel", 
        joinColumns = {@JoinColumn(name="project_id")}, 
        inverseJoinColumns = {@JoinColumn(name = "id")})
	private Set<Personnel> personnel = new HashSet<Personnel>();
	
	public Project() { }

	public Long getProjectId() { return projectId; }
	public void setProjectId(Long p) { projectId = p; }

	public String getProjectName() { return projectName; }
	public void setProjectName(String p) { projectName = p; }

	public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }  

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }  

	public Set<Personnel> getPersonnel() { return personnel; }
	public void setPersonnel(Set<Personnel> p) { personnel = p; }

	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (!(obj instanceof Project))
	        return false;
	    if (obj == this)
	        return true;
	    return this.getProjectId() == ((Project) obj).getProjectId();
	}

	public int hashCode() {
	    return Objects.hash(projectId);
	}
	
}