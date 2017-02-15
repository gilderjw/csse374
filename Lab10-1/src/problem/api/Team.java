package problem.api;

/**
 * The Team interface. DO NOT IMPLEMENT YOURSELF.
 */
public interface Team{
	String getName();
	void setName(String name);
	int getWins();
	int getLosses();
	void incrementWins();
	void incrementLosses();
	Roster getOffensiveStartingLineup();
	void setOffensiveStartingLineup(Roster r);
	Roster getDefensiveStartingLineup();
	void setDefensiveStartingLineup(Roster r);
	Roster getSpecialTeamsStartingLineup();
	void setSpecialTeamsStartingLineup(Roster r);
}
