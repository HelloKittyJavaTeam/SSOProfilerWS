package it.hellokitty.gt.xmlstructure.arealist;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "area-list")
public class AreaXML {
	private String areaId;
	private String description;
	
	public String getAreaId() {
		return areaId;
	}
	
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}