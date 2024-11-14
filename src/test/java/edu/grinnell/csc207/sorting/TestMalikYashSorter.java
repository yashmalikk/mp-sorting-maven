package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Tests of our SelectionSorter.
 */
public class TestMalikYashSorter extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new MalikYashSorter<String>((x,y) -> x.compareTo(y));
    intSorter = new MalikYashSorter<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestSelectionSorter
