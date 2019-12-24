/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regressionimpl;

import Jama.Matrix;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Rupesh Biradar
 */
public class RegressionImpl {

    public static void main(String[] args) throws InterruptedException{
        List<Double> inputlat=new ArrayList<Double>();
        List<Double> inputlon=new ArrayList<Double>();
        List<Double> outlat=new ArrayList<Double>();
        List<Double> outlon=new ArrayList<Double>();
//        double inputlon[]={1,2.5,3.5,5.5,7.0,9.5,11.0,12.5,14.5,16.0,17.5,19.5,21.5,23.5,24.5,25.3,26.7,28.9,30.2,32.6};
//        inputlat[0]=0.0;
//        inputlon[0]=0.0;
//        outlon[0]=0.0;
//        outlon[0]=0.0;
        CSVReader csv=new CSVReader();
        List<Book> values=csv.compute();
        
        for (Iterator<Book> iterator = values.iterator(); iterator.hasNext();) {
            Book next = iterator.next();
            inputlat.add(next.lat);
            inputlon.add(next.lon);
            outlat.add(next.latop);
            outlon.add(next.lonop);
            
            
        
        }
        List<List<Double>> list=new ArrayList<List<Double>>();
        List<Double> temp= new ArrayList<Double>();
        
        for (int i = 0; i < inputlat.size(); i++) {
            temp=new ArrayList<Double>();
            temp.add(1.0);      //0
            double x=inputlat.get(i);
            double y=inputlon.get(i);
            temp.add(x);        //1
            temp.add(x*x);      //2
            temp.add(temp.get(2)*x);//3
            temp.add(y);        //4
            temp.add(y*y);      //5
            temp.add(temp.get(5)*y);//6
            temp.add(temp.get(1)*temp.get(4));
            temp.add(temp.get(1)*temp.get(5));
            temp.add(temp.get(1)*temp.get(6));
            temp.add(temp.get(2)*temp.get(4));
            temp.add(temp.get(2)*temp.get(5));
            temp.add(temp.get(2)*temp.get(6));
            temp.add(temp.get(3)*temp.get(4));
            temp.add(temp.get(3)*temp.get(5));
            temp.add(temp.get(3)*temp.get(6));
            list.add(temp);
        }
        double arr[][]=new double[list.size()][temp.size()];
        
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < temp.size(); j++) {
                arr[i][j]=list.get(i).get(j);
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }
        double outlatarr[]=new double[outlat.size()];
        int k=0;
        for (Iterator<Double> iterator = outlat.iterator(); iterator.hasNext();) {
            Double next = iterator.next();
            outlatarr[k++]=next;
        }
        double outlonarr[]=new double[outlon.size()];
        k=0;
        for (Iterator<Double> iterator = outlon.iterator(); iterator.hasNext();) {
            Double next = iterator.next();
            outlonarr[k++]=next;
        }
        
//        Matrix in=new Matrix(arr);
//        Matrix out=new Matrix(outlatarr, 0); 22.30435492	94.41363576

        double lt=22.30435492;
        double ln=94.41363576;

        System.out.println("\n\n *******Beta values for  Latitude******");
        MultipleLinearReg mlr= new MultipleLinearReg(arr, outlatarr);
        double betaArr[]=new double[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            betaArr[i]=mlr.beta(i);
            System.out.println(mlr.beta(i));
        }
        double ans=0.0;
        temp=new ArrayList<Double>();
            temp.add(1.0);      //0
            double x=lt;
            double y=ln;
            temp.add(x);        //1
            temp.add(x*x);      //2
            temp.add(temp.get(2)*x);//3
            temp.add(y);        //4
            temp.add(y*y);      //5
            temp.add(temp.get(5)*y);//6
            temp.add(temp.get(1)*temp.get(4));
            temp.add(temp.get(1)*temp.get(5));
            temp.add(temp.get(1)*temp.get(6));
            temp.add(temp.get(2)*temp.get(4));
            temp.add(temp.get(2)*temp.get(5));
            temp.add(temp.get(2)*temp.get(6));
            temp.add(temp.get(3)*temp.get(4));
            temp.add(temp.get(3)*temp.get(5));
            temp.add(temp.get(3)*temp.get(6));
        for( int l=0;l<temp.size();l++)
        {
          ans+=betaArr[l]*temp.get(l);
          
        }
        System.out.println("Lat: "+ans);
        Thread.sleep(2000);
        
