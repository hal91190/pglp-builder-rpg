package fr.uvsq.hal.pglp.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.uvsq.hal.pglp.rpg.Ability.Dexterity;
import static fr.uvsq.hal.pglp.rpg.Ability.Strength;
import static fr.uvsq.hal.pglp.rpg.Skill.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {
  private Character frodon;

  @BeforeEach
  public void setup() {
    Dice.setSeed(1L);

    frodon = new CharacterBuilder("Frodon")
      .nonRamdomAbilities(Ability.values())
      .isProficientIn(Acrobatics, History, Medicine)
      .build();
  }

  @Test
  public void aCharacterShouldHaveACorrectSkillProficiency() {
    assertEquals(frodon.get(Strength).getModifier(), frodon.getProficiencyBonusIn(Athletics));
    assertEquals(frodon.get(Dexterity).getModifier() + frodon.getProficiencyBonus(), frodon.getProficiencyBonusIn(Acrobatics));
  }
}
