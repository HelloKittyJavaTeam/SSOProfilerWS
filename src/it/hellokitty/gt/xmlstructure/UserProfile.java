package it.hellokitty.gt.xmlstructure;

import it.hellokitty.gt.xmlstructure.application.ApplicationsXML;
import it.hellokitty.gt.xmlstructure.arealist.AreaListXML;
import it.hellokitty.gt.xmlstructure.country.CountryListXML;
import it.hellokitty.gt.xmlstructure.dealer.DealerListXML;
import it.hellokitty.gt.xmlstructure.region.RegionListXML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"areaList", "regionList", "countryList", "dealerList", "applicationList"})
@XmlRootElement(name = "user-profile")
public class UserProfile {
	
	AreaListXML 	 areaList;

	RegionListXML   regionList;

	CountryListXML  countryList;

	DealerListXML   dealerList;

	ApplicationsXML applicationList;
	
	@XmlElement(name = "area-list")
	public AreaListXML getAreaList() {
		return areaList;
	}
	
	public void setAreaList(AreaListXML areaList) {
		this.areaList = areaList;
	}
	
	@XmlElement(name = "region-list")
	public RegionListXML getRegionList() {
		return regionList;
	}
	
	public void setRegionList(RegionListXML regionList) {
		this.regionList = regionList;
	}

	@XmlElement(name = "country-list")
	public CountryListXML getCountryList() {
		return countryList;
	}

	public void setCountryList(CountryListXML countryList) {
		this.countryList = countryList;
	}

	@XmlElement(name = "dealer-list")
	public DealerListXML getDealerList() {
		return dealerList;
	}

	public void setDealerList(DealerListXML dealerList) {
		this.dealerList = dealerList;
	}

	@XmlElement(name = "application-list")
	public ApplicationsXML getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(ApplicationsXML applicationList) {
		this.applicationList = applicationList;
	}
}