package tkhr.monsters.conditions;

public class 死神の呪い extends MonsterCondition{
	public 死神の呪い() {
		name="死神の呪い";
	}
	
	@Override
	public void turnEnd() {
		amount--;
	}
}
