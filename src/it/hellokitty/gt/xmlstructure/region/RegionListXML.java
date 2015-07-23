package it.hellokitty.gt.xmlstructure.region;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "area-list")
public class RegionListXML {
	List<RegionXML> region;

	public List<RegionXML> getRegionList() {
		return region;
	}

	public void setRegionList(List<RegionXML> regionList) {
		this.region = regionList;
	}
	
}
