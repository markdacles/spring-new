import javax.persistence.*;

@Embeddable
public class Name {

    @Column(name = "last_name")
	private String lname;
	
    @Column(name = "first_name")
    private String fname;
	
    @Column(name = "middle_name")
    private String mname;
	
    @Column(name = "suffix")
    private String suffix;
	
    @Column(name = "title")
    private String title;

    public Name() { }

    public String getLname() { return lname; }
    public void setLname(String l) { lname = l; }

    public String getFname() { return fname; }
    public void setFname(String f) { fname = f; }

	public String getMname() { return mname; }
    public void setMname(String m) { mname = m; }

    public String getSuffix() { return suffix; }
    public void setSuffix(String s) { suffix = s; }

    public String getTitle() { return title; }
    public void setTitle(String t) { title = t; }    

    public String getFullName() {
        return (lname + " " + fname + " " + mname);
    }

}