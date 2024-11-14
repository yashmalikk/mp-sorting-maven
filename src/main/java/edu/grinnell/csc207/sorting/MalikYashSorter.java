package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * A sorter that dynamically selects between different sorting algorithms
 * based on the initial order of the array elements.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author
 *   Yash Malik
 */

public class MalikYashSorter<T> implements Sorter<T> {
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
  public MalikYashSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MalikYashSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using a dynamically selected algorithm.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to the specified order.
   */
  @Override
  public void sort(T[] values) {
    int sortedCount = countRelativelySorted(values);
    double sortedRatio = (double) sortedCount / (values.length - 1);

    if (sortedRatio > 0.75) {
      // Use Insertion Sort if most elements are sorted
      InsertionSorter<T> insertionSorter = new InsertionSorter<>(order);
      insertionSorter.sort(values);
    } else if (sortedRatio < 0.25) {
      // Use Quicksort if most elements are unsorted
      Quicksorter<T> quicksorter = new Quicksorter<>(order);
      quicksorter.sort(values);
    } else {
      // Use Merge Sort for mixed or moderately sorted arrays
      MergeSorter<T> mergeSorter = new MergeSorter<>(order);
      mergeSorter.sort(values);
    } // decide what sorting algorithm to use.
  } // sort(T[])

  /**
   * Count the number of relatively sorted pairs in the array.
   *
   * @param values
   *   an array to check.
   * @return
   *   the count of relatively sorted pairs.
   */
  private int countRelativelySorted(T[] values) {
    int count = 0;
    for (int i = 0; i < values.length - 1; i++) {
      if (order.compare(values[i], values[i + 1]) <= 0) {
        count++;
      } // check relative sortedness
    } // for loop
    return count;
  } // countRelativelySorted(T[])
} // class MalikYashSorter
