package tkhr.monsters.conditions;

public class 毎ターンHP減少 extends MonsterCondition{
	public String getName() {
		return "毎ターンHP"+amount+"減少";
	}

	public void turnEnd() {
	}
}