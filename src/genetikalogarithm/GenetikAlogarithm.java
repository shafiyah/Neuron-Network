package genetikalogarithm;
import java.util.Random;
import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public final class GenetikAlogarithm {
    Random rn = new Random();
    int[] target;
    public ArrayList<Individual> newIndividu;
    public ArrayList<Individual> parent;
    public ArrayList<Individual> rollet;
    public ArrayList<Individual> offspring;
    public ArrayList<Individual> elitsm;
    public int generation =0;
    public int individuSize=0;
    public int generationSize=0;
    double[][] newGen ;

    public GenetikAlogarithm(int[] target,int individuSize,int generationSize) {
        this.target = target;
        this.individuSize=individuSize;
        this.generationSize=individuSize;
        this.newIndividu= new ArrayList<>();
        this.parent= new ArrayList<>();
        setListData(newIndividu);
        System.out.println("---- random number ----");
        printListData(newIndividu);
        copyData(newIndividu,parent);
    }
    
    public void setListData(ArrayList<Individual> data){
       for(int i=0;i<individuSize;i++){
          data.add(new Individual(target));
       }
    }
    public void printListData(ArrayList<Individual> data){
        for (int i = 0; i < data.size(); i++) {
            data.get(i).printGen();  
        } 
    }
    public void setFitnesIndividu(ArrayList<Individual> data){
        for (int i = 0; i < data.size(); i++) {
            data.get(i).calculateFitness();  
        } 
    }
    
    public void rollet(){
       int [] batas= new int [individuSize];
       int count=0;
        for (int i = 0; i < individuSize; i++) {
            count+=newIndividu.get(i).getFitness();
            batas[i]=count;
            //System.out.println(batas[k]);
        }
        
        for (int i = 0; i < individuSize; i++) {
            int nilai =rn.nextInt((count- 1)+ 1) + 1;
            for (int j = 0; j < batas.length; j++) {
              if(nilai < batas[j]){
                  Individual individu = new Individual(target);
                  for (int k = 0; k < target.length; k++) {
                     individu.setGen(k,newIndividu.get(j).getGen(k));
                  }
                  individu.calculateFitness();
                  parent.set(i, individu);
                 break;
              }
            }
        }
         System.out.println("---- parent ----");
         printListData(parent);
       
     }
    
    
    public void copyData(ArrayList<Individual> data,ArrayList<Individual> tujuan){
        for (int i = 0; i < data.size(); i++) {
           Individual individu = new Individual(target);
            for (int j = 0; j < target.length; j++) {
              individu.setGen(j, data.get(i).getGen(j));
            }
            individu.calculateFitness();
            tujuan.add(individu);
        }
    }
    
    public void crossOver(){
        this.rollet= new ArrayList<>();
        copyData(parent,rollet);
        double probCo= 0.9;
        double prob=  0.0;
        for(int i=0;i<individuSize;i+=2){
            prob=rn.nextDouble()*1.0;
             // System.out.println(prob);
             int j=i+1;
            if(probCo>prob){
                int btsKiri=rn.nextInt((target.length-2));
               // System.out.println("batas kiri "+btsKiri);
                int btsKanan=rn.nextInt(target.length-btsKiri-1)+(btsKiri+1);
                //System.out.println("batas kanan "+btsKanan);
                Individual individu1 = rollet.get(i);
                Individual individu2 = rollet.get(j);
                    for(int k=btsKanan;k<=btsKiri;k++){
                        int tmp1=rollet.get(i).getGen(k);
                        int tmp2=rollet.get(j).getGen(k);
                        individu1.setGen(k, tmp2);
                        individu1.calculateFitness();
                        individu2.setGen(k, tmp1);
                        individu2.calculateFitness();
                    }
                rollet.set(i, individu2);
                rollet.set(j,individu1);
            } 
        }
        System.out.println("---- Cross Over ----");
        printListData(rollet);
    } 
    
    public void mutation(){
       this.offspring= new ArrayList<>();
       copyData(rollet,offspring);
       double probMut= 0.9;
       double prob=0.0;
        for (int i = 0; i <individuSize ; i++) {
         prob=rn.nextDouble()*1.0;  
            if(probMut>prob){
                Individual individu = offspring.get(i);
                int rendom=rn.nextInt(((target.length-1)-0)+1);
               // System.out.println(rendom);
                int value =offspring.get(i).getGen(rendom); 
                boolean nilai = rn.nextBoolean();
                if(nilai==true){
                   individu.setGen(rendom, value+1);
                }else{
                   individu.setGen(rendom, value-1);
                }
                if(offspring.get(i).getGen(rendom)==0){
                   individu.setGen(rendom, 1);                
                }else if(offspring.get(i).getGen(rendom)>26){
                   individu.setGen(rendom, 25);
                }
                individu.calculateFitness();
                offspring.set(i, individu);
            }
       }
        System.out.println("---- Mutation ----");
        setFitnesIndividu(offspring);
        printListData(offspring);
    }
    
    public void optimationElitsm(){
        this.elitsm= new ArrayList<>();
         for (int i = 0; i < individuSize; i++) {
                Individual individu = parent.get(i);
                elitsm.add(individu); 
        }
         for (int i = 0; i < individuSize ; i++) {
                 Individual individu = offspring.get(i);
                elitsm.add(individu); 
        }
        System.out.println("---- Elitism Sebelum ----");
        printListData(elitsm);
        
        Collections.sort(elitsm, Individual.fitnessComparator);
        
        System.out.println("---- Elitism Sebelum ----");
        printListData(elitsm);
        
    }
    
    public void updateGeneration(){
        for (int i = 0; i < newIndividu.size(); i++) {
            Individual get = elitsm.get(i);
            newIndividu.set(i, get);   
        }
    }
    
    public void convertToString(){
        parent.get(0).convertString();
    }
    
    public void test(){
         rollet();
         crossOver();
         mutation();
         optimationElitsm();
         updateGeneration();
    }
    
}
