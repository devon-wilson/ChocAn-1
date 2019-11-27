import java.util.Map;
import java.util.TreeMap

public class Main {
    public static void main (String[] args) {

        /* Build the data trees
        Specifies the type of the key that will be associated
        with the objects in the tree, and the class of the objects
        stored in the tree.
         */
        TreeMap<Integer, Provider> providers = new TreeMap<>();
        TreeMap<Integer, Member> members = new TreeMap<>();
        TreeMap<Integer, Service> services = new TreeMap<>();

        /* Populate the trees with file information */
        buildTree(providers, );
        buildTree(members, ):
        buildTree(services, );
    }
}