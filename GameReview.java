package Task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;

public class GameReview {

    public static void main(String[] args) {
        TreeMap<String, IGNGame> map = new TreeMap<>();
        int total = 0;
        int reviews_platform = 0;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("game-reviews.csv"));
            String line = null;
            String[] splitline = null;
            IGNGame g = null;
            br.readLine();
            while ((line = br.readLine()) != null) {
                splitline = line.split(";");
                total++;

                if (!map.containsKey(splitline[1])) {
                    g = new IGNGame();
                    g.setPlatform(splitline[1]);
                    map.put(g.getPlatform(), g); 
                    reviews_platform++;
                } else {
                    g = map.get(splitline[1]);
                }

                g.getScores().add(Double.valueOf(splitline[2]));

                if (splitline[2].equalsIgnoreCase("Great")) {
                    g.setGreatPct(g.getGreatPct() + 1);
                }

                if (linhaSplit[1].equals("Masterpiece") || linhaSplit[1].equals("masterpiece")) {
                    g.setMasterpiece(g.getMasterpiece() + 1);
                }

                if (linhaSplit[6].equals("Action")) {
                    g.setNintendo(g.getNintendo() + 1);
                    g.getScoresNintendo().add(Double.valueOf(linhaSplit[5]));
                }

                if (g.getBestGame() == null || g.getBestGameScore() < Double.valueOf(linhaSplit[5])) {
                    g.setBestGame(linhaSplit[2]);
                    g.setBestGameScore(Double.valueOf(linhaSplit[5]));
                }

                if (g.getWorstGame() == null || g.getWorstGameScore() > Double.valueOf(linhaSplit[5])) {
                    g.setWorstGame(linhaSplit[2]);
                    g.setWorstGameScore(Double.valueOf(linhaSplit[5]));
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String platform = null;
        double console = 0;
        double median = 0.0;

        for (String k : map.keySet()) {
            System.out.println("################### " + k + " ###################");
            System.out.println("Reviews: " + map.get(k).getReviews());
            System.out.println("'Great' reviews percentage: " + map.get(k).getGreatPct());
            System.out.println("'Amazing' review percentage: " + String.format("%.2f", map.get(k).getAmazingPercentage()));

            System.out.println("Mean: " + String.format("%.2f", map.get(k).getMedia()));
            System.out.println("Standard deviation: " + String.format("%.2f", map.get(k).getDesvioPadrao()));
            System.out.println("Best game: " + map.get(k).getBestGame());
            System.out.println("Worst game: " + map.get(k).getWorstGame());
            System.out.println("");

            if (platform == "null" || console < map.get(k).getNintendo()) {
                if (map.get(k).getMedia2() > console) {
                    platform = k;
                    console = map.get(k).getMedia2();
                    median = map.get(k).getMedian(total);
                }
            }

        }
        System.out.println("##################################################################################");
        System.out.println("Most common genre in Nintendo console family: " + platform);
        System.out.println("Median from all scores: " + median);
        System.out.println("##################################################################################");
    }
}
