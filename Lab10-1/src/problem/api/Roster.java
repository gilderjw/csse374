package problem.api;

import java.util.List;

/**
 * The Roster interface. DO NOT IMPLEMENT YOURSELF.
 */
public interface Roster{
	String getName();
	void setName(String name);
	List<Player> getPlayers();
	void setPlayers(List<Player> p);
	void addPlayerToPlayers(Player p);
}
