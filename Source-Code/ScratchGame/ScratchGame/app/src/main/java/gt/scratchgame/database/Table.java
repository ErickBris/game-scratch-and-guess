package gt.scratchgame.database;

public class Table {

 public enum ScoreTable
 {
	 id("integer"),
     categoryId("integer"),
     player_name("text"),
     player_score("REAL");

	 
	 private String type;
     ScoreTable(String type1) {
		// TODO Auto-generated constructor stub
		  this.type = type1;
	}
	  public String type()
	  {
		return type;
	  }
	  
	  public String print()
	  {
		return name() + " " + type();
		  
	  }
 }

}
