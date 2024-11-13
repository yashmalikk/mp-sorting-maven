package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */
public class InsertionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------

  /**
   * Sort an array in place using insertion sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    // Loop through each element starting from index 1.
    for (int i = 1; i < values.length; i++) {
      T current = values[i]; // Store the element to be inserted.
      int j = i - 1; // Start comparing with the element just before it.

      // Shift elements of values[0..i-1] that are greater than current.
      while (j >= 0 && order.compare(values[j], current) > 0) {
        values[j + 1] = values[j]; // Shift the larger element to the right.
        j--; // Move the index leftward.
      }

      // Insert the current element into its correct position.
      values[j + 1] = current;
    } // for-loop over the whole array.
  } // sort(T[])
} // class InsertionSorter
