public class wordbreakproblem {
    // given an input string and a dictionary of words,find out if the input string
    //can be broken into a spac-seperated sequence of dictionary words.
    //words[] = { i , like, sam, samsung, mobile, ice }
    //key = "likesamsung"

    //output:- true
    static class Node {
        Node [] children = new Node [26];
        boolean eow;

        public Node () {
            for (int i=0; i<26; i++) {
                children[i] = null;
            }
        }
    }

public static Node root = new Node();

public static void insert (String word ) {
    int level =0;
    int len = word.length();
    int idx = 0;

    Node curr = root;
    for (; level<len; level++) {
        idx = word.charAt(level) -'a';
        if(curr.children[idx] == null ) {
            curr.children[idx] = new Node ();
        }
        curr = curr.children[idx];
    }
    curr.eow = true;
}

public static boolean search ( String key ) {
 int level =0;
 int len = key.length();
 int idx =0;

 Node curr = root;
 for (; level<len; level++) {
    idx = key.charAt(level)-'a';
    if(curr.children[idx] == null) {
        return false;
    }
    curr = curr.children[idx];
 }
 return curr.eow == true;
}

public static boolean wordBreak (String key) {
    if (key.length() == 0 ) {
        return true;
    }
    for (int i=1 ; i<=key.length(); i++) {
        String firstPart = key.substring(0,i);// isme i include nhi rahta sunstring funmction me 
        String secPart = key.substring(i);
        if (search(firstPart) && wordBreak(secPart)) {
            return true;
        }
     

    }
       return false;
}

public static void main(String[] args) {
    String words [] = { "i", "like", "sam", "samsung","mobile"};
    String key = "ilikesamsung";
    
    for (int i=0; i<words.length; i++) {
        insert(words[i]);
    }
    System.out.println(wordBreak(key));
}
}
