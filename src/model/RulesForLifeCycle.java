package model;

/**
 * Class that contains methods that are responsible for checking rules of the
 * Game Of Life
 * 
 * the rules are:
 * <ul>
 * For a space that is 'populated':
 * <li>Each cell with one or no neighbors dies, as if by solitude.</li>
 * <li>Each cell with four or more neighbors dies, as if by overpopulation.</li>
 * <li>Each cell with two or three neighbors survives.</li>
 * </ul>
 * <ul>
 * For a space that is 'empty' or 'unpopulated'
 * <li>Each cell with three neighbors becomes populated</li>
 * </ul>
 * 
 */
public class RulesForLifeCycle {

	/**
	 * Method that returns true or false based on Game Of LifeRules Rules
	 * 
	 * @param numberOfNeighbors
	 *            is the count of live neighbors around the cell
	 * @return true if cell stayed alive or has been created
	 */
	public boolean setLifeStatus(int numberOfNeighbors) {
		boolean isCellAliveStatus;
		
		switch (numberOfNeighbors) {
			case 0:
			case 1:
				isCellAliveStatus = false;
				break;
			case 2:
			case 3:
				isCellAliveStatus = true;
				break;
			default:
				isCellAliveStatus = true;
				break;
		}
		
		return isCellAliveStatus;
	}
	
	
}
