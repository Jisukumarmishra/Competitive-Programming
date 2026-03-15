public class triesDefinition {
    static class Node {
        Node [] children ;
        boolean eow;

        public Node () {
            children = new Node [26]; // a to z
            for (int i=0; i<26;i++) {
                children[i] = null;
            }
            eow = false;
        }
    }
    static Node root = new Node ();
    public static void main(String[] args) {
       String words []  = {"the","a","there","their","any"}; 
    }
}
