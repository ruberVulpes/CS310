import java.util.Iterator;

import data_structures.*;

/**
 * William Fox cssc0917
 * prog3 LatinDictionary.java
 */
public class LatinDictionary {
    private DictionaryADT<String, String> dictionary;
    private DictionaryReader dictionaryReader;

    // constructor takes no arguments.  Size depends on the datafile.
    // creates an instance of the DictionaryADT. Use your HashTable
    // implementation in this class (though all four should work).
    // Methods that make modifications to the dictionary modify the
    // DictionaryADT object, not the datafile.
    public LatinDictionary() {
        dictionary = new HashTable<String, String>(10000);
//        dictionary = new OrderedArrayDictionary<String, String>(10000);
//        dictionary = new BinarySearchTree<String,String>();
//        dictionary = new RedBlackTree<String,String>();
    }

    // reads the key=value pairs from the datafile and builds a dictionary structure
    public void loadDictionary(String fileName) {
        DictionaryEntry[] entries = dictionaryReader.getDictionaryArray(fileName);
        for (int i = 0; i < entries.length; i++)
            if (entries[i] != null)
                dictionary.add(entries[i].getKey(), entries[i].getValue());
    }

    // inserts a new Latin word and its definition
    public boolean insertWord(String word, String definition) {
        return dictionary.add(word, definition);
    }

    // removes the key value pair that is identified by the key from the dictionary
    public boolean deleteWord(String word) {
        return dictionary.delete(word);
    }

    // looks up the definition of the Latin word
    public String getDefinition(String word) {
        return dictionary.getValue(word);
    }

    // returns true if the Latin word is already in the dictionary
    public boolean containsWord(String word) {
        return dictionary.contains(word);
    }

    // returns all of the keys in the dictionary within the range start .. finish
    // inclusive, in sorted order. Neither value 'start' or 'finish' need be in the
    // dictionary.  Returns null if there are no keys in the range specified.
    public String[] getRange(String start, String finish) {
        UnorderedList<String> list = new UnorderedList<>();
        Iterator<String> keys = words();
        String s;
        while (keys.hasNext()) {  //I don't know how to use a for each here
            s = keys.next();
            if (start.compareTo(s) <= 0 && finish.compareTo(s) >= 0)
                list.addLast(s);
        }
        String[] range = new String[list.size()];
        for (int i = 0; i < range.length; i++) //Loads new array in order, cant use list.size() as
            range[i] = list.removeFirst();      //the list shrinks
        return range;
    }

    // returns an Iterator of the latin words (the keys) in the dictionary,
    // in sorted order.
    public Iterator<String> words() {
        return dictionary.keys();
    }

    // returns the definitions in the dictionary, in exactly the same order
    // as the words() Iterator
    public Iterator<String> definitions() {
        return dictionary.values();
    }

}//Eof