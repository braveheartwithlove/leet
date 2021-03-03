211. Design Add and Search Words Data Structure
Design a data structure that supports adding new words and finding 
if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure
 that matches word or false otherwise. word may contain dots '.' 
 where dots can be matched with any letter.
Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true] 

HashMap O(MN)
Finding all keys with a common prefix.

Enumerating a dataset of strings in lexicographical order.

Scaling for the large datasets. Once the hash table increases 
in size, there are a lot of hash collisions and the search time 
complexity could degrade to \mathcal{O}(N^2 \cdot M)O(N 
2
 ⋅M), where NN is the number of the inserted keys.

Trie could use less space compared to hashmap when storing many keys with the same prefix. 
In this case, using trie has only \mathcal{O}(M \cdot N)O(M⋅N) time complexity, where MM is
 the key length, and NN is the number of keys.

class WordDictionary {
    Map<Integer, Set<String>> d;

    /** Initialize your data structure here. */
    public WordDictionary() {
        d = new HashMap();
    }
    
    /** Adds a word into the data structure. */
	//define key based on the length of word
    public void addWord(String word) {
        int m = word.length();
        if (!d.containsKey(m)) {
            d.put(m, new HashSet());
        }
        d.get(m).add(word);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        int m = word.length();
        if (d.containsKey(m)) {
            for (String w : d.get(m)) { //search all the words with same length
                int i = 0;
                while ((i < m) && (w.charAt(i) == word.charAt(i) || word.charAt(i) == '.')) {
                    i++;
                }
                if (i == m) return true;    //if going through the complete word, we find it.
            }    
        }
        return false;
    }
}
