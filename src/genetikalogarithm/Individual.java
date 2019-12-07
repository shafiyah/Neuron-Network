  package genetikalogarithm;

import java.util.Random;
import java.lang.*;
import java.util.Comparator;
public class Individual {
    int fitness=0;
    int [] gen;
    int genLengh;
    int [] target;
  
    public Individual(int[] target) {
        this.fitness=0;
        this.target=target;
        this.genLengh=target.length;
        this.gen=new int [this.genLengh];
         Random rn = new Random();
        for(int i=0;i<this.target.length;i++){
          this.gen[i]=rn.nextInt((26 - 1 )+ 1) + 1;
        }
        calculateFitness();  
    }
    
    public static Comparator<Individual> fitnessComparator = new Comparator<Individual>(){
        @Override
        public int compare(Individual o1, Individual o2) {
            int fitnesno1 = o1.getFitness();
	    int fitnesno2 = o2.getFitness();
            
            return fitnesno2-fitnesno1;
            
        }
    };
    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int[] getTarget() {
        return target;
    }

    public void setTarget(int[] target) {
        this.target = target;
    }

    public float getFitnesss() {
        return this.fitness;
    }

    public void setFitnesss(int fitness) {
        this.fitness = fitness;
    }
    

    public int[] getGen() {
        return this.gen;
    }

    public void setGen(int[] gen) {
        this.gen = gen;
    }
    
    
    public int getGen(int i) {
        return this.gen[i];
    }

    public void setGen(int i , int value) {
        this.gen[i] = value;
    }

    public int getGenLengh() {
        return genLengh;
    }

    public void setGenLengh(int genLengh) {
        this.genLengh = genLengh;
    }
    
    public void printGen() {
        for(int i=0;i<target.length;i++){
           System.out.print(this.gen[i]+" ");
        }
       System.out.print(" Fitnes = "+getFitness());
       System.out.println("");
    }
    
    public void calculateFitness(){
        int coun=0;
        for(int i=0;i< this.genLengh;i++){
          coun+=Math.abs(target[i]-this.gen[i]);
        }
        this.fitness=((26*genLengh)-coun);
        //System.out.println(this.fitness);
    }
    
    
   public void convertString(){  
      char[] huruf = {' ','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
      for(int i=0;i<this.gen.length;i++){
          System.out.print(huruf[gen[i]]);
      }
     System.out.println("");
   }
    
}
