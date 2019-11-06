package shaart.debugging.rpg;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpgPvp {

  static int generation = 1;

  public static void main(String[] args) {
    new RpgPvp().start();
  }

  public void start() {
    final List<BaseCharacter> blue = createParty();
    final List<BaseCharacter> red = createParty();

    fight(red, blue);

    showStats("Blue", blue);
    showStats("Red", red);

    printWinner("Blue", blue, "Red", red);
  }

  private List<BaseCharacter> createParty() {
    final int healerHealth = generation * 10 + 100;
    final int warriorHealth = generation * 20 + 150;
    final int tankHealth = generation * 30 + 200;

    final int healerDamage = generation * 2 + 5;
    final int warringDamage = generation * 5 + 20;
    final int tankDamage = generation * 4 + 10;

    final Healer healer = new Healer(healerHealth, healerDamage);
    final Warrior warrior = new Warrior(warriorHealth, warringDamage);
    final Tank tank = new Tank(tankHealth, tankDamage);

    generation++;
    return Arrays.asList(healer, warrior, tank);
  }

  private void fight(List<BaseCharacter> redTeam, List<BaseCharacter> blueTeam) {
    int stepsTotal = 100;
    int currentStep = 0;
    while (currentStep < stepsTotal) {
      log.info("Step #{}", currentStep);
      final List<BaseCharacter> aliveRed = redTeam.stream().filter(BaseCharacter::isAlive)
          .collect(Collectors.toList());
      final List<BaseCharacter> aliveBlue = blueTeam.stream().filter(BaseCharacter::isAlive)
          .collect(Collectors.toList());

      if (aliveRed.isEmpty() || aliveBlue.isEmpty()) {
        return;
      }

      for (int blueIndex = 0, redIndex = 0;
          blueIndex < aliveBlue.size() && redIndex < aliveRed.size();
          blueIndex++, redIndex++) {

        final BaseCharacter currentRed = aliveRed.get(redIndex);
        currentRed.makeRandomAction(aliveBlue.get(blueIndex), aliveRed.get(redIndex));

        final BaseCharacter currentBlue = aliveBlue.get(blueIndex);
        currentBlue.makeRandomAction(aliveRed.get(redIndex), aliveBlue.get(blueIndex));
      }
      currentStep++;
    }
  }

  private void showStats(String teamName, List<BaseCharacter> team) {
    log.info("Team: {}", teamName);
    team.forEach(BaseCharacter::printStats);
  }

  private void printWinner(String firstTeamName, List<BaseCharacter> firstTeam,
      String secondTeamName, List<BaseCharacter> secondTeam) {

    int firstTeamHealth = firstTeam.stream().mapToInt(BaseCharacter::getCurrentHealth).sum();
    int secondTeamHealth = secondTeam.stream().mapToInt(BaseCharacter::getCurrentHealth).sum();

    String winnerTeamName =
        firstTeamHealth == secondTeamHealth
            ? "No winner"
            : firstTeamHealth > secondTeamHealth
                ? firstTeamName
                : secondTeamName;

    log.info("\n"
            + "Winner: {}\n"
            + " - {} health remained: {}\n"
            + " - {} health remained: {}  ",
        winnerTeamName, firstTeamName, firstTeamHealth, secondTeamName, secondTeamHealth);
  }
}
