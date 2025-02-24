package tkhr.items;

import tkhr.Battle;

public interface ItemObserver {
	public void turnStart(Battle battle);
	public void turnEnd(Battle battle);
}
