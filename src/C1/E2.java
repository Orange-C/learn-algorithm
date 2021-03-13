import java.util.LinkedList;

public class E2 {
    public static char[][] charMap = {
        {'t', 'h', 'i', 's'},
        {'w', 'a', 't', 's'},
        {'o', 'a', 'h', 'g'},
        {'f', 'g', 'd', 't'},
    };
    public static String[] words = {
        "this",
        "two",
        "fat",
        "that"
    };

    public static void main(String[] args) throws Exception {
        Solution.solve(charMap, words);
    }
}

class Solution {
    public static LinkedList<FinalRes> resultList = new LinkedList<>();
    public static DictionaryTreeNode createDictionaryTree(String[] words) {
        DictionaryTreeNode parent = new DictionaryTreeNode(' ');
        for(String word: words) {
            parent.addChar(word, 0);
        }

        return parent;
    }

    public static void findWord(char[][] charMap, int i, int j, DictionaryTreeNode treeNode, int[] start, Integer[] direction) {
        char current = charMap[i][j];
        MatchRes matchRes = treeNode.hasChar(current);
        if (matchRes.match) {
            if(treeNode.wordEnd) {
                int[] end = {i+1, j+1};
                FinalRes res = new FinalRes(start, end, treeNode.targetWord);
                resultList.add(res);
            }
            
            int xNext = i + direction[0];
            int yNext = j + direction[1];
            if (Directions.checkIndex(charMap, xNext, yNext)) {
                findWord(charMap, xNext, yNext, matchRes.node, start, direction);
            }
        }
    }
    
    public static void solve(char[][] charMap, String[] words) {
        DictionaryTreeNode wordTree = createDictionaryTree(words);
        
        for(int i = 0; i < charMap.length; i ++) {
            for(int j = 0; j < charMap[i].length; j ++) {
                int[] start = {i+1, j+1};
                LinkedList<Integer[]> directions = Directions.genDirection(charMap, i, j);
                for(Integer[] direction : directions) {
                    findWord(charMap, i, j, wordTree, start, direction);
                }
            }
        }

        for(FinalRes item : resultList) {
            System.out.printf("(%d, %d) -> (%d, %d), %S\n", item.start[0], item.start[1], item.end[0], item.end[1], item.word);
        }
    }
}

class DictionaryTreeNode {
    public char nodeChar;
    public DictionaryTreeNode[] nextNodes = new DictionaryTreeNode[26];
    public int nodeCount = 0;
    public boolean wordEnd = false;
    public String targetWord;

    DictionaryTreeNode(char nodeChar) {
        this.nodeChar = nodeChar;
    }

    public MatchRes hasChar(char target) {
        boolean flag = false;
        int nodeIndex = 0;
        for(int i = 0; i < nodeCount; i++) {
            DictionaryTreeNode nextNode = nextNodes[i];
            if(nextNode.nodeChar == target) {
                flag = true;
                nodeIndex = i;
                break;
            }
        }

        MatchRes res = new MatchRes(flag, nextNodes[nodeIndex]);
        
        return res;
    }

    public void addChar(String word, int index) {
        if(index > word.length() - 1) {
            return;
        }

        char current = word.charAt(index);

        if(index == word.length() - 1) {
            this.wordEnd = true;
            this.targetWord = word;
        }

        boolean hasNode = false;
        int nodeIndex = 0;
        for(int i = 0; i < nodeCount; i++) {
            DictionaryTreeNode nextNode = nextNodes[i];
            if(nextNode.nodeChar == current) {
                hasNode = true;
                nodeIndex = i;
                break;
            }
        }

        DictionaryTreeNode subNode; 
        if (hasNode) {
            subNode = nextNodes[nodeIndex];
        } else {
            subNode = new DictionaryTreeNode(current);
            nextNodes[nodeCount] = subNode;
            nodeCount++;
        }

        subNode.addChar(word, index + 1);
    }

    public void readChar() {
        if(this.nodeChar != ' ') {
            System.out.printf("%s ", this.nodeChar);
        }

        for(int i = 0; i < nodeCount; i++) {
            DictionaryTreeNode nextNode = nextNodes[i];
            nextNode.readChar();
        }
    }
}

class MatchRes {
    public boolean match;
    public DictionaryTreeNode node;

    MatchRes(boolean match, DictionaryTreeNode node){
        this.match = match;
        this.node = node;
    }
}


class Directions {
    private static int[][] DIRECTION = {
        {1,0},
        {1,1},
        {1,-1},
        {-1,0},
        {-1,1},
        {-1,-1},
        {0,1},
        {0,-1},
    };

    public static LinkedList<Integer[]> genDirection(char[][] charMap, int i, int j) {
        LinkedList<Integer[]> res = new LinkedList<>();

        for(int dIndex = 0; dIndex < DIRECTION.length; dIndex++) {
            int x = i + DIRECTION[dIndex][0];
            int y = j + DIRECTION[dIndex][1];

            if (checkIndex(charMap, x, y)) {
                Integer[] temp = {DIRECTION[dIndex][0], DIRECTION[dIndex][1]};
                res.add(temp);
            }
        }
        
        return res;
    }

    public static boolean checkIndex(char[][] charMap, int i, int j) {
        return i <= charMap.length -1 && i >=0 && j <= charMap[i].length -1 && j >= 0;
    }
}

class FinalRes {
    public int[] start;
    public int[] end;
    public String word;

    FinalRes(int[] start, int[] end, String word){
        this.start = start;
        this.end = end;
        this.word = word;
    }
}
