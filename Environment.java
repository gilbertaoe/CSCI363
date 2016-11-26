public class Environment //Airport
{
	private char backgroundColor;
	private char lineColor;
	private int lineSpacing;
	private String airportName = "REKT";
	private int simulation = 0;
	
	public Environment()
	{
		
	}
	
	public void setBackgroundColor(char color)
	{
		backgroundColor = color;
		return;
	}
	
	public char getBackgroundColor()
	{
		return backgroundColor;
	}
	
	public void setLineColor(char color)
	{
		lineColor = color;
		return;
	}
	
	public char getLineColor()
	{
		return lineColor;
	}
	
	public void setLineSpacing(int spacing)
	{
		lineSpacing = spacing;
		return;
	}
	
	public int getLineSpacing()
	{
		return lineSpacing;
	}
	
	public void setAirportName(String name)
	{
		airportName = name;
		return;
	}
	
	public String getAirportName()
	{
		return airportName;
	}
	
	public void setSimulation(int i)
	{
		simulation = i;
		return;
	}
	
	public int getSimulation()
	{
		return simulation;
	}
}
