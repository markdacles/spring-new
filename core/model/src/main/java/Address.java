import javax.persistence.*;

@Embeddable
public class Address {

    @Column(name = "st_number")
	private String stNum;
	
    @Column(name = "barangay")
    private String brgy;
	
    @Column(name = "city")
    private String city;
	
    @Column(name = "zip_code")
    private String zipCode;

    public Address() { }

    public Address(String stNum, String brgy, String city, String zipCode) {
        this.stNum = stNum;
        this.brgy = brgy;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getStNum() { return stNum; }
    public void setStNum(String s) { stNum = s; }  

    public String getBrgy() { return brgy; }
    public void setBrgy(String b) { brgy = b; }  

    public String getCity() { return city; }
    public void setCity(String c) { city = c; }   

    public String getZipCode() { return zipCode; }
    public void setZipCode(String z) { zipCode = z; } 
}