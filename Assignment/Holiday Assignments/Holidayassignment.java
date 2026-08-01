public class Holidayassignment{
        public static void main(String [] args){
    //question 1
    int[] arr1 = {12, 45, 6, 78, 9, 34, 21, 56, 89, 1};
    MinMaxArray obj = new MinMaxArray();
    System.out.println("Array numbers");
     obj.printArray(arr1);
    System.out.println("miminum values :"+ obj.findMin(arr1));
    System.out.println("maximum values :"+ obj.findMax(arr1));
    //question 2
    int[] arr2 ={12,23,34,45,56,67,78};
    ReverseArray obj2 =new ReverseArray();
    System.out.println("Original Array:");
     obj2.printOriginal(arr2);

    System.out.println("Reverse Array:");
    obj2.printReverse(arr2);
    //question 3
    int[] arr3={11,22,33,44,54,55,66,77,};
    EvenOddCounter obj3=new EvenOddCounter();
    System.out.println("Array numbers");
     obj3.printArray(arr3);
    System.out.println("Even Count:"+ obj3.countEven(arr3));
    System.out.println("odd count:"+ obj3.countOdd(arr3));

    //question 4

    int[] arr4={13,37,46,98,23,43,16};
    LinearSearchDemo obj4=new LinearSearchDemo();
    System.out.println("Array numbers");
     obj4.printArray(arr4);
    int result = obj4.searchElement(arr4, 98);
    int result1 = obj4.searchElement(arr4,99);
    System.out.println("Element 98 found at index:" + result);
    System.out.println("Element 99 found at index:" + result1);

    //question 5

    int[] arr5={1,1,2,2,3,4,4,5,5,6,7,8,8};
    SortedArrayDuplicates obj5=new SortedArrayDuplicates();
    System.out.println("original array");
    obj5.printArray(arr5);
     int size=obj5.removeDuplicates(arr5);
     System.out.println("New logical size: " + size);
     System.out.println("Unique element:");
     obj5.printUniqueElements(arr5,size);
}

  //question 1
  static class MinMaxArray{
  public void printArray(int[] arr1){
    for(int i=0;i<arr1.length;i++){
        System.out.print(arr1[i]+" ");
    }
    System.out.println();
  }
    public int findMax(int[] arr1) {
        int max = arr1[0];

        for (int i = 1; i < arr1.length; i++) {
            if (arr1[i] > max) {
                max = arr1[i];
            }
        }
        return max;
    }
  public int findMin(int[]arr1){
    int min=arr1[0];
  for(int i=1;i<arr1.length;i++){
    if(arr1[i]<min){
        min=arr1[i];
  }
  }
  return min;
  }
}
//question 2
static class ReverseArray{
    public void printOriginal(int[]arr2){
        for(int i=0;i<arr2.length;i++)
            System.out.print(arr2[i]+" ");
        System.out.println();

    }
    public void printReverse(int[]arr2){
        for(int i=arr2.length-1;i>=0;i--)
            System.out.print(arr2[i]+" "); 
    System.out.println();
}
}
//question 3
static class EvenOddCounter{
    public void printArray(int[] arr3){
    for(int i=0;i<arr3.length;i++){
        System.out.print(arr3[i]+" ");
    }
    System.out.println();
  }
    public int countEven(int[]arr3){
        int evenCount = 0;
        for(int i=0;i<arr3.length;i++){
            if(arr3[i]%2==0){
                evenCount++;
            }
        }
        return evenCount;
    }   
    public int countOdd(int[]arr3){
        int oddCount=0;
        for(int i=0;i<arr3.length;i++){
            if(arr3[i]%2!=0){
                oddCount++;
            }
        }
        return oddCount;
    }
}
//question 4
   static class LinearSearchDemo{
    public int searchElement(int[]arr4,int target){
        for(int i=0;i<arr4.length;i++){
            if(arr4[i]==target){
                return i;
            }
        }
            return -1;
    }
    public void printArray(int[] arr3){
    for(int i=0;i<arr3.length;i++){
        System.out.print(arr3[i]+" ");
    }
    System.out.println();
  } 
   }
// question 5
    static class SortedArrayDuplicates{
        public void printArray(int[] arr5){
    for(int i=0;i<arr5.length;i++){
        System.out.print(arr5[i]+" ");
    }
    System.out.println();
  } 
        public int removeDuplicates(int[] arr5){
            int size=1;
            for(int i=1;i<arr5.length;i++){
                if(arr5[i]!=arr5[i-1]){
                    arr5[size]=arr5[i];
                size++;
                }
            }
            return size;
        }
        public void printUniqueElements(int[] arr5, int size){
            for(int i=0;i<size;i++){
                System.out.print(arr5[i] +" ");
            }

        }
    }
}