package ign;

import java.util.ArrayList;

public class IGNGame {

    private String platform;
    private double greatTotal;
    private double bestScore;
    private String bestGame;
    private double worstScore;
    private String worstGame;
    private int racing;
    private ArrayList<Double> scores = new ArrayList<>();;
    private ArrayList<Double> racingScores = new ArrayList<>();

    public int getReviews() {
        return getScores().size();
    }
    
    public double getGreatPct() {
        return (((double) greatTotal) / getReviews()) * 100;
    }
    
    public double getAvgScores() {
        double total = 0.0;
        for (Double s : getScores()) {
            total = total + s;
        }
        return total / getReviews();
    }
    
    public double getStdDeviation() {
        double avg = getAvgScores();
        double var = 0.0;
        for (Double s : getScores()) {
            var = var + Math.pow(s - avg, 2.0);
        }
        var = var / getReviews();
        return Math.sqrt(var);
    }
    
    public double getAvgRacingScores() {
        double totalScore = 0.0;
        
        for (int i = 0; i < this.racingScores.size(); i++) {
            totalScore = totalScore + this.racingScores.get(i);
        }
        
        return totalScore / this.racingScores.size();
    }
    
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public double getGreatTotal() {
        return greatTotal;
    }

    public void setGreatTotal(double greatTotal) {
        this.greatTotal = greatTotal;
    }

    public double getBestScore() {
        return bestScore;
    }

    public void setBestScore(double bestScore) {
        this.bestScore = bestScore;
    }

    public String getBestGame() {
        return bestGame;
    }

    public void setBestGame(String bestGame) {
        this.bestGame = bestGame;
    }

    public double getWorstScore() {
        return worstScore;
    }

    public void setWorstScore(double worstScore) {
        this.worstScore = worstScore;
    }

    public String getWorstGame() {
        return worstGame;
    }

    public void setWorstGame(String worstGame) {
        this.worstGame = worstGame;
    }

    public int getRacing() {
        return racing;
    }

    public void setRacing(int racing) {
        this.racing = racing;
    }

    public ArrayList<Double> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Double> scores) {
        this.scores = scores;
    }

    public ArrayList<Double> getRacingScores() {
        return racingScores;
    }

    public void setRacingScores(ArrayList<Double> racingScores) {
        this.racingScores = racingScores;
    }
    
}