package dialog.patterns;

import java.util.ArrayList;
import java.util.List;

import model.GridComponent;
import model.LifeSquare;

public class CustomPatten implements Pattern {
	private GridComponent gridComponent;

	CustomPatten(GridComponent gridComponent) {
		this.gridComponent = gridComponent;
	}

	@Override
	public List<Integer[]> createPattern() {
		List<Integer[]> storedPatternPositons = new ArrayList<>();
		for (ArrayList<LifeSquare> x : gridComponent.getSqGrid()) {
			for (LifeSquare y : x) {
				if (y.isAlive()) {
					storedPatternPositons.add(new Integer[] { y.getCoorX(), y.getCoorY() });
				}
			}
		}
		if (storedPatternPositons.isEmpty()) {
			storedPatternPositons.add(new Integer[] {0 , 0});
		}
		
		return storedPatternPositons;
	}
}
