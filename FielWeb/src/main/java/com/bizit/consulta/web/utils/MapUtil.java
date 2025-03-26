package com.bizit.consulta.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class MapUtil {
	
	private MapUtil() {
		super();
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public static List map(List collection, String[] mapWith) {
		List result = new ArrayList();
		
		if(collection != null) {
			Iterator<Object[]> iter = collection.iterator();
			while(iter.hasNext()) {
				Object[] obj = (Object[]) iter.next();
				HashMap<String, Object> mapAux = new HashMap<String, Object>();
				
				for(int i=0 ; i<mapWith.length ; i++) {
					mapAux.put(mapWith[i], !(obj[i] == null) && obj[i].toString().equals("f") ? Boolean.FALSE : 
					    (!(obj[i] == null) && obj[i].toString().equals("t") ? Boolean.TRUE : obj[i]));
				}
				
				result.add(mapAux);
			}
		}
		
		
		return result;
	}
}

