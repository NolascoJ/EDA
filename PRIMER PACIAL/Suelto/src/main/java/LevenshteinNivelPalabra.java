 import java.util.Arrays;

    public class LevenshteinNivelPalabra {

        public static int levenshtein(String str1, String str2) {
            String[] words1 = str1.split("\\s+");
            String[] words2 = str2.split("\\s+");

            int len1 = words1.length;
            int len2 = words2.length;

            int[][] dp = new int[len1 + 1][len2 + 1];

            for (int i = 0; i <= len1; i++) {
                dp[i][0] = i;
            }

            for (int j = 0; j <= len2; j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (words1[i - 1].equals(words2[j - 1])) {
                        dp[i][j] = dp[i - 1][j - 1]; //no necesito ninguna insercion
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                        //EL MINIMO ENTRE LA DIAGONAL , LA DE ARRIBA Y LA DE LA IZQUIERDA + 1
                    }
                    //    if (words1[i - 1].equals(words2[j - 1])) {
                    //                    // No operation needed, words are the same
                    //                    dp[i][j] = dp[i - 1][j - 1];
                    //                } else {
                    //                    // Calculate cost for insertion, deletion, and substitution
                    //                    int insertionCost = dp[i][j - 1] + 1;    // Insertion
                    //                    int deletionCost = dp[i - 1][j] + 1;     // Deletion
                    //                    int substitutionCost = dp[i - 1][j - 1] + 1; // Substitution
                    //
                    //                    // Choose the operation with the minimum cost
                    //                    dp[i][j] = Math.min(substitutionCost, Math.min(insertionCost, deletionCost));
                    //                }
                }
            }

            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[i].length; j++) {
                    System.out.printf("%4d", dp[i][j]);
                }
                System.out.println();
            }

            return dp[len1][len2];
        }


        public static void main(String[] args) {
            String str1 = "estructura de datos y algoritmos";
            String str2 = "algoritmos y estructura de datos";

            System.out.println("Levenshtein distance between \"" + str1 + "\" and \"" + str2 + "\" is: " + levenshtein(str1, str2));
        }

    }

