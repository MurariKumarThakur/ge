package getScreenJsonValues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import fileReader.JsonReader;
import fileReader.PropertiesFileReader;
import staticDataManager.PathManager;

public class GetJsonProperty {

	public static String getJsonPath() {

		PropertiesFileReader pro = new PropertiesFileReader(PathManager.getConfigPropertiesFile());

		return pro.ReadPropertyValueUsingKey("getScreenJsonLocation");

	}

	public static JSONObject getRootJsonData() {
		return JsonReader.readAllJson(getJsonPath(), "root");
	}

	public static String getDefaultRoute() {

		JSONObject root = getRootJsonData();
		String defaultRoute = (String) root.get("defaultRoute");

		return defaultRoute;
	}

	// reading header data

	public static JSONObject getHeaderBody() {

		JSONObject header = (JSONObject) getRootJsonData().get("header");
		return header;
	}

	public static String getHeaderComponent() {

		JSONObject headerBody = getHeaderBody();
		String headerComponent = (String) headerBody.get("Component");

		return headerComponent;
	}

	public static JSONObject getHeaderLayoutMetaData() {

		JSONObject headerBody = getHeaderBody();
		JSONObject HeaderLayoutMetaData = (JSONObject) headerBody.get("LayoutMetaData");

		return HeaderLayoutMetaData;
	}

	public static String getHeaderLayoutMetaDataClassName() {

		JSONObject layoutMetaData = getHeaderLayoutMetaData();

		String layoutMetaDataClassName = (String) layoutMetaData.get("className");

		return layoutMetaDataClassName;
	}

	public static JSONObject getHeaderComponentMetaData() {
		JSONObject header = getHeaderBody();

		JSONObject headerComponentMetaData = (JSONObject) header.get("ComponentMetaData");

		return headerComponentMetaData;
	}

	public static JSONArray getHeaderItemArray() {
		JSONObject headerComponentMetaData = getHeaderComponentMetaData();

		JSONArray Items = (JSONArray) headerComponentMetaData.get("items");

		return Items;

	}

	public static int getHeaderItemsCount() {
		JSONArray items = getHeaderItemArray();
		int headerItemCount = items.size();
		return headerItemCount;
	}

	public static List getHeaderItemsClassName() {
		List allClassName = null;
		int totalItemsCount = getHeaderItemsCount();
		JSONArray items = getHeaderItemArray();
		ArrayList<String> list = new ArrayList<String>();
		int s = 0;
		for (Object o : items) {

			s = s + 1;
			JSONObject data = (JSONObject) o;
			JSONObject ability = (JSONObject) data.get("LayoutMetaData");
			String layoutClassName = (String) ability.get("className");

			list.add(layoutClassName);

			if (s == totalItemsCount) {

				allClassName = list;

			}

		}
		return allClassName;

	}

	public static JSONObject getAllDataProps(String EnterComponentName) {
		JSONObject dataProps = null;
		List items = getHeaderItemArray();

		for (Object allItemsObject : items) {

			JSONObject allItemsObj = (JSONObject) allItemsObject;
			String data = (String) allItemsObj.get("Component");
			if (EnterComponentName.contains(data)) {
				JSONObject ComponentMetaData = (JSONObject) allItemsObj.get("ComponentMetaData");
				dataProps = (JSONObject) ComponentMetaData.get("dataProps");
				String featureName = (String) dataProps.get("name");
				String feature_list[] = EnterComponentName.split("/");
				if (EnterComponentName.contains("WeatherReport")) {
					if (featureName.contains(feature_list[1])) {

						return dataProps;

					}

				} else {
					return dataProps;
				}

			}
		}
		// System.out.println(dataProps);
		return dataProps;

	}

	public static JSONArray getHeaderMainMenu() {
		JSONObject MenuList = getAllDataProps("MainMenuItems");

		JSONArray Menu = (JSONArray) MenuList.get("menuLists");

		return Menu;

	}

	public static int getMainMenuCount() {

		JSONArray menuList = getHeaderMainMenu();

		int headerMenuCount = menuList.size();

		return headerMenuCount;

	}

