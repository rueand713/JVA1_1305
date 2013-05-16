package handlingJSON;

public enum JSONData {
	
	// set the enum data up for creating the JSON objects
	PaperBoy("Atari Games", "Various", "Paper Boy", "1984 - 1991"),
	KillerInstinct("Rareware", "Super Nintendo", "Killer Instinct", "1995"),
	Lufia2("Neverland", "Super Nintendo", "Lufia 2: Rise of the Sinistrals", "1996"),
	GoldenEye("Rare", "Super Nintendo", "Golden Eye: 007", "1997"),
	OcarinaOfTime("Nintendo", "Nintendo 64", "Legend of Zelda: Ocarina of Time", "1998"),
	Halo("Bungie", "Xbox", "Halo: Combat Evolved", "2001"),
	Oblivion("Bethesda", "Xbox 360 / Playstation 3", "Elder Scrolls IV: Oblivion", "2006"),
	CallOfDuty4("Infinity Ward", "Xbox 360 / Playstation 3", "Call of Duty 4: Modern Warfare", "2007"),
	BadCompany2("Electronic Arts", "Xbox 360 / Playstation 3", "Battlefield: Bad Company 2", "2010"),
	MineCraft("4J Studios", "Xbox 360", "MineCraft", "2012");
	
	private String developer;
	private String platform;
	private String release;
	private String title;
	
	// the enum constructor method
	JSONData(String developer, String platform, String title, String release)
	{
		this.developer = developer;
		this.platform = platform;
		this.title = title;
		this.release = release;
	}
	
	// accessor 'getter' methods for retrieving the private string values
	
	// developer accessor
	public String getDeveloper()
	{
		return developer;
	}
	
	// platform accessor
	public String getPlatform()
	{
		return platform;
	}
	
	// release accessor
	public String getRelease()
	{
		return release;
	}
	
	// title accessor
	public String getTitle()
	{
		return title;
	}
}
