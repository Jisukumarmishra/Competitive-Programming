public class searchinTries {
     static class Node { // yha pe node ka matlab ham tree ke vertex ko le rhe hai to Node class tries ke har ek element ko represent karega
        Node[] children = new Node [26];// starting me children nod(vertex ) ke pass apne children ki koi information nhi hai isliye shru me ham bas children array ko 
        //intialize karenge null ke sath
        boolean eow;
        public Node  (){ 
            for ( int i=0;i<26;i++) {
                children[i] = null;
            }
         eow =  false ;

        }
    }
    public static Node root = new Node ();// ye root node bnata hai jab bhi ham  tries data structure create karte hai 
    // root node hamesha khali hota hai ye hamesha apne children ki addresh store karta hai 
    
    
    public static void insert (String word) {
        Node curr = root;
     for ( int i=0; i<word.length(); i++) { //0(L)
        int idx = word.charAt(i) - 'a';
         if (curr.children[idx] == null ) {
            // add new node 
            curr.children[idx] = new Node ();
        }
        if ( i == word.length()- 1) {
            curr.children[idx].eow = true;
        }
        curr = curr.children[idx];

     }
    }
    public static boolean  search(String key) {
        Node curr = root;
        for (int i=0; i<key.length(); i++ ) {
            int idx = key.charAt(i)-'a';

            if (curr.children[idx] == null ) {
                return false;
            }
            if ( i == key.length()-1 && curr.children[idx].eow == false ) {
                return false;
            }
           curr = curr.children[idx];
        }

        return true;
    }

    public static void main(String[] args) {
        String words [] = { "the", "a" , "there","their","any"};
        for ( int i=0; i<words.length; i++ ) {
            insert(words[i]);
        }
        System.out.println(search("their"));// true
        System.out.println(search("thor"));////false
        System.out.println(search("an"));//false
    }
}

