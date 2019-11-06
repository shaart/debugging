package shaart.debugging.stack;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseCharacter {

  private int maxHealth;
  private int health;
  private int basicDamage;

  private int damageGiven;
  private int damageTaken;
  private int healGiven;
  private int healTaken;

  private int actionIndex = 0;

  public BaseCharacter(int maxHealth, int basicDamage) {
    this.maxHealth = maxHealth;
    this.health = maxHealth;
    this.basicDamage = basicDamage;
  }

  public boolean isAlive() {
    return !isDead();
  }

  public boolean isDead() {
    return health <= 0;
  }

  public final void increaseHealthUpToMax(int value) {
    health += value;
    if (health > maxHealth) {
      health = maxHealth;
    }
    addHealTaken(value);
  }

  protected void addHealTaken(int value) {
    healTaken += value;
  }

  public void makeRandomAction(BaseCharacter enemy, BaseCharacter ally) {
    actionIndex++;
    switch (actionIndex) {
      case 0:
        dealDamage(enemy);
        break;
      case 1:
        heal(ally);
        break;
      default:
        actionIndex = 0;
        break;
    }
  }

  public void dealDamage(BaseCharacter character) {
    character.takeDamage(basicDamage);
    addDamageGiven(basicDamage);
  }

  public void heal(BaseCharacter character) {
    //do nothing
  }

  public void takeDamage(int damage) {
    health -= damage;
    addTakenDamage(damage);
  }

  protected void addDamageGiven(int value) {
    damageGiven += value;
  }

  protected void addTakenDamage(int value) {
    damageTaken += value;
  }

  protected void addHealGiven(int value) {
    healGiven += value;
  }

  public void printStats() {
    log.info("\n"
            + "[{}]\n"
            + " - Status: damage = {}, current health = {}\n"
            + " - Damage: given = {}, taken = {}\n"
            + " - Heal: given = {}, taken = {}",
        getClass().getSimpleName(),
        basicDamage, health,
        damageGiven, damageTaken, healGiven, healTaken);
  }

  public int getCurrentHealth() {
    return health;
  }
}
