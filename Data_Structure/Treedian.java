  import java.io.*;
  import java.math.*;
  import java.text.*;
  import java.util.*;
  import java.util.regex.*;

  // class Comp implements Comparator<Integer>{
  //   public int compare(Integer x,Integer y){
  //     return -1*(x.compareTo(y));
  //   }
  // }
  class Treedian 
  {
  		private  PriorityQueue<Integer> lower;
      private PriorityQueue<Integer> higher;
  		private int size;
  		private double median;
  		public Treedian()
  		{
        // this.size=s;

  			  lower = new PriorityQueue<Integer>(10, (a,b) ->
                  {
                      return small(a,b);
                  });
               higher = new PriorityQueue<Integer>(10,(a,b) ->
                  {
                      return small(a,b) <=0 ? 1 : -1;
                  });
               
  		}
  		
  		public int getSize()
  		{
  			return size;
  		}
  		public boolean isEmpty()
  		{
  			return size==0;
  		}
    	private int small(int x,int y){
            String s1=String.valueOf(x);
            String s2=String.valueOf(y);
            int m=s1.compareTo(s2);
            return m;          
      }
  	  public  void add(int number)
      {
          if(lower.size()==0 || small(number,lower.peek())<0 ? false : true ){
              size++;
              lower.add(number);
            }
          else{
              higher.add(number);
              size++;
            }
          rebalance();
          
          median=findMedian();
      }	  
  	  public boolean removeMedian() throws Exception
  	  {
  		  return remove((int)median);
  	  }
    
  	  private double findMedian()
        {
            if (lower.size() == higher.size())
                return (lower.peek() + higher.peek()+0.0) / 2;
            else
                return lower.peek();
        }
  	  public double getMedian()throws Exception
  	  {
  		  if(size==0)
  			  throw new Exception("NO DATA  ELEMENT TO FIND MEDIAN");
  		  return median;
  	  }
    
  	  private  void rebalance()
        {
            if(lower.size()==higher.size())
                return ;
            else if(lower.size()-higher.size()>1)
                {
                    higher.add(lower.remove());
                }
            else if(higher.size()-lower.size()>0)
                {
                    lower.add(higher.remove());
                }    
        }
  	  
  	  public boolean remove(int number)throws Exception
  	  {
  		  if(size==0)
  			  throw new Exception("NO DATA  ELEMENT TO REMOVE");
  		  boolean removed=false;
  	      if(number<=median)
  	          removed=lower.remove(number);
  	      else
  	          removed=higher.remove(number);
  	      if(!removed)
  	    	  return false;
  	      rebalance();
  	      size--;
  	      median=findMedian();
  	      return true;
  	  }	  
      public void printLower(){
        System.out.println("lower heap"+lower);
      }
      public void printHigher(){
        System.out.println("higher heap"+higher);
      }
  }
  class Solution {

      /*
       * Complete the runningMedian function below.
       */
      
      private static final Scanner sc = new Scanner(System.in);

      private static double round (double value, int precision) {
          int scale = (int) Math.pow(10, precision);
          return (double) Math.round(value * scale) / scale;
      }
      public static void main(String[] args) throws Exception {
          int n=sc.nextInt();
          Treedian tree=new Treedian();
          StringBuilder sb=new StringBuilder("");
          for(int i=0;i<n;i++){
              tree.add(sc.nextInt());
              sb.append(tree.getMedian()+"\n");
              // System.out.println(tree.getMedian());
              // tree.printLower();
              // tree.printHigher();
          }
          
          // double ans[]=runningMedian(arr);
          
          // for(int i=0;i<ans.length;i++){
          //     sb.append(round(ans[i],1)+"\n");
          // }
          System.out.println(sb.toString());
      }
  }
