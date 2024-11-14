package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */
public class Quicksorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  /** Random number generator for selecting pivot indices. */
  private Random rand = new Random();

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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
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
    quicksort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * A recursive helper for the quicksort algorithm.
   *
   * @param values
   *   The array to sort.
   * @param low
   *   The starting index of the portion to sort.
   * @param high
   *   The ending index of the portion to sort.
   */
  private void quicksort(T[] values, int low, int high) {
    if (low < high) {
      // Partition the array and get the pivot index.
      int pivotIndex = partition(values, low, high);

      // Recursively sort elements before and after partition.
      quicksort(values, low, pivotIndex - 1);
      quicksort(values, pivotIndex + 1, high);
    } // check valid input
  } // quicksort(T[], int, int)

  /**
   * Partition the array around a pivot selected randomly from the range [low, high].
   *
   * @param values
   *   The array to partition.
   * @param low
   *   The starting index for partitioning.
   * @param high
   *   The ending index for partitioning.
   * @return
   *   The index of the pivot after partitioning.
   */
  private int partition(T[] values, int low, int high) {
    // Select a random pivot index.
    int pivotIndex = low + rand.nextInt(high - low + 1);
    T pivotValue = values[pivotIndex];

    // Move the pivot element to the end.
    swap(values, pivotIndex, high);
    int storeIndex = low;

    // Rearrange elements based on pivot value.
    for (int i = low; i < high; i++) {
      if (order.compare(values[i], pivotValue) < 0) {
        swap(values, i, storeIndex);
        storeIndex++;
      } // if statement
    } // for-loop

    // Place the pivot in its final position.
    swap(values, storeIndex, high);
    return storeIndex;
  } // partition(T[], int, int)

  /**
   * Swap two elements in the array.
   *
   * @param values
   *   The array in which elements will be swapped.
   * @param i
   *   The index of the first element.
   * @param j
   *   The index of the second element.
   */
  private void swap(T[] values, int i, int j) {
    T temp = values[i];
    values[i] = values[j];
    values[j] = temp;
  } // swap(T[], int, int)
} // class Quicksorter
