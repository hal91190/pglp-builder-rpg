package fr.uvsq.hal.pglp.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.uvsq.hal.pglp.rpg.Dice.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiceGroupTest {
  @BeforeEach
  public void setup() {
    Dice.setSeed(1L);
  }

  @Test
  public void aDiceGroupShouldReturnCorrectValues() {
    DiceGroup twoD20 = new DiceGroup.Builder(2, d20).build();
    int[] values = twoD20.roll();
    assertEquals(2, values[0]);
    assertEquals(4, values[1]);
  }

  @Test
  public void aDiceGroupWithZeroDiceShouldFail() {
    Exception exception = assertThrows(
      IllegalArgumentException.class,
      () -> new DiceGroup.Builder(0, d20).build());
    assertEquals("The number of dices have to be strictly positive.", exception.getMessage());
  }

  @Test
  public void aMoreComplicatedDiceGroupShouldReturnCorrectValues() {
    DiceGroup diceGroup = new DiceGroup.Builder(2, d20)
      .add(3, d4)
      .add(4, d6)
      .add(1, d12)
      .build();
    int[] values = diceGroup.roll();
    assertEquals(10, values.length);
    assertEquals(3, values[0]);
    assertEquals(3, values[1]);
    assertEquals(1, values[2]);
    assertEquals(2, values[3]);
    assertEquals(1, values[4]);
    assertEquals(2, values[5]);
    assertEquals(5, values[6]);
    assertEquals(4, values[7]);
    assertEquals(17, values[8]);
    assertEquals(8, values[9]);
  }

  @Test
  public void operationsCanBePerformedOnDiceGroups() {
    DiceGroup twoD20 = new DiceGroup.Builder(2, d20).build();
    int value = twoD20.rollnSum();
    assertEquals(6, value);
    value = twoD20.rollnMin();
    assertEquals(9, value);
    value = twoD20.rollnMax();
    assertEquals(3, value);
    value = twoD20.rollnApply(Integer::sum);
    assertEquals(29, value);
    value = twoD20.rollnApply(Integer::min);
    assertEquals(8, value);
    value = twoD20.rollnApply(Integer::max);
    assertEquals(15, value);
  }
}
