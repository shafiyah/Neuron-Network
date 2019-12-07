package And;

import java.util.Random;

public class And {
     Perceptron perceptron = new Perceptron();
     int epoch = 1;
    
    public void settingData(){
      Random r = new Random();
      perceptron.addData(new Data(0,0,0));
      perceptron.addData(new Data(0,1,0));
      perceptron.addData(new Data(1,0,0));
      perceptron.addData(new Data(1,1,1));
      perceptron.setThreshold(0);
      for(int i=0;i<3;i++){
         perceptron.setWeight(i,((-1)+(1 - (-1))) * r.nextDouble());
      }
    }
    
    public void learningData(){
    double error=99;
    double output;
    boolean errorFlag = true;
    while(errorFlag){
        printHeading();
         errorFlag = false;
        for(int i=0;i<4;i++){
            double count = perceptron.getOutput(perceptron.getData(i));
            if(count < perceptron.getThreshold()){
                output=0;
            }else{
                output=1;
            }
            error=perceptron.getData(i).getTarget()-output;
            printVector(perceptron.getData(i),perceptron.getWeight(),output,error);
            if(error<0.0 || error >=1.0){
                updateWeight(error,perceptron.getData(i));
                errorFlag = true;
            } 
        }
        epoch++;
    }
   }
    
    public void updateWeight(double error , Data data) {
      double coun =0;
      double miu =0.1;
      for(int i=0;i<3;i++){
        if(i==0){
            coun=perceptron.getWeight(i)+(miu*data.getxB()*error);
        }else if(i==1){
            coun=perceptron.getWeight(i)+(miu*data.getX1()*error);
        }else{ 
            coun=perceptron.getWeight(i)+(miu*data.getX2()*error);}
        perceptron.setWeight(i, coun);
      }
   } 
    
   public void printHeading(){
        System.out.println("\n============================Epoch #"+epoch+"=============================");
        System.out.println("bias|  x1 |  x2 |  Y  |   w1    |    w2  |   w3   |output |  error ");
        System.out.println("==================================================================");  
    }
    public void printVector( Data data, double[]weights, double output, double error){
        System.out.println(" "+data.getxB()+"  |  "+ data.getX1()+"  |  "+ data.getX2()+"  |  "
        +data.getTarget() +"  |  "+String.format("%.2f",weights[0] )+"  |  "
        +String.format("%.2f",weights[1])+"  |  "+String.format("%.2f",weights[2])+"  |  "
        +output+"  |  "+error);
}
    public static void main(String[] args) {
       And and = new And();
       and.settingData();
       and.learningData();
    }
}
