//数组和链表算法一样，不同数据结构不同实现。
//找并集算法如下：
//两两相并。
//由于排好序，只要两个数组从数组头走到尾，对应的值小的存入新数组，移动下标，大的不移动。若相等，存入新数组，两个数组都移动下标。
//时间复杂度先合并A、B到新数组AB，需要遍历A、B：O(N+N)=O(2N)，然后合并AB和C，需要遍历AB和C：O(2N+N)=O(3N)，所以是O(2N)+O(3N)=O(5N)=O(N)
public class Union_Array {
    //数组数据
    static int[] A = {1,2,5,6,7,9};
    static int[] B = {3,5,6,7,8,10};
    static int[] C = {1,3,4,5,6,8};

    public static int[] merge2Array(int[] a, int[] b){
        int len1 = a.length;
        int len2 = b.length;
        int index1=0, index2 =0, index3=0;
        int[] c = new int[len1+len2];
        while(index1!=len1 && index2!=len2){
            if(a[index1]<b[index2]){
                c[index3] = a[index1];
                index1++;
            }
            else if(a[index1]==b[index2]){
                c[index3] = a[index1];
                index1++;
                index2++;
            }
            else{
                c[index3] = b[index2];
                index2++;
            }
            index3++;
        }
        while(index1<len1){
            c[index3] = a[index1++];
            index3++;
        }
        while(index2<len2){
            c[index3] = b[index2++];
            index3++;
        }
        if(index3==(len1+len2)){
            return c;
        }
        else{
            int[] res = new int[index3];
            System.arraycopy(c,0,res,0,index3);
            return res;
        }
    }

    public static void main(String[] args) {
        int[] AB =  Union_Array.merge2Array(A,B);//T = O(2N)
        int[] ABC = Union_Array.merge2Array(AB,C);//T = O(3N)
        //总共O(5N) = O(N)
        for(int a: ABC){
            System.out.println(a);
        }
    }
}
