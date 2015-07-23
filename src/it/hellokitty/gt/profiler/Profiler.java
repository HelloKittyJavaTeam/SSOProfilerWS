package it.hellokitty.gt.profiler;

import it.hellokitty.gt.ssoprofiler.entity.AdUsers;
import it.hellokitty.gt.ssoprofiler.entity.Application;
import it.hellokitty.gt.ssoprofiler.entity.Dealers;
import it.hellokitty.gt.ssoprofiler.entity.GeoAreas;
import it.hellokitty.gt.ssoprofiler.entity.GeoCountries;
import it.hellokitty.gt.ssoprofiler.entity.GeoRegions;
import it.hellokitty.gt.ssoprofiler.entity.Role;
import it.hellokitty.gt.ssoprofiler.service.impl.ServiceImplExt;
import it.hellokitty.gt.xmlstructure.UserProfile;
import it.hellokitty.gt.xmlstructure.application.ApplicationXML;
import it.hellokitty.gt.xmlstructure.application.ApplicationsXML;
import it.hellokitty.gt.xmlstructure.application.RoleListXML;
import it.hellokitty.gt.xmlstructure.application.RoleXML;
import it.hellokitty.gt.xmlstructure.arealist.AreaListXML;
import it.hellokitty.gt.xmlstructure.arealist.AreaXML;
import it.hellokitty.gt.xmlstructure.country.CountryListXML;
import it.hellokitty.gt.xmlstructure.country.CountryXML;
import it.hellokitty.gt.xmlstructure.dealer.DealerListXML;
import it.hellokitty.gt.xmlstructure.dealer.DealerXML;
import it.hellokitty.gt.xmlstructure.region.RegionListXML;
import it.hellokitty.gt.xmlstructure.region.RegionXML;

import java.io.File;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@WebService(serviceName="profiler")
public class Profiler {

	ServiceImplExt serviceImplExt = ServiceImplExt.getInstance();
	
	@WebMethod
	public void hello() {}
	
