package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */
public class SelectionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
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
    int n = values.length;
    // Loop over each position in the array.
    for (int i = 0; i < n - 1; i++) {
      // Assume the minimum element is at the current position.
      int minIndex = i;

      for (int j = i + 1; j < n; j++) {
        if (order.compare(values[j], values[minIndex]) < 0) {
          minIndex = j;
        } // Find the index of the minimum element in the remaining unsorted part.
      } // for loop.

      if (minIndex != i) {
        T temp = values[i];
        values[i] = values[minIndex];
        values[minIndex] = temp;
      } // Swap the found minimum element with the first unsorted element.
    } // for - loop
  } // sort(T[])
} // class SelectionSorter
