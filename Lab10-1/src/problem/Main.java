package problem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import problem.api.Team;

public class Main {

	/**
	 * Prints the Colts' starting lineups.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// GENERATES a new concrete Team CLASS at RUNTIME and instantiates it.
		Team t = UniversalObjectFactory.create(Team.class);
		
		// Programming to the interface, not the implementation, to set the team's stats.
		t.setName("Colts");
		// Colts went 8-8
		for(int i=0; i<8; i++){
			t.incrementWins();
		}
		for(int i=0; i<8; i++){
			t.incrementLosses();
		}
		
		// Read in offensive line into a new Roster.
		// Roster offensiveLine = GENERATE a new concrete Roster CLASS at RUNTIME and instantiate it. 
		// DO NOT WRITE a concrete Roster class!
		// offensiveLine.setName("Offense");
		Scanner offenseFile = new Scanner(new File("input_output/colts_offense.txt"));
		// skip header
		offenseFile.nextLine();
		while(offenseFile.hasNext()){
			String[] posAndPlayers=offenseFile.nextLine().split("\\t");
			String pos = posAndPlayers[0];
			String firstPlayer = posAndPlayers[1];
			// Player p = GENERATE a new concrete Player CLASS at RUNTIME and instantiate it. DO NOT WRITE a concrete Player class!
			// ...
		}
		// ...
		offenseFile.close();
		
		// set roster on team
		// t.setOffensiveStartingLineup(offensiveLine);
		
		// TODO: REPEAT for defensive line, special teams.
		
		// Prints entire team recursively.
		System.out.println(t);
		// no more code than this needed to print entire starting lineup
	}
}
