package resources;

import java.util.ArrayList;
import java.util.List;

import pojoPack.AddPlace;
import pojoPack.Location;

public class TestDataBuild {

	
	public AddPlace addPlacePayLoad(String name,String language,String address) {
		
		AddPlace p = new AddPlace();
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		p.setAccuracy(50);
		p.setName(name);
		p.setAddress(address);
		p.setPhone_number(("+91) 983 893 3937"));
		List<String> list=new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		return p;
	}
	
	public String deleteplacePayload(String placeId){
		
	
	return "{\r\n\t    \"place_id\":\""+placeId+"\"\r\n\t}";
}
	
}