	@WebMethod
	public String getUserProfile(String user) throws IllegalArgumentException, Exception {
		ApplicationXML		application		= new ApplicationXML();
		ApplicationsXML		applications    = new ApplicationsXML();
		AreaXML 	     	area 	    	= new AreaXML();
		AreaListXML      	areas       	= new AreaListXML();
		CountryXML       	country     	= new CountryXML();
		CountryListXML   	countries   	= new CountryListXML();
		DealerXML		  	dealer	  		= new DealerXML();
		DealerListXML	  	dealers	  		= new DealerListXML();
		List<ApplicationXML>applicationList = new ArrayList<ApplicationXML>();
		List<AreaXML>    	areaList    	= new ArrayList<AreaXML>();
		List<RegionXML>  	regionList  	= new ArrayList<RegionXML>();
		List<RoleXML>		roleList		= new ArrayList<RoleXML>();	
		List<CountryXML> 	countryList 	= new ArrayList<CountryXML>();
		List<DealerXML>  	dealerList  	= new ArrayList<DealerXML>();
		Method 	      		method;
		RegionXML		  	region      	= new RegionXML();
		RegionListXML    	regions     	= new RegionListXML();
		RoleXML				role			= new RoleXML();
		RoleListXML			roles			= new RoleListXML();
		UserProfile   		userProfile 	= new UserProfile();
		
		AdUsers userFound;
		
		HashMap<String,Object> equalsParam = new HashMap<String, Object>();
		equalsParam.put("username", user);
		List<AdUsers> listAdUsers = serviceImplExt.searchAdUsers(0, 1, null, equalsParam, null, null, null);
		if(listAdUsers == null || listAdUsers.size() == 0){
			return user;
		}
		
		userFound = listAdUsers.get(0);
		
		// area-list section
		List<GeoAreas> listGeoAreas = userFound.getAreas();
		
		for(GeoAreas geoArea : listGeoAreas){
			area.setAreaId(geoArea.getId());
			try {
				  method = geoArea.getClass().getMethod("getDescription"+userFound.getLanguage());
				  area.setDescription((String) method.invoke(geoArea));
				} catch (SecurityException e) {
				  // ...
				} catch (NoSuchMethodException e) {
				  // ...
				}
			
			areaList.add(area);
		}
		areas.setArea(areaList);
		
		// region-list section
		List<GeoRegions> listGeoRegions = userFound.getRegions();
		
		for(GeoRegions geoRegion : listGeoRegions){
			region.setAreaId(geoRegion.getArea().getId());
			region.setRegionId(geoRegion.getId());
			try {
				  method = geoRegion.getClass().getMethod("getDescription"+userFound.getLanguage());
				  region.setDescription((String) method.invoke(geoRegion));
				} catch (SecurityException e) {
				  // ...
				} catch (NoSuchMethodException e) {
				  // ...
				}
			
			regionList.add(region);
		}
		regions.setRegionList(regionList);
		
		// country-list section
		List<GeoCountries> geoCountryList = userFound.getCountries();
		
		for(GeoCountries geoCountry : geoCountryList){
			country.setRegionId(geoCountry.getRegion().getId());
			country.setCountryId(geoCountry.getIdIso3166());
			country.setCountryIdISO3166_3(geoCountry.getIdIso31663());
			try {
				  method = geoCountry.getClass().getMethod("getDescription"+userFound.getLanguage());
				  country.setDescription((String) method.invoke(geoCountry));
				} catch (SecurityException e) {
				  // ...
				} catch (NoSuchMethodException e) {
				  // ...
				}
			
			countryList.add(country);
		}
		countries.setCountry(countryList);
		
		// dealer-list section
		List<Dealers> listDealer = userFound.getDealers();
		
		for(Dealers dealerElem : listDealer){
			dealer.setCountryId(dealerElem.getCountry().getIdIso3166());
			dealer.setId(dealerElem.getId());
			dealer.setName(dealerElem.getName());
			
			dealerList.add(dealer);
		}
		dealers.setDealerList(dealerList);
		
		//application-list section
		Map<Long, List<RoleXML>> applicationMap = new HashMap<Long, List<RoleXML>>();
		
		HashMap<Long,Application> id_app = new HashMap<Long, Application>();
		
		for(Role roleIndex : userFound.getRoles()){
			if(applicationMap.containsKey(roleIndex.getApplication().getId())){
				role = new RoleXML();
				role.setId(roleIndex.getId().toString());
				role.setValue(roleIndex.getName());
				applicationMap.get(roleIndex.getApplication().getId()).add(role);
			} else {
				roleList = new LinkedList<RoleXML>();
				role = new RoleXML();
				role.setId(roleIndex.getId().toString());
				role.setValue(roleIndex.getName());
				
				roleList.add(role);
				id_app.put(roleIndex.getApplication().getId(), roleIndex.getApplication());
				applicationMap.put(roleIndex.getApplication().getId(), roleList);
			}
		}
		
		for(Long applicationId : id_app.keySet()){
			application = new ApplicationXML();
			application.setId(id_app.get(applicationId).getId().toString());
			application.setName(id_app.get(applicationId).getName());
			roleList = new ArrayList<RoleXML>();
			for(RoleXML roleXMLIndex : applicationMap.get(applicationId)){
				roleList.add(roleXMLIndex);	
			}
			roles = new RoleListXML();
			roles.setRole(roleList);
			application.setRoleList(roles);
			
			applicationList.add(application);
		}
		
		applications.setApplication(applicationList);
		
		userProfile.setAreaList(areas);
		userProfile.setApplicationList(applications);
		userProfile.setRegionList(regions);
		userProfile.setCountryList(countries);
		userProfile.setDealerList(dealers);
		
		return jaxbObjectToXML(userProfile);
		
//		 <user-level>
//				0
//			</user-level>
//			<!-- 0 = HQ - 1 = Area User - 2 = Region User - 3 = Country - 4 = Dealer -->
//			<!-- Blindare col sangue che Area = Filiale / EMEA ovvero presenza di company -->
//           <area-list>
//               <area>
//                  <areaId>EM</areaId>
//                  <companyId>001</companyId>
//                  <description>EMEA</description>
//				  <company_roles>
//				  	<company_role>HOH</company_role>
//					<company_role>SM</company_role>
//				  </company_roles>
//               </area>			   
//            </area-list>
//           <region-list>
//               <region>
//                  <areaId>EM</areaId>
//                  <description>Central Europe</description>
//                  <regionId>CE</regionId>
//               </region>
//               <region>
//                  <areaId>EM</areaId>
//                  <description>East Europe</description>
//                  <regionId>EE</regionId>
//               </region>
//           </region-list>
//           <country-list>
//               <country>
//                  <description>Austria</description>
//                  <regionId>CE</regionId>
//                  <countryId>at</countryId>
//                  <countryIdISO3166_3>AUT</countryIdISO3166_3>
//				  <!--Tenere finchè CRM non migrato-->
//               </country>
//			</country-list>
//           <dealer-list>
//               <dealer>
//                  <countryId>at</countryId>
//                  <id>064168</id>
//                  <name>Keusch Premium</name>
//               </dealer>
//            <dealer-list>
//			<applications>
//				<application id="1">
//					<name>modiscs</name>
//					<role-list>
//						<role id="1234">AM Aftersales EMEA</role>
//				   </role-list>
//			   </application>
//				<application id="2">
//					<name>SOCRATE</name>
//					<role-list>
//						<role id="12345">SOCRATE User</role>
//						<role id="12346">Socrate Admin</role>
//				   </role-list>
//			   </application>
//			</applications>
//			<company-roles-list>
//				<company-roles level="0" id="1">
//					Head Of HUB
//				
//					<company-roles id="2">Region Manager</company-roles>
//					<company-roles id="3">Region Manager</company-roles>
//				</company-roles>
//			</company-roles-list>		
	}
	
    private static String jaxbObjectToXML(UserProfile userProfile) {
    	StringWriter writer = null;
        try {
        	writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(UserProfile.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            // Write to System.out for debugging
            // m.marshal(emp, System.out);
 
            // Write to File
            m.marshal(userProfile, new File("XML.xml"));
            m.marshal(userProfile, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}