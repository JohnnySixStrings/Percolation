

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats{
    private double[] runs;
    private int sizeOfArray;


 public PercolationStats(int size, int count){
        if (size <= 0 || count <= 0) {
            throw new IllegalArgumentException("Given N <= 0 || T <= 0");
        }
        this.runs = new double[count];
        this.sizeOfArray = size*size;


        for (int i = 0; i <count ; i++) {
            Percolation percolation = new Percolation(size);
            while (!percolation.percolates()){
                int x = StdRandom.uniform(size);
                int y = StdRandom.uniform(size);
                percolation.open(y,x);
            }
            int openCount=0;
            for (int j = 0; j <size ; j++) {
                for (int k = 0; k <size; k++) {
                    if(percolation.isOpen(i,j)){
                        openCount++;
                    }
                }
            }
            runs[i]=(double) openCount/sizeOfArray;
        }
    }


    public double mean(){
        return StdStats.mean(runs);
    }
    public double standardDeviation(){
        return StdStats.stddev(runs);
    }
    public double confidenceLow(){
        return mean() - ((1.96* standardDeviation())/Math.sqrt(sizeOfArray));
    }
    public double confidenceHigh(){
        return mean() + ((1.96* standardDeviation())/Math.sqrt(sizeOfArray));
    }
    @Override
    public String toString(){
     return "Mean: "+mean()+"\nStandard Dev: "+ standardDeviation()+"\nConfidence High and Low: "+confidenceHigh()+" "+confidenceLow();
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200,100);
        System.out.println( percolationStats.toString());
    }
}

