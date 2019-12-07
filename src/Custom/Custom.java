package Custom;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Custom {
    
    Perceptron perceptron = new Perceptron();
    int epoch = 1;
    int[][] dataset = new int[75][3];
    public void readData(String nama){
        BufferedReader br;
        String line;
        try {
            int i=0;
            br=new BufferedReader(new FileReader(nama));
            while((line=br.readLine())!=null){
                String[] ruspini=line.split(",");
                for(int j=0;j<3;j++){
                    dataset[i][j] = Integer.parseInt(ruspini[j]);
                }
                i++;   
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dataToObject();
    }
    
    public void dataToObject(){
       int target=0;
       for(int i=0;i<dataset.length;i++){
          if(dataset[i][2]==1 || dataset[i][2]==3 ){
              target=0;
          }else{
              target=1;          
          }
          perceptron.addData(new Data(dataset[i][0],dataset[i][1],target));
          //System.out.println(perceptron.getData(i).toString());
       }
        Random r = new Random();
        for(int i=0;i<3;i++){
         perceptron.setWeight(i,((-1)+(1 - (-1))) * r.nextDouble());
      }
    }
    
     public void learningData(){
    double error=99;
    double output;
    boolean errorFlag = true;
    while(epoch<=200){
         printHeading();
         errorFlag = false;
        for(int i=0;i<dataset.length;i++){
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
    
     public void printVectorTesting( Data data, double[]weights, double output){
        System.out.println("\n============================Testing Data #======================");
        System.out.println("bias|  x1 |  x2 |   w1    |    w2  |   w3   |output ");
        System.out.println("=================================================================="); 
        System.out.println(" "+data.getxB()+"  |  "+ data.getX1()+"  |  "+ data.getX2()+"  |  "
        +String.format("%.2f",weights[0] )+"  |  "
        +String.format("%.2f",weights[1])+"  |  "+String.format("%.2f",weights[2])+"  |  "
        +output);
}
     
    public void input(){
        int x1,x2,target;
       Scanner input = new Scanner(System.in);
       System.out.print("\nmasukan nilai x1  :");
       x1=Integer.parseInt(input.nextLine());
       System.out.print("masukan nilai x2  :");
       x2=Integer.parseInt(input.nextLine());
       perceptron.addDataTesting(new Data(x1,x2,0));
    }
    
    public void testing(int tambah){
        int output;
         double count = perceptron.getOutput(perceptron.getDataTesting(tambah));
            if(count < perceptron.getThreshold()){
                output=0;
            }else{
                output=1;
            }
            int error=perceptron.getDataTesting(tambah).getTarget()-output;
            printVectorTesting(perceptron.getDataTesting(tambah),perceptron.getWeight(),output);
            
    }
    
    public static void main(String[] args) {
        String nama="D:/PENS/SMT05/Mesin Learning/praktikum/NeuronNetwork/src/Custom/ruspini.csv";
        String again;
        Scanner in = new Scanner(System.in);
        Custom custom = new Custom();
        custom.readData(nama);
        int tambah=0;
        custom.learningData();
       do{
        custom.input();
        custom.testing(tambah);
        System.out.print("\ntry again (y/n) ? ");
         again = in.nextLine();
         tambah++;
        }while(again.equals("y"));  
       
    } 
}
