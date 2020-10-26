package matura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    static int[] liczby = new int[1000];
    static StringBuilder results = new StringBuilder("");

    public static void main(String[] args) throws IOException {
        readFromfiles();
        zad1();
        zad2();
        zad3();
        writeResults();
    }
    // write your code here


    static void readFromfiles() throws FileNotFoundException {
        File f1 = new File(Main.class.getResource("dane/liczby.txt").getFile());

        Scanner sc = new Scanner(f1);

        int i=0;
        while (sc.hasNextInt())
        {
            liczby[i]=sc.nextInt();
            //System.out.println(liczby[i]);
            i++;
        }

    }


    static void zad1()
    {
        long st=System.currentTimeMillis();
        int s=0;
        for(int i=0;i<liczby.length;i++)
        {
            if(jestOk(liczby[i]))
            {
                s++;
            }
        }
        results.append("59.1\n").append(s).append("\n\n");
        //System.out.println(s);
        System.out.println(System.currentTimeMillis()-st);
    }

    static boolean jestOk(int l)
    {
        int lc=0, c=3;
        if(l%2==0)
            return false;
        while (l>1)
        {
            if(l%c==0) lc++;
            while (l%c==0)
                l=l/c;
            c=c+2;
        }
        if (lc == 3)
            return true;
        else return false;
    }

    static void zad2()
    {
        int s=0;
        for(int i=0;i<liczby.length;i++)
        {
            int r = Integer.parseInt(new StringBuffer(String.valueOf(liczby[i])).reverse().toString());
            //System.out.println(liczby[i]+" "+r);
            if(czyPalindrom(liczby[i]+r))
                s++;
        }
        results.append("59.2\n").append(s).append("\n\n");
    }

    static boolean czyPalindrom(int g)
    {
        String s=String.valueOf(g);
        String rs=new StringBuffer(s).reverse().toString();
        return s.equals(rs);
    }

    static void zad3()
    {
        int[] moce = {0,0,0,0,0,0,0,0};
        int moc1max=Integer.MIN_VALUE,moc1min=Integer.MAX_VALUE;


        for(int i=0;i<liczby.length;i++)
        {
            int moc=moc(liczby[i]);
            if(moc==1)
            {
                if(liczby[i]>moc1max)
                    moc1max=liczby[i];
                if(liczby[i]<moc1min)
                    moc1min=liczby[i];
            }

            if(moc<9)
                moce[moc(liczby[i])-1]++;
        }

        results.append("59.3\n");
        for(int i=0;i<moce.length;i++)
        {
            results.append("Liczby o mocy ").append(i + 1).append(": ").append(moce[i]).append("\n");
        }
        results.append("Maksymalna liczba o mocy 1: ").append(moc1max).append("\n");
        results.append("Minimalna liczba o mocy 1: ").append(moc1min).append("\n");
    }

    static int moc(int k)
    {
        int moc=1;
        k=iloczynCyfr(k);
        while(k>9)
        {
            k=iloczynCyfr(k);
            moc++;
        }
        return moc;
    }

    static int iloczynCyfr(int n)
    {
        int iloczynCyfr = 1;

        while (n != 0)
        {
            iloczynCyfr = iloczynCyfr * (n % 10);
            n = n / 10;
        }

        return iloczynCyfr;
    }



    static void writeResults() throws IOException {
        File f = new File("wyniki_liczby.txt");
        f.createNewFile();
        PrintWriter pw = new PrintWriter(f);
        pw.write(results.toString());
        pw.close();

    }
}
