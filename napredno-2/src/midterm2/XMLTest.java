package midterm2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SpaceIN{
    static String createSpace(int space){
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, space).forEach(i -> sb.append("\t"));
        return sb.toString();
    }
}

public class XMLTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        XMLComponent component = new XMLLeaf("student", "Trajce Trajkovski");
        component.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        XMLComposite composite = new XMLComposite("name");
        composite.addComponent(new XMLLeaf("first-name", "trajce"));
        composite.addComponent(new XMLLeaf("last-name", "trajkovski"));
        composite.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        if (testCase==1) {
            //TODO Print the component object
            System.out.println(component.toString(0));
        } else if(testCase==2) {
            //TODO print the composite object
            System.out.println(composite.toString(0));
        } else if (testCase==3) {
            XMLComposite main = new XMLComposite("level1");
            main.addAttribute("level","1");
            XMLComposite lvl2 = new XMLComposite("level2");
            lvl2.addAttribute("level","2");
            XMLComposite lvl3 = new XMLComposite("level3");
            lvl3.addAttribute("level","3");
            lvl3.addComponent(component);
            lvl2.addComponent(lvl3);
            lvl2.addComponent(composite);
            lvl2.addComponent(new XMLLeaf("something", "blabla"));
            main.addComponent(lvl2);
            main.addComponent(new XMLLeaf("course", "napredno programiranje"));

            //TODO print the main object
            System.out.println(main.toString(0));
        }
    }
}
interface XML{

    String toString(int space);
}
abstract class XMLComponent implements XML {
    Map<String,String> attributes;
    String name;

    public XMLComponent(String name) {
        this.name = name;
        attributes = new LinkedHashMap<>();
    }

    public void addAttribute(String name, String value){
        attributes.put(name, value);
    }
    private String createString(Map.Entry<String,String> entry){
        return entry.getKey()+"=\""+entry.getValue()+"\"";
    }
    public String putAttributes(){
        if(attributes.isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(attributes.entrySet().stream().map(this::createString).collect(Collectors.joining(" ")));
        return sb.toString();
    }
    @Override
    public String toString(int space) {
        return String.format("%s<%s%s>",SpaceIN.createSpace(space),name,putAttributes());

    }

}
class XMLComposite extends XMLComponent {
    List<XMLComponent> components = new ArrayList<>();

    public XMLComposite(String name) {
        super(name);
    }
    public void addComponent(XMLComponent component){
        components.add(component);
    }

    @Override
    public String toString(int space) {
     StringBuilder sb = new StringBuilder();
     sb.append(super.toString(space));
     sb.append("\n");
     components.forEach(comp -> sb.append(comp.toString(space + 1)).append("\n"));
     sb.append(SpaceIN.createSpace(space)).append("</").append(name).append(">");
     return sb.toString();
    }
}
class XMLLeaf extends XMLComponent{
    String value;

    public XMLLeaf(String name, String value) {
        super(name);
        this.value = value;
    }

    @Override
    public String toString(int space) {
        return super.toString(space)+value+"</"+name+">";
    }
}