package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *            The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
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
   *                   The order in which elements in the array should be ordered
   *                   after sorting.
   */
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values
   *               an array to sort.
   *
   * @post
   *       The array has been sorted according to some order (often
   *       one given to the constructor).
   * @post
   *       For all i, 0 &lt; i &lt; values.length,
   *       order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    if (values.length > 1) {
      // Split the array into two halves.
      int mid = values.length / 2;
      T[] left = Arrays.copyOfRange(values, 0, mid);
      T[] right = Arrays.copyOfRange(values, mid, values.length);

      // Sort each half recursively.
      sort(left);
      sort(right);

      // Merge the sorted halves back into the original array.
      merge(values, left, right);
    } // only works on valid input.
  } // sort(T[])

  /**
   * Merge two sorted arrays into a single sorted array.
   *
   * @param result
   *               The destination array for the merged result.
   * @param left
   *               The first sorted subarray.
   * @param right
   *               The second sorted subarray.
   *
   * @post
   *       The result array is a sorted combination of the left and right arrays.
   */
  private void merge(T[] result, T[] left, T[] right) {
    int i = 0;
    int j = 0;
    int k = 0;

    // Merge elements from left and right arrays in sorted order.
    while (i < left.length && j < right.length) {
      if (order.compare(left[i], right[j]) <= 0) {
        result[k++] = left[i++];
      } else {
        result[k++] = right[j++];
      } // if loop
    } // while loop

    // Copy any remaining elements from left array.
    while (i < left.length) {
      result[k++] = left[i++];
    } // while loop

    // Copy any remaining elements from right array.
    while (j < right.length) {
      result[k++] = right[j++];
    } // while loop
  } // merge(T[], T[], T[])
} // class MergeSorter
