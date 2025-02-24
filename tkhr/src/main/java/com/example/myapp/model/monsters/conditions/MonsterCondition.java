package tkhr.monsters.conditions;

public abstract class MonsterCondition {
	protected int amount;
	protected String name;

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}

	public void plusAmount() {
		amount++;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void minusAmount() {
		amount--;
	}

	public void turnEnd() {
		amount = 0;
	}
}
