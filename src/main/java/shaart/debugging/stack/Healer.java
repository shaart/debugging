package shaart.debugging.stack;

public class Healer extends BaseCharacter {

  private final int basicHealPower = 10;

  public Healer(int maxHealth, int basicDamage) {
    super(maxHealth, basicDamage);
  }

  @Override
  public void heal(BaseCharacter character) {
    character.increaseHealthUpToMax(basicHealPower);
    addHealGiven(basicHealPower);
  }
}
