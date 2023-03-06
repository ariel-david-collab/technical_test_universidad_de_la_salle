package com.technical_test_universidad_de_la_salle.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObtainController {

    @PostMapping("/search-horandvert")
    public ResponseEntity<SearchResult> searchHorAndVert(@RequestBody SearchRequest request) {
        if (!isValid(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        char[][] matrix = buildMatrix(request.getSearchword(), request.getRows());
        boolean found = search(matrix, "hor-and-vert", request.getWord());

        if (found) {
            int[] pos = findStartPosition(matrix, request.getWord());
            return ResponseEntity.ok(new SearchResult(request.getSearchword(), request.getRows(), request.getWord(),
                    true, pos[0], pos[1]));
        } else {
            return ResponseEntity
                    .ok(new SearchResult(request.getSearchword(), request.getRows(), request.getWord(), false));
        }
    }

    @PostMapping("/search")
    public ResponseEntity<SearchResult> searchDiagonally(@RequestBody SearchRequest request) {
        if (!isValid(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        char[][] matrix = buildMatrix(request.getSearchword(), request.getRows());
        boolean found = search(matrix, "diagonally", request.getWord());

        if (found) {
            int[] pos = findStartPosition(matrix, request.getWord());
            return ResponseEntity.ok(new SearchResult(request.getSearchword(), request.getRows(), request.getWord(),
                    true, pos[0], pos[1]));
        } else {
            return ResponseEntity
                    .ok(new SearchResult(request.getSearchword(), request.getRows(), request.getWord(), false));
        }
    }

    public boolean search(char[][] matrix, String type, String word) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == word.charAt(0)) {
                    if (searchDiagonally(matrix, word, i, j) && type.equals("diagonally")) {
                        return true;
                    }
                    if (searchHorizontally(matrix, word, i, j) && type.equals("hor-and-vert")) {
                        return true;
                    }
                    if (searchVertically(matrix, word, i, j) && type.equals("hor-and-vert")) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean searchDiagonally(char[][] matrix, String word, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int wordLen = word.length();

        // Check diagonal from left to right, top to bottom
        for (int o = 1; o < wordLen; o++) {
            if (row + o >= rows || col + o >= cols || matrix[row + o][col + o] != word.charAt(o)) {
                break;
            }
            if (o == wordLen - 1) {
                return true;
            }
        }

        // Check diagonal from right to left, top to bottom
        for (int o = 1; o < wordLen; o++) {
            if (row + o >= rows || col - o < 0 || matrix[row + o][col - o] != word.charAt(o)) {
                break;
            }
            if (o == wordLen - 1) {
                return true;
            }
        }

        // Check diagonal from left to right, bottom to top
        for (int o = 1; o < wordLen; o++) {
            if (row - o < 0 || col + o >= cols || matrix[row - o][col + o] != word.charAt(o)) {
                break;
            }
            if (o == wordLen - 1) {
                return true;
            }
        }

        // Check diagonal from right to left, bottom to top
        for (int o = 1; o < wordLen; o++) {
            if (row - o < 0 || col - o < 0 || matrix[row - o][col - o] != word.charAt(o)) {
                break;
            }
            if (o == wordLen - 1) {
                return true;
            }
        }

        return false;
    }

    private boolean isValid(SearchRequest request) {
        return (request.getSearchword() != null) &&
                (request.getRows() > 0) &&
                (request.getWord() != null) &&
                ((request.getSearchword().length() % request.getRows()) == 0);
    }

    private char[][] buildMatrix(String searchword, int rows) {
        char[][] matrix = new char[rows][searchword.length() / rows];

        if (searchword.length() % rows != 0) {
            throw new IllegalArgumentException("The searchword length must be a multiple of the number of rows");
        }

        for (int i = 0; i < searchword.length(); i++) {
            int row = i / matrix[0].length;
            int col = i % matrix[0].length;
            matrix[row][col] = searchword.charAt(i);
            System.out.print(matrix[row][col]);
            if (col == matrix[0].length - 1) {
                System.out.println();
            }
        }

        return matrix;
    }

    private int[] findStartPosition(char[][] matrix, String word) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == word.charAt(0)) {
                    if (searchDiagonally(matrix, word, i, j)) {
                        return new int[] { i, j };
                    }
                    if (searchHorizontally(matrix, word, i, j)) {
                        return new int[] { i, j };
                    }
                    if (searchVertically(matrix, word, i, j)) {
                        return new int[] { i, j };
                    }
                }
            }
        }

        return new int[] { -1, -1 };
    }

    private boolean searchHorizontally(char[][] matrix, String word, int row, int col) {
        int cols = matrix[0].length;
        for (int k = 1; k < word.length(); k++) {
            if (col + k >= cols || matrix[row][col + k] != word.charAt(k)) {
                return false;
            }
        }
        return true;
    }

    private boolean searchVertically(char[][] matrix, String word, int row, int col) {
        int rows = matrix.length;
        for (int k = 1; k < word.length(); k++) {
            if (row + k >= rows || matrix[row + k][col] != word.charAt(k)) {
                return false;
            }
        }
        return true;
    }

}