	public static List getMenuProperty(String whatYouWantToAccess) {

		JSONArray menuList = getHeaderMainMenu();
		int totalSize = menuList.size();
		ArrayList list = new ArrayList();
		for (int i = 0; i < totalSize; i++) {
			JSONObject item = (JSONObject) menuList.get(i);

			try {
				String itemProperty = (String) item.get(whatYouWantToAccess);
				list.add(itemProperty);

			} catch (Exception e) {
				if (whatYouWantToAccess.equals("orderId")) {
					int id = Integer.parseInt(item.get(whatYouWantToAccess).toString());
					list.add(id);
				}

			}

		}
		System.out.println(list);
		return list;

	}

	public static JSONObject getMainBody() {
		JSONObject main = (JSONObject) getRootJsonData().get("main");
		return main;
	}

	public static String getDefaultActiveItem() {
		String activeMenuItemValue = null;
		JSONObject MenuList = getAllDataProps("MainMenuItems");

		activeMenuItemValue = (String) MenuList.get("defaultActiveItem");

		return activeMenuItemValue;
	}

	public static String getHeaderComponantName(String enterExpectedComponantName) {
		String componantName = null;
		JSONArray headerItems = getHeaderItemArray();
		for (Object name : headerItems) {

			JSONObject data = (JSONObject) name;
			componantName = (String) data.get("Component");

			if (enterExpectedComponantName.contains(componantName)) {

				return componantName;

			} else {
				componantName = null;

			}

		}
		return componantName;

	}

	public static String getHeaderComponantClassName(String whichHeaderClassNameRequired) {
		String headerClassName = null;
		JSONArray headerItems = getHeaderItemArray();
		for (Object name : headerItems) {

			JSONObject data = (JSONObject) name;

			JSONObject layoutMd = (JSONObject) data.get("LayoutMetaData");
			headerClassName = (String) layoutMd.get("className");
			 String breakClassName [] = whichHeaderClassNameRequired.split("/");
			if (headerClassName.contains(breakClassName[0])) {
				
				
				if (headerClassName.contains("WeatherReport")) {
					JSONObject ComponentMetaData = (JSONObject) data.get("ComponentMetaData");
					JSONObject dataProps = (JSONObject) ComponentMetaData.get("dataProps");
					String featureName = (String) dataProps.get("name");
                    if(featureName.contains(breakClassName[1]))
					return headerClassName+"/"+featureName;

				} else {

					return headerClassName;
				}

			} else {
				headerClassName = null;
			}

		}
		return headerClassName;

	}

	public static int getHeaderComponantPosition(String enterExpectedComponantClassName) {

		JSONArray headerItems = getHeaderItemArray();
		int position = 0;
		for (Object name : headerItems) {
			position = position + 1;

			JSONObject data = (JSONObject) name;

			JSONObject layoutMd = (JSONObject) data.get("LayoutMetaData");
			String headerClassName = (String) layoutMd.get("className");
			String className[] = enterExpectedComponantClassName.split("/");
			if (headerClassName.contains(className[0])) {

				if (headerClassName.contains("WeatherReport")) {
					
					JSONObject ComponentMetaData = (JSONObject) data.get("ComponentMetaData");
					JSONObject dataProps = (JSONObject) ComponentMetaData.get("dataProps");
					String featureName = (String) dataProps.get("name");	
					if (featureName.contains(className[1])) {
						return position;
					} 
				} else {

					return position;
				}

			} else {

			}

		}
		return position;

	}

	public static String getLogoSrc() {

		JSONObject allDataProps = getAllDataProps("LogoImage");
		String srcValue = (String) allDataProps.get("src");
		return srcValue;

	}

	public static void getTempConfig() {
		getAllDataProps("WeatherReport");
	}

	public static String getWeatherReportLabelText(String enterFeature) {
		String name = null;
		JSONObject data = getAllDataProps("WeatherReport/" + enterFeature);
		if (data != null) {
			name = (String) data.get("name");
		}
		return name;
	}
	
	
	public static String getTemperatureIconValue(){
		
	 JSONObject data =	  getAllDataProps("WeatherReport/Temp");
	 
	   String iconValue =   (String) data.get("icon");
	     return iconValue;
	}
	public static String getWindspeedIconValue(){
		JSONObject data =	  getAllDataProps("WeatherReport/Windspeed");
		String iconValue =    (String) data.get("icon");
		return iconValue;
		
		
	}
	
	

	public static void main(String[] args) {
		getTemperatureIconValue();

	}

}
