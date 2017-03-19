//数组和链表算法一样，不同数据结构不同实现。
//找交集算法如下：
//用三个数据下标从头走到尾，若三个下标对应的值都相等，说明是重合部分，一旦有不相等的，就离开了重合部分，即找到重合部分
//不相等时，找最大的，让其他的数据下标超过最大的。循环找到重复点。
//以这个为例：一开始，下标都是0，对应的值为1,3,1.不相等，最大的是3。A开始找比3大的，2不行，找到5.C找到3.
//下标对应值为5,3,3，不相等，最大为5,。B移动下标找到5，C也找到5.
//下标对应值为5,5,5，相等。进入重合部分。
//6,6,6,相等，重合
//7,7,8，不相等，结束。
//重合部分为5,6
//时间复杂度最多三个数据遍历完，所以是O(N+N+N)=O(3N)=O(N)
public class Intersect_Array {
    //数组数据
    static int[] A = {1,2,5,6,7,9};
    static int[] B = {3,5,6,7,8,10};
    static int[] C = {1,3,4,5,6,8};

    public static void main(String[] args) {
        int len = A.length;
        int[] tmp = new int[len];//临时数组，储存重合区数据
        int index=0;//临时数组下标
        boolean enter = false;//是否进入重合区标志
        //i,j,k为三个数据下标
        for(int i=0,j=0,k=0;i<len && j<len && k<len;){//遍历三个数据
            if(A[i]==B[j] && B[j]==C[k]){//重合处
                enter = true;
                tmp[index++] = A[i];
                i++;
                j++;
                k++;
                continue;
            }
            if(enter){//若重合结束，退出
                break;
            }
            //当前下标三个数据中最大值
            int max = ((A[i] > B[j] ? A[i] : B[j]) > C[k]) ? (A[i] > B[j] ? A[i] : B[j]) : C[k];
            //三个下标若小于目标值，则移动下标，直到找到大于等于max的下标
            while(A[i]<max){
                i++;
            }
            while(B[j]<max){
                j++;
            }
            while(C[k]<max){
                k++;
            }
        }
        //找到的重合数组长度必然小于（或等于）原数组，于是建立个新的数组，将临时数组数据复制
        int[] res = new int[index];
        System.arraycopy(tmp,0,res,0,index);
        for(int a: res){
            System.out.println(a);
        }
    }
}
