package tkhr.monsters.conditions;

public class 根性 extends MonsterCondition{
	public 根性() {
		name="根性";
	}
	
	@Override
	public void plusAmount() {
		amount=1;
	}
	
	@Override
	public void turnEnd() {
	}
}