package zfj.swordToOffer;

import java.util.Arrays;
import java.util.List;

//×ÖµäÐò
public class Trie {
	//(leetcode 208) Implement Trie
	class TrieNode
	{
		public TrieNode child[];
		public boolean isWord;
		public TrieNode()
		{
			child = new TrieNode[26];
			this.isWord = false;
		}
	}
	public TrieNode root;
	public Trie()
	{
		root = new TrieNode();
	}
	
	public void insert(String word)
	{
		TrieNode t = root;
		for(int i = 0; i < word.length(); i ++)
		{
			int a = word.charAt(i) - 'a';
			if(t.child[a] == null)
				t.child[a]= new TrieNode();
			t = t.child[a];
		}
		t.isWord = true;
	}
	public boolean search(String word)
	{
		TrieNode t = root;
		for(int i = 0; i < word.length(); i ++)
		{
			int a = word.charAt(i) - 'a';
			if(t.child[a] == null)
				return false;
			t = t.child[a];
		}
		return t.isWord;
	}
	public boolean startWith(String prefix)
	{
		TrieNode t = root;
		for(int i = 0; i < prefix.length(); i ++)
		{
			int a = prefix.charAt(i) - 'a';
			if(t.child[a] == null)
				return false;
			t = t.child[a];
		}
		return true;
	}
	public String find(String word)
    {
        TrieNode t = root;
        for(int i = 0; i < word.length(); i ++)
        {
            int a = word.charAt(i) - 'a';
            if(t.child[a] == null)
                return word;
            if(t.child[a].isWord) 
            	return word.substring(0, i);
            t= t.child[a];
        }
        return word;
    }
	//(leetcode 648) Replace words
    public String replaceWords(List<String> dict, String sentence) {
        String res = "";
        for(int i = 0; i < dict.size(); i ++)
            insert(dict.get(i));
        String cur = "";
        for(int i = 0; i < sentence.length(); i ++)
        {
            char c = sentence.charAt(i);
            if(c >= 'a' && c <= 'z')
                cur += c;
            else if(cur.equals(""))
            {
                res += c;
                continue;
            }
            else
            {
                res += find(cur) + c;
                cur = "";
            }
        }
        res += find(cur);
        return res;
    }
    
    public static void main(String[] args)
    {
    	Trie t = new Trie();
    	String ss[] = {"cat", "bat", "ratt"};
    	t.replaceWords(Arrays.asList(ss), "  the cattle   was rattled by the battery   ");
    }
}
