import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by
 */
//读取地图文件类
//主要方法：readObjects
public class MapLoading {
    public static int size = Maze.SIZE;
    public static String src =  "map1.csv"; //迷宫目录
    //public static String src =  "map2_deadend.csv";
    public static char[][] chs = new char[size][size];

    //返回值，迷宫每个坐标字符表。
    public static char[][] readObjects() {
        File file = new File(src);
        String line;
        try {
            BufferedReader fr = new BufferedReader(new FileReader(file));
            int i = 0,j = 0;
            int countT = 0,countL = 0;
            while ((line = fr.readLine()) != null) {
                String[] s = line.split(",");
                if(s.length!=size && line.charAt(line.length()-1)!=','){
                    System.out.println("Bad file");
                    System.exit(1);
                }
                for(j=0;j<s.length;j++){
                    if(s[j].equals("")){
                        chs[i][j] = 'E';
                    }
                    else {
                        char ch = s[j].charAt(0);
                        switch(ch){
                            case 'T':
                                if(countT==1){
                                    System.out.println("Bad file");
                                    System.exit(1);
                                }
                                countT++;
                                break;
                            case 'L':
                                if(countL==1){
                                    System.out.println("Bad file");
                                    System.exit(1);
                                }
                                countL++;
                                break;
                            case 'W': break;
                            default:
                                System.out.println("Bad file");
                                System.exit(1);
                        }
                        chs[i][j] = ch;
                    }
                }
                if(j!=size) {
                    for(;j<size;j++) {
                        chs[i][j] = 'E';
                    }
                }
                i++;
            }
            if(i!=size){
                System.out.println("Bad file");
                System.exit(1);
            }
            fr.close();
        } catch (IOException e1) {
            System.out.println("Error reading file");
        }
        return chs;
    }

    //测试用，可注释
    public static void display(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(chs[i][j]);
            }
            System.out.println();
        }
    }

}
