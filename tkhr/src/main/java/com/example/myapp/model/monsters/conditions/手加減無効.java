package tkhr.monsters.conditions;

public class 手加減無効 extends MonsterCondition{
	public 手加減無効() {
		name="手加減無効";
	}
	
	@Override
	public void plusAmount() {
		amount=1;
	}
	
	@Override
	public void turnEnd() {
	}
}