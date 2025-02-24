package com.lima.study_alone.codingtest.nexton_20250112;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
  Implement a prototype image processing service.

  There is a black and white image represented as an n*n binary matrix.
  A 0 represents a white pixel, 1 represents a black pixel, and there are three asscociated integers:
  rotation, vertical_flip, and horizontal_flip.

  Threre are as many as three operations to perform on the image in the following order.

  1. Rotate the image by 90, 180, or 270 degrees clockwise as indicated by the variable rotation.
  2. Flip the image vertically if vertical_flip=1. Flipping the image vertically means that the mioor
  image with the mirror placed in a horizontal direction should be returned.
  3. Flip the image horizontally if horizontal_flip=1. Flipping the image horizontally means that the
  mirror image with the mirror placed in a vertical direction should be returned.
 */
public class Solution2 {
  private static final int FLIP_ENABLED = 1;

  public static List<List<Integer>> transformImage(List<List<Integer>> image, int rotation, int verticalFlip, int horizontalFlip) {
    List<List<Integer>> result = deepCopyImage(image);

    // Handle rotation
    if (rotation != 0) {
      result = rotateImage(result, rotation);
    }
    // Handle vertical flip
    if (verticalFlip == FLIP_ENABLED) {
      result = flipVertically(result);
    }
    // Handle horizontal flip
    if (horizontalFlip == FLIP_ENABLED) {
      result = flipHorizontally(result);
    }
    return result;
  }

  private static List<List<Integer>> deepCopyImage(List<List<Integer>> image) {
    List<List<Integer>> copy = new ArrayList<>();
    for (List<Integer> row : image) {
      copy.add(new ArrayList<>(row));
    }
    return copy;
  }

  private static List<List<Integer>> flipVertically(List<List<Integer>> image) {
    List<List<Integer>> flipped = new ArrayList<>();
    for (int i = image.size() - 1; i >= 0; i--) {
      flipped.add(new ArrayList<>(image.get(i)));
    }
    return flipped;
  }

  private static List<List<Integer>> flipHorizontally(List<List<Integer>> image) {
    List<List<Integer>> flipped = new ArrayList<>();
    for (List<Integer> row : image) {
      List<Integer> newRow = new ArrayList<>();
      for (int i = row.size() - 1; i >= 0; i--) {
        newRow.add(row.get(i));
      }
      flipped.add(newRow);
    }
    return flipped;
  }
  /*
  The main getFinalImage method processes the operations in the specified order:
  First rotation
  Then vertical flip
  Finally horizontal flip
   */
//  public static List<List<Integer>> getFinalImage(List<List<Integer>> image, int rotation,int vertical_flip, int horizontal_flip) {
//    int n = image.size();
//    List<List<Integer>> result = new ArrayList<>();
//
//    // Deep copy the image
//    for (List<Integer> row : image) {
//      result.add(new ArrayList<>(row));
//    }
//
//    // Step 1: Handle rotation
//    if (rotation != 0) {
//      result = rotateImage(result, rotation);
//    }
//
//    // Step 2: Handle vertical flip
//    if (vertical_flip == 1) {
//      result = flipVertically(result);
//    }
//
//    // Step 3: Handle horizontal flip
//    if (horizontal_flip == 1) {
//      result = flipHorizontally(result);
//    }
//
//    return result;
//  }
//  /*
//  The rotateImage method:
//  Handles 90, 180, and 270 degree rotations
//  Uses different mapping formulas for each rotation angle
//  Creates a new matrix for the rotated image
//   */
//
  private static List<List<Integer>> rotateImage(List<List<Integer>> image, int degrees) {
    int n = image.size();
    List<List<Integer>> rotated = new ArrayList<>();

    // Initialize the rotated matrix
    for (int i = 0; i < n; i++) {
      rotated.add(new ArrayList<>(Collections.nCopies(n, 0)));
    }

    // Calculate number of 90-degree rotations needed
    int rotations = (degrees / 90) % 4;

    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        switch (rotations) {
          case 1: // 90 degrees
            rotated.get(c).set(n - 1 - r, image.get(r).get(c));
            break;
          case 2: // 180 degrees
            rotated.get(n - 1 - r).set(n - 1 - c, image.get(r).get(c));
            break;
          case 3: // 270 degrees
            rotated.get(n - 1 - c).set(r, image.get(r).get(c));
            break;
        }
      }
    }

    return rotations == 0 ? image : rotated;
  }
//
//  /*
//  The flipVertically method:
//  Reverses the order of rows in the matrix
//  Creates a deep copy to avoid modifying the input
//   */
//  private static List<List<Integer>> flipVertically(List<List<Integer>> image) {
//    int n = image.size();
//    List<List<Integer>> flipped = new ArrayList<>();
//
//    // Create a deep copy and flip vertically
//    for (int i = n - 1; i >= 0; i--) {
//      flipped.add(new ArrayList<>(image.get(i)));
//    }
//
//    return flipped;
//  }
//
//  /*
//  The flipHorizontally method:
//  Reverses each row in the matrix
//  Creates a deep copy to avoid modifying the input
//   */
//  private static List<List<Integer>> flipHorizontally(List<List<Integer>> image) {
//    int n = image.size();
//    List<List<Integer>> flipped = new ArrayList<>();
//
//    // Create a deep copy and flip horizontally
//    for (List<Integer> row : image) {
//      List<Integer> newRow = new ArrayList<>();
//      for (int i = n - 1; i >= 0; i--) {
//        newRow.add(row.get(i));
//      }
//      flipped.add(newRow);
//    }
//
//    return flipped;
//  }


// input
  /*
    2
    2
    1 0
    0 1
    90
    1
    0
   */
  public static void main(String[] args) {

  }
}
