package model;

import java.util.ArrayList;
import java.util.List;

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
	 * If enabled, it will wrap the grid edges together. Thus all squares on the
	 * edges will treat squares on the opposite edge as 'adjacent'.
	 * 
	 * <p>
	 * <b>False</b> by default.
	 */
	public static boolean wrapping = false;

	/**
	 * Method that returns true or false based on Game Of LifeRules Rules
	 * 
	 * @param numberOfNeighbors
	 *            is the count of live neighbors around the cell
	 * @return true if cell stayed alive or has been created
	 */
	public static boolean setGenerationLifeStatus(int numberOfNeighbors, LifeSquare curentSquare) {
		boolean isCellAliveStatus = false;

		switch (numberOfNeighbors) {
		case 0:
		case 1:
			isCellAliveStatus = false;
			break;
		case 2:
			if (curentSquare.isAlive()) {
				isCellAliveStatus = true;
			}
			break;
		case 3:
			isCellAliveStatus = true;
			break;
		default:
			isCellAliveStatus = false;
			break;
		}

		return isCellAliveStatus;
	}

	// TODO dorobit na plochu, mozno lepsie riesenie
	// TODO vymazat/prerobit
	/**
	 * Method that computes how many alive neighbors current cell/square has
	 * from List of LifeSquares
	 * 
	 * @param areaAroundLifeSquare
	 *            is the list of LifeSquares around tested LifeSquare
	 * @return count of Alive neighbors
	 */
	@Deprecated
	public int countAliveNeighbors(List<LifeSquare> areaAroundLifeSquare) {
		int liveSquaresCount = 0;
		for (LifeSquare lifeSquare : areaAroundLifeSquare) {
			if (lifeSquare.isAlive())
				liveSquaresCount++;
		}

		return liveSquaresCount;
	}

	/**
	 * Method that takes current LifeSquare and returns list of squares
	 * connected to it, including current LifeSquare
	 * 
	 * @param currentSquare
	 *            is the selected square
	 * @param sqGrid
	 *            is the tested grid
	 * @return List<LifeSquare> surrounding the curentSquare
	 */
	@Deprecated
	public List<LifeSquare> getAllLifeSquarestSurroundingSelectedSquare(LifeSquare currentSquare,
			ArrayList<ArrayList<LifeSquare>> sqGrid) {
		int listSizeX = sqGrid.get(0).size();
		int listSizeY = sqGrid.size();
		int xArrayPosition = currentSquare.getCoorX();
		int yArrayPosition = currentSquare.getCoorY();
		List<LifeSquare> area = new ArrayList<>();

		// TODO remove tests
		System.out.println("array size x: " + listSizeX);
		System.out.println("array size y: " + listSizeY);

		// check if it is most left + corners
		// add squares to area
		if (isMostLeft(xArrayPosition)) {
			if (isMostBot(yArrayPosition, listSizeY)) {
				area.add(currentSquare);
				area.add(sqGrid.get(listSizeX).get(1));
				area.add(sqGrid.get(listSizeX - 1).get(0));
				area.add(sqGrid.get(listSizeX - 1).get(1));
			} else if (isMostTop(yArrayPosition)) {
				area.add(currentSquare);
				area.add(sqGrid.get(0).get(1));
				area.add(sqGrid.get(1).get(0));
				area.add(sqGrid.get(1).get(1));
			} else {
				area.add(currentSquare);
				area.add(sqGrid.get(listSizeX).get(1));
				area.add(sqGrid.get(listSizeX - 1).get(0));
				area.add(sqGrid.get(listSizeX - 1).get(1));
				area.add(sqGrid.get(listSizeX + 1).get(0));
				area.add(sqGrid.get(listSizeX + 1).get(1));
			}
			// check for most right + corners
		} else if (isMostRight(listSizeX, listSizeX)) {
			if (isMostBot(yArrayPosition, listSizeY)) {
				area.add(currentSquare);
				area.add(sqGrid.get(listSizeX).get(listSizeY - 1));
				area.add(sqGrid.get(listSizeX - 1).get(listSizeY));
				area.add(sqGrid.get(listSizeX - 1).get(listSizeY - 1));
			} else if (isMostTop(yArrayPosition)) {
				area.add(currentSquare);
				area.add(sqGrid.get(0).get(listSizeY - 1));
				area.add(sqGrid.get(1).get(listSizeY - 1));
				area.add(sqGrid.get(1).get(listSizeY));
			} else {
				area.add(currentSquare);
				area.add(sqGrid.get(listSizeX).get(1));
				area.add(sqGrid.get(listSizeX - 1).get(listSizeY - 1));
				area.add(sqGrid.get(listSizeX - 1).get(listSizeY));
				area.add(sqGrid.get(listSizeX + 1).get(listSizeY - 1));
				area.add(sqGrid.get(listSizeX + 1).get(listSizeY));
			}
			// check if most top
		} else if (isMostTop(yArrayPosition)) {
			area.add(currentSquare);
			area.add(sqGrid.get(0).get(yArrayPosition + 1));
			area.add(sqGrid.get(0).get(yArrayPosition - 1));
			area.add(sqGrid.get(1).get(yArrayPosition));
			area.add(sqGrid.get(1).get(yArrayPosition + 1));
			area.add(sqGrid.get(1).get(yArrayPosition - 1));
		} else if (isMostBot(yArrayPosition, listSizeY)) {
			area.add(currentSquare);
			area.add(sqGrid.get(listSizeX).get(yArrayPosition + 1));
			area.add(sqGrid.get(listSizeX).get(yArrayPosition - 1));
			area.add(sqGrid.get(listSizeX - 1).get(yArrayPosition));
			area.add(sqGrid.get(listSizeX - 1).get(yArrayPosition + 1));
			area.add(sqGrid.get(listSizeX - 1).get(yArrayPosition - 1));
			// not corner position
		} else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					area.add(sqGrid.get(i - 1).get(j - 1));
				}
			}
		}
		return null;
	}

	// methods for checking corners
	// x and y are the list coordinates of checked obj
	private boolean isMostLeft(int x) {
		return x == 0;
	}

	private boolean isMostRight(int x, int listSizeX) {
		return x == listSizeX;
	}

	private boolean isMostTop(int y) {
		return y == 0;
	}

	private boolean isMostBot(int y, int listSizeY) {
		return y == listSizeY;
	}
}
