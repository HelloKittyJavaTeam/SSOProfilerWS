package it.hellokitty.gt.xmlstructure.dealer;

import javax.xml.bind.annotation.XmlAttribute;

public class DealerXML {
	private String countryId;
	private String id;
	private String name;
	
	public String getCountryId() {
		return countryId;
	}
	
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	
	@XmlAttribute(name = "id")
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}