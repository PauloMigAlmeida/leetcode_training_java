package mock.microsoft;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean exist(char[][] board, String word) {
        char c = word.charAt(0);
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == c) {
                    Set<String> visited = new HashSet<>();
                    StringBuilder stringBuilder = dfs("", board, word, 0, row, col, visited);
                    if (stringBuilder != null && word.equals(stringBuilder.toString()))
                        return true;
                }
            }
        }

        return false;

    }

    private StringBuilder dfs(String currentContent,
                              char[][] board,
                              String word,
                              int wordI,
                              int row, int col, Set<String> visited) {
        String key = String.format("%d-%d", row, col);
        if (visited.contains(key) ||
                row < 0 || col < 0 ||
                row > board.length - 1 || col > board[0].length - 1 ||
                wordI > word.length() - 1) {
            return null;
        }

        char c = word.charAt(wordI);
        if (board[row][col] == c) {
            visited.add(key);

            StringBuilder sb = new StringBuilder(currentContent);
            sb.append(c);
            String newContent = sb.toString();
            if (word.equals(newContent))
                return sb;

            wordI += 1;
            //up
            StringBuilder tmp = dfs(newContent, board, word, wordI, row - 1, col, visited);
            if (tmp != null && word.equals(tmp.toString()))
                return tmp;
            //down
            tmp = dfs(newContent, board, word, wordI, row + 1, col, visited);
            if (tmp != null && word.equals(tmp.toString()))
                return tmp;
            //left
            tmp = dfs(newContent, board, word, wordI, row, col - 1, visited);
            if (tmp != null && word.equals(tmp.toString()))
                return tmp;
            //right
            tmp = dfs(newContent, board, word, wordI, row, col + 1, visited);
            if (tmp != null && word.equals(tmp.toString()))
                return tmp;
        }
        return null;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().exist(new char[][]{
//                        {'A', 'B', 'C', 'E'},
//                        {'S', 'F', 'C', 'S'},
//                        {'A', 'D', 'E', 'E'}
//                },
//                "ABCCED"));
//
//        System.out.println(new Solution().exist(new char[][]{
//                        {'a', 'b'},
//                        {'c', 'd'},
//
//                },
//                "cdba"));
//
//        System.out.println(new Solution().exist(new char[][]{
//                        {'C', 'A', 'A'},
//                        {'A', 'A', 'A'},
//                        {'B', 'C', 'D'},
//
//                },
//                "AAB"));
//
//        System.out.println(new Solution().exist(new char[][]{
//                        {'C', 'A', 'A'},
//                        {'A', 'A', 'A'},
//                        {'B', 'C', 'D'},
//
//                },
//                "AAB"));

        System.out.println(new Solution().exist(new char[][]{
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'E', 'S'},
                        {'A', 'D', 'E', 'E'}

                },
                "ABCESEEEFS"));


    }
}
