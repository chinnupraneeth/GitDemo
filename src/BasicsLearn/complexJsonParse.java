package BasicsLearn;

import Files.PayLoad;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {

	public static void main(String[] args) {
		
		JsonPath js1 = new JsonPath(PayLoad.coursePrice());
		
		//Print no of courses in an Array
		int count = js1.getInt("courses.size()");
		System.out.println(count);
		
		//print purchase amount
		int amt = js1.getInt("dashboard.purchaseAmount");
		System.out.println(amt);
		
		//print title of the frst course
		String firstTitle = js1.get("courses[0].title");
		System.out.println(firstTitle);
		
	for (int i=0;i<count;i++)
	{
		String titles = js1.get("courses["+i+"].title");
		System.out.println(js1.get("courses["+i+"].price").toString());
		System.out.println(titles);
	}
	System.out.println("Print no of copies sold by RPA");
	for (int i=0;i<count;i++)
	{
		String titles = js1.get("courses["+i+"].title");
		
		if(titles.equalsIgnoreCase("RPA"))
		{
			int copiesCount = js1.get("courses["+i+"].copies");
			System.out.println(copiesCount);
			break;
		}
		
	
	}
	int totalCount = 0;
	
	for (int i=0;i<count;i++)
	{
		int coursePrice = js1.get("courses["+i+"].price");
		int copiesCount = js1.get("courses["+i+"].copies");
		
		totalCount+=coursePrice*copiesCount;	
	}
	System.out.println(totalCount);
	if(totalCount==amt)
	{
		System.out.println("Both the amounts are same");
	}
	
}
}

