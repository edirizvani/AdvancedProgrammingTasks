package aud9;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NamesTest {
    public static void main(String[] args) throws FileNotFoundException {

        Map<String,Integer>  boynamesMap= getNamesMap("/Users/edirizvani/IdeaProjects/napredno-2/src/aud9/boynames.txt");
        Map<String,Integer>  girlNamesMap= getNamesMap("/Users/edirizvani/IdeaProjects/napredno-2/src/aud9/girlnames.txt");
        //since set doesnt hold duplicates , if i need only the unique names I can put them in a set.
        Set<String> names=new HashSet<>();
        names.addAll(boynamesMap.keySet());
        names.addAll(girlNamesMap.keySet());

        names.stream()
                .filter(name -> boynamesMap.containsKey(name) && girlNamesMap.containsKey(name))
                .forEach(name-> System.out.printf("%s : Male: %d, Female: %d Total:%d\n",name,boynamesMap.get(name),girlNamesMap.get(name),boynamesMap.get(name)+girlNamesMap.get(name)));
    }





    private static Map<String, Integer> getNamesMap(String path) throws FileNotFoundException {
        InputStream is = new FileInputStream(path);
        Map<String, Integer> result = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
;
//        return br.lines().collect(Collectors.toMap(
//                line -> line.split("\\s+")[0],
//                line -> Integer.parseInt(line.split("\\s+")[1])
//        ));
                br.lines().forEach(line -> {
            String [] parts = line.split("\\s+");
            String name = parts[0];
            int freq = Integer.parseInt(parts[1]);
            result.put(name, freq);
        });
        return result;


    }
    }


