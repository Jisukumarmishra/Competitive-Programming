// tries basically ek aisa data structure hai jisme hajm word ke character ko as tree formate store karte hai
// tries ka basically use hamlong bahut sare dictionary ke data ko stire karne ke liye karte hai 
// procedure :- 
//do word me same prefix jha tak same rahta hai usko pahle store karta hai hai and baki remainnig postfix ko vo alag se tree ban ke store karta hai 
// ya phir bole to ek alag branch ban jata hai new jha pe eow true hota hai 
public class TriesImplementation {


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
     for ( int i=0; i<word.length(); i++) { //0(L)
        int idx = word.charAt(i) - 'a';
         if (root.children[idx] == null ) {
            // add new node 
            root.children[idx] = new Node ();
        }
        if ( i == word.length()- 1) {
            root.children[idx].eow = true;
        }
        root = root.children[idx];

     }
    }


    public static void main(String[] args) {
        String words [] = { "the", "a" , "there","their","any"};
        for ( int i=0; i<words.length; i++ ) {
            insert(words[i]);
        }
    }
}