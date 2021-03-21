//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列： 
//
// 
// 序列中第一个单词是 beginWord 。 
// 序列中最后一个单词是 endWord 。 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典 wordList 中的单词。 
// 
//
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中
//的 单词数目 。如果不存在这样的转换序列，返回 0。 
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有字符串 互不相同 
// 
// Related Topics 广度优先搜索 
// 👍 717 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 1) 先将 wordList 转换为 Set，提高访问速度
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }

        // 2) 记录已经访问过的 word
        Set<String> visited = new HashSet<>();
        // 双向 BFS 搜索，初始化两端数据
        Set<String> startVisited = new HashSet<>();
        startVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        // 3) 双向 BFS，左右交替扩散的步数之和为所求
        int step = 1;
        while (!startVisited.isEmpty() && !endVisited.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少（一直保持 startVisited 最小）
            if (startVisited.size() > endVisited.size()) {
                Set<String> temp = startVisited;
                startVisited = endVisited;
                endVisited = temp;
            }

            // 保证 start 是相对较小的集合，next 在扩散完成以后，会成为新的 start
            Set<String> next = new HashSet<>();
            for (String word : startVisited) {
                if (changeOneletter(word, endVisited, visited, wordSet, next)) {
                    return step + 1;
                }
            }

            // 原来的 startVisited 废弃，不然构不成接龙，需要从 next 开始新的双向 BFS
            startVisited = next;
            step ++;
        }

        return 0;
    }

    // 替换单词中的一个字母
    private boolean changeOneletter(String word, Set<String> endVisited, Set<String> visited,
                                    Set<String> wordSet, Set<String> next) {
        char[] charArray = word.toCharArray();
        // 遍历这个单词，将每个字母用字母进行替换
        for (int i = 0; i < word.length(); i++) {
            char originChar = charArray[i];
            // 使用 a~z 替换当前字母
            for (char c = 'a'; c <= 'z'; c++) {
                if (originChar == c) {
                    continue;
                }
                charArray[i] = c;
                String newWord = String.valueOf(charArray);
                // 是否符合字典要求
                if (wordSet.contains(newWord)) {
                    // 已经到达最终单词
                    if (endVisited.contains(newWord)) {
                        return true;
                    }
                    // 否则记录已经变换完成的单词，下次继续转换
                    if (!visited.contains(newWord)) {
                        next.add(newWord);
                        visited.add(newWord);
                    }
                }
            }
            // 恢复，进行下一个替换
            charArray[i] = originChar;
        }
        return false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
