package it.hellokitty.gt.xmlstructure.region;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "area-list")
public class RegionXML {
	String areaId;
	String description;
	String regionId;
	
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
	
	public String getRegionId() {
		return regionId;
	}
	
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
}
