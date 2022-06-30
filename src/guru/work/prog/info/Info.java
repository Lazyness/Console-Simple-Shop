package guru.work.prog.info;

import java.util.ArrayList;
import java.util.List;

public class Info {
    public static <T> void printData(T ...data){
        System.out.print("\nYour data is: ");
        for (T datum : data) {
            System.out.print(datum+" ");
        }
        System.out.println("\n");
    }

    public static <T> void printData(List<T> o){
        if(o instanceof ArrayList){
            ArrayList <T> al = (ArrayList<T>) o;
            System.out.println("\nYour data is: ");
            for (int i = 0; i < al.size(); i++){
                System.out.println("#"+(i+1)+" "+al.get(i));
            }
        }
        System.out.println("\n");
    }

}
