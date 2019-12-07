package Or;

import And.*;
import java.util.ArrayList;
import java.util.List;

public class Perceptron {
   
    public List datas;
    public double[] weight;
    public int Threshold;

    public Perceptron() {
        this.datas = new ArrayList();
        this.weight = new double[4];
        this.Threshold = 0;
    }

    public List getDatas() {
        return this.datas;
    }
    
     public Data getData(int i) {
        return (Data) this.datas.get(i);
    }
    
    public void addData(Data data){
        this.datas.add(data);
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }
    
    public double getWeight(int index) {
        return this.weight[index];
    }

    public double[] getWeight() {
        return this.weight;
    }

    public void setWeight(double[] weight) {
        this.weight = weight;
    }
    
     public void setWeight(int idx, double weight) {
        this.weight[idx] = weight;
    }
     
    public void printWeight() {
        for(int i=0;i<4;i++){
            System.out.println(this.weight[i]);
        }
    }

    public int getThreshold() {
        return Threshold;
    }

    public void setThreshold(int Threshold) {
        this.Threshold = Threshold;
    }
    
    public double getOutput(Data data){
     double output =(data.xB*this.weight[0])+(data.x1*this.weight[1])+(data.x2*this.weight[2]);
        return output;
    }
    
}