        System.out.println("\n\n *******Beta values for  Longitude******");
        MultipleLinearReg mlr1= new MultipleLinearReg(arr, outlonarr);
        betaArr=new double[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            betaArr[i]=mlr1.beta(i);
            System.out.println(mlr1.beta(i));
        }
        ans=0.0;
        temp=new ArrayList<Double>();
            temp.add(1.0);      //0
            temp.add(x);        //1
            temp.add(x*x);      //2
            temp.add(temp.get(2)*x);//3
            temp.add(y);        //4
            temp.add(y*y);      //5
            temp.add(temp.get(5)*y);//6
            temp.add(temp.get(1)*temp.get(4));
            temp.add(temp.get(1)*temp.get(5));
            temp.add(temp.get(1)*temp.get(6));
            temp.add(temp.get(2)*temp.get(4));
            temp.add(temp.get(2)*temp.get(5));
            temp.add(temp.get(2)*temp.get(6));
            temp.add(temp.get(3)*temp.get(4));
            temp.add(temp.get(3)*temp.get(5));
            temp.add(temp.get(3)*temp.get(6));
        for( int l=0;l<temp.size();l++)
        {
          ans+=betaArr[l]*temp.get(l);
          
        }
        System.out.println("Lon: "+ans);
        
        
    }
}
//    public static void main(String[] args) {
//        double x[]={0,0.5,1.0,1.5,2.0,2.5};
//        double y[]={0,0.25,1.0,2.25,4.0,6.25};
//        double sumX=0,sumY=0,sumX2=0,sumX3=0,sumX4=0,sumX5=0,sumX6=0,sumX7=0,sumX8=0,
//                sumXY=0,sumX2Y=0,sumX3Y=0,sumX4Y=0;
//        for(int i=0;i<x.length;i++){
//            double temp=x[i];
//            sumY+=y[i];
//            sumX+=temp;
//            sumXY+=y[i]*temp;
//            temp*=temp;
//            sumX2+=temp;
//            sumX2Y+=temp*y[i];
//            temp*=temp;
//            sumX3+=temp;
//            sumX3Y+=temp*y[i];
//            temp*=temp;
//            sumX4+=temp;
//            sumX4Y+=temp*y[i];
//            temp*=temp;
//            sumX5+=temp;
//            temp*=temp;
//            sumX6+=temp;
//            temp*=temp;
//            sumX7+=temp;
//            temp*=temp;
//            sumX8+=temp;
//        }
//        double matX[][]={{x.length,sumX,sumX2,sumX3,sumX4},
//            {sumX,sumX2,sumX3,sumX4,sumX5},
//            {sumX2,sumX3,sumX4,sumX5,sumX6},
//            {sumX3,sumX4,sumX5,sumX6,sumX7},
//            {sumX4,sumX5,sumX6,sumX7,sumX8}
//        };
//        
//
//        double matY[][]=new double[5][1];
//        matY[0][0]=sumY;
//        matY[1][0]=sumXY;
//        matY[2][0]=sumX2Y;
//        matY[3][0]=sumX3Y;
//        matY[4][0]=sumX4Y;
//        
//
//        Matrix X= new Matrix(matX);
//        Matrix Y= new Matrix(matY);
//        double b[][]=matY;
//        X=X.inverse();
//        Matrix Z=X.times(Y);
//        
//        for (int i = 0; i < 5; i++) {
//            System.out.println(Z.get(i, 0));
//        }
//        double temp=Z.get(0,0)+Z.get(1,0)*0.5+Z.get(2,0)*0.5*0.5+Z.get(3,0)*0.5*0.5*0.5+
//                Z.get(4,0)*0.5*0.5*0.5*0.5;
//        System.out.println(temp);
////        Matrix A=Y.times(Z);
////        double a[][]=A.getArray();
//        
////        for (int i = 0; i < a.length; i++) {
////            for (int j = 0; j < a[0].length; j++) {
////                System.out.println(a[i][j]);
////            }
//        }
    

