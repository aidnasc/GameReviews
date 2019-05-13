package ign;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeMap;

public class GameReview {

    public static void main(String[] args) {
        TreeMap<String, IGNGame> map = new TreeMap<>();
        String platform = null;
        double bestRacingScore = 0.0;
        ArrayList<Double> racingScores = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Task2/game-reviews.csv"));
            String line = null;
            String[] splitline = null;
            IGNGame g = null;
            br.readLine();
            while ((line = br.readLine()) != null) {
                splitline = line.split(";");

                if (!map.containsKey(splitline[1])) {
                    g = new IGNGame();
                    g.setPlatform(splitline[1]);
                    map.put(g.getPlatform(), g); 
                } else {
                    g = map.get(splitline[1]);
                }

                g.getScores().add(Double.valueOf(splitline[3]));

                if (splitline[2].equalsIgnoreCase("Great")) {
                    g.setGreatTotal(g.getGreatTotal() + 1);
                }

                if (splitline[4].equals("Racing")) {
                    g.setRacing(g.getRacing() + 1);
                    g.getRacingScores().add(Double.valueOf(splitline[3]));
                }

                if (g.getBestGame() == null || g.getBestScore() < Double.valueOf(splitline[3])) {
                    g.setBestGame(splitline[0]);
                    g.setBestScore(Double.valueOf(splitline[3]));
                }

                if (g.getWorstGame() == null || g.getWorstScore() > Double.valueOf(splitline[3])) {
                    g.setWorstGame(splitline[0]);
                    g.setWorstScore(Double.valueOf(splitline[3]));
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String k : map.keySet()) {
            System.out.println("PLATAFORMA: " + k);
            System.out.println("Reviews: " + map.get(k).getReviews());
            System.out.println("Porcentagem de reviews 'Great': " + String.format("%.2f", map.get(k).getGreatPct()) + "%");
            System.out.println("Média de scores: " + String.format("%.2f", map.get(k).getAvgScores()));
            System.out.println("Desvio padrão populacional: " + String.format("%.2f", map.get(k).getStdDeviation()));
            System.out.println("Melhor jogo: " + map.get(k).getBestGame());
            System.out.println("Pior jogo: " + map.get(k).getWorstGame());
            System.out.println();

            racingScores.add(map.get(k).getAvgRacingScores()); 
            
            for (int i = 0; i < racingScores.size(); i++) {
                if (racingScores.get(i) > bestRacingScore) {
                    bestRacingScore = racingScores.get(i);
                    platform = k;
                } 	 
            }
        }
        
        System.out.println("Plataforma com jogos de genêro 'Racing' mais bem avaliados: " + platform);
    }
}